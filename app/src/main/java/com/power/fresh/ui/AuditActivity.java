package com.power.fresh.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.chen.concise.RxHttp;
import com.power.common_opensurce.App;
import com.power.fresh.R;
import com.power.fresh.api.FreshService;
import com.powerrich.common.base.BaseObserver;
import com.powerrich.common.base.UIActivity;
import com.powerrich.common.image.GlideLoad;
import com.powerrich.common.widget.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 审核
 */
public class AuditActivity extends UIActivity {


    @BindView(R.id.iv_type)
    ImageView ivType;
    @BindView(R.id.tv_desc)
    TextView tvDesc;
    @BindView(R.id.tv_desc_true)
    TextView tvDescTrue;
    @BindView(R.id.mTitleBar)
    TitleBar mTitleBar;
    private int mType;

    private int mStatusBarColor;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, AuditActivity.class);
        intent.putExtra("TYPE", App.getUserInfoCc().getAuthStatus());
        context.startActivity(intent);
    }

    @Override
    protected int statusBarColor() {
        return mStatusBarColor;
    }

    @Override
    protected int getLayoutId() {
        mType = getIntent().getIntExtra("TYPE", 0);

        if (mType == 10||mType == 20||mType == 30) {
            mStatusBarColor=R.color.color_FFA43A;
        } else if (mType == 11||mType == 21||mType == 31) {
            mStatusBarColor=R.color.color_399BFF;
        } else if (mType == 12||mType == 22||mType == 32) {
            mStatusBarColor=R.color.color_FF5A3A;
        } else if (mType == 13||mType == 23||mType == 33) {
            mStatusBarColor=R.color.color_FFA43A;
        } else {
            mStatusBarColor = R.color.color_399BFF;
        }


        return R.layout.activity_audit;
    }

    @Override
    protected void initView() {

     /*   10商家认证中
        11商家成功
        12商家失败
        13商家信息更替
        20供应商
        21
        22
        23
        30配送员
        31
        32
        33
        1 状态初始化未认证*/

        if (mType == 10||mType == 20||mType == 30) {
            mTitleBar.setTitleBarBackgroundColor(ContextCompat.getColor(this,R.color.color_FFA43A));
            GlideLoad.loadImage(ivType, R.mipmap.shenhe_shz);
            tvDesc.setText("提交成功，请等待审核");
            tvDescTrue.setText("预计1个工作日内审核完毕，以短信形式通知您");
        } else if (mType == 11||mType == 21||mType == 31) {
            mTitleBar.setTitleBarBackgroundColor(ContextCompat.getColor(this,R.color.color_399BFF));
            tvDesc.setText("恭喜您审核通过");
            tvDescTrue.setText("请遵守《宁将生鲜服务协议》");
            GlideLoad.loadImage(ivType, R.mipmap.shenhe_cg);
        } else if (mType == 12||mType == 22||mType == 32) {
            mTitleBar.setTitleBarBackgroundColor(ContextCompat.getColor(this,R.color.color_FF5A3A));
            tvDesc.setText("抱歉！您的审核未通过");
            tvDescTrue.setText("抱歉，您的资料信息存在不符，请重新审核后再申请");
            GlideLoad.loadImage(ivType, R.mipmap.shenhe_sb);

        }else if (mType == 13||mType == 23||mType == 33) {
            mTitleBar.setTitleBarBackgroundColor(ContextCompat.getColor(this,R.color.color_FFA43A));
            GlideLoad.loadImage(ivType, R.mipmap.shenhe_shz);      tvDesc.setText("更替信息已提交，请等待审核..");
            tvDescTrue.setText("预计1个工作日内审核完毕，以短信形式通知您");
        } else {
            mTitleBar.setTitleBarBackgroundColor(ContextCompat.getColor(this,R.color.color_399BFF));
            tvDesc.setText("恭喜您审核通过");
            tvDescTrue.setText("请遵守《宁将生鲜服务协议》");
            GlideLoad.loadImage(ivType, R.mipmap.shenhe_cg);
        }

    }

    @Override
    protected void initData() {

    }

    private  void cancel() {

        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userAuthCancel())
                .subscribe(new BaseObserver<Object>() {
                    @Override
                    public void onSuccess(Object it) {



                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        finish();
    }
}
