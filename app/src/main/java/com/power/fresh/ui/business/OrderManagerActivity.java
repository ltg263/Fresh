package com.power.fresh.ui.business;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.chen.concise.RxHttp;
import com.hjq.toast.ToastUtils;
import com.power.fresh.R;
import com.power.fresh.api.FreshService;
import com.power.fresh.bean.TempBean;
import com.power.fresh.bean.bussiness.商品管理统计;
import com.power.fresh.bean.bussiness.商家订单统计;
import com.power.fresh.bean.order.OrderListBean;
import com.power.fresh.ui.aftersale.AfterSaleCcActivity;
import com.power.fresh.ui.marki.MarketOrderActivity;
import com.power.fresh.ui.order.MyOrderActivity;
import com.power.fresh.utils.Constant;
import com.powerrich.common.base.BaseObserver;
import com.powerrich.common.base.UIActivity;
import com.powerrich.common.base.adapter.CommonRvAdapter;
import com.powerrich.common.base.adapter.CommonViewHolderRv;
import com.powerrich.common.widget.TitleBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 订单管理
 */
public class OrderManagerActivity extends UIActivity {


    @BindView(R.id.mTitleBar)
    TitleBar mTitleBar;
    @BindView(R.id.tv_ysd_value)
    TextView tvYsdValue;
    @BindView(R.id.tv_spsh_error_value)
    TextView tvSpshErrorValue;
    @BindView(R.id.tv_yfh_value)
    TextView tvYfhValue;
    @BindView(R.id.tv_dps_value)
    TextView tvDpsValue;
    @BindView(R.id.tv_kczl_value)
    TextView tvKczlValue;
    @BindView(R.id.tv_shtk_value)
    TextView tvShtkValue;
    @BindView(R.id.rv_busy_order_manager)
    RecyclerView rvBusyOrderManager;
    private CommonRvAdapter<OrderListBean.ListBean> mCommonRvAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_manager;
    }

    @Override
    protected void initView() {
        mCommonRvAdapter = new CommonRvAdapter<OrderListBean.ListBean>(getActivity(), null, R.layout.item_busy_goods_manager) {

            @Override
            public void convert(CommonViewHolderRv holder, OrderListBean.ListBean item, int position) {
                String commodityName = item.getOrderDetailsDTO().get(0).getCommodityName();
                int surplusNum = item.getOrderDetailsDTO().get(0).getSurplusNum();
                holder.setText(R.id.tv_title,commodityName);
                holder.setText(R.id.tv_num,surplusNum+"件");

            }
        };
        mCommonRvAdapter.setNeedEmptyView(false);
        rvBusyOrderManager.setNestedScrollingEnabled(false);
        rvBusyOrderManager.setAdapter(mCommonRvAdapter);
    }

    @Override
    protected void initData() {
        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userBusinessStatisticsOrders())
                .subscribe(new BaseObserver<商家订单统计>() {
                    @Override
                    public void onSuccess(商家订单统计 it) {
                        tvYsdValue.setText(it.getArriveCount());
                        tvYfhValue.setText(it.getSendingCount());
                        tvDpsValue.setText(it.getUnHarvestCount());
                        tvShtkValue.setText(it.getRefunApplyingdCount());
                    }
                });
        getPageData();
    }


    private void getPageData(){
        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userBusinessOrderUserList(2, 1, 20))
                .subscribe(new BaseObserver<OrderListBean>() {
                    @Override
                    public void onSuccess(OrderListBean it) {
                        List<OrderListBean.ListBean> list = it.getList();
                        mCommonRvAdapter.setData(list);

                    }
                });
    }


    @OnClick({R.id.ll_ysd, R.id.ll_yfh, R.id.ll_dps, R.id.ll_shtk})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            /** 已送达 */
            case R.id.ll_ysd:
               // MyOrderActivity.startActivity(getActivity(),3,"销售订单", Constant.销售订单);
                MarketOrderActivity.startActivity(getActivity(),3);
                break;
                /** 已发货 */
            case R.id.ll_yfh:
             //   MyOrderActivity.startActivity(getActivity(),2,"销售订单", Constant.销售订单);
                MarketOrderActivity.startActivity(getActivity(),2);
                break;
                /** 待配送 */
            case R.id.ll_dps:
               // MyOrderActivity.startActivity(getActivity(),1,"销售订单", Constant.销售订单);
                MarketOrderActivity.startActivity(getActivity(),1);
                break;
                /** 售后退款 */
            case R.id.ll_shtk:

                AfterSaleCcActivity.startActivity(this,1);


                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }
}
