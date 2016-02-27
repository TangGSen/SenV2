package com.sen.activity.details;

import android.support.v7.widget.Toolbar;

import com.sen.base.BaseActivity;
import com.sen.liuboss.R;

/**
 * Created by Administrator on 2016/2/27.
 */
public class ActFoundChildItemDeatil extends BaseActivity {
    @Override
    protected void initView() {
        setContentView(R.layout.actlayout_foundchild_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_behavior);
        toolbar.getBackground().setAlpha(0);//toolbar透明度初始化为0
    }
}
