package com.sen.widget;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.sen.liuboss.R;
import com.sen.uitls.ImageLoadOptions;

import java.util.ArrayList;

/**
 * 作者：
 * Created by zhouweilong on 15/11/22.
 * <p/>
 * 修改：
 * 唐家森 by 2016/02/29
 * 为了适应我的项目，修改和删除东西
 * 1.指示点就是圆点
 * 2.如果viewPager Url 集合的size为1个时不显示指示点和不轮播图
 */
public class ImageCycleViewForDetail extends LinearLayout {

    private SwitchingAbleViewPager mViewpager;

    private LinearLayout viewGroup;

    private Context context;

    private View view;
    /**
     * 滚动图片指示视图列表
     */
    private ImageView[] mImageViews = null;



    private int selectPosition = 0;

    private int imageCount = 0;

    /**
     * 切换时间
     */
    private int time = 3000;

    /**
     * 图片轮播指示个图
     */
    private ImageView mImageView = null;

    private ImageCycleAdapter mAdvAdapter;

    public ImageCycleViewForDetail(Context context) {
        this(context, null);
    }

    public ImageCycleViewForDetail(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        view = LayoutInflater.from(context).inflate(R.layout.widget_image_cycle_view, null);
        mViewpager = (SwitchingAbleViewPager) view.findViewById(R.id.adv_pager);
        mViewpager.setOnPageChangeListener(new GuidePageChangeListener());
        viewGroup = (LinearLayout) view.findViewById(R.id.viewGroup);
    }


    /**
     * 装填图片数据
     *
     * @param imageUrlList
     * @param imageCycleViewListener
     */
    public void setImageResources(ArrayList<String> imageUrlList, ImageCycleViewListener imageCycleViewListener) {

        if (imageUrlList == null || imageUrlList.size() == 0) {
            return;
        }
        // 清除
        viewGroup.removeAllViews();
        // 图片广告数量
        imageCount = imageUrlList.size();


        if (imageCount > 1) {
            mImageViews = new ImageView[imageCount];
            mViewpager.setCanSwitching(true);
            for (int i = 0; i < imageCount; i++) {
                mImageView = new ImageView(context);
                LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                params.leftMargin = 30;
                mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                mImageView.setLayoutParams(params);

                mImageViews[i] = mImageView;
                if (i == 0) {

                    mImageViews[i].setBackgroundResource(R.drawable.head_spot_selected);

                } else {

                    mImageViews[i].setBackgroundResource(R.drawable.head_spot_normal);

                }
                viewGroup.addView(mImageViews[i]);
            }
    } else {
            //imageCount <=1 ViewPager不能滑动
        mViewpager.setCanSwitching(false);
    }

    mAdvAdapter=new ImageCycleAdapter(context, imageUrlList, imageCycleViewListener);

    mViewpager.setAdapter(mAdvAdapter);
    int num = Integer.MAX_VALUE / 2 % imageUrlList.size();
    int itemPostion = Integer.MAX_VALUE / 2 - num;
    mViewpager.setCurrentItem(itemPostion);
    this.

    removeAllViews();

    addView(view);
}


/**
 * 轮播图片监听
 */
private final class GuidePageChangeListener implements ViewPager.OnPageChangeListener {
    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
    }

    @Override
    public void onPageSelected(int index) {
        if (imageCount <= 1) {
            return;
        }
        selectPosition = index;
        index = index % mImageViews.length;
        // 设置当前显示的图片
        // 设置图片滚动指示器背

        mImageViews[index].setBackgroundResource(R.drawable.head_spot_selected);

        for (int i = 0; i < mImageViews.length; i++) {
            if (index != i) {
                mImageViews[i].setBackgroundResource(R.drawable.head_spot_normal);
            }
        }
    }
}


/**
 * 适配器
 */
private class ImageCycleAdapter extends PagerAdapter {

    /**
     * 图片视图缓存列表
     */
    private ArrayList<ImageView> mImageViewCacheList;

    /**
     * 图片资源列表
     */
    private ArrayList<String> mAdList = new ArrayList<String>();

    /**
     * 广告图片点击监听
     */
    private ImageCycleViewListener mImageCycleViewListener;

    private Context mContext;

    public ImageCycleAdapter(Context context, ArrayList<String> adList, ImageCycleViewListener imageCycleViewListener) {
        this.mContext = context;
        this.mAdList = adList;
        mImageCycleViewListener = imageCycleViewListener;
        mImageViewCacheList = new ArrayList<ImageView>();
    }

    public void changeData(ArrayList<String> adList) {
        this.mAdList = adList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        String imageUrl = mAdList.get(position % mAdList.size());
        ImageView imageView = null;
        if (mImageViewCacheList.isEmpty()) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            imageView.setAdjustViewBounds(true);
            //test
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        } else {
            imageView = mImageViewCacheList.remove(0);
        }
        // 设置图片点击监听
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mImageCycleViewListener.onImageClick(position % mAdList.size(), v);
            }
        });
        imageView.setTag(imageUrl);
        container.addView(imageView);
        ImageLoader.getInstance().displayImage(imageUrl, imageView, ImageLoadOptions.getBannerImageOptions());
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ImageView view = (ImageView) object;
        mViewpager.removeView(view);
        mImageViewCacheList.add(view);

    }

}

/**
 * 轮播控件的监听事件
 *
 * @author minking
 */
public static interface ImageCycleViewListener {

    /**
     * 单击图片事件
     *
     * @param position
     * @param imageView
     */
    public void onImageClick(int position, View imageView);
}

}
