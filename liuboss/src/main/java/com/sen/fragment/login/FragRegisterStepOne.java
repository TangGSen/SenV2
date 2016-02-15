package com.sen.fragment.login;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sen.base.BaseFragment;
import com.sen.liuboss.R;
import com.sen.uitls.Constants;
import com.sen.uitls.NetUtils;
import com.sen.uitls.RegexUtils;
import com.sen.uitls.ResourcesUtils;

import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.utils.SMSLog;

/**
 * Created by Sen on 2016/1/27.
 */
public class FragRegisterStepOne extends BaseFragment {
    private View rootView;

    @Bind(R.id.et_user_name_one)
    AppCompatEditText et_user_name_one;
    @Bind(R.id.et_verifi_code_one)
    AppCompatEditText et_verifi_code_one;
    @Bind(R.id.bt_get_code_one)
    AppCompatButton bt_get_code_one;

    @Bind(R.id.bt_register_next)
    AppCompatButton bt_register_next;
    @Bind(R.id.user_layout_step_one)
    TextInputLayout user_layout_step_one;
    @Bind(R.id.verifi_code_layout_step_one)
    TextInputLayout verifi_code_layout_step_one;

    private OnNextStepLinstener mOnNextStepListener;
    //手机号码通过
    private boolean phonePass ;
    //验证码通过，两者通过就，可以点击下一步
    private boolean codePass;

     Handler  mHandler = new Handler() {
        public void handleMessage(Message msg) {

            super.handleMessage(msg);
            int event = msg.arg1;
            int result = msg.arg2;
            Object data = msg.obj;
            Log.e("event", "event="+event);
            if (result == SMSSDK.RESULT_COMPLETE) {
                System.out.println("--------result"+event);
                //短信注册成功后，返回MainActivity,然后提示新好友
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {//提交验证码成功
                 //   Toast.makeText(g, "提交验证码成功", Toast.LENGTH_SHORT).show();

                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                    //已经验证
                 //   Toast.makeText(getContext(), "验证码已经发送", Toast.LENGTH_SHORT).show();


                }

            } else {
//				((Throwable) data).printStackTrace();
//				Toast.makeText(MainActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
//					Toast.makeText(MainActivity.this, "123", Toast.LENGTH_SHORT).show();
                int status = 0;
                try {
                    ((Throwable) data).printStackTrace();
                    Throwable throwable = (Throwable) data;

                    JSONObject object = new JSONObject(throwable.getMessage());
                    String des = object.optString("detail");
                    status = object.optInt("status");
                    if (!TextUtils.isEmpty(des)) {
                      //  Toast.makeText(getContext(), des, Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (Exception e) {
                    SMSLog.getInstance().w(e);
                }
            }


        };
    };

    TextWatcher mPhoneWatcher = new TextWatcher() {
        private CharSequence temp;
        private int editStart;
        private int editEnd;

        @Override
        public void beforeTextChanged(CharSequence s, int arg1, int arg2, int arg3) {
            temp = s;
        }

        @Override
        public void onTextChanged(CharSequence s, int arg1, int arg2, int arg3) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            editStart = et_user_name_one.getSelectionStart();
            editEnd = et_user_name_one.getSelectionEnd();
            if (temp.length() > 11) {
                s.delete(editStart - 1, editEnd);
                int tempSelection = editStart;
                et_user_name_one.setText(s);
                et_user_name_one.setSelection(tempSelection);
            }
            if (temp.length() == 11) {
                if (RegexUtils.checkMobile(temp.toString())) {
                    phonePass = true;
                    user_layout_step_one.setErrorEnabled(false);
                    bt_get_code_one.setEnabled(true);
                    bt_get_code_one.setTextColor(ResourcesUtils.getResColor(getContext(), R.color.textActionBarTile));
                }else{
                    phonePass = false;
                    user_layout_step_one.setErrorEnabled(true);
                    user_layout_step_one.setError(ResourcesUtils.getResString(getContext(),R.string.frag_regis_error_phone));
                }
            } else {
                phonePass = false;
                user_layout_step_one.setErrorEnabled(false);
                bt_get_code_one.setEnabled(false);
                bt_get_code_one.setTextColor(ResourcesUtils.getResColor(getContext(), R.color.colorPrimaryDark));
            }
        }
    };

    //点击下一步的，调用activity 的方法
    public interface OnNextStepLinstener {
        void onNextStep();
    }

    //获取验证码
    @OnClick(R.id.bt_get_code_one)
    public void getVifiCode(){
        if (NetUtils.isNetWork(getContext())){
            SMSSDK.getVerificationCode(Constants.CON_COUTRY,et_user_name_one.getText().toString());
        }
    }



    public FragRegisterStepOne() {

    }

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_register_step_one, container, false);
        ButterKnife.bind(this, rootView);
        et_user_name_one.addTextChangedListener(mPhoneWatcher);
        SMSSDK.initSDK(getActivity(), Constants.CON_APPKEY,Constants.CON_APPSECRET);
        EventHandler eh=new EventHandler(){
            @Override
            public void afterEvent(int event, int result, Object data) {
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
               mHandler.sendMessage(msg);
            }

        };
        SMSSDK.registerEventHandler(eh);

        return rootView;
    }

    @OnClick(R.id.bt_register_next)
    public void regsNextStep() {
        if (mOnNextStepListener != null) {
            mOnNextStepListener.onNextStep();
        }
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnNextStepListener = (OnNextStepLinstener) context;
        } catch (ClassCastException castException) {
            /** The activity does not implement the listener. */
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setDefaultFragmentTitle(String title) {

    }

    @Override
    public void onDestroy() {
        SMSSDK.unregisterAllEventHandler();
        super.onDestroy();
    }
}
