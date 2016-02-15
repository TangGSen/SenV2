package com.sen.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Sen on 2016/2/15.
 */
public class NoSwitchingViewPager extends ViewPager {
    //
    private boolean isCanSwitching = false;

    public NoSwitchingViewPager(Context context) {
        super(context);
    }

    public NoSwitchingViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public void setCanSwitching(boolean isCanSwitching) {
        this.isCanSwitching = isCanSwitching;
    }

    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isCanSwitching) {
            return super.onTouchEvent(ev);
        } else {
            return false;
        }
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isCanSwitching) {

            return super.onInterceptTouchEvent(ev);
        } else {
            return false;
        }
    }
}
