package com.sen.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.sen.factory.FoundTabFragmentFactory;


/**
 * Created by Sen on 2016/2/3.
 */
public class FoundFragTabAdpter extends FragmentPagerAdapter {

    Context mContext;

    //tab title
    private String mTabTiles[] ;

    public FoundFragTabAdpter(FragmentManager fm, Context context, String tabTiles[]) {
        super(fm);
        mContext = context;
        mTabTiles = tabTiles;

    }



    @Override
    public Fragment getItem(int position) {
        return FoundTabFragmentFactory.createFragment(position);
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
