package com.sen.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.sen.factory.ClassFragFragmentFactory;


/**
 * Created by Sen on 2016/2/3.
 */
public class ClassFragTabAdpter extends FragmentPagerAdapter {

    Context mContext;

    //tab title
    private String mTabTiles[] ;

    public ClassFragTabAdpter(FragmentManager fm, Context context, String tabTiles[]) {
        super(fm);
        mContext = context;
        mTabTiles = tabTiles;

    }



    @Override
    public Fragment getItem(int position) {
        return ClassFragFragmentFactory.createFragment(position);
    }

    @Override
    public int getCount() {
        return mTabTiles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabTiles[position];
    }




}
