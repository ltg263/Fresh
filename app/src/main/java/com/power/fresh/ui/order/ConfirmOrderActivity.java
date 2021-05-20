package com.power.fresh.ui.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.chen.concise.Response;
import com.chen.concise.RxHttp;
import com.google.gson.reflect.TypeToken;
import com.hjq.toast.ToastUtils;
import com.power.common_opensurce.App;
import com.power.fresh.R;
import com.power.fresh.api.FreshService;
import com.power.fresh.bean.alipay.PayQueryBean;
import com.power.fresh.bean.home.HomeGouponBean;
import com.power.fresh.bean.order.CreateOrderBean;
import com.power.fresh.bean.reponse.CreateOrder;
import com.power.fresh.bean.reponse.PayOrderReponse;
import com.power.fresh.bean.request.CreateOrderRequest;
import com.power.fresh.bean.request.SaveOrderRequest;
import com.power.fresh.bean.shoppingcar.ShoppingCarBean;
import com.power.fresh.ui.PayStatusActivity;
import com.power.fresh.ui.my.DiscountActivity;
import com.power.fresh.ui.my.ShippingListAddressActivity;
import com.power.fresh.utils.Constant;
import com.power.fresh.utils.PayUtil;
import com.powerrich.common.base.BaseDialog;
import com.powerrich.common.base.BaseObserver;
import com.powerrich.common.base.UIActivity;
import com.powerrich.common.base.adapter.CommonRvAdapter;
import com.powerrich.common.base.adapter.CommonViewHolderRv;
import com.powerrich.common.dialog.WaitDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;

/**
 * 确认订单
 */
public class ConfirmOrderActivity extends UIActivity {

    @BindView(R.id.tv_confirm)
    RecyclerView rvConfirm;
    List<CreateOrderRequest.CheckCartDTOSBean> checkCartDTOSBeans = new ArrayList<>();
    @BindView(R.id.tv_confirm_location)
    TextView tvConfirmLocation;
    @BindView(R.id.tv_confirm_acceptname)
    TextView tvConfirmAcceptname;
    @BindView(R.id.tv_mobile)
    TextView tvMobile;
    @BindView(R.id.tv_all_total)
    TextView tvAllTotal;
    @BindView(R.id.tv_num_all_total)
    TextView tvNumAllTotal;
    @BindView(R.id.tv_serice_amount)
    TextView tvSericeAmount;
    @BindView(R.id.et_remark)
    EditText etRemark;
    @BindView(R.id.tv_business_name)
    TextView tvBusinessName;
    @BindView(R.id.rg_pay_type)
    RadioGroup rgPayType;
    @BindView(R.id.tv_coupon_note)
    TextView tvCouponNote;
    @BindView(R.id.rb_zhye)
    RadioButton rbZhye;
    @BindView(R.id.rb_xxzf)
    RadioButton rbXxzf;

    private int mBusinessid;
    /**
     * 优惠券
     */
    private Integer couponId;
    /**
     * 支付类型 1微信  2支付宝 4 线下支付
     */
    private int payType = 1;
    /**
     * 选中地址id
     */
    private int checkAddressId;

    /**
     * 合计金额
     */
    private double allTotalPrice;
    /**
     * 1 是供应商
     */
    private int mIntent_type;

