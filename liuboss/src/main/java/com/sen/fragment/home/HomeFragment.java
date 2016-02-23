package com.sen.fragment.home;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatTextView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;

import com.sen.adapter.HomeActFragAdpter;
import com.sen.base.BaseFragment;
import com.sen.liuboss.R;
import com.sen.uitls.ResourcesUtils;
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

    @Bind(R.id.home_tablayout)
    TabLayout home_tablayout;
    @Bind(R.id.home_viewpager_shows)
    ViewPager home_viewpager_shows;

    private String tabTiles[];

    private int tabCount ;

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home_act_home, container, false);
        ButterKnife.bind(this, rootView);
        //处理适配 设置比例
        dealAdaptationToPhone();

        initTabView();



        return rootView;
    }

    private void initViewPager() {

    }

    private void initTabView() {
        //init tab data
        tabTiles = ResourcesUtils.getStringArray(getContext(), R.array.taHomeItemName);
        final HomeActFragAdpter fragAdapter = new HomeActFragAdpter(getActivity().getSupportFragmentManager(), getContext(), tabTiles);
        home_viewpager_shows.setAdapter(fragAdapter);
        home_tablayout.setupWithViewPager(home_viewpager_shows);
        tabCount = home_tablayout.getTabCount();
        for (int i = 0; i < tabCount; i++) {
            TabLayout.Tab tab = home_tablayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(getTabView(tabTiles[i]));

            }
        }
//        home_tablayout.getTabAt(0).select(); 无效
        AppCompatTextView compatTextView = (AppCompatTextView) home_tablayout.getTabAt(0).getCustomView();
        compatTextView.setSelected(true);
        compatTextView.setBackgroundDrawable(ResourcesUtils.getResDrawable(getContext(),R.drawable.home_tab_background));
        home_viewpager_shows.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //重设textview 的背景
                for (int i = 0; i < tabCount; i++) {
                    AppCompatTextView compatTextView = (AppCompatTextView) home_tablayout.getTabAt(i).getCustomView();
                    if (i==position){
                        compatTextView.setBackgroundDrawable(ResourcesUtils.getResDrawable(getContext(),R.drawable.home_tab_background));
                    }else{
                        compatTextView.setBackgroundDrawable(null);
                    }

                }



            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void dealAdaptationToPhone() {


        //获取TabLayout的高度然后设置CoordinatorLayout 的总高度
        ViewTreeObserver vto = home_tablayout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                home_tablayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                int tablayoutHeight = home_tablayout.getHeight();

                WindowManager windowManager = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
                DisplayMetrics dm = new DisplayMetrics();
                windowManager.getDefaultDisplay().getMetrics(dm);

                int commomHeight = (int) (dm.widthPixels * 0.421875);

                ViewGroup.LayoutParams params = head_viewpager.getLayoutParams();
                params.height = commomHeight;
                head_viewpager.setLayoutParams(params);

                ViewGroup.LayoutParams collapsingLayoutParams = collapsingToolbarLayout.getLayoutParams();
                collapsingLayoutParams.height = commomHeight + tablayoutHeight;
                collapsingToolbarLayout.setLayoutParams(collapsingLayoutParams);

            }
        });


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
        });




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

    //自定义TabView
    public View getTabView(String text) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.home_inner_item_tab, null);
        AppCompatTextView textView = (AppCompatTextView) view.findViewById(R.id.inner_tab_name);
        textView.setText(text);
        textView.setTextSize(16);
        return view;
    }
}
