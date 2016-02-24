package com.sen.factory;

import android.support.v4.app.Fragment;

import com.sen.base.BaseFragment;
import com.sen.fragment.main.found.FoundChildFragment;

import java.util.HashMap;

/**
 * Created by Sen on 2016/2/3.
 */
public class FoundTabFragmentFactory {
    private static HashMap<Integer,Fragment> hashMap = new HashMap<Integer, Fragment>();
    public static BaseFragment createFragment(int position) {
        BaseFragment baseFragment = null;
        //有对象就获取，没对象就创建
        if(hashMap.containsKey(position)){
            //从缓存中获取对象
            if(hashMap.get(position)!=null){
                baseFragment = (BaseFragment) hashMap.get(position);
            }
        }else{
            //没有对象的时候，创建
            switch (position) {
                case 0:
                    //流行新果
                    baseFragment = new FoundChildFragment();
                    break;
                case 1:
                    //人气热销
                    baseFragment = new FoundChildFragment();
                    break;
                case 2:
                    //为你推荐
                    baseFragment = new FoundChildFragment();
                    break;

            }
            //缓存到本地
            hashMap.put(position, baseFragment);
        }
        return baseFragment;
    }
}
