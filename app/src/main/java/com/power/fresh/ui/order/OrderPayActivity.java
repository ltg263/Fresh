package com.power.fresh.ui.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.chen.concise.RxHttp;
import com.power.common_opensurce.App;
import com.power.fresh.R;
import com.power.fresh.api.FreshService;
import com.power.fresh.bean.reponse.PayOrderReponse;
import com.power.fresh.utils.Constant;
import com.powerrich.common.base.BaseObserver;
import com.powerrich.common.base.UIActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 订单支付
 */
public class OrderPayActivity extends UIActivity {


    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.rb_yefk)
    TextView rbYefk;
    @BindView(R.id.tv_title_status)
    TextView tvTitleStatus;

    private int mOrderId;


    public static void startActivity(Context context, int orderId, String money) {
        Intent intent = new Intent(context, OrderPayActivity.class);
        intent.putExtra("orderId", orderId);
        intent.putExtra("money", money);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_pay;
    }

    @Override
    protected void initView() {
        String money = getIntent().getStringExtra("money");
        tvPrice.setText(money);

        mOrderId = getIntent().getIntExtra("orderId", 0);
        if (App.getUserInfoCc().getLogPort() == Constant.用户登陆) {
            rbYefk.setVisibility(View.GONE);
        }


    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.rb_wx, R.id.rb_zfb, R.id.rb_yefk})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_wx:
                Constant.get().payOrder(getActivity(), mOrderId, 1, 1);
                break;
            case R.id.rb_zfb:

                Constant.get().payOrder(getActivity(), mOrderId, 1, 2);
                break;
            case R.id.rb_yefk:
                Constant.get().payOrder(getActivity(), mOrderId, 1, 3);

                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }


    //    /**
//     * @param orderId
//     * @param orderType    1支付商品 2退款
//     * @param payType      1微信 2支付宝 3余额(商家)
//     * @param subOrderType APP(APP支付)  JSAPI(小程序支付)  BALANCE 余额支付
//     */
    private void payOrder(int orderId, int orderType, int payType) {
        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userPayOrder(orderId, orderType, payType, "APP"))
                .subscribe(new BaseObserver<PayOrderReponse>() {
                    @Override
                    public void onSuccess(PayOrderReponse it) {

                    }
                });
    }

}
