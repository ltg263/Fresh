package com.power.fresh.ui.marki;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.chen.concise.RxHttp;
import com.hjq.toast.ToastUtils;
import com.power.common_opensurce.dialog.EvaluationDialog;
import com.power.fresh.R;
import com.power.fresh.api.FreshService;
import com.power.fresh.bean.marki.MarkiOrderBean;
import com.power.fresh.ui.order.OrderDetailsActivity;
import com.power.fresh.utils.AppRequest;
import com.power.fresh.utils.Constant;
import com.power.fresh.utils.GaoDeUtil;
import com.power.fresh.widget.MarkiView;
import com.powerrich.common.base.BaseDialog;
import com.powerrich.common.base.BaseObserver;
import com.powerrich.common.base.BaseTablayoutActivity;
import com.powerrich.common.base.adapter.CommonRvAdapter;
import com.powerrich.common.base.adapter.CommonViewHolderRv;

import java.util.ArrayList;
import java.util.List;

/**
 * 配送员订单
 */
public class MarkiOrderActivity extends BaseTablayoutActivity<MarkiOrderBean.ListBean> {
    private CommonRvAdapter mCommonRvAdapter;

    private AppRequest mAppRequest;
    private List<MarkiOrderBean.ListBean> mList;

    @Override
    protected String setTitleText() {
        mIndex = getIntent().getIntExtra("index", 0);
        return "配送员订单";
    }


    public static void startActivity(Context context, int index) {
        Intent intent = new Intent(context, MarkiOrderActivity.class);
        intent.putExtra("index", index);
        context.startActivity(intent);
    }


    @Override
    protected ArrayList<String> setTabTitleList() {
        mAppRequest =new AppRequest(getActivity());
        ArrayList<String> tabTitleList = new ArrayList<>();
        tabTitleList.add("全部");
        tabTitleList.add("已接单");
        tabTitleList.add("已送达");
        return tabTitleList;
    }

    @Override
    protected CommonRvAdapter<MarkiOrderBean.ListBean> getAdapter() {
        // mCommonRvAdapter = new OrderAdapter(getActivity(), null, R.layout.layout_adapter_item_order_list_item);

        mCommonRvAdapter = new CommonRvAdapter<MarkiOrderBean.ListBean>(getActivity(), null, R.layout.item_marki_order) {
            @Override
            public void convert(CommonViewHolderRv holder, MarkiOrderBean.ListBean booth, int position) {
                View 导航Btn = holder.getItemView(R.id.btn_daohang);
                View 送达Btn = holder.getItemView(R.id.btn_songda);
                View 联系客户Btn = holder.getItemView(R.id.btn_lxkh);
                holder.setText(R.id.btn_lxkh,"联系客户");

                //3 //已送达,6//派送中
                if (booth.getOrderStatus() == 6) {
                    holder.setText(R.id.tv_orderStatus, "配送中");
                    导航Btn.setVisibility(View.VISIBLE);
                    送达Btn.setVisibility(View.VISIBLE);
                    联系客户Btn.setVisibility(View.VISIBLE);
                } else {
                    holder.setText(R.id.tv_orderStatus, "已送达");
                    导航Btn.setVisibility(View.GONE);
                    送达Btn.setVisibility(View.GONE);
                    联系客户Btn.setVisibility(View.GONE);
                }

                holder.setText(R.id.tv_type_num, String.format("共%s种", booth.getOrderDetailsDTOS().size()));

                holder.setText(R.id.tv_adress, "配送地址：" + booth.getLocation());
                LinearLayout linearLayout = holder.getItemView(R.id.ll_shangpin_content);
                linearLayout.removeAllViews();

                for (MarkiOrderBean.ListBean.OrderDetailsDTOSBean it : booth.getOrderDetailsDTOS()) {
                    MarkiView markiView = new MarkiView.Build()
                            .setTvFruitNameStr(it.getCommodityName())
                            .setTvNormsNameStr(it.getNormsName())
                            .setTvTotalPriceStr(it.getTotalPrice())
                            .setTvNumStr(it.getNum())
                            .build(getActivity());
                    markiView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            OrderDetailsActivity.startActivity(MarkiOrderActivity.this,it.getOrderId(),0,Constant.配送员订单);
                        }
                    });

                    linearLayout.addView(markiView);
                }


                holder.getItemView(R.id.btn_daohang).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        GaoDeUtil.getInstance(getActivity()).openMap(booth.getLat(),booth.getLng(),booth.getAddresses());
                    }
                });


                holder.getItemView(R.id.btn_lxkh).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Constant.get().callPhone(getActivity(), booth.getMobile());
                    }
                });

                holder.getItemView(R.id.btn_songda).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        BaseDialog baseDialog = new EvaluationDialog.Builder(getActivity()).setMessage("是否确认已送达").setConfirm("确定").setCancel("取消").setListener(dialog
                                        ->
                                {

                                    mAppRequest.userDeliveryOrderService(booth.getOrderId(), new Constant.IListener() {
                                        @Override
                                        public void onSuccess(Object o) {
                                            reFreshData();
                                        }
                                    });

//                                    Constant.get().userBusinessOeliveryOrderSending(booth.getDeliveryId(), booth.getOrderId(), new Constant.IListener() {
//                                        @Override
//                                        public void onSuccess(Object o) {
//                                            reFreshData();
//                                        }
//                                    });



                                }

                        ).create();
                        baseDialog.show();





                    }
                });


            }
        };

        DividerItemDecoration divider = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.order_list_status_custom_divider));

        mCommonRvAdapter.setDividerItemDecoration(divider);

//        mCommonRvAdapter.setOnItemClickListener(new CommonRvAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//                MarkiOrderBean.ListBean listBean = mList.get(position);
//                OrderDetailsActivity.startActivity(getActivity(), listBean.getOrderId());
//            }
//        });


        return mCommonRvAdapter;
    }

    @Override
    public void onLoadData(int index, int pageSize, String type) {

        /** 1 商品上架 2 上架下架 */
        Integer tcStatus = null;

        switch (type) {
            case "0":
                tcStatus = null;
                break;
            /** 已接单 */
            case "1":
                tcStatus = 6;
                break;
            /** 已送达 */
            case "2":
                tcStatus = 3;
                break;
            default:
                tcStatus = null;
                break;
        }


        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userDeliveryOrderList(tcStatus, index, pageSize))
                .subscribe(new BaseObserver<MarkiOrderBean>() {
                    @Override
                    public void onSuccess(MarkiOrderBean it) {
                        mList = it.getList();
                        notifyDataChanged(type, it.getList());
                    }
                });
    }
}
