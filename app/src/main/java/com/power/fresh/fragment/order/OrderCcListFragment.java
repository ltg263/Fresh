package com.power.fresh.fragment.order;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.chen.concise.Response;
import com.chen.concise.RxHttp;
import com.hjq.toast.ToastUtils;
import com.power.fresh.R;
import com.power.fresh.adapter.OrderAdapter;
import com.power.fresh.api.FreshService;
import com.power.fresh.bean.alipay.PayQueryBean;
import com.power.fresh.bean.order.OrderListBean;
import com.power.fresh.bean.reponse.PayOrderReponse;
import com.power.fresh.ui.PayStatusActivity;
import com.power.fresh.ui.order.OrderDetailsActivity;
import com.power.fresh.ui.order.OrderPayActivity;
import com.power.fresh.utils.Constant;
import com.power.fresh.utils.PayUtil;
import com.powerrich.common.base.BaseListFragment;
import com.powerrich.common.base.BaseObserver;
import com.powerrich.common.base.adapter.CommonRvAdapter;
import com.powerrich.common.base.adapter.CommonViewHolderRv;
import com.powerrich.common.helper.LogUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * 订单分类获取数据
 */
public class OrderCcListFragment extends BaseListFragment<OrderListBean.ListBean> {

    private OrderAdapter adapter;
    private List<OrderListBean.ListBean> mOrderListBeanList = new ArrayList<>();

    int mPosition;

    /**
     * 0：采购订单  1 销售订单
     */
    int mIntentType;


    public static OrderCcListFragment newInstance(int type, int intentType) {
        Bundle args = new Bundle();
        args.putInt("type", type);
        args.putInt("intent_type", intentType);
        OrderCcListFragment fragment = new OrderCcListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initData() {
        super.initData();
        Bundle bundle = getArguments();
        if (null != bundle) {
            mPosition = bundle.getInt("type");
            mIntentType = bundle.getInt("intent_type");
        }
    }

    @Override
    protected CommonRvAdapter<OrderListBean.ListBean> getAdapter() {

        adapter = new OrderAdapter(getActivity(), mOrderListBeanList, R.layout.layout_adapter_item_order_list_item);

        adapter.setRefreshListener(new OrderAdapter.RefreshListener() {
            @Override
            public void refresh() {
                refreshData();
            }
        });
     /*   adapter = new CommonRvAdapter<OrderListBean.ListBean>(getActivity(), mOrderListBeanList, R.layout.layout_adapter_item_order_list_item) {
            @Override
            public void convert(CommonViewHolderRv holder, OrderListBean.ListBean item, int position) {

                holder.setText(R.id.tv_orderStatus, Constant.canvertOrderStatus(item.getOrderStatus()));

                RecyclerView holderItemView = holder.getItemView(R.id.rv_order_child);

                CommonRvAdapter adapterChild = new CommonRvAdapter<OrderListBean.ListBean.OrderDetailsDTOBean>(getActivity(), item.getOrderDetailsDTO(), R.layout.item_order_child) {
                    @Override
                    public void convert(CommonViewHolderRv holderChild, OrderListBean.ListBean.OrderDetailsDTOBean childItem, int position) {
                        holderChild.setImageUrl(R.id.iv_url,childItem.getCommodityHeaderUri());
                        holderChild.setText(R.id.tv_name,childItem.getCommodityName());
                        holderChild.setText(R.id.tv_specification,childItem.getNormsName());
                        holderChild.setText(R.id.tv_num,"x"+childItem.getNum());
                        holderChild.setText(R.id.tv_price, Constant.￥+childItem.getSalePrice());
                    }
                };

                adapterChild.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        OrderDetailsActivity.startActivity(getActivity(),item.getId());
                    }
                });

                DividerItemDecoration divider = new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL);
                divider.setDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.order_list_status_custom_1dp_divider));
                holderItemView.addItemDecoration(divider);
                holderItemView.setAdapter(adapterChild);

                Button pinglun= holder.getItemView(R.id.btn_pinglun0);
                Button cancel= holder.getItemView(R.id.btn_cancel1);
                Button pay= holder.getItemView(R.id.btn_pay2);
                Button details= holder.getItemView(R.id.btn_details3);
                Button delete= holder.getItemView(R.id.btn_delete_order4);
                Button cantact= holder.getItemView(R.id.btn_cantact_order5);


                LogUtils.i("jsc", "OrderCcListFragment-订单状态:"+item.getOrderStatus());
                *//** 1,未完成;2,待收货(已付款);3,待评价（到货）；4完成（流程结束）；5，已取消；6，已发货 *//*
         *//** 待付款 *//*
                if (item.getOrderStatus()==1) {
                    pinglun.setVisibility(View.GONE);
                    cancel.setVisibility(View.VISIBLE);
                    pay.setVisibility(View.VISIBLE);
                    cantact.setVisibility(View.VISIBLE);
                    details.setVisibility(View.GONE);
                    delete.setVisibility(View.GONE);

                }
                *//** 3,待评价（到货） *//*
                else  if(item.getOrderStatus()==3){
                    pinglun.setVisibility(View.VISIBLE);
                    cancel.setVisibility(View.GONE);
                    pay.setVisibility(View.GONE);
                    details.setVisibility(View.GONE);
                    delete.setVisibility(View.GONE);
                    cantact.setVisibility(View.VISIBLE);

                }
                *//** 4完成（流程结束）；5，已取消 *//*
                else  if(item.getOrderStatus()==4|item.getOrderStatus()==5){
                    pinglun.setVisibility(View.GONE);
                    cancel.setVisibility(View.GONE);
                    pay.setVisibility(View.GONE);
                    details.setVisibility(View.VISIBLE);
                    delete.setVisibility(View.VISIBLE);
                    cantact.setVisibility(View.GONE);

                }else{
                    *//** 查看详情 和 删除订单 *//*
                    pinglun.setVisibility(View.GONE);
                    cancel.setVisibility(View.GONE);
                    pay.setVisibility(View.GONE);
                    details.setVisibility(View.GONE);
                    delete.setVisibility(View.GONE);
                    cantact.setVisibility(View.VISIBLE);
                }


                *//** 联系客服 *//*
                holder.getItemView(R.id.btn_cantact_order5).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Constant.get().startKeFu();
                    }
                });



                *//** 立即评论 *//*
                holder.getItemView(R.id.btn_pinglun0).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Constant.get().startPingjia(getActivity());
                    }
                });


                *//** 查看详情 *//*
                holder.getItemView(R.id.btn_details3).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        OrderDetailsActivity.startActivity(getActivity(),item.getId());
                    }
                });

                *//** 删除订单 *//*
                holder.getItemView(R.id.btn_delete_order4).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteOrder(item.getId());

                    }
                });
                *//** 取消订单 *//*
                holder.getItemView(R.id.btn_cancel1).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cancelOrder(item.getId());
                    }
                });
                *//** 立即支付*//*
                holder.getItemView(R.id.btn_pay2).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        OrderPayActivity.startActivity(getActivity(),"");
                  //      payOrder(item.getId(),1,item.getPayType(),1);
                    }
                });


            }
        };
*/

//        mRecyclerView.setAdapter(adapter);
        DividerItemDecoration divider = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.order_list_status_custom_divider));
        mRecyclerView.addItemDecoration(divider);

