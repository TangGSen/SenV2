package com.sen.fragment.main;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.sen.adapter.FoundFragTabAdpter;
import com.sen.base.BaseFragment;
import com.sen.liuboss.R;
import com.sen.uitls.DisplayUtils;
import com.sen.uitls.ResourcesUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Sen on 2016/2/3.
 */
public class FoundFragment extends BaseFragment {
    private View rootView;
    @Bind(R.id.class_tablayout)
    TabLayout class_tablayout;
    @Bind(R.id.class_toolbar)
    Toolbar class_toolbar;
    @Bind(R.id.tv_search)
    AppCompatTextView tv_search;
    @Bind(R.id.class_viewpager_shows)
    ViewPager class_viewpager_shows; @Bind(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    private String tabTiles[];
    private int tabCount ;


    @Override
    protected void dealAdaptationToPhone() {

        ViewGroup.LayoutParams params = tv_search.getLayoutParams();
        params.width = (int) (DisplayUtils.getInstance(getActivity()).getScreenSize()[0] * 0.618);
        tv_search.setLayoutParams(params);

        //动态设置coordinatorLayout 的marginTop,因为在小米的手机xml 的24dp中显示不正常
        FrameLayout.LayoutParams coordinatorParams = (FrameLayout.LayoutParams) coordinatorLayout.getLayoutParams();
        coordinatorParams.setMargins(0,ResourcesUtils.getStatusBarHeight(getContext()),0,0);
        coordinatorLayout.setLayoutParams(coordinatorParams);
    }

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main_act_found, container, false);
        ButterKnife.bind(this, rootView);
        initTabView();
        return rootView;
    }

    private void initTabView() {
        //init tab data
        tabTiles = ResourcesUtils.getStringArray(getContext(), R.array.taHomeItemName);
         FoundFragTabAdpter fragAdapter = new FoundFragTabAdpter(getChildFragmentManager(), getContext(), tabTiles);
        class_viewpager_shows.setAdapter(fragAdapter);
        class_tablayout.setupWithViewPager(class_viewpager_shows);

        tabCount = class_tablayout.getTabCount();
        for (int i = 0; i < tabCount; i++) {
            TabLayout.Tab tab = class_tablayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(getTabView(tabTiles[i]));

            }
        }
//        home_tablayout.getTabAt(0).select(); 无效
        AppCompatTextView compatTextView = (AppCompatTextView) class_tablayout.getTabAt(0).getCustomView();
        compatTextView.setSelected(true);
        compatTextView.setBackgroundDrawable(ResourcesUtils.getResDrawable(getContext(), R.drawable.home_tab_background));
        class_viewpager_shows.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //重设textview 的背景
                for (int i = 0; i < tabCount; i++) {
                    AppCompatTextView compatTextView = (AppCompatTextView) class_tablayout.getTabAt(i).getCustomView();
                    if (i == position) {
                        compatTextView.setBackgroundDrawable(ResourcesUtils.getResDrawable(getContext(), R.drawable.home_tab_background));
                    } else {
                        compatTextView.setBackgroundDrawable(null);
                    }

                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }






    private void initToolbarAndTab(View rootView) {

    }

    @Override
    protected void initData() {

    }



    //自定义TabView
    public View getTabView(String text) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.home_inner_item_tab, null);
        AppCompatTextView textView = (AppCompatTextView) view.findViewById(R.id.inner_tab_name);
        textView.setText(text);
        textView.setTextSize(14);
        return view;
    }
}
