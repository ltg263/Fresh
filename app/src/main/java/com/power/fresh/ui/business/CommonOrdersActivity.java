package com.power.fresh.ui.business;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.chen.concise.RxHttp;
import com.power.fresh.R;
import com.power.fresh.adapter.OrderAdapter;
import com.power.fresh.api.FreshService;
import com.power.fresh.bean.CommodityList;
import com.power.fresh.bean.order.OrderListBean;
import com.power.fresh.utils.Constant;
import com.powerrich.common.base.BaseObserver;
import com.powerrich.common.base.BaseTablayoutActivity;
import com.powerrich.common.base.adapter.CommonRvAdapter;
import com.powerrich.common.base.adapter.CommonViewHolderRv;

import java.util.ArrayList;

/**
 * 采购订单  与销售订单和普通用户订单 解耦 共用Adapter
 *
 */
public class CommonOrdersActivity extends BaseTablayoutActivity<OrderListBean.ListBean> {

    private OrderAdapter mCommonRvAdapter;
    private int mIndex;


    public static void startActivity(Context context, int index){
              Intent intent = new Intent(context, CommonOrdersActivity.class);
              intent.putExtra("index", index);
              context.startActivity(intent);
          }

    @Override
    protected String setTitleText() {
        mIndex = getIntent().getIntExtra("index", 0);
        return "采购订单";
    }

    @Override
    protected ArrayList<String> setTabTitleList() {
        ArrayList<String> tabTitleList = new ArrayList<>();
        tabTitleList.add("全部");
        tabTitleList.add("待付款");
        tabTitleList.add("待发货");
        tabTitleList.add("待收货");
        tabTitleList.add("待评价");
        return tabTitleList;
    }

    @Override
    protected CommonRvAdapter<OrderListBean.ListBean> getAdapter() {
//        CommonRvAdapter commonRvAdapter = new CommonRvAdapter<OrderListBean.ListBean>(getActivity(), null, R.layout.item_order_sold_out) {
//            @Override
//            public void convert(CommonViewHolderRv holder, OrderListBean.ListBean item, int position) {
//
//            }
//        };
        mCommonRvAdapter = new OrderAdapter(getActivity(), null, R.layout.layout_adapter_item_order_list_item, Constant.采购订单);

        DividerItemDecoration divider = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.order_list_status_custom_divider));
        mCommonRvAdapter.setDividerItemDecoration(divider);

        mCommonRvAdapter.setRefreshListener(new OrderAdapter.RefreshListener() {
            @Override
            public void refresh() {
                refreshData();
            }
        });

        return mCommonRvAdapter;
    }

    public void refreshData(){

        reFreshData();

    }

    @Override
    protected void initData() {
        super.initData();
        viewPager.setCurrentItem(mIndex);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    public void onLoadData(int index, int pageSize, String type) {
        /** 1 商品上架 2 上架下架 */
        Integer tcStatus = null;
        /** 待付款 */
        if (type.equals("1")) {
            tcStatus=1;
        }
        /** 待发货 */
        else  if (type.equals("2")) {
            tcStatus=2;
        }
        /** 待收货 */
        else  if (type.equals("3")) {
            tcStatus=6;
        }
        /** 待评价 */
        else  if (type.equals("4")) {
            tcStatus=3;
        }

        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userBusinessOrderBusinessOrderlist(tcStatus, index, pageSize))
                .subscribe(new BaseObserver<OrderListBean>() {
                    @Override
                    public void onSuccess(OrderListBean it) {
                        notifyDataChanged(type, it.getList());
                    }
                });



    }
}
