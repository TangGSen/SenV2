package com.sen.fragment.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.sen.base.BaseFragment;
import com.sen.liuboss.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Sen on 2016/2/3.
 */
public class ClassificationFragment extends BaseFragment {
    private View rootView;
    @Bind(R.id.class_tablayout)
    TabLayout class_tablayout;
    @Bind(R.id.class_head_layout)
    LinearLayout class_head_layout;


    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main_act_classification, container, false);
        ButterKnife.bind(this, rootView);
        initToolbarAndTab(rootView);
        return rootView;
    }

    private void initToolbarAndTab(View rootView) {
      View toolbarView = View.inflate(getContext(), R.layout.class_toolbar_layout, null);

        class_head_layout.addView(toolbarView);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setDefaultFragmentTitle(String title) {

    }
}
