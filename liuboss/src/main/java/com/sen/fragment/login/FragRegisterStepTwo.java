package com.sen.fragment.login;

import android.content.Context;
import android.os.Bundle;
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
import rx.functions.Func3;

/**
 * Created by Sen on 2016/1/19.
 */
public class FragRegisterStepTwo extends BaseFragment {
    private View view;

    @Bind(R.id.et_password)
    AppCompatEditText et_password;
    @Bind(R.id.bt_register)
    AppCompatButton bt_register;
    @Bind(R.id.btn_show_password)
    AppCompatButton btn_show_password;

    @Bind(R.id.register_password_input_layout)
    TextInputLayout password_input_layout;
    //密码是否显示
    private boolean isShow;

    private Observable<CharSequence> mUserNameChangeObservable;
    private Observable<CharSequence> mPasswordChangeObservable;
    private Observable<CharSequence> mVerificodeChangeObservable;
    private Subscription mSubscription;
    private boolean boolUser;
    private boolean boolCode;
    private boolean boolPassword;

    @Override
    protected void dealAdaptationToPhone() {

    }

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_register_step_two, container, false);
        ButterKnife.bind(this, view);

        initObservable();
        return view;
    }

    private void initObservable() {
       // mUserNameChangeObservable = RxTextView.textChanges(et_user_name).skip(1);
      //  mVerificodeChangeObservable = RxTextView.textChanges(et_verifi_code).skip(1);
        mPasswordChangeObservable = RxTextView.textChanges(et_password).skip(1);

        setCombineLatestEvents();
    }

    private void setCombineLatestEvents() {

        mSubscription = Observable.combineLatest(mUserNameChangeObservable, mVerificodeChangeObservable,
                mPasswordChangeObservable, new Func3<CharSequence, CharSequence, CharSequence, Boolean>() {
                    @Override
                    public Boolean call(CharSequence charUserName, CharSequence charCode, CharSequence charPassword) {

                        if (charUserName.length() == 11) {
                            boolUser= true;
                        }else{
                            boolUser = false;
                        }
                 //       bt_get_code.setEnabled(boolUser);


                        if ("158758".equals(charCode)){
                            boolCode = true;
                        }else{
                            boolCode = false;
                        }

                        if (charPassword.length()>=5){
                            boolPassword = true;
                        }else{
                            boolPassword = false;
                        }
                        return boolUser && boolCode && boolPassword ;
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

                bt_register.setEnabled(aBoolean);
                if (aBoolean) {
                    bt_register.setTextColor(ResourcesUtils.getResColor(getContext(),R.color.textActionBarTile));
                } else {
                    bt_register.setTextColor(ResourcesUtils.getResColor(getContext(),R.color.colorPrimaryDark));

                }
            }
        });

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

    @OnClick(R.id.et_password)
    public void setPasswordOnclick() {
        et_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        btn_show_password.setBackgroundDrawable(ResourcesUtils.getResDrawable(getContext(), R.drawable.eye_off));
        Editable etPassword = et_password.getText();
        et_password.setSelection(etPassword.length());
    }


    @Override
    protected void initData() {

    }

    @Override
    protected void setDefaultFragmentTitle(String title) {

    }

    @Override
    public void onPause() {
        super.onPause();
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}
