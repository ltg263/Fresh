package com.power.fresh.ui.aftersale;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.power.fresh.R;
import com.power.fresh.bean.order.OrderListBean;
import com.power.fresh.ui.order.OrderDetailsActivity;
import com.powerrich.common.base.adapter.CommonRvAdapter;
import com.powerrich.common.base.adapter.CommonViewHolderRv;
import com.powerrich.common.base.UIActivity;
import com.powerrich.common.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 售后/退款
 */
public class CustomerServiceActivity extends UIActivity {

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.mTitleBar)
    TitleBar mTitleBar;

    private CommonRvAdapter<OrderListBean.ListBean> adapter;

    private List<OrderListBean.ListBean> mOrderListBeanList = new ArrayList<>();

    @Override
    public boolean statusBarDarkFont() {
        return false;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_customer_service;
    }

    @Override
    protected int getTitleId() {
        return R.id.mTitleBar;
    }

    @Override
    protected void initView() {
        setTitle(R.string.activity_customer_service_title);
        mTitleBar.setLeftResId(R.mipmap.icon_public_arrow_back_white);
        adapter = new CommonRvAdapter<OrderListBean.ListBean>(getActivity(), 4, R.layout.layout_adapter_item_order_list_item) {
            @Override
            public void convert(CommonViewHolderRv holder, OrderListBean.ListBean item, int position) {

               // holder.setText(R.id.tv_orderStatus, canvertOrderStatus(item.getOrderStatus()));

                RecyclerView holderItemView = holder.getItemView(R.id.rv_order_child);

                CommonRvAdapter adapterChild = new CommonRvAdapter<OrderListBean.ListBean.OrderDetailsDTOBean>(getActivity(), 2, R.layout.item_order_child) {
                    @Override
                    public void convert(CommonViewHolderRv holderChild, OrderListBean.ListBean.OrderDetailsDTOBean childItem, int position) {
//                        holderChild.setImageUrl(R.id.iv_url,childItem.getCommodityHeaderUri());
//                        holderChild.setText(R.id.tv_name,childItem.getCommodityName());
//                        holderChild.setText(R.id.tv_specification,childItem.getNormsName());
//                        holderChild.setText(R.id.tv_num,"x"+childItem.getNum());
//                        holderChild.setText(R.id.tv_price, Constant.￥+childItem.getSalePrice());
                    }
                };

                DividerItemDecoration divider = new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL);
                divider.setDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.order_list_status_custom_1dp_divider));
                holderItemView.addItemDecoration(divider);
                holderItemView.setAdapter(adapterChild);

                /** 查看详情 */
                holder.getItemView(R.id.btn_details3).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getActivity(), OrderDetailsActivity.class));
                    }
                });



            }
        };
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        adapter = new OrderListAdapter(this);
//        adapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(RecyclerView recyclerView, View itemView, int position) {
//                if(position>3){
//                    startActivity(new Intent(CustomerServiceActivity.this, CustomerServiceRefundActivity.class));
//
//                }else {
//                    startActivity(new Intent(CustomerServiceActivity.this, CustomerServiceDetailsActivity.class));
//                }
//            }
//        });
        mRecyclerView.setAdapter(adapter);
        DividerItemDecoration divider = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this,R.drawable.order_list_status_custom_divider));
        mRecyclerView.addItemDecoration(divider);
    }

    @Override
    protected void initData() {

    }
}
