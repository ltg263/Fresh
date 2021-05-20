package com.power.fresh.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.chen.concise.RxHttp;
import com.hjq.toast.ToastUtils;
import com.power.fresh.R;
import com.power.fresh.api.FreshService;
import com.power.fresh.utils.AccountValidatorUtil;
import com.power.fresh.widget.CountdownView;
import com.power.fresh.widget.DeletableEditText;
import com.power.fresh.widget.PasswordEditText;
import com.powerrich.common.base.BaseObserver;
import com.powerrich.common.base.UIActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 忘记密码  + 修改密码
 */
public class ForgetActivity extends UIActivity {


    @BindView(R.id.et_account)
    DeletableEditText etAccount;
    @BindView(R.id.tv_send_code)
    CountdownView tvSendCode;
    @BindView(R.id.et_captcha)
    DeletableEditText etCaptcha;
    @BindView(R.id.et_pwd)
    PasswordEditText etPwd;
    @BindView(R.id.tv_finish)
    TextView tvFinish;
    @BindView(R.id.tv_not_code)
    TextView tvNotCode;
    private int mIntentType;


    public static void startActivity(Context context, int data) {
        Intent intent = new Intent(context, ForgetActivity.class);
        intent.putExtra("intentType", data);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_forget;
    }

    @Override
    protected void initView() {
        mIntentType = getIntent().getIntExtra("intentType", 0);
        if (mIntentType == 1) {
            tvFinish.setVisibility(View.VISIBLE);
            tvSendCode.setVisibility(View.GONE);
            tvNotCode.setVisibility(View.GONE);
            etAccount.setHint("请输入旧密码");
            etAccount.setTransformationMethod(PasswordTransformationMethod.getInstance());
            etCaptcha.setHint("请输入新密码");
            etCaptcha.setTransformationMethod(PasswordTransformationMethod.getInstance());
            etPwd.setHint("请再次输入新密码");
            tvFinish.setOnClickListener(v->finish());
        }

    }

    @Override
    protected void initData() {

    }

    @Override
    public boolean statusBarDarkFont() {
        return false;
    }


    @OnClick({R.id.tv_send_code, R.id.tv_not_code, R.id.btn_forget})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_send_code:
                sendCode();
                break;
            case R.id.tv_not_code:
                break;
            case R.id.btn_forget:
                if (mIntentType == 1) {
                    setting();
                } else {
                    forget();
                }
                break;

        }
    }

    private void setting() {
        if (isEmpty()) {
            return;
        }
        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userChangePwd(etAccount.getText().toString(), etPwd.getText().toString()))
                .subscribe(new BaseObserver<Integer>() {
                    @Override
                    public void onSuccess(Integer s) {
                        ToastUtils.show("设置成功！");
                        finish();
                    }
                });
    }

    private boolean isEmpty() {
        boolean flag = true;
        if (TextUtils.isEmpty(etAccount.getText().toString())) {
            ToastUtils.show("请输入旧密码");
        } else if (TextUtils.isEmpty(etCaptcha.getText().toString())) {
            ToastUtils.show("请输入新密码");
        } else if (TextUtils.isEmpty(etPwd.getText().toString())) {
            ToastUtils.show("请输入再次新密码");
        } else if (!etCaptcha.getText().toString().equals(etPwd.getText().toString())) {
            ToastUtils.show("两次密码不一致");
        } else {
            flag = false;
        }
        return flag;
    }


    private void forget() {
        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).forgetPwd(etAccount.getText().toString(), etCaptcha.getText().toString(), etPwd.getText().toString()))
                .subscribe(new BaseObserver<Integer>() {
                    @Override
                    public void onSuccess(Integer s) {
                        ToastUtils.show("设置成功！");
                        finish();
                    }
                });
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

                            exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userVerifyGetVerifyCode(etAccount.getText().toString()))
                                    .subscribe(new BaseObserver<Object>() {
                                        @Override
                                        public void onSuccess(Object s) {

                                            tvSendCode.start();

                                        }
                                    });

                        } else {
                            ToastUtils.show("账号未注册");
                        }
                    }
                });
    }

    private void sendCode() {
        if (!TextUtils.isEmpty(etAccount.getText().toString())) {
            if (AccountValidatorUtil.isMobile(etAccount.getText().toString())) {
                checkUser();
            } else {
                ToastUtils.show("请输入正确格式手机号");
            }

        } else {
            ToastUtils.show("请输入手机号");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }
}
