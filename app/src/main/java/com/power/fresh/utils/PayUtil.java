package com.power.fresh.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;

import com.alipay.sdk.app.PayTask;
import com.power.common_opensurce.App;
import com.power.fresh.bean.alipay.AlipayResponse;
import com.power.fresh.bean.alipay.PayResult;
import com.power.fresh.bean.reponse.PayOrderReponse;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.Map;

/**
 * @author AlienChao
 * @date 2020/06/01 11:50
 */
public class PayUtil {


    private IPayStatusListenr mIPayStatusListenr;
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    public void setIPayStatusListenr(IPayStatusListenr IPayStatusListenr) {
        mIPayStatusListenr = IPayStatusListenr;
    }

    public static final PayUtil getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final PayUtil INSTANCE = new PayUtil();
    }

    private PayUtil() {

    }


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            PayResult result = new PayResult((Map<String, String>) msg.obj);

            if (!TextUtils.isEmpty(result.getResult())) {
                AlipayResponse alipayResponse = App.getmGson().fromJson(result.getResult(), AlipayResponse.class);
                if (mIPayStatusListenr != null) {
                    mIPayStatusListenr.onSuccess("");
                }
            }
            Log.e("jsc", "ConfirmOrderActivity-handleMessage:" + result.getResult());

        }

        ;
    };


    public void payAliStart(FragmentActivity context, String orderInfo, IPayStatusListenr iPayStatusListenr) {
        // orderInfo 订单信息
        this.setIPayStatusListenr(iPayStatusListenr);

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(context);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }


    /**
     * 回调在 WXPayEntryActivity
     */
    public void payWXStart(Context context, PayOrderReponse it) {
        IWXAPI api = WXAPIFactory.createWXAPI(context, App.APP_ID);
        api.registerApp(App.APP_ID);
        PayReq request = new PayReq();
        request.appId = it.getAppid();
        request.partnerId = it.getPartnerid();
        request.prepayId = it.getPrepayid();
        request.packageValue = it.getPackageX();
        request.nonceStr = it.getNoncestr();
        request.timeStamp = it.getTimestamp();
        request.sign = it.getSign();
        api.sendReq(request);
    }

    public interface IPayStatusListenr<T> {
        void onSuccess(T t);
    }

}
