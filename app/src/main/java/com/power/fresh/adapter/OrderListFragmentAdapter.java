package com.power.fresh.adapter;

import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.power.fresh.fragment.order.OrderCcListFragment;
import com.power.fresh.fragment.order.OrderListFragment;

public class OrderListFragmentAdapter extends FragmentPagerAdapter {


    /**
     * 0：采购订单  1 销售订单
     */
    int mIntentType;

    /** fragment 的数量 */
    private  int count;


    public OrderListFragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public OrderListFragmentAdapter(@NonNull FragmentManager fm, int behavior,int mIntentType,int count) {
        super(fm, behavior);
        this.mIntentType =mIntentType;
        this.count = count;
    }

    @Override
    public void restoreState(@Nullable Parcelable state, @Nullable ClassLoader loader) {
        super.restoreState(state, loader);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
//        OrderListFragment orderListFragment = OrderListFragment.newInstance();
//        orderListFragment.setOrderType(position);
//        return orderListFragment;
       return OrderCcListFragment.newInstance(position,mIntentType);
    }

    @Override
    public int getCount() {
        return count;
    }
}
