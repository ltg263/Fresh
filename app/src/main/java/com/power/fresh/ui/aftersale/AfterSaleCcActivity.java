package com.power.fresh.ui.aftersale;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.chen.concise.RxHttp;
import com.power.common_opensurce.App;
import com.power.fresh.R;
import com.power.fresh.api.FreshService;
import com.power.fresh.bean.AfterBean;
import com.power.fresh.ui.aftersale.details.CustomerServiceDetailsActivity;
import com.power.fresh.ui.order.OrderDetailsActivity;
import com.power.fresh.utils.Constant;
import com.powerrich.common.base.BaseListActivity;
import com.powerrich.common.base.BaseObserver;
import com.powerrich.common.base.adapter.CommonRvAdapter;
import com.powerrich.common.base.adapter.CommonViewHolderRv;

import java.util.ArrayList;
import java.util.List;

import static com.power.fresh.utils.Constant.canvertOrderStatus;

/**
 * 售后/退款
 */
public class AfterSaleCcActivity extends BaseListActivity<AfterBean.ListBean> {

    private CommonRvAdapter<AfterBean.ListBean> adapter;
    private List<AfterBean.ListBean> mOrderListBeanList = new ArrayList<>();
    private AfterBean mIt ;
    /**
     * 0 普通退款 -采购订单    1 订单管理-销售退款（从订单管理界面进入）
     */
    private int mIntentType;


    public static void startActivity(Context context, int intentType) {
        Intent intent = new Intent(context, AfterSaleCcActivity.class);
        intent.putExtra("intentType", intentType);
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        super.initView();
        mIntentType = getIntent().getIntExtra("intentType", 0);
    }

    @Override
    protected CommonRvAdapter<AfterBean.ListBean> getAdapter() {
        //按钮名换成 查看详情 吧  点击效果不要了 放后台管理了
        adapter = new CommonRvAdapter<AfterBean.ListBean>(getActivity(), mOrderListBeanList, R.layout.layout_adapter_item_order_list_item) {
            @Override
            public void convert(CommonViewHolderRv holder, AfterBean.ListBean item, int position) {


                AfterBean.ListBean.JsonOrderCommodityBean jsonOrderCommodity = item.getJsonOrderCommodity();


                /**  申请中(申请中的订单可以取消), */
                if (item.getRefundStatus().equals("APPLYING")) {
                    holder.setText(R.id.tv_orderStatus, "申请中");
                } else if (item.getRefundStatus().equals("ACCEPTING")) {
                    holder.setText(R.id.tv_orderStatus, "受理中");
                } else if (item.getRefundStatus().equals("SUCCESS")) {
                    holder.setText(R.id.tv_orderStatus, "成功");
                } else if (item.getRefundStatus().equals("FAIl")) {
                    holder.setText(R.id.tv_orderStatus, "失败");
                } else if (item.getRefundStatus().equals("FINISH")) {
                    holder.setText(R.id.tv_orderStatus, "完成");
                } else if (item.getRefundStatus().equals("CANCEL ")) {
                    holder.setText(R.id.tv_orderStatus, "取消");
                }


                List<AfterBean.ListBean.JsonOrderCommodityBean> list = new ArrayList<>();
                list.add(jsonOrderCommodity);
                RecyclerView holderItemView = holder.getItemView(R.id.rv_order_child);
                CommonRvAdapter adapterChild = new CommonRvAdapter<AfterBean.ListBean.JsonOrderCommodityBean>(getActivity(), list, R.layout.item_order_child) {
                    @Override
                    public void convert(CommonViewHolderRv holderChild, AfterBean.ListBean.JsonOrderCommodityBean childItem, int position) {

                        holderChild.setImageUrl(R.id.iv_url, childItem.getCommodityHeaderUri(), 5);
                        holderChild.setText(R.id.tv_name, childItem.getCommodityName());
                        holderChild.setText(R.id.tv_specification, childItem.getNormsName());


                        if (item.getRefundType() == 1) {
                            holderChild.setText(R.id.tv_desc, "退款");
                        } else if (item.getRefundType() == 2) {
                            holderChild.setText(R.id.tv_desc, "退货");
                        } else if (item.getRefundType() == 3) {
                            holderChild.setText(R.id.tv_desc, "调货");
                        }


                        holderChild.setText(R.id.tv_num, "x" + item.getNum());
                        holderChild.setText(R.id.tv_price, Constant.￥ + Constant.get().canvetDouble(item.getRefundPrice()));
                    }
                };

                DividerItemDecoration divider = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
                divider.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.order_list_status_custom_1dp_divider));
                holderItemView.addItemDecoration(divider);
                holderItemView.setAdapter(adapterChild);

                /** 查看详情 */
                holder.getItemView(R.id.btn_details3).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //   OrderDetailsActivity.startActivity(getActivity(), item.getOrderId());

                        CustomerServiceDetailsActivity.startActivity(getActivity(), item.getId());
                    }
                });

            }
        };

        mRecyclerView.setAdapter(adapter);
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.order_list_status_custom_divider));
        mRecyclerView.addItemDecoration(divider);


        return adapter;
    }

    @Override
    protected String getTitleText() {
        return getString(R.string.activity_customer_service_title);
    }

    @Override
    protected void onLoadData(int index, int pageSize) {


//        if (mIntentType == 1) {
//
//            exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userBusinessRefundUserRefundList(index, pageSize))
//                    .subscribe(new BaseObserver<AfterBean>() {
//                        @Override
//                        public void onSuccess(AfterBean it) {
//
//                            mOrderListBeanList = it.getList();
//                            notifyDataChanged(mOrderListBeanList);
//
//                        }
//                    });
//
//            return;
//        }


        if (App.getUserInfoCc().getLogPort() == Constant.经销商登陆) {

            exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userBusinessRefund(index, pageSize))
                    .subscribe(new BaseObserver<AfterBean>() {
                        @Override
                        public void onSuccess(AfterBean it) {

                            mOrderListBeanList = it.getList();
                            notifyDataChanged(mOrderListBeanList);

                        }
                    });

        } else if (App.getUserInfoCc().getLogPort() == Constant.供应商登陆) {

            //  商家从供应商采购的订单中申请的退款
            if (mIntentType == 0) {

                exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userBusinessRefund(index, pageSize))
                        .subscribe(new BaseObserver<AfterBean>() {
                            @Override
                            public void onSuccess(AfterBean it) {

                                mOrderListBeanList = it.getList();
                                notifyDataChanged(mOrderListBeanList);

                            }
                        });


            }
            //订单管理进入
            else {
                exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userBusinessSupplyRefund(index, pageSize))
                        .subscribe(new BaseObserver<AfterBean>() {
                            @Override
                            public void onSuccess(AfterBean it) {

                                mOrderListBeanList = it.getList();
                                notifyDataChanged(mOrderListBeanList);

                            }
                        });
            }


        } else {
            exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userRefundList(index, pageSize))
                    .subscribe(new BaseObserver<AfterBean>() {
                        @Override
                        public void onSuccess(AfterBean it) {

                            mOrderListBeanList = it.getList();
                            notifyDataChanged(mOrderListBeanList);

                        }
                    });

        }


    }
}
