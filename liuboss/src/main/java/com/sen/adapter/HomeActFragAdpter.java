package com.sen.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;

import com.sen.liuboss.R;
import com.sen.uitls.FragmentFactory;
import com.sen.uitls.ResourcesUtils;

/**
 * Created by Sen on 2016/2/3.
 */
public class HomeActFragAdpter  extends FragmentPagerAdapter{

    Context mContext;

    //tab title
    private String mTabTiles[] ;
    private int mTabDrawableIds[];
    private Toolbar mToolbar;

    public HomeActFragAdpter(FragmentManager fm, Context context, String tabTiles[], int tabDrawableIds[]) {
        super(fm);
        mContext = context;
        mTabTiles = tabTiles;
        mTabDrawableIds = tabDrawableIds;
    }



    @Override
    public Fragment getItem(int position) {
        return FragmentFactory.createFragment(position);
    }

    @Override
    public int getCount() {
        return mTabTiles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabTiles[position];
    }

    public View getTabView(int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.home_buttom_item_tab,null);
        AppCompatTextView textView = (AppCompatTextView) view.findViewById(R.id.tab_name);
        setTabDrawableAndText(position,textView);
        return view ;
    }

    private void setTabDrawableAndText(int position, AppCompatTextView textView) {
        Drawable topDrawable = ResourcesUtils.getResDrawable(mContext,mTabDrawableIds[position]);
        topDrawable.setBounds(0, 0, topDrawable.getMinimumWidth(), topDrawable.getMinimumHeight());
        textView.setCompoundDrawables(null, topDrawable, null , null);
        textView.setText(mTabTiles[position]);
    }

    public void changeSelecteTabColor(AppCompatTextView textView,int drawableId,boolean isSelected){
        Drawable topDrawable = ResourcesUtils.getResDrawable(mContext,drawableId);
        topDrawable.setBounds(0, 0, topDrawable.getMinimumWidth(), topDrawable.getMinimumHeight());
        textView.setCompoundDrawables(null, topDrawable, null , null);
        textView.setSelected(isSelected);
    }
}
