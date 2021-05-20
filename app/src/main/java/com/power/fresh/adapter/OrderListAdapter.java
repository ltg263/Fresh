package com.power.fresh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.power.fresh.R;
import com.power.fresh.bean.order.OrderListBean;
import com.powerrich.common.base.adapter.BaseRecyclerViewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 订单列表适配器
 */
public class OrderListAdapter extends BaseRecyclerViewAdapter<OrderListBean.ListBean, OrderListAdapter.OrderListAdapterHolder> {

    public OrderListAdapter(Context context) {
        super(context);
//        OrderListBean orderListBean = new OrderListBean();
//        List<OrderListBean.ListBean> list = orderListBean.getList();
    }

    @NonNull
    @Override
    public OrderListAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderListAdapterHolder(LayoutInflater.from(getContext()).inflate(R.layout.layout_adapter_item_order_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderListAdapterHolder holder, int position) {

        holder.tvName.setText(position+"");

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class OrderListAdapterHolder extends BaseRecyclerViewAdapter.ViewHolder {
        @BindView(R.id.iv_url)
        ImageView ivUrl;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_desc)
        TextView tvDesc;
        @BindView(R.id.tv_specification)
        TextView tvSpecification;
        @BindView(R.id.tv_num)
        TextView tvNum;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        public OrderListAdapterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
