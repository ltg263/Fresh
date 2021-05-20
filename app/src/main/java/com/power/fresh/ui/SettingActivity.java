package com.power.fresh.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.chen.concise.RxHttp;
import com.power.common_opensurce.dialog.EvaluationDialog;
import com.power.fresh.MainActivity;
import com.power.fresh.R;
import com.power.fresh.api.FreshService;
import com.power.fresh.bean.CommodityDetails;
import com.power.fresh.ui.login.ForgetActivity;
import com.power.fresh.ui.login.LoginActivity;
import com.power.fresh.utils.dialog.Protocol_Dialog;
import com.powerrich.common.base.BaseDialog;
import com.powerrich.common.base.BaseObserver;
import com.powerrich.common.base.UIActivity;
import com.powerrich.common.helper.ActivityStackManager;
import com.powerrich.common.helper.DataCleanManager;
import com.powerrich.common.helper.SPUtils;
import com.powerrich.common.permission.CcPermissions;
import com.powerrich.common.permission.Consumer;
import com.powerrich.common.permission.PermissionName;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

/**
 * 系统设置
 */
public class SettingActivity extends UIActivity {

    @BindView(R.id.tv_clear_value)
    TextView tvClearValue;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        tvClearValue.setText(DataCleanManager.getCacheSize(this));
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }

    @OnClick({R.id.tv_modify_pwd, R.id.tv_protocal, R.id.ll_clear, R.id.tv_about, R.id.btn_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_modify_pwd:

                ForgetActivity.startActivity(SettingActivity.this,1);


                break;
            case R.id.tv_protocal:
                Protocol_Dialog updateDialog = new Protocol_Dialog(getActivity());
                updateDialog.setOnListener(new Protocol_Dialog.OnListener() {
                    @Override
                    public void onConfirm() {

                    }
                });
                updateDialog.show();

                break;
            case R.id.ll_clear:
                DataCleanManager.cleanInternalCache(getActivity());
                tvClearValue.setText("0M");
                break;
            case R.id.tv_about:
                startActivity(AboutActivity.class);
                break;
            case R.id.btn_exit:
                exitLogin();
                break;
        }
    }


    private  void requestExit() {
        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userSignOut())
                .subscribe(new BaseObserver<Object>() {
                    @Override
                    public void onSuccess(Object it) {
                        SPUtils.clear();
                        JPushInterface.stopPush(SettingActivity.this);
                        startActivity(LoginActivity.class);
                        ActivityStackManager.getInstance().finishActivitiesByClass(MainActivity.class);
                        finish();
                    }
                });
    }


    private void exitLogin() {
        BaseDialog baseDialog = new EvaluationDialog.Builder(this).setMessage("确定退出吗?").setConfirm("确定").setCancel("取消").setListener(new EvaluationDialog.OnListener() {
            @Override
            public void onConfirm(Dialog dialog) {
                requestExit();
            }


        }).create();
        baseDialog.show();
    }
}
