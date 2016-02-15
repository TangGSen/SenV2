package com.sen.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.sen.fragment.login.FragRegisterStepOne;
import com.sen.fragment.login.FragRegisterStepTwo;
import com.sen.fragment.login.LoginFragment;
import com.sen.liuboss.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Sen on 2016/1/8.
 */
public class LoginRegisterActivity extends AppCompatActivity implements FragRegisterStepOne.OnNextStepLinstener {


    @Bind(R.id.app_bar)
    Toolbar mToolbar;
    @Bind(R.id.action_bar_title)
    TextView actionBarTile;
    @Bind(R.id.fab_change_fragment)
    FloatingActionButton fabChangeFragment;
    FragRegisterStepOne mRegisterStepOne;
    FragRegisterStepTwo mRegisterStepTwo;


    FragmentManager mFragmentManager;
    Fragment mCurrentFragment;
    LoginFragment mLoginFragment;

    private final static String FLAG_REGISTER = "register";
    private final static String FLAG_LOGIN = "login";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData(savedInstanceState);
        initActionBar();
       // SMSSDK.initSDK(this, Constants.CON_APPKEY,Constants.CON_APPSECRET,true);


    }

    protected void initView() {
        setContentView(R.layout.activity_login_reguster);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.fab_change_fragment)
    public void FabChangeFragment() {

        changeFragment();


    }

    protected void initActionBar() {
        mToolbar.setTitle("");
        mToolbar.setNavigationIcon(R.drawable.arrow_left);
        setSupportActionBar(mToolbar);
    }

    protected void initData(Bundle savedInstanceState) {
        mFragmentManager = getSupportFragmentManager();
        mLoginFragment = new LoginFragment();

        mCurrentFragment = mLoginFragment;
        actionBarTile.setText(R.string.loginString);
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.add(R.id.login_register_layout, mLoginFragment, FLAG_LOGIN).commit();


    }

    private void changeFragment() {
        if (mCurrentFragment instanceof LoginFragment) {
            if (mRegisterStepOne == null) {
                mRegisterStepOne = new FragRegisterStepOne();
            }
            switchContent(mLoginFragment, mRegisterStepOne, FLAG_REGISTER);
            mCurrentFragment = mRegisterStepOne;
            actionBarTile.setText(R.string.register_new_user);
            fabChangeFragment.setVisibility(View.GONE);

        } else {
//            switchContent(mRegisterStepOne, mLoginFragment, FLAG_LOGIN);
//            mCurrentFragment = mLoginFragment;
//            actionBarTile.setText(R.string.loginString);
        }
    }



    public void switchContent(Fragment from, Fragment to, String flag) {

        if (mCurrentFragment != to) {
            mCurrentFragment = to;
            FragmentTransaction transaction = mFragmentManager.beginTransaction().setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
            if (!to.isAdded()) {    // 先判断是否被add过
                transaction.hide(from).add(R.id.login_register_layout, to, flag).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
        }
    }


    @Override
    public void onNextStep() {
        if (mCurrentFragment instanceof FragRegisterStepOne) {
            if (mRegisterStepTwo == null) {
                mRegisterStepTwo = new FragRegisterStepTwo();
            }
            switchContent(mRegisterStepOne,mRegisterStepTwo, FLAG_REGISTER);
            mCurrentFragment = mRegisterStepTwo;
            actionBarTile.setText(R.string.register_new_user);
            fabChangeFragment.setVisibility(View.GONE);

        }
    }
}
