package com.power.fresh.ui.business;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chen.concise.RxHttp;
import com.power.fresh.R;
import com.power.fresh.api.FreshService;
import com.power.fresh.bean.MessageBean;
import com.power.fresh.bean.home.HomeMerchant;
import com.power.fresh.ui.ShopDetailsActivity;
import com.power.fresh.ui.my.AddAddressActivity;
import com.power.fresh.utils.Constant;
import com.powerrich.common.base.BaseListActivity;
import com.powerrich.common.base.BaseObserver;
import com.powerrich.common.base.UIActivity;
import com.powerrich.common.base.adapter.CommonRvAdapter;
import com.powerrich.common.base.adapter.CommonViewHolderRv;
import com.powerrich.common.helper.PublicUtil;
import com.powerrich.common.helper.SPUtils;
import com.powerrich.common.image.GlideLoad;
import com.powerrich.common.widget.SimpleDividerItemDecoration;
import com.yalantis.ucrop.util.ScreenUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static me.jessyan.autosize.utils.AutoSizeUtils.dp2px;

/**
 * 商家列表
 */
public class BusinessListActivity extends BaseListActivity<HomeMerchant.ListBean> {
    CommonRvAdapter<HomeMerchant.ListBean> mCommonRvAdapter;
    private double lng, lat;
    private int mCategoryId;
    private String mName;

    public static void startActivity(Context context,int categoryId,String name) {
        Intent intent = new Intent(context, BusinessListActivity.class);
        intent.putExtra("categoryId",categoryId);
        intent.putExtra("name",name);
        context.startActivity(intent);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        FrameLayout pLayout = findViewById(R.id.top_content);
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.bussess_list_top, null, false);


        pLayout.addView(inflate);
        inflate.setOnClickListener(v->{
            startActivity(new Intent(this, AddAddressActivity.class));
        });
    }

    @Override
    protected CommonRvAdapter<HomeMerchant.ListBean> getAdapter() {
        mCategoryId = getIntent().getIntExtra("categoryId", 0);
        mName = getIntent().getStringExtra("name");

        lng = (double) SPUtils.get(Constant.LNG, 0.0);
        lat = (double) SPUtils.get(Constant.LAT, 0.0);

        mCommonRvAdapter = new CommonRvAdapter<HomeMerchant.ListBean>(true, BR.homeshops, getActivity(), null, R.layout.item_search) {
            @Override
            public void convert(CommonViewHolderRv holder, HomeMerchant.ListBean it, int position) {
                GlideLoad.loadImage(holder.getItemView(R.id.iv_shop_head), it.getSiteHeaderUri());
                RecyclerView rvSearchChird = holder.itemView.findViewById(R.id.rv_search_chird);
                GridLayoutManager manager = new GridLayoutManager(getActivity(), 4);
                manager.setOrientation(RecyclerView.VERTICAL);
                rvSearchChird.setLayoutManager(manager);
                CommonRvAdapter<HomeMerchant.ListBean.AppCommodityDTOSBean> mAdapter = new CommonRvAdapter<HomeMerchant.ListBean.AppCommodityDTOSBean>(true, BR.home_shop_chird, getActivity(), it.getAppCommodityDTOS(), R.layout.item_search_chird, 4) {
                    @Override
                    public void convert(CommonViewHolderRv holder, HomeMerchant.ListBean.AppCommodityDTOSBean booth, int position) {
                        holder.setImageUrl(R.id.iv_serch_chird, booth.getCommodityHeaderUri(), 5);
                        holder.setText(R.id.tv_activitySalePrice, "¥" + booth.getMinPrice());
                    }
                };
                mAdapter.setOnItemClickListener((index) -> {
//                    int commodityId = mAdapter.getData().get(index).getCommodityId();
//                    CommodityDetailsActivity.startActivity(getActivity(),commodityId);
                    HomeMerchant.ListBean listBean = mCommonRvAdapter.getData().get(position);
                    ShopDetailsActivity.startActivity(getActivity(), listBean.getSiteName(), listBean.getId(), "", "");


                });
                mAdapter.setNeedEmptyView(false);
                rvSearchChird.setNestedScrollingEnabled(false);
                rvSearchChird.setAdapter(mAdapter);


            }
        };

        mCommonRvAdapter.setOnItemClickListener(new CommonRvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                HomeMerchant.ListBean listBean = mCommonRvAdapter.getData().get(position);
                ShopDetailsActivity.startActivity(getActivity(), listBean.getSiteName(), listBean.getId(), "", "");
            }
        });

        mCommonRvAdapter.setNeedEmptyView(false);
        mCommonRvAdapter.setDividerItemDecoration(new SimpleDividerItemDecoration(getActivity(), dp2px(getActivity(), 0), 10));
        //mCommonRvAdapter.getRecyclerView().addItemDecoration();

        return mCommonRvAdapter;
    }

    @Override
    protected String getTitleText() {
        return "商家列表";
    }

    @Override
    protected void onLoadData(int index, int pageSize) {


        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).businessListByCommodity(mCategoryId,mName,index,pageSize, lng, lat))
                .subscribe(new BaseObserver<HomeMerchant>() {
                    @Override
                    public void onSuccess(HomeMerchant it) {
                        if (mCommonRvAdapter != null ) {

                            notifyDataChanged(it.getList());
                        }
                    }
                });


    }


