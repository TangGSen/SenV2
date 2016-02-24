package com.sen.fragment.main;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.sen.adapter.FoundFragTabAdpter;
import com.sen.base.BaseFragment;
import com.sen.liuboss.R;
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
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main_act_found, container, false);
        ButterKnife.bind(this, rootView);
        dealAdaptationToPhone();
        initTabView();
        return rootView;
    }

    private void initTabView() {
        //init tab data
        tabTiles = ResourcesUtils.getStringArray(getContext(), R.array.taHomeItemName);
         FoundFragTabAdpter fragAdapter = new FoundFragTabAdpter(getActivity().getSupportFragmentManager(), getContext(), tabTiles);
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


    private void dealAdaptationToPhone() {
        WindowManager windowManager = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);

        ViewGroup.LayoutParams params = tv_search.getLayoutParams();
        params.width = (int) (dm.widthPixels * 0.618);
        tv_search.setLayoutParams(params);

        LinearLayout.LayoutParams coordinatorParams = (LinearLayout.LayoutParams) coordinatorLayout.getLayoutParams();
        coordinatorParams.setMargins(0,getStatusBarHeight(),0,0);
        coordinatorLayout.setLayoutParams(coordinatorParams);
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    private void initToolbarAndTab(View rootView) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setDefaultFragmentTitle(String title) {

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
