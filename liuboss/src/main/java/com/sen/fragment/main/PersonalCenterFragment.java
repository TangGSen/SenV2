package com.sen.fragment.main;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.AppCompatImageView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.sen.base.BaseFragment;
import com.sen.liuboss.R;
import com.sen.uitls.ResourcesUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Sen on 2016/2/3.
 */
public class PersonalCenterFragment extends BaseFragment {
    private View rootView;
    @Bind(R.id.personal_rootLayout)
    CoordinatorLayout personal_rootLayout;
    @Bind(R.id.img_user)
    AppCompatImageView img_user;

    @Override
    protected void dealAdaptationToPhone() {
        //动态设置coordinatorLayout 的marginTop,因为在小米的手机xml 的24dp中显示不正常
        FrameLayout.LayoutParams rootLayoutParams = (FrameLayout.LayoutParams) personal_rootLayout.getLayoutParams();
        rootLayoutParams.setMargins(0, ResourcesUtils.getStatusBarHeight(getContext()), 0, 0);
        personal_rootLayout.setLayoutParams(rootLayoutParams);


        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) img_user.getLayoutParams();
        lp.setMargins(0, (int) (getTabLayoutHeight()*1.6), 0, 0);
        img_user.setLayoutParams(lp);
    }

    private int getTabLayoutHeight() {
        int actionBarHeight = 0;
        TypedValue tv = new TypedValue();
        if (getContext().getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getContext().getResources().getDisplayMetrics());
        }
        return actionBarHeight;
    }


    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main_act_personal, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void initData() {

    }


}
