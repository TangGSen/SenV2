package com.sen.fragment.login;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.sen.base.BaseFragment;
import com.sen.liuboss.R;
import com.sen.uitls.ResourcesUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.functions.Func2;

import static android.text.TextUtils.isEmpty;

/**
 * Created by Sen on 2016/1/14.
 */
public class LoginFragment extends BaseFragment {
    private View view;
    @Bind(R.id.user_name)
    AppCompatEditText et_user_name;
    @Bind(R.id.password)
    AppCompatEditText et_password;
    @Bind(R.id.bt_login)
    AppCompatButton bt_login;

    @Bind(R.id.btn_show_password)
    AppCompatButton btn_show_password;
    @Bind(R.id.login_user_input_layout)
    TextInputLayout user_input_layout;
    @Bind(R.id.login_password_input_layout)
    TextInputLayout password_input_layout;
    //密码是否显示
    private boolean isShow;

    private Observable<CharSequence> mUserNameChangeObservable;
    private Observable<CharSequence> mPasswordChangeObservable;
    private Subscription mSubscription;

    private static Handler mHandle = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){

            }
        }
    };


    @Override
    protected void dealAdaptationToPhone() {

    }

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        user_input_layout.setHint(ResourcesUtils.getResString(getContext(), R.string.frag_login_username_hint));
        password_input_layout.setHint(ResourcesUtils.getResString(getContext(), R.string.frag_login_password_hint));
        initObservable();
        return view;
    }

    private void initObservable() {
        mUserNameChangeObservable = RxTextView.textChanges(et_user_name).skip(1);
        mPasswordChangeObservable = RxTextView.textChanges(et_password).skip(1);
        setCombineLatestEvents();
    }

    private void setCombineLatestEvents() {
        mSubscription = Observable.combineLatest(mUserNameChangeObservable, mPasswordChangeObservable,
                new Func2<CharSequence, CharSequence, Boolean>() {
                    @Override
                    public Boolean call(CharSequence strUserName, CharSequence strPassword) {

                        boolean userNameValid = !isEmpty(strUserName) && strUserName.length() > 3;
                        user_input_layout.setErrorEnabled(!userNameValid);
                        if (userNameValid) {
                            //userName is error
                        } else {
                            user_input_layout.setError(ResourcesUtils.getResString(getContext(), R.string.input_user_error));
                        }


                        boolean passwordValid = !isEmpty(strPassword) && strPassword.length() > 3;
                        password_input_layout.setErrorEnabled(!passwordValid);
                        if (passwordValid) {
                            //
                        } else {
                            password_input_layout.setError(ResourcesUtils.getResString(getContext(), R.string.input_password_error));
                        }
                        return userNameValid && passwordValid;
                    }
                }).subscribe(new Observer<Boolean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Boolean aBoolean) {
                bt_login.setEnabled(aBoolean);
                if (aBoolean) {
                    bt_login.setTextColor(ResourcesUtils.getResColor(getContext(), R.color.textActionBarTile));
                } else {
                    bt_login.setTextColor(ResourcesUtils.getResColor(getContext(), R.color.colorPrimaryDark));
                }
            }
        });
    }

    @OnClick(R.id.user_name)
    public void setUserOnclick() {
        et_user_name.setInputType(InputType.TYPE_CLASS_PHONE);
    }




    @OnClick(R.id.password)
    public void setPasswordOnclick() {
        et_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        btn_show_password.setBackgroundDrawable(ResourcesUtils.getResDrawable(getContext(), R.drawable.eye_off));
        Editable etPassword = et_password.getText();
        et_password.setSelection(etPassword.length());
    }

    @OnClick(R.id.btn_show_password)
    public void setShowPassword() {
        //关闭软件盘
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(et_password.getWindowToken(), 0);
        if (!isShow) {
            et_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            btn_show_password.setBackgroundDrawable(ResourcesUtils.getResDrawable(getContext(), R.drawable.eye_open));
        } else {
            et_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            btn_show_password.setBackgroundDrawable(ResourcesUtils.getResDrawable(getContext(), R.drawable.eye_off));
        }
        //将光标移动到最后
        Editable etPassword = et_password.getText();
        et_password.setSelection(etPassword.length());
        isShow = !isShow;
    }


    @Override
    protected void initData() {

    }

    @Override
    protected void setDefaultFragmentTitle(String title) {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }
    }
}
