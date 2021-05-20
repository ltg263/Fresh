package com.power.fresh.ui.business;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.chen.concise.RxHttp;
import com.hjq.toast.ToastUtils;
import com.power.fresh.R;
import com.power.fresh.api.FreshService;
import com.power.fresh.bean.CommodityDetails;
import com.power.fresh.bean.TempBean;
import com.power.fresh.bean.TempBean2;
import com.power.fresh.bean.bussiness.商品管理统计;
import com.power.fresh.bean.order.OrderListBean;
import com.power.fresh.ui.ShopListActivity;
import com.powerrich.common.base.BaseObserver;
import com.powerrich.common.base.UIActivity;
import com.powerrich.common.base.adapter.CommonRvAdapter;
import com.powerrich.common.base.adapter.CommonViewHolderRv;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 商品管理
 */
public class GoodsManagerActivity extends UIActivity {


    @BindView(R.id.tv_spsh_ing_value)
    TextView tvSpshIngValue;
    @BindView(R.id.tv_spsh_error_value)
    TextView tvSpshErrorValue;
    @BindView(R.id.tv_spsj_num_value)
    TextView tvSpsjNumValue;
    @BindView(R.id.tv_spk_drk_value)
    TextView tvSpkDrkValue;
    @BindView(R.id.tv_kczl_value)
    TextView tvKczlValue;
    @BindView(R.id.tv_bjsp_value)
    TextView tvBjspValue;
    @BindView(R.id.rv_busy_goods)
    RecyclerView rvBusyGoods;
    private CommonRvAdapter mCommonRvAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_goods_manager;
    }

    @Override
    protected void initView() {


        mCommonRvAdapter = new CommonRvAdapter<OrderListBean.ListBean>(getActivity(),null,R.layout.item_busy_goods_manager){

            @Override
            public void convert(CommonViewHolderRv holder, OrderListBean.ListBean item, int position) {
                OrderListBean.ListBean.OrderDetailsDTOBean orderDetailsDTOBean = item.getOrderDetailsDTO().get(0);
                holder.setText(R.id.tv_title,orderDetailsDTOBean.getCommodityName());
                holder.setText(R.id.tv_num,String.format("共%s元",orderDetailsDTOBean.getTotalPrice()));
            }
        };
        mCommonRvAdapter.setNeedEmptyView(false);
        rvBusyGoods.setNestedScrollingEnabled(false);
        rvBusyGoods.setAdapter(mCommonRvAdapter);



    }

    @Override
    protected void initData() {
        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userBusinessStatisticsCommodities())
                .subscribe(new BaseObserver<商品管理统计>() {
                    @Override
                    public void onSuccess(商品管理统计 it) {
                        tvSpshIngValue.setText(it.getInitStatusCount());
                        tvSpshErrorValue.setText(it.getFailStatusCount());
                        tvSpsjNumValue.setText(it.getTotalCount());
                        tvSpkDrkValue.setText(it.getBoughtOrderCount());
                        tvBjspValue.setText(it.getSelectOrderCount());
                    }
                });
        requestPageData();
    }

    private  void requestPageData() {
        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userBusinessOrderBusinessOrderlist())
                .subscribe(new BaseObserver<OrderListBean>() {
                    @Override
                    public void onSuccess(OrderListBean it) {
                        List<OrderListBean.ListBean> list = it.getList();
                        mCommonRvAdapter.setData(list);
                    }
                });
    }




    @OnClick({R.id.ll_spsh, R.id.ll_spsxj, R.id.ll_spk, R.id.ll_busb})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_spsh:
                CommoditySoldOutActivity.startActivity(getActivity(),1);
                break;
            case R.id.ll_spsxj:
                CommoditySoldOutActivity.startActivity(getActivity(),0);
                break;
            case R.id.ll_spk:
                startActivity(PendingActivity.class);
                break;
            case R.id.ll_busb:
                ShopListActivity.startBussinessActivity(getActivity(),"供应商列表","");
                break;
        }
    }
}
