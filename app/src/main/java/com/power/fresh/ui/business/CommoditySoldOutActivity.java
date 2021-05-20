package com.power.fresh.ui.business;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.chen.concise.Response;
import com.chen.concise.RxHttp;
import com.power.common_opensurce.App;
import com.power.fresh.R;
import com.power.fresh.api.FreshService;
import com.power.fresh.bean.CommodityList;
import com.power.fresh.ui.ShopListActivity;
import com.power.fresh.utils.Constant;
import com.powerrich.common.base.BaseObserver;
import com.powerrich.common.base.BaseTablayoutActivity;
import com.powerrich.common.base.adapter.CommonRvAdapter;
import com.powerrich.common.base.adapter.CommonViewHolderRv;
import com.powerrich.common.widget.SimpleDividerItemDecoration;

import java.util.ArrayList;

import io.reactivex.Observable;

import static com.powerrich.common.helper.PublicUtil.dp2px;

/**
 * 商品状态（下架、上架）
 *          （审核中、审核失败）
 */
public class CommoditySoldOutActivity extends BaseTablayoutActivity<CommodityList.ListBean> {


    /** position 0  或者 1 */
    private String mType;
    /** 0 上架/下架 1： 审核中/审核失败 */
    private int mIntent_type;

    public static void startActivity(Context context, int intent_type) {
        Intent intent = new Intent(context, CommoditySoldOutActivity.class);
        intent.putExtra("intent_type", intent_type);
        context.startActivity(intent);
    }


    @Override
    protected String setTitleText() {
        mIntent_type = getIntent().getIntExtra("intent_type",0);
        return "商品状态";
    }


    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        FrameLayout pLayout = findViewById(R.id.bottom_content);
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.bottom_commodity_sold_out, null, false);
        pLayout.addView(inflate);
        TextView tvChangYong = inflate.findViewById(R.id.tv_changyong);
        TextView tvXuanGou = inflate.findViewById(R.id.tv_xuangou);
        tvChangYong.setOnClickListener(v -> {
            CommonOrdersActivity.startActivity(getActivity(),0);
        });
        tvXuanGou.setOnClickListener(v -> {
            ShopListActivity.startBussinessActivity(getActivity(),"供应商列表","");

        });

    }


    @Override
    protected ArrayList<String> setTabTitleList() {
        ArrayList<String> tabTitleList = new ArrayList<>();
        if (mIntent_type==1) {
            tabTitleList.add("审核中");
            tabTitleList.add("审核失败");
        }else{
            tabTitleList.add("商品待上架");
            tabTitleList.add("商品待下架");
        }

        return tabTitleList;
    }

    @Override
    protected CommonRvAdapter<CommodityList.ListBean> getAdapter() {
        CommonRvAdapter<CommodityList.ListBean> commonRvAdapter = new CommonRvAdapter<CommodityList.ListBean>(getActivity(), null, R.layout.item_order_sold_out) {
            @Override
            public void convert(CommonViewHolderRv holder, CommodityList.ListBean item, int position) {
                 holder.setImageUrl(R.id.iv_url,item.getCommodityHeaderUri());
                 holder.setText(R.id.tv_name,item.getName());
                 holder.setText(R.id.tv_num,"销量："+item.getSaleNum());
                 holder.setText(R.id.tv_total_stock,"库存："+item.getTotalStock());
                 holder.setText(R.id.tv_update_time,"更新时间："+item.getCreateTime());
                 holder.setText(R.id.tv_sale_price, Constant.￥ +item.getSalePrice());


//                if (mIntent_type==1) {
//                    tabTitleList.add("审核中");
//                    tabTitleList.add("审核失败");
//                }else{
//                    tabTitleList.add("商品待上架");
//                    tabTitleList.add("商品待下架");
//                }
//

//                if (mType.equals("0")) {
//                    if (mIntent_type==1) {
//                        holder.setText(R.id.tv_price, "审核中");
//                    }else {
//                        holder.setText(R.id.tv_price, "上架");
//                    }
//
//                }else {
//
//                    if (mIntent_type==1) {
//                        holder.setText(R.id.tv_price, "审核失败");
//                    }else {
//                        holder.setText(R.id.tv_price, "下架");
//                    }
//
//                }



                if (mIntent_type==1) {
                    if (mType.equals("0")) {
                        holder.setText(R.id.tv_price, "审核中");
                    } else {
                        holder.setText(R.id.tv_price, "审核失败");
                    }
                }else{
                    if (mType.equals("0")) {
                        holder.setText(R.id.tv_price, "上架");
                    } else {
                        holder.setText(R.id.tv_price, "下架");
                    }
                }



                 holder.getItemView(R.id.tv_price).setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {

                         ///审核中 不要点击
                         if (mIntent_type==1) {
                             return;
                         }

                         /** 1 商品上架 2 上架下架 */
                         int tcStatus ;

                         if (mIntent_type==1) {
                             if (mType.equals("0")) {
                                 tcStatus = -1;
                             } else {
                                 tcStatus = 3;
                             }
                         }else{
                             if (mType.equals("0")) {
                                 tcStatus = 2;
                             } else {
                                 tcStatus = 1;
                             }
                         }


                         userBusinessStatus(item.getCommodityId(),tcStatus);

                     }
                 });
            }
        };
        SimpleDividerItemDecoration simpleDividerItemDecoration = new SimpleDividerItemDecoration(getActivity(), 0, dp2px(getActivity(), 1));
        commonRvAdapter.setDividerItemDecoration(simpleDividerItemDecoration);

        return commonRvAdapter;
    }

    /**
     *
     * @param commodityId
     * @param status 1下架 2上架
     */
    private  void userBusinessStatus(int commodityId,int status) {
        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userBusinessCommodityUpdateStatus(commodityId,status))
                .subscribe(new BaseObserver<Object>() {
                    @Override
                    public void onSuccess(Object it) {

                        try {
                            int postion =Integer.valueOf(mType);
                            mFragmentList.get(postion).refreshData();
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }

                    }
                });
    }



    @Override
    public void onLoadData(int index, int pageSize, String type) {

        mType = String.valueOf(mIndex);
        Log.e("jsc", "CommoditySoldOutActivity-onLoadData:"+mType);

        int tcStatus ;
        /** 第一页 */
        if (type.equals("0")) {
            /** 上架、下架   */
            if (mIntent_type==0) {
                tcStatus = 1;
            }else {
                tcStatus = -1;
            }

        } else {

            if (mIntent_type==0) {
                tcStatus = 2;
            }else{
                tcStatus = 3;
            }


        }


        FreshService retrofitService = RxHttp.getRetrofitService(FreshService.class);
        Observable<Response<CommodityList>> responseObservable;
        if (App.getUserInfoCc().getLogPort()==Constant.经销商登陆) {
            responseObservable = retrofitService.userBusinessCommodityBusinessList(tcStatus, index, pageSize);
        } else {
            responseObservable =  retrofitService.userBusinessCommoditySupplyList(tcStatus, index, pageSize);
        }


        exeHttpWithDialog(responseObservable)
                .subscribe(new BaseObserver<CommodityList>() {
                    @Override
                    public void onSuccess(CommodityList it) {
                        notifyDataChanged(type, it.getList());
                    }
                });
    }
}
