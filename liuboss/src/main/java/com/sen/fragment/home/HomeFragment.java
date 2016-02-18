package com.sen.fragment.home;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.sen.base.BaseFragment;
import com.sen.liuboss.R;
import com.sen.widget.ImageCycleView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Sen on 2016/2/3.
 */
public class HomeFragment extends BaseFragment {
    private View rootView;
    private ArrayList<String> urls = new ArrayList<>();
    @Bind(R.id.head_viewpager)
    ImageCycleView head_viewpager;

    @Bind(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home_act_home, container, false);
        ButterKnife.bind(this, rootView);
        //处理适配 设置比例
        dealAdaptationToPhone();




        return rootView;
    }

    private void dealAdaptationToPhone() {

        WindowManager windowManager = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);

        ViewGroup.LayoutParams params = head_viewpager.getLayoutParams();
        params.height = (int) (dm.widthPixels * 0.421875);
        head_viewpager.setLayoutParams(params);

        ViewGroup.LayoutParams collapsingLayoutParams = collapsingToolbarLayout.getLayoutParams();
        collapsingLayoutParams.height = (int) (dm.widthPixels);
        collapsingToolbarLayout.setLayoutParams(collapsingLayoutParams);


    }

    @Override
    protected void initData() {
        //注意，懒加载 一定要调用 setUserVisibleHint(true)后该方法才调用
        urls.add("http://s.xnimg.cn/rrinvest/wap/events/2015/recruitment/img/banner.jpg");
        urls.add("http://a.xnimg.cn/rrinvest/img/bannerwap/2015-11-11.jpg");
        urls.add("http://s.xnimg.cn/rrinvest/wap/events/2015/fall/img/appbanner.png");
        urls.add("http://fmn.rrfmn.com/fmn074/20150413/1720/original_WyLG_60c800005a561e83.jpg");
        head_viewpager.setImageResources(urls, new ImageCycleView.ImageCycleViewListener() {
            @Override
            public void onImageClick(int position, View imageView) {

            }
        }, 1);
    }

    @Override
    protected void setDefaultFragmentTitle(String title) {

    }

    @Override
    public void onResume() {
        super.onResume();
        head_viewpager.startImageCycle();
        setUserVisibleHint(true);
    }

    @Override
    public void onStop() {
        super.onStop();
        head_viewpager.stopImageCycle();
        setUserVisibleHint(false);
    }
}
