package com.sen.uitls;

import android.support.v4.app.Fragment;

import com.sen.base.BaseFragment;
import com.sen.fragment.home.ClassificationFragment;
import com.sen.fragment.home.HomeFragment;
import com.sen.fragment.home.PersonalCenterFragment;
import com.sen.fragment.home.ShoppingCartFragment;

import java.util.HashMap;

/**
 * Created by Sen on 2016/2/3.
 */
public class FragmentFactory {
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
                    //首页
                    baseFragment = new HomeFragment();
                    break;
                case 1:
                    //分类
                    baseFragment = new ClassificationFragment();
                    break;
                case 2:
                    //购物车
                    baseFragment = new ShoppingCartFragment();
                    break;
                case 3:
                    baseFragment = new PersonalCenterFragment();
                    break;

            }
            //缓存到本地
            hashMap.put(position, baseFragment);
        }
        return baseFragment;
    }
}
