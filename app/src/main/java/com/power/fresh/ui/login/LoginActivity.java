package com.power.fresh.ui.login;

import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.chen.concise.RxHttp;
import com.hjq.toast.ToastUtils;
import com.power.common_opensurce.App;
import com.power.common_opensurce.UserInfo;
import com.power.fresh.MainActivity;
import com.power.fresh.R;
import com.power.fresh.api.FreshService;
import com.power.fresh.utils.AccountValidatorUtil;
import com.power.fresh.utils.dialog.Protocol_Dialog;
import com.power.fresh.widget.CountdownView;
import com.power.fresh.widget.DeletableEditText;
import com.power.fresh.widget.PasswordEditText;
import com.powerrich.common.base.BaseObserver;
import com.powerrich.common.base.UIActivity;
import com.powerrich.common.helper.SPUtils;
import com.powerrich.common.permission.CcPermissions;
import com.powerrich.common.permission.Consumer;
import com.powerrich.common.permission.PermissionName;
import com.tencent.mmkv.MMKV;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

public class LoginActivity extends UIActivity {

    @BindView(R.id.et_account)
    DeletableEditText etAccount;
    @BindView(R.id.et_pwd)
    PasswordEditText etPwd;
    @BindView(R.id.tv_user_pwd)
    TextView tvUserPwd;
    @BindView(R.id.tv_forget)
    TextView tvForget;
    @BindView(R.id.tv_not_code)
    TextView tvNotCode;
    @BindView(R.id.tv_send_code)
    CountdownView tvSendCode;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.cb_protocol)
    CheckBox cbProtocol;

    /**
     * 是否为验证码登录
     */
    private boolean isSms = true;
    private String mRegistrationID="";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        //15995921097  111111 供应商
        //18458794212 123456  经销商
        //16225790052 123456 配送员
        //17762560406 111111 普通用户

        开启密码登录();

        etAccount.setText("18458794212");
        etPwd.setText("123456");


        if (!MMKV.defaultMMKV().decodeBool("firstStart",true)) {
            cbProtocol.setChecked(true);
        }

//        Button btnLogin = findViewById(R.id.btn_login);
//
//        DisplayMetrics dm = getResources().getDisplayMetrics();
//        int screenWidth = dm.widthPixels;
//
//        int screenHeight = dm.heightPixels;
//
//        int dens=dm.densityDpi;
//        btnLogin.setText(screenWidth+"-"+screenHeight+"-dens:"+dens);



        //JPushInterface.stopPush(getApplicationContext());

    }

    @Override
    public boolean statusBarDarkFont() {
        return false;
    }

    @Override
    protected void initData() {
        String userinfo = (String) SPUtils.get("USERINFO", "");
        if (!TextUtils.isEmpty(userinfo)) {
            startActivity(MainActivity.class);
            finish();
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

    private void requestLogin() {
        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).signIn(mRegistrationID, etAccount.getText().toString(), etPwd.getText().toString(), 3, 1))
                .subscribe(new BaseObserver<UserInfo>() {
                    @Override
                    public void onSuccess(UserInfo s) {
                        MMKV.defaultMMKV().encode("firstStart",false);
                        SPUtils.put("token", s.getTokenId());
                        SPUtils.put("USERINFO", App.getmGson().toJson(s));

                        ToastUtils.show("登录成功");
                        startActivity(MainActivity.class);
                        finish();

                    }
                });
    }

    private void requestSmsLogin() {

        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).smsLogin(mRegistrationID, etAccount.getText().toString(), etPwd.getText().toString()))
                .subscribe(new BaseObserver<UserInfo>() {
                    @Override
                    public void onSuccess(UserInfo s) {
                        MMKV.defaultMMKV().encode("firstStart",false);

                        SPUtils.put("token", s.getTokenId());
                        SPUtils.put("USERINFO", App.getmGson().toJson(s));
                        ToastUtils.show("登录成功");
                        startActivity(MainActivity.class);
                        finish();
                    }
                });
    }


    @OnClick({R.id.tv_ysxy, R.id.btn_login, R.id.tv_forget, R.id.tv_user_pwd, R.id.tv_register, R.id.tv_send_code})
    public void onViewClicked(View view) {

        CcPermissions.with(this).permission(PermissionName.READ_PHONE_STATE)
                .request(new Consumer() {
                    @Override
                    public void accept(List<String> granted, boolean isAll) {

                        switch (view.getId()) {
                            case R.id.tv_ysxy:

                                Protocol_Dialog updateDialog = new Protocol_Dialog(getActivity());
                                updateDialog.setOnListener(new Protocol_Dialog.OnListener() {
                                    @Override
                                    public void onConfirm() {

                                    }
                                });
                                updateDialog.show();

                                break;
                            case R.id.btn_login:

                                mRegistrationID = JPushInterface.getRegistrationID(getActivity());


                                if (!TextUtils.isEmpty(mRegistrationID)) {

                                    if (!TextUtils.isEmpty(etAccount.getText().toString()) && !TextUtils.isEmpty(etPwd.getText().toString())) {
                                        if (cbProtocol.isChecked()) {
                                            if (isSms) {
                                                requestSmsLogin();
                                            } else {
                                                requestLogin();
                                            }
                                        }else{
                                            ToastUtils.show("请勾选《隐私政策和用户协议》");
                                        }
                                    } else {
                                        //120c83f760a2deb82ec
                                        ToastUtils.show("账号或密码不能为空");
                                    }
                                } else {

                                    ToastUtils.show("推送初始化中..");

                                }
                                break;
                            case R.id.tv_forget:
                                startActivity(ForgetActivity.class);
                                break;
                            case R.id.tv_user_pwd:
                                /** 切换账号登录 */
                                if (tvUserPwd.getText().toString().equals("密码登录")) {

                                    开启密码登录();
                                } else {
                                    /** 切换手机号登录 */
                                    开启验证码登录();
                                }

                                break;
                            case R.id.tv_register:
                                startActivity(RegisterActivity.class);
                                break;
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
                        }
                    }
                });

    }


    private void 开启密码登录() {
        isSms = false;
        etAccount.setHint(getString(R.string.please_input_your_accout));
        etPwd.setHint(getString(R.string.please_input_your_pwd));
        etAccount.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        tvForget.setVisibility(View.VISIBLE);
        tvNotCode.setVisibility(View.GONE);
        tvSendCode.setVisibility(View.GONE);
        tvUserPwd.setText("验证码登录");
    }

    private void 开启验证码登录() {
        isSms = true;
        etAccount.setHint(getString(R.string.please_input_your_phone));
        etPwd.setHint(getString(R.string.please_input_your_authCode));
        tvForget.setVisibility(View.GONE);
        tvNotCode.setVisibility(View.VISIBLE);
        tvSendCode.setVisibility(View.VISIBLE);
        etAccount.setInputType(InputType.TYPE_CLASS_NUMBER);
        tvUserPwd.setText("密码登录");
    }



}
