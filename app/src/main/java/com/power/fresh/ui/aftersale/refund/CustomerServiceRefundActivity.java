package com.power.fresh.ui.aftersale.refund;

import android.os.Bundle;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.power.fresh.R;
import com.power.fresh.adapter.CustomerServiceDetailsGoodItemAdapter;
import com.powerrich.common.base.adapter.BaseRecyclerViewAdapter;
import com.powerrich.common.base.UIActivity;
import com.powerrich.common.widget.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 申请退款
 */
public class CustomerServiceRefundActivity extends UIActivity {

    @BindView(R.id.rvGoods)
    RecyclerView rvGoods;
    @BindView(R.id.mTitleBar)
    TitleBar mTitleBar;

    private CustomerServiceDetailsGoodItemAdapter adapter;

    @Override
    public boolean statusBarDarkFont() {
        return false;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_customer_service_refund;
    }

    @Override
    protected int getTitleId() {
        return R.id.mTitleBar;
    }

    @Override
    protected void initView() {
        setTitle(R.string.activity_customer_service_refund_title);
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
        rvGoods.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        adapter = new CustomerServiceDetailsGoodItemAdapter(this);
        adapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView recyclerView, View itemView, int position) {

            }
        });
        rvGoods.setAdapter(adapter);
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.order_list_status_custom_divider));
        rvGoods.addItemDecoration(divider);
    }

    @Override
    protected void initData() {

    }
}
