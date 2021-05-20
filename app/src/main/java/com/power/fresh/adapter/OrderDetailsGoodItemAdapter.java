package com.power.fresh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.power.fresh.R;
import com.powerrich.common.base.adapter.BaseRecyclerViewAdapter;

/**
 * 订单详情内的订单商品item
 */
public class OrderDetailsGoodItemAdapter extends BaseRecyclerViewAdapter<String, OrderDetailsGoodItemAdapter.OrderDetailsGoodsItemViewHolder> {


    public OrderDetailsGoodItemAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public OrderDetailsGoodItemAdapter.OrderDetailsGoodsItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_adapter_item_order_details_goods_item, parent, false);
        return new OrderDetailsGoodsItemViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailsGoodItemAdapter.OrderDetailsGoodsItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class OrderDetailsGoodsItemViewHolder extends BaseRecyclerViewAdapter.ViewHolder {

        public OrderDetailsGoodsItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
