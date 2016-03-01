package com.sen.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		init();
		initView(savedInstanceState);
		dealAdaptationToPhone();
		initData(savedInstanceState);
		initActionBar();

	}

	/**
	 * 处理手机适配
	 */
	protected  void dealAdaptationToPhone(){

	};



	protected void initData(Bundle savedInstanceState) {
	}

	/**
	 * 初始化ActionBar
	 */
	protected void initActionBar() {
	}

	/**
	 * 初始化界面
	 * @param savedInstanceState
	 */
	protected abstract void initView(Bundle savedInstanceState) ;

	/**
	 * 初始化
	 */
	protected void init() {

	}








}
