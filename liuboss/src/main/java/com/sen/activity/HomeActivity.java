package com.sen.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatTextView;

import com.sen.adapter.HomeActFragAdpter;
import com.sen.base.BaseActivity;
import com.sen.liuboss.R;
import com.sen.uitls.ResourcesUtils;
import com.sen.uitls.StatusBarCompat;
import com.sen.widget.NoSwitchingViewPager;

import butterknife.Bind;
import butterknife.ButterKnife;


public class HomeActivity extends BaseActivity {



    @Bind(R.id.viewPager_content)
    NoSwitchingViewPager viewPager_content;
    @Bind(R.id.layout_buttom_tab)
    TabLayout layout_buttom_tab;
    //tab item name
    String tabTiles[];
    //tab item drawable
    int tabItemDrawableNormal[];
    int tabItemDrawableSelected[];
    private int tabCount;


    public void initView() {
        setContentView(R.layout.activity_home);
        StatusBarCompat.compat(this,ResourcesUtils.getResColor(this,R.color.colorPrimaryDark));
        ButterKnife.bind(this);

        initTabView();

    }

    private void initTabView() {

        tabTiles = ResourcesUtils.getStringArray(this, R.array.tabItemName);
        tabItemDrawableNormal = new int[]{R.drawable.ic_tab_home_normal, R.drawable.ic_tab_classification_normal, R.drawable.ic_tab_car_normal, R.drawable.ic_tab_personal_normal};
        tabItemDrawableSelected = new int[]{R.drawable.ic_tab_home_selected, R.drawable.ic_tab_classification_selected, R.drawable.ic_tab_car_selected, R.drawable.ic_tab_personal_selected};


        final HomeActFragAdpter fragAdapter = new HomeActFragAdpter(getSupportFragmentManager(), this, tabTiles, tabItemDrawableNormal);
        viewPager_content.setAdapter(fragAdapter);
        layout_buttom_tab.setupWithViewPager(viewPager_content);
        tabCount = layout_buttom_tab.getTabCount();
        for (int i = 0; i < tabCount; i++) {
            TabLayout.Tab tab = layout_buttom_tab.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(fragAdapter.getTabView(i));
            }
        }
        viewPager_content.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < tabCount; i++) {
                    TabLayout.Tab tab = layout_buttom_tab.getTabAt(i);
                    AppCompatTextView textView = (AppCompatTextView) tab.getCustomView();
                    if (i == position) {
                       fragAdapter.changeSelecteTabColor(textView,tabItemDrawableSelected[i],true);
                    } else {
                        fragAdapter.changeSelecteTabColor(textView,tabItemDrawableNormal[i],false);
                    }

                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager_content.setCurrentItem(0);
        fragAdapter.changeSelecteTabColor((AppCompatTextView)layout_buttom_tab.getTabAt(0).getCustomView(),tabItemDrawableSelected[0],true);

    }

    @Override
    protected void initActionBar() {
        super.initActionBar();



    }
}
