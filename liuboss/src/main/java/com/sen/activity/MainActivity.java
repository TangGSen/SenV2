package com.sen.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.sen.base.BaseActivity;
import com.sen.fragment.main.FoundFragment;
import com.sen.fragment.main.HomeFragment;
import com.sen.fragment.main.PersonalCenterFragment;
import com.sen.fragment.main.ShoppingCartFragment;
import com.sen.liuboss.R;
import com.sen.uitls.ResourcesUtils;
import com.sen.uitls.StatusBarCompat;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity {


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

    private HomeFragment mHomeFragment;
    private FoundFragment mFoundFragment;
    private ShoppingCartFragment mShoppingCartFragment;
    private PersonalCenterFragment mPersonalCenterFragment;

    private int currentFragPosition = 0;
    private final String FRAG_POSITION = "currentFragPosition";


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
        initTabView();
        mFragmentManager = getSupportFragmentManager();
        if (savedInstanceState != null) {

            //取出上一次保存的数据
            currentFragPosition = savedInstanceState.getInt(FRAG_POSITION,0);
            Log.e("sen","恢复的状态"+currentFragPosition);
            mHomeFragment = (HomeFragment) mFragmentManager.findFragmentByTag(tabTiles[0]);
            mFoundFragment = (FoundFragment) mFragmentManager.findFragmentByTag(tabTiles[1]);
            mShoppingCartFragment = (ShoppingCartFragment) mFragmentManager.findFragmentByTag(tabTiles[2]);
            mPersonalCenterFragment = (PersonalCenterFragment) mFragmentManager.findFragmentByTag(tabTiles[3]);

            layout_buttom_tab.getTabAt(currentFragPosition).select();


        }

        setSelectedFragment(currentFragPosition);




    }

    private void setSelectedFragment(int position) {

        FragmentTransaction transaction = mFragmentManager.beginTransaction().setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        hideAllFragments(transaction);
        switch (position) {
            case 0:
                if (mHomeFragment == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    mHomeFragment = new HomeFragment();
                    transaction.add(R.id.home_layout_content, mHomeFragment, tabTiles[position]);
                } else {
                    // 如果不为空，则直接将它显示出来
                    transaction.show(mHomeFragment);
                }
                break;
            case 1:
                if (mFoundFragment == null) {
                    mFoundFragment = new FoundFragment();
                    transaction.add(R.id.home_layout_content, mFoundFragment, tabTiles[position]);
                } else {
                    transaction.show(mFoundFragment);
                }
                break;
            case 2:
                if (mShoppingCartFragment == null) {
                    mShoppingCartFragment = new ShoppingCartFragment();
                    transaction.add(R.id.home_layout_content, mShoppingCartFragment, tabTiles[position]);
                } else {
                    transaction.show(mShoppingCartFragment);
                }
                break;
            case 3:
                if (mPersonalCenterFragment == null) {
                    mPersonalCenterFragment = new PersonalCenterFragment();
                    transaction.add(R.id.home_layout_content, mPersonalCenterFragment, tabTiles[position]);
                } else {
                    transaction.show(mPersonalCenterFragment);
                }
                break;
        }
        currentFragPosition = position;
        transaction.commit();
    }

    private void hideAllFragments(FragmentTransaction transaction) {
        if (mHomeFragment != null) {
            transaction.hide(mHomeFragment);
        }
        if (mFoundFragment != null) {
            transaction.hide(mFoundFragment);
        }
        if (mShoppingCartFragment != null) {
            transaction.hide(mShoppingCartFragment);
        }
        if (mPersonalCenterFragment != null) {
            transaction.hide(mPersonalCenterFragment);
        }
    }

    //系统销毁Activity 的时候保存Fragment 的状态
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //保存tab选中的状态
        Log.e("sen","保存tab选中的状态"+currentFragPosition);
        outState.putInt(FRAG_POSITION, currentFragPosition);
        super.onSaveInstanceState(outState);
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
                setSelectedFragment(positionTab);
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





}
