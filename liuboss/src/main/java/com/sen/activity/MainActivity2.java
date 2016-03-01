package com.sen.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.sen.base.BaseActivity;
import com.sen.factory.MainActFragmentFactory;
import com.sen.liuboss.R;
import com.sen.uitls.ResourcesUtils;
import com.sen.uitls.StatusBarCompat;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity2 extends BaseActivity {


    @Bind(R.id.home_layout_content)
    FrameLayout home_layout_content;
    @Bind(R.id.layout_buttom_tab)
    TabLayout layout_buttom_tab;
    //tab item name
    String tabTiles[];
    //tab item drawable
    int tabItemDrawableNormal[];
    int tabItemDrawableSelected[];
    private int tabCount;

    Fragment mCurrentFragment;
    FragmentManager mFragmentManager;


    public void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
       StatusBarCompat.compat(this, ResourcesUtils.getResColor(this, R.color.colorPrimaryDark));
        ButterKnife.bind(this);


    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tabTiles = ResourcesUtils.getStringArray(this, R.array.tabButtonItemName);
        tabItemDrawableNormal = new int[]{R.drawable.ic_tab_home_normal, R.drawable.ic_tab_classification_normal, R.drawable.ic_tab_car_normal, R.drawable.ic_tab_personal_normal};
        tabItemDrawableSelected = new int[]{R.drawable.ic_tab_home_selected, R.drawable.ic_tab_classification_selected, R.drawable.ic_tab_car_selected, R.drawable.ic_tab_personal_selected};

        initFragmentSelect();
        initTabView();
    }


    private void initFragmentSelect() {
        mFragmentManager = getSupportFragmentManager();
        //Home is selected
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        mCurrentFragment = MainActFragmentFactory.createFragment(0);
        transaction.add(R.id.home_layout_content, mCurrentFragment, tabTiles[0]).commit();

    }

    private void initTabView() {
        tabCount = tabItemDrawableNormal.length;
        for (int i = 0; i < tabCount; i++) {
            TabLayout.Tab tab = layout_buttom_tab.newTab();
            tab.setCustomView(getTabView(tabItemDrawableNormal[i], tabTiles[i]));
            layout_buttom_tab.addTab(tab, i);

        }

        layout_buttom_tab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int positionTab = tab.getPosition();
                AppCompatTextView textView = (AppCompatTextView) tab.getCustomView();
                changeSelecteTabColor(textView, tabItemDrawableSelected[positionTab], true);
                switchContent(mCurrentFragment, MainActFragmentFactory.createFragment(positionTab), tabTiles[positionTab]);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int positionTab = tab.getPosition();
                AppCompatTextView textView = (AppCompatTextView) tab.getCustomView();
                changeSelecteTabColor(textView, tabItemDrawableNormal[positionTab], false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //不知为啥 0 的不会调用setOnTabSelectedListener 的方法
        //  layout_buttom_tab.getTabAt(0).select();


        AppCompatTextView textView = (AppCompatTextView) layout_buttom_tab.getTabAt(0).getCustomView();
        
        changeSelecteTabColor(textView, tabItemDrawableSelected[0], true);

    }

    @Override
    protected void initActionBar() {
        super.initActionBar();


    }

    //自定义TabView
    public View getTabView(int id, String text) {
        View view = LayoutInflater.from(this).inflate(R.layout.home_buttom_item_tab, null);
        AppCompatTextView textView = (AppCompatTextView) view.findViewById(R.id.tab_name);
        //设置图片
        Drawable topDrawable = ResourcesUtils.getResDrawable(this, id);
        topDrawable.setBounds(0, 0, topDrawable.getMinimumWidth(), topDrawable.getMinimumHeight());
        textView.setCompoundDrawables(null, topDrawable, null, null);
        textView.setText(text);
        return view;
    }

    public void changeSelecteTabColor(AppCompatTextView textView, int drawableId, boolean isSelected) {
        Drawable topDrawable = ResourcesUtils.getResDrawable(this, drawableId);
        topDrawable.setBounds(0, 0, topDrawable.getMinimumWidth(), topDrawable.getMinimumHeight());
        textView.setCompoundDrawables(null, topDrawable, null, null);
        textView.setSelected(isSelected);
    }


    public void switchContent(Fragment from, Fragment to, String flag) {
        if (mCurrentFragment != to) {
            mCurrentFragment = to;
            FragmentTransaction transaction = mFragmentManager.beginTransaction().setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
            if (!to.isAdded()) {    // 先判断是否被add过
                transaction.hide(from).add(R.id.home_layout_content, to, flag).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
        }
    }


}
