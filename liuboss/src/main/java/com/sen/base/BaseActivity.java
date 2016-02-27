package com.sen.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		init();
		initView();
		dealAdaptationToPhone();
		initData();
		initActionBar();

	}

	/**
	 * 处理手机适配
	 */
	protected  void dealAdaptationToPhone(){

	};



	protected void initData() {
	}

	/**
	 * 初始化ActionBar
	 */
	protected void initActionBar() {
	}

	/**
	 * 初始化界面
	 */
	protected abstract void initView() ;

	/**
	 * 初始化
	 */
	protected void init() {

	}








}
