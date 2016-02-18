package com.sen.fragment.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sen.base.BaseFragment;
import com.sen.liuboss.R;

import butterknife.ButterKnife;

/**
 * Created by Sen on 2016/2/3.
 */
public class HomeFragment extends BaseFragment {
    private View rootView;

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home_act_home, container, false);
        ButterKnife.bind(this, rootView);



        return rootView;
    }
    @Override
    protected void initData() {

    }

    @Override
    protected void setDefaultFragmentTitle(String title) {

    }
}