    private int mOrderId;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_confirm_order;
    }


    public static void startActivity(Context context, int businessId, String data) {
        if (TextUtils.isEmpty(data)) {
            ToastUtils.show("请选择商品");
            return;
        }
        Intent intent = new Intent(context, ConfirmOrderActivity.class);
        intent.putExtra("DATA", data);
        intent.putExtra("businessid", businessId);
        context.startActivity(intent);
    }

    /**
     * 供应商跳转 ： 转换好了传过来的
     */
    public static void startSupplyActivity(Context context, int businessId, String data) {
        if (TextUtils.isEmpty(data)) {
            ToastUtils.show("请选择商品");
            return;
        }
        Intent intent = new Intent(context, ConfirmOrderActivity.class);
        intent.putExtra("businessid", businessId);
        intent.putExtra("DATA", data);
        intent.putExtra("intent_type", 1);
        context.startActivity(intent);
    }


    @Override
    protected void initView() {
        mIntent_type = getIntent().getIntExtra("intent_type", 0);
        String data = getIntent().getStringExtra("DATA");
        mBusinessid = getIntent().getIntExtra("businessid", 0);

        if (mIntent_type == 1) {
            List<CreateOrderRequest.CheckCartDTOSBean> checkCartDTOSBeans = App.getmGson().fromJson(data, new TypeToken<List<CreateOrderRequest.CheckCartDTOSBean>>() {
            }.getType());
            this.checkCartDTOSBeans = checkCartDTOSBeans;
        } else {
            List<ShoppingCarBean.ListBean.UserCartDTOSBean> intentList = App.getmGson().fromJson(data, new TypeToken<List<ShoppingCarBean.ListBean.UserCartDTOSBean>>() {
            }.getType());
            convetDataType(intentList);
        }

        rgPayType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_wx) {
                    payType = 1;
                } else if (checkedId == R.id.rb_zfb) {
                    payType = 2;
                } else if (checkedId == R.id.rb_zhye) {
                    payType = 3;
                } else if (checkedId == R.id.rb_xxzf) {
                    payType = 4;
                }
            }
        });

        if (App.getUserInfoCc().getLogPort() == Constant.用户登陆) {
            rbZhye.setVisibility(View.GONE);
        }


    }


    @Override
    protected void initData() {

    }

    private boolean needResumeData = true;

    @Override
    protected void onResume() {
        Log.e("jsc", "ConfirmOrderActivity-onResume:");
        super.onResume();
        if (needResumeData) {
            getPageData();
            needResumeData = false;
        }

        if (Constant.WX_PAY) {
            getPayQuery(mOrderId);
            Constant.WX_PAY = false;
        }

    }

    private void initRecycle(List<CreateOrderBean.AppCheckDetailsDTOListBean> appCheckDetailsDTOList) {


        CommonRvAdapter<CreateOrderBean.AppCheckDetailsDTOListBean> commonRvAdapter = new CommonRvAdapter<CreateOrderBean.AppCheckDetailsDTOListBean>(getActivity(), appCheckDetailsDTOList, R.layout.item_confirm_order) {
            @Override
            public void convert(CommonViewHolderRv holder, CreateOrderBean.AppCheckDetailsDTOListBean it, int position) {
                holder.setImageUrl(R.id.iv_goods_icon, it.getBaseInfo().getCommodityHeaderUri());
                holder.setText(R.id.tv_goods_name, it.getBaseInfo().getCommodityName());
                holder.setText(R.id.tv_goods_desc, it.getBaseInfo().getSimple());
                holder.setText(R.id.tv_specification, it.getCommodityNorms().getNorms());
                holder.setText(R.id.tv_price, "￥" + it.getCommodityNorms().getPrice());
                holder.setText(R.id.tv_num, "共" + it.getCommodityNorms().getNum() + "件");
                holder.setText(R.id.tv_total_price, "￥" + Constant.get().canvetDouble(allTotalPrice));
            }
        };

        commonRvAdapter.setNeedEmptyView(false);
        rvConfirm.setNestedScrollingEnabled(false);
        rvConfirm.setAdapter(commonRvAdapter);
    }


    private void getPageData() {
        CreateOrderRequest createOrderResponse = new CreateOrderRequest();
        createOrderResponse.setCheckCartDTOS(checkCartDTOSBeans);
        createOrderResponse.setBusinessId(mBusinessid);

        Observable<Response<CreateOrderBean>> responseObservable;
        if (mIntent_type == 1) {
            responseObservable = RxHttp.getRetrofitService(FreshService.class).userBusinessOrderCheck(createOrderResponse);
        } else {
            responseObservable = RxHttp.getRetrofitService(FreshService.class).userOrderCheck(createOrderResponse);
        }


        exeHttpWithDialog(responseObservable)
                .subscribe(new BaseObserver<CreateOrderBean>() {
                    @Override
                    public void onSuccess(CreateOrderBean it) {
                        allTotalPrice = it.getTotalPrice();
                        initRecycle(it.getAppCheckDetailsDTOList());

                        /** 1,可线下支付
                         2,不可线下支付 */
                        int unlinePayStatus = it.getBaseBusiness().getUnlinePayStatus();


                        if (unlinePayStatus==1) {
                            rbXxzf.setVisibility(View.VISIBLE);
                        }else{
                            rbXxzf.setVisibility(View.GONE);
                        }

                        int totalSize = 0;
                        for (int i = 0; i < it.getAppCheckDetailsDTOList().size(); i++) {
                            totalSize += it.getAppCheckDetailsDTOList().get(i).getCommodityNorms().getNum();
                        }

                        tvNumAllTotal.setText("共" + totalSize + "件");

                        tvAllTotal.setText(Constant.get().canvetDouble(it.getTotalPrice()));
                        tvConfirmLocation.setText(it.getUserAddressDTO().getLocation());
                        tvConfirmAcceptname.setText("收货人:" + it.getUserAddressDTO().getAcceptName());
                        tvMobile.setText("联系电话：" + it.getUserAddressDTO().getMobile());
                        tvBusinessName.setText(it.getBaseBusiness().getBusinessName());
                        tvSericeAmount.setText("￥" + it.getServiceAmount());
                        checkAddressId = it.getUserAddressDTO().getId();
                    }

                    @Override
                    protected void onError(String errorStr, int code) {
                        super.onError(errorStr, code);
                    }
                });
    }

    /**
     * 是否第一次创建订单
     */
    private boolean isFristOrderSave = true;

    private void saveOrder() {
        if (checkAddressId == 0) {
            ToastUtils.show("请选择收货地址");
            return;
        }
        if (payType == 0) {
            ToastUtils.show("请选择支付类型");
            return;
        }


        if (isFristOrderSave) {
            Log.e("jsc", "是否第一次创建订单-优惠券id:"+couponId);
            SaveOrderRequest saveOrderResponse = new SaveOrderRequest();
            saveOrderResponse.setCheckCartDTOS(checkCartDTOSBeans);
            saveOrderResponse.setBusinessId(mBusinessid);
            saveOrderResponse.setUserCouponId(couponId);
            saveOrderResponse.setCheckAddressId(checkAddressId);
            saveOrderResponse.setPayType(payType);
            saveOrderResponse.setRemark(etRemark.getText().toString());
            Observable<Response<CreateOrder>> responseObservable;
            if (App.getUserInfoCc().getLogPort() == Constant.经销商登陆 || App.getUserInfoCc().getLogPort() == Constant.供应商登陆 || mIntent_type == 1) {
                responseObservable = RxHttp.getRetrofitService(FreshService.class).userBusinessOrderSave(saveOrderResponse);
            } else {
                responseObservable = RxHttp.getRetrofitService(FreshService.class).userOrderSave(saveOrderResponse);
            }

            exeHttpWithDialog(responseObservable)
                    .subscribe(new BaseObserver<CreateOrder>() {
                        @Override
                        public void onSuccess(CreateOrder it) {
                            isFristOrderSave = false;
                            mOrderId = it.getOrderId();
                            payOrder(it.getOrderId(), 1, payType);
                        }
                    });

        } else {
            Log.e("jsc", "是否第一次创建订单-优惠券id:");
            payOrder(mOrderId, 1, payType);
        }
    }


    /**
     * @ orderId
     * @ orderType  1支付商品 2退款
     * @ payType    1微信 2支付宝 3余额(商家)
     * @ APP(APP支付) JSAPI(小程序支付)  BALANCE 余额支付 UNLINEPAY 线下展示
     */
    private void payOrder(int orderId, int orderType, int payType) {
        String subOrderType;
        if (payType == 3) {
            subOrderType = "BALANCE";
        } else if (payType == 4) {
            subOrderType = "UNLINEPAY";
        }else {
            subOrderType = "APP";
        }

        exeHttp(RxHttp.getRetrofitService(FreshService.class).userPayOrder(orderId, orderType, payType, subOrderType))
                .subscribe(new BaseObserver<PayOrderReponse>() {
                    @Override
                    public void onSuccess(PayOrderReponse it) {
                        if (it == null) {
                            return;
                        }

                        if (payType == 1) {
                            ToastUtils.show("启动微信支付");
                            PayUtil.getInstance().payWXStart(getActivity(), it);
                        } else if (payType == 2) {

                            PayUtil.getInstance().payAliStart(getActivity(), it.getOrderInfo(), new PayUtil.IPayStatusListenr() {
                                @Override
                                public void onSuccess(Object o) {
                                    getPayQuery(mOrderId);
                                }
                            });

                        } else {


                            if (it.getStatus().equals("SUCCESS")) {
                                PayStatusActivity.startActivity(getActivity(), mOrderId, 0);
                            } else {
                                PayStatusActivity.startActivity(getActivity(), mOrderId, 1);
                            }


                        }

                    }
                });
    }


    /**
     * 订单查询 支付成功后调用的
     *
     * @param orderId
     */

    private void getPayQuery(int orderId) {
        BaseDialog dialog = new WaitDialog.Builder(this).show();
        dialog.show();
        Log.e("jsc", "ConfirmOrderActivity-getPayQuery:订单查询");
        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userPayQuery(orderId))
                .subscribe(new BaseObserver<PayQueryBean>() {
                    @Override
                    public void onSuccess(PayQueryBean it) {
                        dialog.dismiss();
                        if (it.getStatus().equals("SUCCESS")) {
                            PayStatusActivity.startActivity(getActivity(), mOrderId, 0);
                        } else {
                            PayStatusActivity.startActivity(getActivity(), mOrderId, 1);
                        }

                    }

                    @Override
                    protected void onError(String errorStr, int code) {
                        super.onError(errorStr, code);
                        dialog.dismiss();
                    }
                });


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }

    @OnClick({R.id.ll_head, R.id.ll_coupons, R.id.btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_head:
                startActivity(ShippingListAddressActivity.class);
                needResumeData = true;
                break;

            /** 优惠券回来不用请求网络 */
            case R.id.ll_coupons:
                Intent intent = new Intent(this, DiscountActivity.class);
                intent.putExtra("TYPE", 1);
                intent.putExtra("TotalPrice", allTotalPrice);
                startActivityForResult(intent, (resultCode, intent1) -> {

                    if (resultCode != RESULT_OK) {
                        return;
                    }

                    String item_bean = intent1.getStringExtra("item_bean");
                    HomeGouponBean.ListBean listBean = App.getmGson().fromJson(item_bean, HomeGouponBean.ListBean.class);


                    /** 如果 合计金额 大于 达标金额 */
                    if (allTotalPrice > listBean.getSubAmount()) {
                        couponId = listBean.getUserCouponId();
                        Log.e("jsc", "优惠券Id:"+couponId);
                        tvCouponNote.setText(listBean.getNote()+String.format("(-%s%s)",Constant.￥,listBean.getSubAmount()));
                        double v = allTotalPrice - listBean.getSubAmount();
                        tvAllTotal.setText(Constant.get().canvetDouble(v));
                    } else {
                        ToastUtils.show("优惠券金额不能大于商品价格");
                    }

                });
                break;
            case R.id.btn_commit:
                saveOrder();

                break;
        }
    }

    /**
     * 强转数据类型
     */
    private void convetDataType(List<ShoppingCarBean.ListBean.UserCartDTOSBean> intentList) {
        List<CreateOrderRequest.CheckCartDTOSBean> checkCartDTOSBeans = new ArrayList<>();
        for (int i = 0; i < intentList.size(); i++) {
            ShoppingCarBean.ListBean.UserCartDTOSBean userCartDTOSBean = intentList.get(i);
            CreateOrderRequest.CheckCartDTOSBean checkCartDTOSBean = new CreateOrderRequest.CheckCartDTOSBean();
            checkCartDTOSBean.setNum(userCartDTOSBean.getNum());
            checkCartDTOSBean.setCartId(userCartDTOSBean.getCartId());
            checkCartDTOSBean.setCommodityId(userCartDTOSBean.getBaseInfo().getCommodityId());
            checkCartDTOSBean.setNormsId(userCartDTOSBean.getBaseNorms().getNormsId());
            checkCartDTOSBeans.add(checkCartDTOSBean);
        }
        this.checkCartDTOSBeans = checkCartDTOSBeans;
    }
}
