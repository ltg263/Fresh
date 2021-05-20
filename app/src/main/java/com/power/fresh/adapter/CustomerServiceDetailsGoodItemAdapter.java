package com.power.fresh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.power.fresh.R;
import com.powerrich.common.base.adapter.BaseRecyclerViewAdapter;

/**
 * 退款详情内的订单商品item
 */
public class CustomerServiceDetailsGoodItemAdapter extends BaseRecyclerViewAdapter<String, CustomerServiceDetailsGoodItemAdapter.CustomerServiceDetailsGoodsItemViewHolder> {


    public CustomerServiceDetailsGoodItemAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public CustomerServiceDetailsGoodItemAdapter.CustomerServiceDetailsGoodsItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_adapter_item_customer_service_details_goods_item, parent, false);
        return new CustomerServiceDetailsGoodsItemViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerServiceDetailsGoodItemAdapter.CustomerServiceDetailsGoodsItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class CustomerServiceDetailsGoodsItemViewHolder extends BaseRecyclerViewAdapter.ViewHolder {

        public CustomerServiceDetailsGoodsItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
