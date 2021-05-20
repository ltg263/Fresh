package com.power.fresh.ui.login;

import android.text.TextUtils;
import android.view.View;

import com.chen.concise.RxHttp;
import com.hjq.toast.ToastUtils;
import com.power.common_opensurce.App;
import com.power.fresh.MainActivity;
import com.power.fresh.R;
import com.power.fresh.api.FreshService;
import com.power.common_opensurce.UserInfo;
import com.power.fresh.utils.AccountValidatorUtil;
import com.power.fresh.utils.SystemUtils;
import com.power.fresh.widget.CountdownView;
import com.power.fresh.widget.DeletableEditText;
import com.power.fresh.widget.PasswordEditText;
import com.powerrich.common.base.BaseObserver;
import com.powerrich.common.base.UIActivity;
import com.powerrich.common.helper.ActivityStackManager;
import com.powerrich.common.helper.SPUtils;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

public class RegisterActivity extends UIActivity {


    @BindView(R.id.et_account)
    DeletableEditText etAccount;
    @BindView(R.id.et_captcha)
    DeletableEditText etCaptcha;
    @BindView(R.id.et_pwd)
    PasswordEditText etPwd;
    @BindView(R.id.et_Referral)
    DeletableEditText etReferral;
    @BindView(R.id.tv_send_code)
    CountdownView tvSendCode;
    private String mRegistrationID;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }


//    @OnClick({R.id.btn_register, R.id.tv_go_login})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.btn_register:
//                break;
//            case R.id.tv_go_login:
//                finish();
//                break;
//        }
//    }

    @Override
    public boolean statusBarDarkFont() {
        return false;
    }


    @OnClick({R.id.tv_send_code, R.id.tv_not_code, R.id.btn_register, R.id.tv_go_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_send_code:

                if (!TextUtils.isEmpty(etAccount.getText().toString())) {
                    if (AccountValidatorUtil.isMobile(etAccount.getText().toString())) {
                        checkUser();
                    } else {
                        ToastUtils.show("请输入正确格式手机号");
                    }

                } else {
                    ToastUtils.show("请输入手机号");
                }

                break;
            case R.id.tv_not_code:
                ToastUtils.show("收不到验证码？");
                break;
            case R.id.btn_register:
                if (isNotEmpty()) {
                    requestRegister();
                }
                break;
            case R.id.tv_go_login:
                finish();
                break;
        }
    }


    /**
     * 账号检测
     */
    private void checkUser() {
        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).checkUser(etAccount.getText().toString()))
                .subscribe(new BaseObserver<Boolean>() {
                    @Override
                    public void onSuccess(Boolean s) {

                        if (s) {
                            ToastUtils.show("账号已注册");
                        } else {

                            exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userVerifyGetVerifyCode(etAccount.getText().toString()))
                                    .subscribe(new BaseObserver<Object>() {
                                        @Override
                                        public void onSuccess(Object s) {

                                            tvSendCode.start();

                                        }
                                    });

                        }


                    }
                });
    }


    private void requestRegister() {
        mRegistrationID = JPushInterface.getRegistrationID(getActivity());

        if (!TextUtils.isEmpty(mRegistrationID)) {
            exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).register(mRegistrationID, etAccount.getText().toString(), etPwd.getText().toString(), etCaptcha.getText().toString(), etReferral.getText().toString()))
                    .subscribe(new BaseObserver<UserInfo>() {
                        @Override
                        public void onSuccess(UserInfo s) {

                            SPUtils.put("token", s.getTokenId());
                            SPUtils.put("USERINFO", App.getmGson().toJson(s));
                            ToastUtils.show("注册成功");
                            ActivityStackManager.getInstance().finishActivitiesByClass(LoginActivity.class);
                            startActivity(MainActivity.class);
                            finish();


                        }
                    });
        } else {
            ToastUtils.show("推送初始化中..");
        }
    }


    private boolean isNotEmpty() {
        if (TextUtils.isEmpty(etAccount.getText().toString())) {
            ToastUtils.show("手机号不能为空");
            return false;
        } else if (TextUtils.isEmpty(etCaptcha.getText().toString())) {
            ToastUtils.show("验证码不能为空");
            return false;
        } else if (TextUtils.isEmpty(etPwd.getText().toString())) {
            ToastUtils.show("密码不能为空");
            return false;
        }

//        else if (TextUtils.isEmpty(etReferral.getText().toString())) {
//            ToastUtils.show("推荐码不能为空");
//            return false;
//        }
        return true;
    }


}
