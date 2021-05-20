package com.power.fresh.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.chen.concise.Response;
import com.chen.concise.RxHttp;
import com.hjq.toast.ToastUtils;
import com.power.common_opensurce.mode.CommonRequest;
import com.power.fresh.api.FreshService;
import com.power.fresh.api.RxApi;
import com.power.fresh.bean.alipay.PayQueryBean;
import com.power.fresh.bean.home.HomeGouponBean;
import com.power.fresh.bean.reponse.PayOrderReponse;
import com.power.fresh.bean.shoppingcar.SaveCartResponse;
import com.power.fresh.ui.EvaluationActivity;
import com.power.fresh.ui.PayStatusActivity;
import com.power.fresh.ui.WebViewActivity;
import com.power.fresh.ui.business.DeliveryManManageActivity;
import com.powerrich.common.base.BaseActivity;
import com.powerrich.common.base.BaseDialog;
import com.powerrich.common.base.BaseObserver;
import com.powerrich.common.dialog.WaitDialog;
import com.powerrich.common.helper.SPUtils;
import com.powerrich.common.other.RxSchedulers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static android.app.Activity.RESULT_OK;

/**
 * @author AlienChao
 * @date 2020/04/18 20:57
 */
public class Constant {

    public static final String NEWHAND_FLAG = "NEWHAND_FLAG";


    /**
     * 是否需要回到首页
     */
    public static boolean MainIsFirst = false;

    /**
     * 微信支付是否成功
     */
    public static boolean WX_PAY = false;


    int[] business = {10, 11, 12, 13};
    int[] supplier = {20, 21, 22, 23};
    int[] delivery = {30, 31, 32, 33};


    HashMap<String, int[]> mAuthHashMap = new HashMap<>();


    /**
     * 拨打电话（跳转到拨号界面，用户手动点击拨打）
     *
     * @param phoneNum 电话号码
     */
    public void callPhone(FragmentActivity fragmentActivity, String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        fragmentActivity.startActivity(intent);
    }

    /**
     * 得到角色名称
     */
    private String getRoleName(int mAuthStatus) {

        if (mAuthHashMap.size() == 0) {
            mAuthHashMap.put("商家", business);
            mAuthHashMap.put("供应商", supplier);
            mAuthHashMap.put("配送员", delivery);
        }


        for (Map.Entry<String, int[]> entry : mAuthHashMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + " Value: " + entry.getValue());
            if (Arrays.asList(entry.getValue()).contains(mAuthStatus)) {
                return entry.getKey();
            }
        }