        return adapter;
    }


    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = (int) v.getTag();

            //deleteOrder(item.getId());

        }
    };


    @Override
    protected String getTitleText() {
        getTitleBar().setVisibility(View.GONE);
        return "";
    }

    @Override
    protected void onLoadData(int index, int pageSize) {
        Integer orderStatus = null;
        Observable<Response<OrderListBean>> responseObservable;

        /**  0：普通用户  1 销售订单  */
        if (mIntentType == Constant.销售订单) {
            if (mPosition == 0) {
                orderStatus = null;
            } else if (mPosition == 1) {
                orderStatus = 2;
            } else if (mPosition == 2) {
                orderStatus = 6;
            } else if (mPosition == 3) {
                orderStatus = 3;
            }
            responseObservable = RxHttp.getRetrofitService(FreshService.class).userBusinessOrderUserList(orderStatus, index, pageSize);

        } else {
            if (mPosition == 0) {
                orderStatus = null;
            }
            /** 待收货 */
            else if (mPosition == 3) {
                orderStatus = 3;  // bug [2020-07-30]普通用户，待发货订单详情商家还没发货，订单状态应该待配送中。
            } else {
                orderStatus = mPosition;
            }
            responseObservable = RxHttp.getRetrofitService(FreshService.class).userOrderList(orderStatus, index, pageSize);
        }


        //""\2\6\3

        exeHttp(responseObservable)
                .subscribe(new BaseObserver<OrderListBean>() {
                    @Override
                    public void onSuccess(OrderListBean it) {
                        if (it!=null) {
                            mOrderListBeanList = it.getList();
                            notifyDataChanged(mOrderListBeanList);
                        }

                    }
                });
    }


    private void deleteOrder(int orderId) {
        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userOrderRemove(orderId))
                .subscribe(new BaseObserver<Object>() {
                    @Override
                    public void onSuccess(Object it) {
                        refreshData();
                    }
                });
    }


    private void cancelOrder(int orderId) {
        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userOrderCancel(orderId))
                .subscribe(new BaseObserver<Object>() {
                    @Override
                    public void onSuccess(Object it) {
                        refreshData();
                    }
                });
    }

    /**
     * @param orderId
     * @param orderType    1支付商品 2退款
     * @param payType      1微信 2支付宝 3余额(商家)
     * @param subOrderType APP(APP支付)  JSAPI(小程序支付)  BALANCE 余额支付
     */
    private void payOrder(int orderId, int orderType, int payType) {
        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userPayOrder(orderId, orderType, payType, "APP"))
                .subscribe(new BaseObserver<PayOrderReponse>() {
                    @Override
                    public void onSuccess(PayOrderReponse it) {
                        if (payType == 1) {
                            ToastUtils.show("启动微信支付");
                            PayUtil.getInstance().payWXStart(getActivity(),it);
                        } else if (payType == 2) {
                            Log.e("jsc", "ConfirmOrderActivity-onSuccess:" + it.getOrderInfo());
                            PayUtil.getInstance().payAliStart(getActivity(), it.getOrderInfo(), new PayUtil.IPayStatusListenr() {
                                @Override
                                public void onSuccess(Object o) {
                                    getPayQuery(orderId);
                                }
                            });

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

        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userPayQuery(orderId))
                .subscribe(new BaseObserver<PayQueryBean>() {
                    @Override
                    public void onSuccess(PayQueryBean it) {

                        if (it.getStatus().equals("SUCCESS")) {
                            PayStatusActivity.startActivity(getActivity(), it.getOrderId(), 0);
                        } else {
                            PayStatusActivity.startActivity(getActivity(), it.getOrderId(), 1);
                        }

                    }
                });


    }

    @Override
    protected int getTitleId() {
        return 0;
    }
}
