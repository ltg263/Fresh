package com.power.fresh.fragment.order;

import android.content.Intent;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.power.fresh.R;
import com.power.fresh.bean.order.OrderListBean;
import com.power.fresh.ui.EvaluationActivity;
import com.power.fresh.ui.order.OrderDetailsActivity;
import com.powerrich.common.base.adapter.CommonRvAdapter;
import com.powerrich.common.base.adapter.CommonViewHolderRv;
import com.powerrich.common.base.MyLazyFragment;

import butterknife.BindView;

/**
 * 订单分类获取数据
 */
public class OrderListFragment extends MyLazyFragment {
    /**
     * 订单类型
     */
    private int orderType;

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    private CommonRvAdapter<OrderListBean.ListBean> adapter;

    @Override
    public boolean statusBarDarkFont() {
        return false;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    public static OrderListFragment newInstance() {
        OrderListFragment fragment = new OrderListFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_order_list;
    }

    @Override
    protected int getTitleId() {
        return 0;
    }

    @Override
    protected void initView() {

        adapter = new CommonRvAdapter<OrderListBean.ListBean>(getActivity(), 4, R.layout.layout_adapter_item_order_list_item) {
            @Override
            public void convert(CommonViewHolderRv holder, OrderListBean.ListBean item, int position) {

                RecyclerView holderItemView = holder.getItemView(R.id.rv_order_child);

                CommonRvAdapter adapterChild =new CommonRvAdapter<OrderListBean.ListBean.OrderDetailsDTOBean>(getActivity(),2,R.layout.item_order_child) {
                    @Override
                    public void convert(CommonViewHolderRv holder, OrderListBean.ListBean.OrderDetailsDTOBean item, int position) {

                    }
                };

                holderItemView.setAdapter(adapterChild);


            }
        };

        mRecyclerView.setAdapter(adapter);
        DividerItemDecoration divider = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.order_list_status_custom_divider));
        mRecyclerView.addItemDecoration(divider);

        initEvent();


    }

    private void initEvent() {
        adapter.setOnItemClickListener(new CommonRvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (position % 2 == 0) {
                    startActivity(new Intent(getContext(), OrderDetailsActivity.class));
                } else {
                    //评价
                    startActivity(new Intent(getContext(), EvaluationActivity.class));
                }
            }
        });
    }

    @Override
    protected void initData() {

    }
}
