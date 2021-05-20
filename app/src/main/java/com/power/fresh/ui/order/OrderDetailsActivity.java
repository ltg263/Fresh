package com.power.fresh.ui.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chen.concise.RxHttp;
import com.hjq.toast.ToastUtils;
import com.power.common_opensurce.App;
import com.power.fresh.R;
import com.power.fresh.api.FreshService;
import com.power.fresh.bean.order.OrderDetails;
import com.power.fresh.ui.ApplyRefundActivity;
import com.power.fresh.ui.ShopDetailsActivity;
import com.power.fresh.utils.Constant;
import com.power.fresh.widget.AddButtonView;
import com.power.fresh.widget.CircleImageView;
import com.powerrich.common.base.BaseObserver;
import com.powerrich.common.base.UIActivity;
import com.powerrich.common.base.adapter.CommonRvAdapter;
import com.powerrich.common.base.adapter.CommonViewHolderRv;
import com.powerrich.common.image.GlideLoad;
import com.powerrich.common.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 订单详情
 */
public class OrderDetailsActivity extends UIActivity {

    @BindView(R.id.mTitleBar)
    TitleBar mTitleBar;
    @BindView(R.id.rvGoods)
    RecyclerView rvGoods;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.tv_info)
    TextView tvInfo;
    @BindView(R.id.iv_circle)
    CircleImageView ivCircle;
    @BindView(R.id.tv_shop_name)
    TextView tvShopName;
    @BindView(R.id.tv_activity)
    TextView tvActivity;
    @BindView(R.id.ll_activity)
    LinearLayout llActivity;
    @BindView(R.id.tv_yhq_value)
    TextView tvYhqValue;
    @BindView(R.id.tv_jifen)
    TextView tvJifen;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.tv_mobile)
    TextView tvMobile;
    @BindView(R.id.tv_contact_kning)
    TextView tvContactKning;
    @BindView(R.id.tv_kning_name)
    TextView tvKningName;
    @BindView(R.id.tv_ddh)
    TextView tvDdh;
    @BindView(R.id.tv_pay_type)
    TextView tvPayType;
    @BindView(R.id.tv_pay_time)
    TextView tvPayTime;
    @BindView(R.id.tv_remark)
    TextView tvRemark;
    @BindView(R.id.tv_copy)
    TextView tvCopy;
    @BindView(R.id.btn_pinglun0)
    Button btnPinglun0;
    @BindView(R.id.btn_cancel1)
    Button btnCancel1;
    @BindView(R.id.btn_pay2)
    Button btnPay2;
    @BindView(R.id.btn_details3)
    Button btnDetails3;
    @BindView(R.id.btn_delete_order4)
    Button btnDeleteOrder4;
    @BindView(R.id.btn_cantact_order5)
    Button btnCantactOrder5;
    @BindView(R.id.ll_below_content)
    LinearLayout llBelowContent;

    private int mOrderId;
    private int mDetailsOrderId;
    private CommonRvAdapter<OrderDetails.OrderBean.OrderDetailsDTOBean> mCommonRvAdapter;
    private List<OrderDetails.OrderBean.OrderDetailsDTOBean> mOrderDetailsDTO = new ArrayList<>();
    private int mOrderId1;
    private int mStatus;
    /**
     * 0 订单详情（默认：销售订单详情）  11 是配送员的详情（“orderStatus”:3 //已送达,6//派送中）
     * 2 购买订单，采购订单详情(不会有发货按钮)
     */
    private int mIntentType;

    @Override
    public boolean statusBarDarkFont() {
        return false;
    }


    public static void startActivity(Context context, int OrderId) {
        Intent intent = new Intent(context, OrderDetailsActivity.class);
        intent.putExtra("OrderId", OrderId);
        context.startActivity(intent);
    }

    public static void startActivity(Context context, int OrderId,int DetailsOrderId, int intentType) {
        Intent intent = new Intent(context, OrderDetailsActivity.class);
        intent.putExtra("OrderId", OrderId);
        intent.putExtra("DetailsOrderId", DetailsOrderId);
        intent.putExtra("intentType", intentType);
        context.startActivity(intent);
    }

    /** 推送 */
    public static void startActivity(Context context, String OrderId) {
        Intent intent = new Intent(context, OrderDetailsActivity.class);
        intent.putExtra("OrderId", Integer.valueOf(OrderId));
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_details;
    }

    @Override
    protected int getTitleId() {
        return R.id.mTitleBar;
    }

    @Override
    protected void initView() {
        mIntentType = getIntent().getIntExtra("intentType", 0);
        mOrderId = getIntent().getIntExtra("OrderId", 0);
        mDetailsOrderId = getIntent().getIntExtra("DetailsOrderId", 0);
        setTitle(R.string.activity_order_details_title);
        mTitleBar.setLeftResId(R.mipmap.icon_public_arrow_back_white);
        mTitleBar.setOnTitleBarListener(new TitleBar.OnTitleBarListener() {
            @Override
            public void onLeftClick() {
                finish();
            }

            @Override
            public void onRightClick() {

            }
        });

        mCommonRvAdapter = new CommonRvAdapter<OrderDetails.OrderBean.OrderDetailsDTOBean>(getActivity(), mOrderDetailsDTO, R.layout.layout_adapter_item_order_details_goods_item) {
            @Override
            public void convert(CommonViewHolderRv holder, OrderDetails.OrderBean.OrderDetailsDTOBean item, int position) {

                holder.setImageUrl(R.id.iv_shop, item.getCommodityHeaderUri());
                holder.setText(R.id.tv_name, item.getCommodityName());
                holder.setText(R.id.tv_goods_desc, Constant.￥ + item.getSalePrice() + "/" + item.getNormsName());
                holder.setText(R.id.tv_num, Constant.x + item.getNum());

                View viewById = holder.itemView.findViewById(R.id.tv_tuikuang);

                viewById.setOnClickListener(v -> ApplyRefundActivity.startActivity(getActivity(),item,mDetailsOrderId));

                if (item.getSurplusNum() > 0 && mStatus == 3&&App.getUserInfoCc().getLogPort()!=Constant.配送员登陆) {
                    viewById.setVisibility(View.VISIBLE);
                } else {
                    viewById.setVisibility(View.GONE);
                }


            }
        };

        rvGoods.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        mCommonRvAdapter.setNeedEmptyView(false);
        rvGoods.setAdapter(mCommonRvAdapter);

    }

    OrderDetails mOrderDetails;
    @Override
    protected void initData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    private  void getData() {
        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).orderGetDetails(mOrderId))
                .subscribe(new BaseObserver<OrderDetails>() {
                    @Override
                    public void onSuccess(OrderDetails it) {

                        mOrderDetails = it;


                        mOrderId1 = it.getOrder().getId();
                        mStatus = it.getOrder().getOrderStatus();



                        String orderStatusText="";

                        /** 普通用户、经销商、供应商 */
                        if (mIntentType == Constant.销售订单) {

                            Log.e("jsc", "OrderDetailsActivity-onSuccess-订单状态:" + mStatus);
                            if (mStatus == 1) {
                                orderStatusText = "待支付";
                                tvInfo.setText(String.format("请在%s前支付，逾期未支付订单将自动取消！", it.getOrder().getPayExpireTime()));
                                GlideLoad.loadImage(ivCircle, R.mipmap.order_zhifu);

                                btnPinglun0.setVisibility(View.GONE);
                                btnCancel1.setVisibility(View.VISIBLE);
                                btnPay2.setVisibility(View.VISIBLE);
                                btnDetails3.setVisibility(View.GONE);
                                btnDeleteOrder4.setVisibility(View.GONE);
                                btnCantactOrder5.setVisibility(View.VISIBLE);
                            } else if (mStatus == 2) {
                                orderStatusText = "待发货";
                                tvInfo.setText("买家已付款，赶紧备货吧！");
                                GlideLoad.loadImage(ivCircle, R.mipmap.order_zhifu);

                                btnPinglun0.setVisibility(View.GONE);
                                btnCancel1.setVisibility(View.GONE);
                                btnPay2.setVisibility(View.GONE);
                                btnDetails3.setVisibility(View.GONE);
                                btnDeleteOrder4.setVisibility(View.GONE);
                                btnCantactOrder5.setVisibility(View.VISIBLE);

                                /** 只有商家端供应商订单需要。 且  默认订单*/
                                if (App.getUserInfoCc().getLogPort() == Constant.经销商登陆&&mIntentType==0) {
                                    AddButtonView tv = new AddButtonView(getActivity());
                                    tv.setIClickListener(() -> Constant.get().showPopCommit发货(getActivity(), mOrderId1, o -> finish()));
                                    llBelowContent.addView(tv);
                                }


                            } else if (mStatus == 6) {
                                orderStatusText = "已发货，配送中";
                                tvInfo.setText("订单已发货，正在配送中");
                                GlideLoad.loadImage(ivCircle, R.mipmap.order_peisong);

                                btnPinglun0.setVisibility(View.GONE);
                                btnCancel1.setVisibility(View.GONE);
                                btnPay2.setVisibility(View.GONE);
                                btnDetails3.setVisibility(View.GONE);
                                btnDeleteOrder4.setVisibility(View.GONE);
                                btnCantactOrder5.setVisibility(View.VISIBLE);
                            } else if (mStatus == 3) {
                                orderStatusText = "待评价";
                                tvInfo.setText("订单已完成，赏个好评吧！");
                                GlideLoad.loadImage(ivCircle, R.mipmap.order_pj);


                                // 不是配送员 就能评价
                                if (App.getUserInfoCc().getLogPort() != Constant.配送员登陆) {
                                    btnPinglun0.setVisibility(View.VISIBLE);
                                }


                                btnCancel1.setVisibility(View.GONE);
                                btnPay2.setVisibility(View.GONE);
                                btnDetails3.setVisibility(View.GONE);
                                btnDeleteOrder4.setVisibility(View.GONE);
                                btnCantactOrder5.setVisibility(View.VISIBLE);
                            } else if (mStatus == 4) {

                                orderStatusText = "已完成";
                                tvInfo.setText("订单已完成");
                                GlideLoad.loadImage(ivCircle, R.mipmap.order_wancheng);

                                btnPinglun0.setVisibility(View.GONE);
                                btnCancel1.setVisibility(View.GONE);
                                btnPay2.setVisibility(View.GONE);
                                btnDetails3.setVisibility(View.GONE);
                                btnDeleteOrder4.setVisibility(View.VISIBLE);
                                btnCantactOrder5.setVisibility(View.VISIBLE);
                            } else {
                                orderStatusText = "已取消";
                                tvInfo.setText("订单已取消");
                                GlideLoad.loadImage(ivCircle, R.mipmap.order_wancheng);

                                btnPinglun0.setVisibility(View.GONE);
                                btnCancel1.setVisibility(View.GONE);
                                btnPay2.setVisibility(View.GONE);
                                btnDetails3.setVisibility(View.GONE);
                                btnDeleteOrder4.setVisibility(View.VISIBLE);
                                btnCantactOrder5.setVisibility(View.VISIBLE);
                            }


                        }
                        /** 购买订单，采购订单 */
                        else if (mIntentType == Constant.采购订单) {
                            if (mStatus == 1) {
                                orderStatusText = "待支付";
                                tvInfo.setText(String.format("请在%s前支付，逾期未支付订单将自动取消！", it.getOrder().getPayExpireTime()));
                                GlideLoad.loadImage(ivCircle, R.mipmap.order_zhifu);

                                btnPinglun0.setVisibility(View.GONE);
                                btnCancel1.setVisibility(View.VISIBLE);
                                btnPay2.setVisibility(View.VISIBLE);
                                btnDetails3.setVisibility(View.GONE);
                                btnDeleteOrder4.setVisibility(View.GONE);
                                btnCantactOrder5.setVisibility(View.VISIBLE);
                            } else if (mStatus == 2||mStatus == 6) {
                                orderStatusText = "发货中";
                                tvInfo.setText("商家正在配送中，请耐心等待！");
                                GlideLoad.loadImage(ivCircle, R.mipmap.order_peisong);

                                btnPinglun0.setVisibility(View.GONE);
                                btnCancel1.setVisibility(View.GONE);
                                btnPay2.setVisibility(View.GONE);
                                btnDetails3.setVisibility(View.GONE);
                                btnDeleteOrder4.setVisibility(View.GONE);
                                btnCantactOrder5.setVisibility(View.VISIBLE);


                            } else if (mStatus == 3) {
                                orderStatusText = "待评价";
                                tvInfo.setText("订单已完成，赏个好评吧！");
                                GlideLoad.loadImage(ivCircle, R.mipmap.order_pj);
                                // 不是配送员 就能评价
                                if (App.getUserInfoCc().getLogPort() != Constant.配送员登陆) {
                                    btnPinglun0.setVisibility(View.VISIBLE);
                                }

                                btnCancel1.setVisibility(View.GONE);
                                btnPay2.setVisibility(View.GONE);
                                btnDetails3.setVisibility(View.GONE);
                                btnDeleteOrder4.setVisibility(View.GONE);
                                btnCantactOrder5.setVisibility(View.VISIBLE);
                            } else if (mStatus == 4) {
                                orderStatusText = "已完成";
                                tvInfo.setText("订单已完成，欢迎下次再来！");
                                GlideLoad.loadImage(ivCircle, R.mipmap.order_wancheng);

                                btnPinglun0.setVisibility(View.GONE);
                                btnCancel1.setVisibility(View.GONE);
                                btnPay2.setVisibility(View.GONE);
                                btnDetails3.setVisibility(View.GONE);
                                btnDeleteOrder4.setVisibility(View.VISIBLE);
                                btnCantactOrder5.setVisibility(View.VISIBLE);
                            } else {
                                orderStatusText = "已取消";
                                tvInfo.setText("订单已取消，欢迎继续购买！");
                                GlideLoad.loadImage(ivCircle, R.mipmap.order_wancheng);

                                btnPinglun0.setVisibility(View.GONE);
                                btnCancel1.setVisibility(View.GONE);
                                btnPay2.setVisibility(View.GONE);
                                btnDetails3.setVisibility(View.GONE);
                                btnDeleteOrder4.setVisibility(View.VISIBLE);
                                btnCantactOrder5.setVisibility(View.VISIBLE);
                            }

                        }
                        /** 配送员 */
                        else {

                            /** /已送达 */
                            if (mStatus == 3) {

                                orderStatusText = "买家待评价";
                                tvInfo.setText("订单已送达，等待买家评价");
                                GlideLoad.loadImage(ivCircle, R.mipmap.order_pj);

                                btnPinglun0.setVisibility(View.GONE);
                                btnCancel1.setVisibility(View.GONE);
                                btnPay2.setVisibility(View.GONE);
                                btnDetails3.setVisibility(View.GONE);
                                btnDeleteOrder4.setVisibility(View.GONE);
                                btnCantactOrder5.setVisibility(View.VISIBLE);

                            }
                            /** 配送中 */
                            else if (mStatus == 6) {


                                orderStatusText = "已发货，配送中";
                                tvInfo.setText("订单已发货，正在配送中");


                                GlideLoad.loadImage(ivCircle, R.mipmap.order_peisong);

                                btnPinglun0.setVisibility(View.GONE);
                                btnCancel1.setVisibility(View.GONE);
                                btnPay2.setVisibility(View.GONE);
                                btnDetails3.setVisibility(View.GONE);
                                btnDeleteOrder4.setVisibility(View.GONE);
                                btnCantactOrder5.setVisibility(View.VISIBLE);


                            }


                        }
                        tvStatus.setText(orderStatusText);

                        tvShopName.setText(it.getBusinessName());
                        tvActivity.setText(Constant.￥ + Constant.get().canvetDouble(it.getOrder().getTransportPrice()));
                        tvYhqValue.setText(Constant.￥ + Constant.get().canvetDouble(it.getOrder().getSubPriceAll()));
                        tvTotalPrice.setText(Constant.￥ + Constant.get().canvetDouble(it.getOrder().getTotalPrice()));

                        tvLocation.setText(it.getOrderAddress().getAddress());

                        tvMobile.setText("收货人：" + it.getOrderAddress().getAddressee() + " 收货电话：" + Constant.get().encryptPhoneNum(it.getOrderAddress().getMobile()));

                        if (TextUtils.isEmpty(it.getDeliveryMobile())) {
                            tvContactKning.setVisibility(View.GONE);
                        } else {
                            tvContactKning.setVisibility(View.VISIBLE);
                        }

                        if (TextUtils.isEmpty(it.getDeliveryName())) {
                            tvKningName.setText("--");
                        } else {
                            tvKningName.setText(it.getDeliveryName());
                        }

                        tvDdh.setText(it.getOrder().getOrderNo());

                        String payTypeStr = "--";
                        if (it.getOrder().getPayType() == 1) {
                            payTypeStr = "微信";
                        } else if (it.getOrder().getPayType() == 2) {
                            payTypeStr = "支付宝";
                        } else if (it.getOrder().getPayType() == 3) {
                            payTypeStr = "在线支付";
                        }
                        tvPayType.setText(payTypeStr);
                        tvPayTime.setText(it.getOrder().getCreateTime());

                        /** 暂无备注字段 */
                        //tvRemark.setText("");

                        mOrderDetailsDTO = it.getOrder().getOrderDetailsDTO();
                        mCommonRvAdapter.setData(mOrderDetailsDTO);
                    }

                    @Override
                    protected void onError(String errorStr, int code) {
                        super.onError(errorStr, code);
                        if (llBelowContent==null) {
                            return;
                        }
                        llBelowContent.setVisibility(View.INVISIBLE);
                    }
                });
    }



    @OnClick({R.id.tv_shop_name, R.id.tv_contact_kning, R.id.tv_copy, R.id.btn_pinglun0, R.id.btn_cancel1, R.id.btn_pay2, R.id.btn_details3, R.id.btn_delete_order4, R.id.btn_cantact_order5})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.tv_shop_name:
                ShopDetailsActivity.startActivity(getActivity(), "", mOrderDetails.getBusinessId(), "", "");
                break;

            case R.id.tv_contact_kning:

                Constant.get().callPhone(this,mOrderDetails.getDeliveryMobile());

                break;
            case R.id.tv_copy:
                boolean copy = Constant.get().copy(getActivity(), tvDdh.getText().toString());
                if (copy) {
                    ToastUtils.show("订单号已复制");
                }
                break;
            case R.id.btn_pinglun0:
                Constant.get().startPingjia(getActivity(), mOrderId1,mOrderDetails.getBusinessId());

                break;
            case R.id.btn_cancel1:
                Constant.get().userOrderCancel(mOrderId1, null);
                break;
            case R.id.btn_pay2:


                OrderPayActivity.startActivity(this,mOrderId1,String.valueOf(mOrderDetails.getOrder().getTotalPrice()));

                break;
            case R.id.btn_details3:
                OrderDetailsActivity.startActivity(getActivity(), mOrderId1);
                break;
            case R.id.btn_delete_order4:
                Constant.get().userOrderRemove(mOrderId1, null);
                break;
            case R.id.btn_cantact_order5:
                Constant.get().startKeFu(getActivity());
                break;
        }
    }
}
