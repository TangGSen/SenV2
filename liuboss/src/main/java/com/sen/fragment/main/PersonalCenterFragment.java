package com.sen.fragment.main;

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
public class PersonalCenterFragment extends BaseFragment {
    private View rootView;
//    @Bind(R.id.txt_class)
//    AppCompatTextView textView ;
    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main_act_found, container, false);
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
