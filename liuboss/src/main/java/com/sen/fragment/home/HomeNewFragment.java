package com.sen.fragment.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sen.adapter.HomeNewRecyclerAdapter;
import com.sen.base.BaseFragment;
import com.sen.liuboss.R;
import com.sen.mode.HomeNewBean;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Sen on 2016/2/22.
 */
public class HomeNewFragment extends BaseFragment {
    private View rootView;

    @Bind(R.id.recyclerView_new)
    RecyclerView recyclerView_new;

    LinearLayoutManager mLinearnLayoutManager;

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home_child_new, container, false);
        ButterKnife.bind(this, rootView);
        //设置RecycleView
        initRecyclerView();
        return rootView;
    }

    private void initRecyclerView() {
        mLinearnLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView_new.setLayoutManager(mLinearnLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView_new.setHasFixedSize(true);
        //创建并设置Adapter

    }

    @Override
    protected void initData() {
        HomeNewBean home = new HomeNewBean();
        HomeNewRecyclerAdapter adapter = new HomeNewRecyclerAdapter(home.getData());
        recyclerView_new.setAdapter(adapter);
    }

    @Override
    protected void setDefaultFragmentTitle(String title) {

    }

    @Override
    public void onResume() {
        super.onResume();
        setUserVisibleHint(true);
    }

    @Override
    public void onStop() {
        super.onStop();
        setUserVisibleHint(false);
    }

}