        return "用户";

    }


    public static final int 用户登陆 = 1;
    public static final int 经销商登陆 = 2;
    public static final int 供应商登陆 = 3;
    public static final int 配送员登陆 = 4;


    public static final int 用户订单 = 0;
    /** 只在DefaultUserFragment 使用 */
    public static final int 用户订单2 = 10;
    public static final int 销售订单 = 0;
    public static final int 配送员订单 = 11;
    public static final int 采购订单 = 2;


    public static final String ￥ = "￥";
    public static final String x = "x";

    public static final String LNG = SPUtils.LNG;
    public static final String LAT = SPUtils.LAT;
    public static final String CITYCODE = SPUtils.CITYCODE;
    public static final String CITYENTITY = SPUtils.CITYENTITY;
    /**
     * 全部
     */
    public static final int order_采购订单_全部 = 0;
    /**
     * 待付款
     */
    public static final int order_采购订单_待付款 = 1;
    /**
     * 待发货
     */
    public static final int order_采购订单_待发货 = 2;
    /**
     * 待收货
     */
    public static final int order_采购订单_待收货 = 3;

    /**
     * 待评价
     */
    public static final int order_采购订单_待评价 = 4;


    public static final Constant get() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final Constant INSTANCE = new Constant();
    }

    private Constant() {

    }

    public interface IListener<T> {
        void onSuccess(T t);
    }


    /**
     * 确认发货
     */
    public void showPopCommit发货(BaseActivity baseActivity, int orderId, IListener<Object> iListener) {

        CommonRequest commonRequest = new CommonRequest(baseActivity);
        commonRequest.showPop(orderId, new CommonRequest.IListener<Object>() {

            @Override
            public void startDeliveryManManage() {
                Intent intent = new Intent(baseActivity, DeliveryManManageActivity.class);
                intent.putExtra("Title", "配送员选择");
                intent.putExtra("OrderId", orderId);
                baseActivity.startActivityForResult(intent, new BaseActivity.ActivityCallback() {
                    @Override
                    public void onActivityResult(int resultCode, @Nullable Intent intent) {
                        if (resultCode == RESULT_OK) {
                            //   reFreshData();
                            iListener.onSuccess(null);
                        }


                    }
                });

            }

            @Override
            public void onSuccess(Object o) {
                //   reFreshData();
                iListener.onSuccess(null);
            }
        });

    }


    public String encryptPhoneNum(String phoneNum) {
        if (phoneNum.length() == 11) {
            String maskNumber = phoneNum.substring(0, 3) + "****" + phoneNum.substring(7, phoneNum.length());
            return maskNumber;
        } else {
            return phoneNum;
        }
    }


    public boolean copy(FragmentActivity fragmentActivity, String copyStr) {
        try {
            //获取剪贴板管理器
            ClipboardManager cm = (ClipboardManager) fragmentActivity.getSystemService(Context.CLIPBOARD_SERVICE);
            // 创建普通字符型ClipData
            ClipData mClipData = ClipData.newPlainText("Label", copyStr);
            // 将ClipData内容放到系统剪贴板里。
            cm.setPrimaryClip(mClipData);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * 这是购买订单 采购订单
     */
    public static String canvertOrderStatus(int orderStatus) {
        String orderStatusText;
        if (orderStatus == 1) {
            orderStatusText = "待支付";
        } else if (orderStatus == 2) {
            orderStatusText = "待发货";
        } else if (orderStatus == 3) {
            orderStatusText = "待评价";
        } else if (orderStatus == 4) {
            orderStatusText = "已完成";
        } else if (orderStatus == 5) {
            orderStatusText = "已取消";
        } else if (orderStatus == 6) {
            orderStatusText = "待收货";
        } else {
            orderStatusText = "- -";
        }
        return orderStatusText;
    }


    /**
     * 1,未完成;2,待收货(已付款);3,待评价（到货）；4完成（流程结束）；5，已取消；6，已发货
     */
    public static String canvertOrderStatusDesc(int orderStatus) {
        String orderStatusText;
        if (orderStatus == 1) {
            orderStatusText = "请在$s前支付，逾期未支付订单将自动取消！";
        } else if (orderStatus == 2) {
            orderStatusText = "待收货";
        } else if (orderStatus == 3) {
            orderStatusText = "待评价";
        } else if (orderStatus == 4) {
            orderStatusText = "已完成";
        } else if (orderStatus == 5) {
            orderStatusText = "订单已取消，欢迎继续购买";
        } else if (orderStatus == 6) {
            orderStatusText = "已发货";
        } else {
            orderStatusText = "- -";
        }
        return orderStatusText;
    }


    /**
     * 保留两位小数
     *
     * @param value
     * @return
     */
    public String canvetDouble(double value) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.toString();
    }


    /**
     * 购物车添加
     *
     * @param distributorId 商家id
     * @param commodityId   商品id
     * @param normsId       规格id
     * @param num
     */
    public void saveCart(int distributorId, int commodityId, int normsId, Integer num, boolean isToast, IListener<SaveCartResponse> saveCartResponseIListener) {
        RxHttp.getRetrofitService(FreshService.class)
                .userCartSave(distributorId, commodityId, normsId, num)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<SaveCartResponse>() {

                    @Override
                    public void onSuccess(SaveCartResponse siteBeans) {

                        if (isToast) {
                            ToastUtils.show("操作成功");
                        }

                        if (saveCartResponseIListener != null) {
                            saveCartResponseIListener.onSuccess(siteBeans);
                        }

                    }

                });
    }