//    @BindView(R.id.rv_home_fjsj)
//    RecyclerView rvHomeFjsj;
//    CommonRvAdapter<HomeMerchant.ListBean> mCommonRvAdapter;
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_business_list;
//    }
//
//    @Override
//    protected void initView() {
//
//
//
//    }
//
//
//    /**
//     * 附近商家
//     *
//     * @param
//     */
//    private void initNearbyShops(List<HomeMerchant.ListBean> list) {
//
//
//        mCommonRvAdapter = new CommonRvAdapter<HomeMerchant.ListBean>(true, BR.homeshops, getActivity(), list, R.layout.item_search) {
//            @Override
//            public void convert(CommonViewHolderRv holder, HomeMerchant.ListBean it, int position) {
//                GlideLoad.loadImage(holder.getItemView(R.id.iv_shop_head), it.getSiteHeaderUri());
//                RecyclerView rvSearchChird = holder.itemView.findViewById(R.id.rv_search_chird);
//                GridLayoutManager manager = new GridLayoutManager(getActivity(), 4);
//                manager.setOrientation(RecyclerView.VERTICAL);
//                rvSearchChird.setLayoutManager(manager);
//                CommonRvAdapter<HomeMerchant.ListBean.AppCommodityDTOSBean> mAdapter = new CommonRvAdapter<HomeMerchant.ListBean.AppCommodityDTOSBean>(true, BR.home_shop_chird, getActivity(), it.getAppCommodityDTOS(), R.layout.item_search_chird, 4) {
//                    @Override
//                    public void convert(CommonViewHolderRv holder, HomeMerchant.ListBean.AppCommodityDTOSBean booth, int position) {
//                        holder.setImageUrl(R.id.iv_serch_chird, booth.getCommodityHeaderUri(), 5);
//                        holder.setText(R.id.tv_activitySalePrice, "¥" + booth.getMinPrice());
//                    }
//                };
//                mAdapter.setOnItemClickListener((index) -> {
////                    int commodityId = mAdapter.getData().get(index).getCommodityId();
////                    CommodityDetailsActivity.startActivity(getActivity(),commodityId);
//                    HomeMerchant.ListBean listBean = mCommonRvAdapter.getData().get(position);
//                    ShopDetailsActivity.startActivity(getActivity(), listBean.getSiteName(), listBean.getId(), "", "");
//
//
//                });
//                mAdapter.setNeedEmptyView(false);
//                rvSearchChird.setNestedScrollingEnabled(false);
//                rvSearchChird.setAdapter(mAdapter);
//
//
//            }
//        };
//
//        mCommonRvAdapter.setOnItemClickListener(new CommonRvAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//                HomeMerchant.ListBean listBean = mCommonRvAdapter.getData().get(position);
//                ShopDetailsActivity.startActivity(getActivity(), listBean.getSiteName(), listBean.getId(), "", "");
//            }
//        });
//
//        mCommonRvAdapter.setNeedEmptyView(false);
//        rvHomeFjsj.setAdapter(mCommonRvAdapter);
//        rvHomeFjsj.addItemDecoration(new SimpleDividerItemDecoration(getActivity(), dp2px(getActivity(), 0), 10));
//
//    }
//
//
//    @Override
//    protected void initData() {
//
//    }


}
