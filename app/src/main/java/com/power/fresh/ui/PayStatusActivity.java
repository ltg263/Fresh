package com.power.fresh.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.noober.background.view.BLTextView;
import com.power.common_opensurce.dialog.EvaluationDialog;
import com.power.fresh.MainActivity;
import com.power.fresh.R;
import com.power.fresh.ui.order.OrderDetailsActivity;
import com.power.fresh.utils.Constant;
import com.powerrich.common.base.BaseDialog;
import com.powerrich.common.base.UIActivity;
import com.powerrich.common.helper.ActivityStackManager;
import com.powerrich.common.image.GlideLoad;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 支付结果状态 成功、 失败
 */
public class PayStatusActivity extends UIActivity {

    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.tv_info)
    TextView tvInfo;
    @BindView(R.id.tv_look_order)
    BLTextView tvLookOrder;

    @Override
    public void onLeftClick() {
        ActivityStackManager.getInstance().finishAllActivities(MainActivity.class);
    }

    /**
     * 0:成功 1：失败
     */
    private int mType;

    /**
     * 订单id
     */
    private int mOderid;

    public static void startActivity(Context context, int orderId, int type) {
        Intent intent = new Intent(context, PayStatusActivity.class);
        intent.putExtra("Type", type);
        intent.putExtra("OrderId", orderId);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pay_status;
    }

    @Override
    protected void initView() {
        mType = getIntent().getIntExtra("Type", 0);
        mOderid = getIntent().getIntExtra("OrderId", 0);
        if (mType == 0) {
            GlideLoad.loadImage(ivIcon, R.mipmap.order_wancheng);
            tvStatus.setText("支付成功");
            tvInfo.setText("您的订单已支付成功");
        } else {
            tvLookOrder.setText("继续支付");
            GlideLoad.loadImage(ivIcon, R.mipmap.order_wancheng);
            tvStatus.setText("支付失败");
            tvInfo.setText("您的订单支付失败，请重新支付");
        }

    }

    @Override
    protected void initData() {

    }



    @OnClick({R.id.btn_go_home, R.id.tv_look_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_go_home:

                /** 支付失败 */
                if (mType == 1) {
                    exitHome();
                }else{
                    Constant.MainIsFirst =true;
                    ActivityStackManager.getInstance().finishAllActivities(MainActivity.class);
                    /** 直接跳转到首页，并去掉中间已经打开的Activity */
                }


                break;
            case R.id.tv_look_order:
                /** 支付成功才跳转到详情 */
                if (mType == 0) {
                    ActivityStackManager.getInstance().finishAllActivities(MainActivity.class);
                    OrderDetailsActivity.startActivity(getActivity(), mOderid);
                }
                finish();
                break;
        }
    }

    /**
     * 返回首页
     */
    private void exitHome() {
        BaseDialog baseDialog = new EvaluationDialog.Builder(this).setMessage("订单支付失败，确认返回吗？").setConfirm("确定").setCancel("取消").setListener(new EvaluationDialog.OnListener() {

            @Override
            public void onConfirm(Dialog dialog) {
                Constant.MainIsFirst =true;
                ActivityStackManager.getInstance().finishAllActivities(MainActivity.class);
            }

        }).create();
        baseDialog.show();
    }
}
