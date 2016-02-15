package com.sen.activity;

import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.sen.base.BaseActivity;
import com.sen.liuboss.R;
import com.sen.uitls.ResourcesUtils;
import com.sen.uitls.StatusBarCompat;

import butterknife.Bind;
import butterknife.ButterKnife;


public class HomeActivity extends BaseActivity {


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


    public void initView() {
        setContentView(R.layout.activity_home);
        StatusBarCompat.compat(this, ResourcesUtils.getResColor(this, R.color.colorPrimaryDark));
        ButterKnife.bind(this);

        initTabView();

    }

    private void initTabView() {
        tabTiles = ResourcesUtils.getStringArray(this, R.array.tabItemName);
        tabItemDrawableNormal = new int[]{R.drawable.ic_tab_home_normal, R.drawable.ic_tab_classification_normal, R.drawable.ic_tab_car_normal, R.drawable.ic_tab_personal_normal};
        tabItemDrawableSelected = new int[]{R.drawable.ic_tab_home_selected, R.drawable.ic_tab_classification_selected, R.drawable.ic_tab_car_selected, R.drawable.ic_tab_personal_selected};

        tabCount = tabItemDrawableNormal.length;
        for (int i = 0; i < tabCount; i++) {
            TabLayout.Tab tab = layout_buttom_tab.newTab();
            tab.setCustomView(getTabView(tabItemDrawableNormal[i], tabTiles[i]));
            layout_buttom_tab.addTab(tab, i, false);
        }
        layout_buttom_tab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int positionTab = tab.getPosition();
                AppCompatTextView textView = (AppCompatTextView) tab.getCustomView();
                changeSelecteTabColor(textView, tabItemDrawableSelected[positionTab], true);
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


    }

    @Override
    protected void initActionBar() {
        super.initActionBar();


    }

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
