package com.power.fresh.ui.marki;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.chen.concise.RxHttp;
import com.power.common_opensurce.App;
import com.power.common_opensurce.mode.CommonRequest;
import com.power.fresh.R;
import com.power.fresh.api.FreshService;
import com.power.fresh.bean.order.OrderListBean;
import com.power.fresh.ui.business.DeliveryManManageActivity;
import com.power.fresh.ui.order.OrderDetailsActivity;
import com.power.common_opensurce.utils.MarketOrderPopWindow;
import com.power.common_opensurce.dialog.WLDialog;
import com.power.fresh.utils.Constant;
import com.power.fresh.widget.MarkiView;
import com.powerrich.common.base.BaseDialog;
import com.powerrich.common.base.BaseObserver;
import com.powerrich.common.base.BaseTablayoutActivity;
import com.powerrich.common.base.adapter.CommonRvAdapter;
import com.powerrich.common.base.adapter.CommonViewHolderRv;

import java.util.ArrayList;

/**
 * 销售订单
 */
public class MarketOrderActivity extends BaseTablayoutActivity<OrderListBean.ListBean> {
    private CommonRvAdapter<OrderListBean.ListBean> mCommonRvAdapter;

    private  int curIndex;

    @Override
    protected String setTitleText() {
        mIndex = getIntent().getIntExtra("index", 0);
        return "销售订单";
    }

    public static void startActivity(Context context, int index) {
        Intent intent = new Intent(context, MarketOrderActivity.class);
        intent.putExtra("index", index);
        context.startActivity(intent);
    }


    @Override
    protected ArrayList<String> setTabTitleList() {
        ArrayList<String> mDataList = new ArrayList<>();
        mDataList.add("全部");
        mDataList.add("待配送");
        mDataList.add("已发货");
        mDataList.add("已发送");
        return mDataList;
    }

    @Override
    protected CommonRvAdapter<OrderListBean.ListBean> getAdapter() {


        mCommonRvAdapter = new CommonRvAdapter<OrderListBean.ListBean>(getActivity(), null, R.layout.item_marki_order) {
            @Override
            public void convert(CommonViewHolderRv holder, OrderListBean.ListBean booth, int position) {

                holder.getItemView(R.id.tv_zongji_title).setVisibility(View.VISIBLE);
                holder.getItemView(R.id.tv_zongji_value).setVisibility(View.VISIBLE);
                holder.setText(R.id.tv_zongji_value, booth.getTotalPrice());
                Button itemView = holder.getItemView(R.id.btn_songda);
                itemView.setVisibility(View.GONE);
                //3 //已送达,6//派送中
                if (booth.getOrderStatus() == 6) {
                    holder.setText(R.id.tv_orderStatus, "配送中");

                } else if (booth.getOrderStatus() == 2) {
                    holder.setText(R.id.tv_orderStatus, "待配送");
                    if (App.getUserInfoCc().getLogPort()==Constant.供应商登陆||App.getUserInfoCc().getLogPort()==Constant.经销商登陆) {
                        itemView.setVisibility(View.VISIBLE);
                        itemView.setText("确认发货");
                    }

                }  else if (booth.getOrderStatus() == 5) {
                    holder.setText(R.id.tv_orderStatus, "已取消");
                } else {
                    holder.setText(R.id.tv_orderStatus, "已送达");
                }

                holder.setText(R.id.tv_type_num, String.format("共%s种", booth.getOrderDetailsDTO().size()));

                holder.setText(R.id.tv_adress, "配送地址：" + booth.getOrderAddress().getAddress());
                LinearLayout linearLayout = holder.getItemView(R.id.ll_shangpin_content);
                linearLayout.removeAllViews();

                for (OrderListBean.ListBean.OrderDetailsDTOBean it : booth.getOrderDetailsDTO()) {
                    MarkiView build = new MarkiView.Build()
                            .setTvFruitNameStr(it.getCommodityName())
                            .setTvNormsNameStr(it.getNormsName())
                            .setTvTotalPriceStr(it.getTotalPrice())
                            .setTvNumStr(it.getNum())
                            .build(getActivity());
                    linearLayout.addView(build);
                }

                holder.getItemView(R.id.btn_lxkh).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        OrderDetailsActivity.startActivity(getActivity(), booth.getOrderAddress().getOrderId());
//                        Constant.get().callPhone(getActivity(),booth.getOrderAddress().getMobile());
                    }
                });

                holder.getItemView(R.id.btn_songda).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    //    showPop(booth.getOrderAddress().getOrderId());

                        Constant.get().showPopCommit发货(getActivity(), booth.getOrderAddress().getOrderId(), new     Constant.IListener<Object>() {
                            @Override
                            public void onSuccess(Object o) {
                                reFreshData();
                            }
                        });



                    }
                });


            }
        };

        DividerItemDecoration divider = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.order_list_status_custom_divider));

        mCommonRvAdapter.setDividerItemDecoration(divider);


        return mCommonRvAdapter;
    }

    private  void showPop(int orderId) {
        new MarketOrderPopWindow(getActivity()).showBottomView(new MarketOrderPopWindow.ChooseImgImpl() {
            BaseDialog baseDialog;
            @Override
            public void wlps() {
                baseDialog = new WLDialog.Builder(getActivity()).setInterfaceClick(new WLDialog.InterfaceClick() {
                    @Override
                    public void commit(String info) {
                        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userBusinessOrderAddExpress(orderId, info))
                                .subscribe(new BaseObserver<Object>() {
                                    @Override
                                    public void onSuccess(Object it) {

                                        baseDialog.dismiss();
                                        reFreshData();

                                    }
                                });
                    }
                }).show();
            }

            @Override
            public void tvPsy() {


                Intent intent = new Intent(getActivity(), DeliveryManManageActivity.class);
                intent.putExtra("Title","配送员选择");
                intent.putExtra("OrderId",orderId);
                startActivityForResult(intent, new ActivityCallback() {
                    @Override
                    public void onActivityResult(int resultCode, @Nullable Intent intent) {
                        if (resultCode==RESULT_OK) {
                            reFreshData();
                        }

                        baseDialog.dismiss();
                    }
                });



            }
        });
    }

    @Override
    public void onLoadData(int index, int pageSize, String type) {

        curIndex = Integer.valueOf(type);

        Integer orderStatus = null;


        switch (type) {
            case "0":
                orderStatus = null;
                break;
            case "1":
                orderStatus = 2;
                break;
            case "2":
                orderStatus = 6;
                break;
            case "3":
                orderStatus = 3;
                break;
        }


        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userBusinessOrderUserList(orderStatus, index, pageSize))
                .subscribe(new BaseObserver<OrderListBean>() {
                    @Override
                    public void onSuccess(OrderListBean it) {
                        notifyDataChanged(type, it.getList());
                    }
                });


    }
}
