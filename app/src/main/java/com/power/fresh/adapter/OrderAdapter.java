package com.power.fresh.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.hjq.toast.ToastUtils;
import com.power.common_opensurce.App;
import com.power.fresh.R;
import com.power.fresh.bean.order.OrderListBean;
import com.power.fresh.ui.order.OrderDetailsActivity;
import com.power.fresh.ui.order.OrderPayActivity;
import com.power.fresh.utils.Constant;
import com.powerrich.common.base.adapter.CommonRvAdapter;
import com.powerrich.common.base.adapter.CommonViewHolderRv;
import com.powerrich.common.helper.LogUtils;

import java.util.List;

/**
 * @author AlienChao
 * @date 2020/05/27 18:03
 */
public class OrderAdapter  extends CommonRvAdapter<OrderListBean.ListBean> {



    /** 0：购买订单(Tab进入) 2 ：采购订单 */
    private int mType=0;

    private RefreshListener mRefreshListener;


    private  int mOrderId;
    public void setRefreshListener(RefreshListener refreshListener) {
        mRefreshListener = refreshListener;
    }

    public OrderAdapter(Context context, List mData, int mItemLayoutId) {
        super(context, mData, mItemLayoutId);
    }

    public OrderAdapter(Context context, List mData, int mItemLayoutId,int intentType) {
        super(context, mData, mItemLayoutId);
        this.mType = intentType;
    }

    public interface RefreshListener{
        void refresh();
    }


    @Override
    public void convert(CommonViewHolderRv holder, OrderListBean.ListBean item, int position) {
        holder.setText(R.id.tv_orderStatus, Constant.canvertOrderStatus(item.getOrderStatus()));

        RecyclerView holderItemView = holder.getItemView(R.id.rv_order_child);

        CommonRvAdapter adapterChild = new CommonRvAdapter<OrderListBean.ListBean.OrderDetailsDTOBean>(mContext, item.getOrderDetailsDTO(), R.layout.item_order_child) {
            @Override
            public void convert(CommonViewHolderRv holderChild, OrderListBean.ListBean.OrderDetailsDTOBean childItem, int position) {
                holderChild.setImageUrl(R.id.iv_url,childItem.getCommodityHeaderUri());
                holderChild.setText(R.id.tv_name,childItem.getCommodityName());
                holderChild.setText(R.id.tv_specification,childItem.getNormsName());
                holderChild.setText(R.id.tv_num,"x"+childItem.getNum());
                holderChild.setText(R.id.tv_price, Constant.￥+childItem.getSalePrice());
                mOrderId = childItem.getOrderId();
            }
        };

        adapterChild.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startOrderDetails(item.getId(),item.getOrderDetailsDTO().get(position).getId());
            }
        });

        DividerItemDecoration divider = new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(mContext,R.drawable.order_list_status_custom_1dp_divider));
        holderItemView.addItemDecoration(divider);
        holderItemView.setAdapter(adapterChild);

        Button pinglun= holder.getItemView(R.id.btn_pinglun0);
        Button cancel= holder.getItemView(R.id.btn_cancel1);
        Button pay= holder.getItemView(R.id.btn_pay2);
        Button details= holder.getItemView(R.id.btn_details3);
        Button delete= holder.getItemView(R.id.btn_delete_order4);
        Button cantact= holder.getItemView(R.id.btn_cantact_order5);


        LogUtils.i("jsc", "OrderCcListFragment-订单状态:"+item.getOrderStatus());
        /** 1,未完成;2,待收货(已付款);3,待评价（到货）；4完成（流程结束）；5，已取消；6，已发货 */
        /** 待付款 */
        if (item.getOrderStatus()==1) {
            pinglun.setVisibility(View.GONE);
            cancel.setVisibility(View.VISIBLE);
            pay.setVisibility(View.VISIBLE);
           // cantact.setVisibility(View.VISIBLE);
            details.setVisibility(View.GONE);
            delete.setVisibility(View.GONE);

        }
        /** 3,待评价（到货） */
        else  if(item.getOrderStatus()==3){
            pinglun.setVisibility(View.VISIBLE);
            cancel.setVisibility(View.GONE);
            pay.setVisibility(View.GONE);
            details.setVisibility(View.GONE);
            delete.setVisibility(View.GONE);
            cantact.setVisibility(View.VISIBLE);

        }
        /** 4完成（流程结束）；5，已取消 */
        else  if(item.getOrderStatus()==4|item.getOrderStatus()==5){
            pinglun.setVisibility(View.GONE);
            cancel.setVisibility(View.GONE);
            pay.setVisibility(View.GONE);
            details.setVisibility(View.VISIBLE);
            delete.setVisibility(View.VISIBLE);
            cantact.setVisibility(View.GONE);

        }else{
            /** 查看详情 和 删除订单  【采购订单-代发货】默认详情 */
            pinglun.setVisibility(View.GONE);
            cancel.setVisibility(View.GONE);
            pay.setVisibility(View.GONE);
            details.setVisibility(View.VISIBLE);
            delete.setVisibility(View.GONE);
            cantact.setVisibility(View.GONE);
        }


        /** 联系客服 */
        holder.getItemView(R.id.btn_cantact_order5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constant.get().startKeFu(mContext);
            }
        });



        /** 立即评论 */
        holder.getItemView(R.id.btn_pinglun0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constant.get().startPingjia(mContext,item.getId(),item.getBusinessId());
            }
        });


        /** 查看详情 */
        holder.getItemView(R.id.btn_details3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startOrderDetails(item.getId(),0);
            }
        });

        /** 删除订单 */
        holder.getItemView(R.id.btn_delete_order4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Constant.get().userOrderRemove(item.getId(), new Constant.IListener<Object>() {
                   @Override
                   public void onSuccess(Object o) {
                       ToastUtils.show("已删除");
                       if (mRefreshListener!=null) {
                           mRefreshListener.refresh();
                       }
                   }
               });

            }
        });
        /** 取消订单 */
        holder.getItemView(R.id.btn_cancel1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Constant.get().userOrderCancel(item.getId(), new Constant.IListener<Object>() {
                    @Override
                    public void onSuccess(Object o) {
                        if (mRefreshListener!=null) {
                            mRefreshListener.refresh();
                        }
                    }
                });
            }
        });
        /** 立即支付*/
        holder.getItemView(R.id.btn_pay2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderPayActivity.startActivity(mContext,item.getId(),String.valueOf(item.getTotalPrice()));
                //      payOrder(item.getId(),1,item.getPayType(),1);
            }
        });



    }


    private  void startOrderDetails(int OrderId,int DetailsOrderId) {

        if (mType==Constant.采购订单&& (App.getUserInfoCc().getLogPort() == Constant.供应商登陆||App.getUserInfoCc().getLogPort() == Constant.经销商登陆)) {
            OrderDetailsActivity.startActivity(mContext,OrderId,DetailsOrderId,Constant.采购订单);
        }else if(App.getUserInfoCc().getLogPort() == Constant.配送员登陆){
            OrderDetailsActivity.startActivity(mContext,OrderId,DetailsOrderId,Constant.配送员订单);
        }else{
            OrderDetailsActivity.startActivity(mContext,OrderId,DetailsOrderId,Constant.销售订单);
        }
    }


}
