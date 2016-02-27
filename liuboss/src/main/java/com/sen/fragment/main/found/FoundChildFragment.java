package com.sen.fragment.main.found;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sen.adapter.ClassChildRecyclerAdapter;
import com.sen.base.BaseFragment;
import com.sen.liuboss.R;
import com.sen.mode.HomeNewBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Sen on 2016/2/22.
 */
public class FoundChildFragment extends BaseFragment {
    private View rootView;

    @Bind(R.id.recyclerView_new)
    RecyclerView recyclerView_new;
    //线性布局
   // LinearLayoutManager mLinearnLayoutManager;

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home_child_new, container, false);
        ButterKnife.bind(this, rootView);
        //设置RecycleView
        initRecyclerView();
        return rootView;
    }

    private void initRecyclerView() {
       // mLinearnLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView_new.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView_new.setHasFixedSize(true);
        //创建并设置Adapter

    }

    @Override
    protected void initData() {
        HomeNewBean home = new HomeNewBean();
        List<String> data =home.getData();
        ClassChildRecyclerAdapter adapter = new ClassChildRecyclerAdapter(getActivity(),home.getData());
        adapter.setOnItemClickListener(new ClassChildRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
            }
        });
        recyclerView_new.setAdapter(adapter);
    }



    @Override
    protected void dealAdaptationToPhone() {

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

}
