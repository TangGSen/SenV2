package com.sen.fragment.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sen.base.BaseFragment;
import com.sen.liuboss.R;

/**
 * Created by Sen on 2016/2/22.
 */
public class HomeNewFragment extends BaseFragment {
    private View rootView;
    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home_child_new, container, false);
        return rootView;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setDefaultFragmentTitle(String title) {

    }
}
