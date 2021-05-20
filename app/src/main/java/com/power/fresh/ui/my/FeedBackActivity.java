package com.power.fresh.ui.my;

import android.os.Bundle;
import android.text.TextUtils;

import com.chen.concise.RxHttp;
import com.hjq.toast.ToastUtils;
import com.noober.background.view.BLEditText;
import com.power.fresh.R;
import com.power.fresh.api.FreshService;
import com.powerrich.common.base.BaseObserver;
import com.powerrich.common.base.UIActivity;
import com.powerrich.common.widget.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 意见反馈
 */
public class FeedBackActivity extends UIActivity {

    @BindView(R.id.et_content)
    BLEditText etContent;
    @BindView(R.id.et_mobile)
    BLEditText etMobile;
    @BindView(R.id.mTitleBar)
    TitleBar mTitleBar;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_feed_back;
    }

    @Override
    protected void initView() {
        mTitleBar.setOnTitleBarListener(new TitleBar.OnTitleBarListener() {
            @Override
            public void onLeftClick() {
                finish();
            }

            @Override
            public void onRightClick() {

            }
        });

    }

    @Override
    protected int getTitleId() {
        return -1;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }

    @OnClick(R.id.btn_commit)
    public void onViewClicked() {

        if (TextUtils.isEmpty(etContent.getText().toString())) {
            ToastUtils.show("请输入内容");
            return;
        }
        if (TextUtils.isEmpty(etMobile.getText().toString())) {
            ToastUtils.show("请输入联系方式");
            return;
        }


        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userFeedbackSsave(etMobile.getText().toString(), etContent.getText().toString()))
                .subscribe(new BaseObserver<Object>() {
                    @Override
                    public void onSuccess(Object it) {
                        ToastUtils.show("提交成功");
                        finish();
                    }
                });

    }
}
