package com.sen.activity.details;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.sen.base.BaseActivity;
import com.sen.liuboss.R;
import com.sen.uitls.ResourcesUtils;
import com.sen.uitls.StatusBarCompat;
import com.sen.widget.ImageCycleViewForDetail;
import com.sen.widget.PullPushLayout;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/2/27.
 */
public class ActFoundChildItemDeatil extends BaseActivity {
    private Toolbar item_detail_toolbar;

    private PullPushLayout mPullPushLayout;
    private Drawable bgBackDrawable;
    private Drawable bgShareDrawable;

    private AppCompatImageView iv_back;
    private AppCompatImageView iv_share;

    private Drawable bgToolbarDrawable;
    private ArrayList<String> urls = new ArrayList<>();

    private int alphaMax = 180;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.layout_class_item_datails);
        StatusBarCompat.compat(this, ResourcesUtils.getResColor(this, R.color.colorPrimaryDark));
        mPullPushLayout = (PullPushLayout) this.findViewById(R.id.pullpushlayout);

        item_detail_toolbar = (Toolbar) this.findViewById(R.id.item_detail_toolbar);
        View toolBarView = View.inflate(this, R.layout.toolbar_detail_view, null);
        item_detail_toolbar.addView(toolBarView);
        iv_back = (AppCompatImageView) toolBarView.findViewById(R.id.iv_back);
        iv_share = (AppCompatImageView) toolBarView.findViewById(R.id.iv_share);

        //初始化背景
        bgBackDrawable = iv_back.getBackground();
        bgBackDrawable.setAlpha(alphaMax);
        bgShareDrawable = iv_share.getBackground();
        bgShareDrawable.setAlpha(alphaMax);
        //开始toolbar 是透明的
        bgToolbarDrawable = item_detail_toolbar.getBackground();
        bgToolbarDrawable.setAlpha(0);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        urls.add("http://img4.duitang.com/uploads/item/201112/03/20111203202432_BPPR3.thumb.600_0.jpg");
        urls.add("http://pic.nipic.com/2007-11-14/20071114232440935_2.jpg");
        urls.add("http://img4.duitang.com/uploads/item/201112/03/20111203202432_BPPR3.thumb.600_0.jpg");
        urls.add("http://pic.nipic.com/2007-11-14/20071114232440935_2.jpg");
        ImageCycleViewForDetail head_viewpager = (ImageCycleViewForDetail) this.findViewById(R.id.head_viewpager);
        head_viewpager.setImageResources(urls, new ImageCycleViewForDetail.ImageCycleViewListener() {
            @Override
            public void onImageClick(int position, View imageView) {

            }
        });

        initLinster();

    }

    private void initLinster() {

        mPullPushLayout.setOnTouchEventMoveListenre(new PullPushLayout.OnTouchEventMoveListenre() {

            @Override
            public void onSlideUp(int mOriginalHeaderHeight, int mHeaderHeight) {

            }

            @Override
            public void onSlideDwon(int mOriginalHeaderHeight, int mHeaderHeight) {

            }

            @Override
            public void onSlide(int alpha) {
                int alphaReverse = alphaMax - alpha;
                if (alphaReverse < 0) {
                    alphaReverse = 0;
                }
                bgBackDrawable.setAlpha(alphaReverse);
                bgShareDrawable.setAlpha(alphaReverse);
                bgToolbarDrawable.setAlpha(alpha);

            }
        });

    }
}