//    /**
//     * 获取优惠券
//     */
//    public void getCouponList(IListener<HomeGouponBean> iListener) {
//
//        RxHttp.getRetrofitService(FreshService.class)
//                .homeCouponLis()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new BaseObserver<HomeGouponBean>() {
//
//                    @Override
//                    public void onSuccess(HomeGouponBean siteBeans) {
//                        iListener.onSuccess(siteBeans);
//                    }
//
//                });
//
//    }

    /**
     * 1.7.2 优惠券查询
     */
    public void getUserCouponList(int page, int pageSize, IListener<HomeGouponBean> iListener) {

        RxHttp.getRetrofitService(FreshService.class)
                .userCouponList(page, pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<HomeGouponBean>() {

                    @Override
                    public void onSuccess(HomeGouponBean siteBeans) {
                        iListener.onSuccess(siteBeans);
                    }

                });

    }


    /**
     * //1.23.5 订单分配
     */
    public void userBusinessOeliveryOrderSending(int deliveryId, int orderId, IListener iListener) {


        RxHttp.getRetrofitService(FreshService.class)
                .userBusinessOeliveryOrderSending(deliveryId, orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<Object>() {

                    @Override
                    public void onSuccess(Object siteBeans) {
                        if (iListener != null) {
                            iListener.onSuccess(null);
                        }
                    }

                });

    }


    /**
     * 1.17.9 订单删除
     */
    public void userOrderRemove(int orderId, IListener<Object> iListener) {

        RxHttp.getRetrofitService(FreshService.class)
                .userOrderRemove(orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<Object>() {

                    @Override
                    public void onSuccess(Object siteBeans) {
                        if (iListener != null) {
                            iListener.onSuccess(siteBeans);
                        }
                    }

                });

    }


    /**
     * 1.7.1 优惠券领取
     */
    public void userCouponSave(ArrayList<Integer> couponIds, IListener<Object> iListener) {

        RxHttp.getRetrofitService(FreshService.class)
                .userCouponSave(couponIds)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<Object>() {

                    @Override
                    public void onSuccess(Object siteBeans) {
                        if (iListener != null) {
                            iListener.onSuccess(siteBeans);
                        }
                    }

                });

    }




    /**
     * 1.7.1购物车修改数量
     */
    public void requestCartUpdateNum(int cardId, int num, IListener<Object> iListener) {




        RxHttp.getRetrofitService(FreshService.class)
                .userCartUpdateNum(cardId, num)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<String>() {

                    @Override
                    public void onSuccess(String siteBeans) {
                        if (iListener != null) {
                            iListener.onSuccess(siteBeans);
                        }
                    }

                    @Override
                    protected void onError(String errorStr, int code) {
                       // super.onError(errorStr, code);
                    }
                });







    }



    /**
     * 1.17.9 订单删除
     */
    public void userOrderCancel(int orderId, IListener<Object> iListener) {

        RxHttp.getRetrofitService(FreshService.class)
                .userOrderCancel(orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<Object>() {

                    @Override
                    public void onSuccess(Object siteBeans) {
                        if (iListener != null) {
                            iListener.onSuccess(siteBeans);
                        }
                    }

                });

    }


    public void startKeFu(Context mContext) {

        WebViewActivity.startActivity(mContext, RxHttp.KEFU_URL, "在线客服");

    }


    /**
     * @ orderId
     * @ orderType  1支付商品 2退款
     * @ payType    1微信 2支付宝 3余额(商家)
     * @ APP(APP支付) JSAPI(小程序支付)  BALANCE 余额支付
     */
    public void payOrder(FragmentActivity fragmentActivity, int orderId, int orderType, int payType) {


        String subOrderType;
        if (payType == 3) {
            subOrderType = "BALANCE";
        } else {
            subOrderType = "APP";
        }

        RxHttp.getRetrofitService(FreshService.class)
                .userPayOrder(orderId, orderType, payType, subOrderType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(new BaseObserver<PayOrderReponse>() {
                    @Override
                    public void onSuccess(PayOrderReponse it) {

                        if (payType == 1) {
                            ToastUtils.show("启动微信支付");
                            PayUtil.getInstance().payWXStart(fragmentActivity, it);
                        } else if (payType == 2) {
                            Log.e("jsc", "ConfirmOrderActivity-onSuccess:" + it.getOrderInfo());
                            PayUtil.getInstance().payAliStart(fragmentActivity, it.getOrderInfo(), new PayUtil.IPayStatusListenr() {
                                @Override
                                public void onSuccess(Object o) {
                                    getPayQuery(fragmentActivity, orderId);
                                }
                            });

                        } else {
                            getPayQuery(fragmentActivity, orderId);
                        }

                    }
                });
    }


    /**
     *
     * @param fragmentActivity
     * @param orderId
     */
    public void payOrder退款(FragmentActivity fragmentActivity, int orderId,int num ,String reason,String images,int refundType,String remark) {


        //remark 备注

        //reason 说明

        //images	string	是	图片分号;相隔

        //refundType
        // 1退款
        //2退货
        //3调货

        RxHttp.getRetrofitService(FreshService.class)
                .userRefundSave(orderId, num, remark, reason, images, refundType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(new BaseObserver<PayOrderReponse>() {
                    @Override
                    public void onSuccess(PayOrderReponse it) {

                        ToastUtils.show("退款成功");
                        fragmentActivity.finish();

                    }
                });
    }


    /**
     * 订单查询 支付成功后调用的
     *
     * @param orderId
     */

    private void getPayQuery(FragmentActivity fragmentActivity, int orderId) {
        BaseDialog dialog = new WaitDialog.Builder(fragmentActivity).show();

        RxHttp.getRetrofitService(FreshService.class)
                .userPayQuery(orderId)
                .subscribeOn(Schedulers.io())
                .compose(RxSchedulers.<Response<PayQueryBean>>loadingDialog(dialog))
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(new BaseObserver<PayQueryBean>() {
                    @Override
                    public void onSuccess(PayQueryBean it) {

                        if (it.getStatus().equals("SUCCESS")) {
                            PayStatusActivity.startActivity(fragmentActivity, it.getOrderId(), 0);
                        } else {
                            PayStatusActivity.startActivity(fragmentActivity, it.getOrderId(), 1);
                        }

                    }
                });


    }


    public void startPingjia(Context mContext, int data,int bussId) {
        EvaluationActivity.startActivity(mContext, data,bussId);

    }


}
