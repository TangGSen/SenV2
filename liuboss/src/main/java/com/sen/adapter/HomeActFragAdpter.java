package com.sen.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.Toolbar;

import com.sen.uitls.HomeFragFragmentFactory;


/**
 * Created by Sen on 2016/2/3.
 */
public class HomeActFragAdpter extends FragmentPagerAdapter {

    Context mContext;

    //tab title
    private String mTabTiles[] ;
    private Toolbar mToolbar;

    public HomeActFragAdpter(FragmentManager fm, Context context, String tabTiles[]) {
        super(fm);
        mContext = context;
        mTabTiles = tabTiles;

    }



    @Override
    public Fragment getItem(int position) {
        return HomeFragFragmentFactory.createFragment(position);
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
