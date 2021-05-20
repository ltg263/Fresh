package com.power.fresh.ui;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chen.concise.Response;
import com.chen.concise.RxHttp;
import com.hjq.toast.ToastUtils;
import com.power.common_opensurce.utils.LocationUtil;
import com.power.fresh.R;
import com.power.fresh.api.FreshService;
import com.power.fresh.bean.home.HomeMerchant;
import com.power.fresh.ui.business.SupplyListActivity;
import com.power.fresh.utils.Constant;
import com.powerrich.common.base.BaseObserver;
import com.powerrich.common.base.adapter.CommonRvAdapter;
import com.powerrich.common.base.adapter.CommonViewHolderRv;
import com.powerrich.common.base.UIActivity;
import com.powerrich.common.helper.PublicUtil;
import com.powerrich.common.helper.SPUtils;
import com.powerrich.common.image.GlideLoad;
import com.powerrich.common.widget.SimpleDividerItemDecoration;
import com.powerrich.common.widget.TitleBar;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;

import static com.powerrich.common.helper.PublicUtil.dp2px;

/**
 * 商家列表
 * desc：跟搜索的逻辑一样(SearchActivity)
 */
public class ShopListActivity extends UIActivity {

    @BindView(R.id.rb_01)
    RadioButton rb01;
    @BindView(R.id.rb_02)
    RadioButton rb02;
    @BindView(R.id.rb_03)
    RadioButton rb03;
    @BindView(R.id.rb_04)
    RadioButton rb04;
    @BindView(R.id.rg_search)
    RadioGroup rgSearch;
    @BindView(R.id.rv_search)
    RecyclerView rvSearch;
    @BindView(R.id.title_bar)
    TitleBar titleBar;
    private double lng, lat;

    private CommonRvAdapter<HomeMerchant.ListBean> mCommonRvAdapter = null;

    //综合
    private boolean zhongheFlag = true;
    private boolean scoreFlag = false;
    //推荐排序
    private boolean tuijianFlag = true;

    /**
     * 分类id
     */
    String categoryId;
    private String mTitle;
    /**
     * 1 是供应商列表
     */
    private int mType;

    /**
     * 首页展台的跳转
     */
    public static void startActivity(Context context, String title, String categoryId) {
        Intent intent = new Intent(context, ShopListActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("categoryId", categoryId);
        context.startActivity(intent);
    }

    /**
     * 供应商 “选购商品”的跳转
     */
    public static void startBussinessActivity(Context context, String title, String categoryId) {
        Intent intent = new Intent(context, ShopListActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("categoryId", categoryId);
        intent.putExtra("type", 1);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop_list;
    }


    @Override
    protected void initView() {
        mType = getIntent().getIntExtra("type", 0);
        mTitle = getIntent().getStringExtra("title");
        categoryId = getIntent().getStringExtra("categoryId");
        if (TextUtils.isEmpty(categoryId)) {
            categoryId = "";
        }
        titleBar.setTitle(mTitle);

        lng = (double) SPUtils.get(Constant.LNG, 0.0);
        lat = (double) SPUtils.get(Constant.LAT, 0.0);

        if (lng == 0.0 && lat == 0.0) {
            LocationUtil.getInstance().getLocation(getActivity(), it -> {
                SPUtils.put(Constant.LNG, it.getLongitude());
                SPUtils.put(Constant.LAT, it.getLatitude());
                lng = it.getLongitude();
                lat = it.getLatitude();
            });

        }


        initRecycle();

        rgSearch.setOnCheckedChangeListener((group, checkedId) -> {
            /** 综合 */
            if (checkedId == R.id.rb_01) {
                zhongheFlag = true;
                tuijianFlag = false;
                scoreFlag = false;
                /** 推荐 */
            } else if (checkedId == R.id.rb_02) {
                zhongheFlag = false;
                tuijianFlag = true;
                scoreFlag = false;
                /** 评分 */
            } else if (checkedId == R.id.rb_03) {
                zhongheFlag = false;
                tuijianFlag = false;
                scoreFlag = true;
                /** 距离 */
            } else if (checkedId == R.id.rb_04) {
                zhongheFlag = false;
                tuijianFlag = false;
                scoreFlag = false;
            }
            requestNearbyShops();

        });


    }

    @Override
    protected void initData() {
        requestNearbyShops();
    }


    private void requestNearbyShops() {

        if (lng == 0.0 && lat == 0.0) {
            ToastUtils.show("未获取到定位信息！");
            return;
        }

        FreshService retrofitService = RxHttp.getRetrofitService(FreshService.class);
        Observable<Response<HomeMerchant>> responseObservable;
        if (mType == 1) {
            responseObservable = retrofitService.appBusinessSupplyList(lng, lat, 4, zhongheFlag, scoreFlag, tuijianFlag);
        } else {
            responseObservable = retrofitService.homeBusinessListList(categoryId, lng, lat, 4, zhongheFlag, scoreFlag, tuijianFlag);
        }
        exeHttpWithDialog(responseObservable)
                .subscribe(new BaseObserver<HomeMerchant>() {
                    @Override
                    public void onSuccess(HomeMerchant it) {
                        if (mCommonRvAdapter != null && !PublicUtil.isEmpty(it.getList())) {
                            mCommonRvAdapter.setData(it.getList());
                        }
                    }
                });
    }

    private void initRecycle() {
        initNearbyShops(null);
    }

    /**
     * 附近商家
     *
     * @param
     */
    private void initNearbyShops(List<HomeMerchant.ListBean> list) {


        mCommonRvAdapter = new CommonRvAdapter<HomeMerchant.ListBean>(true, BR.homeshops, getActivity(), list, R.layout.item_search) {
            @Override
            public void convert(CommonViewHolderRv holder, HomeMerchant.ListBean it, int position) {

                GlideLoad.loadImage(holder.getItemView(R.id.iv_shop_head),it.getSiteHeaderUri());
                RecyclerView rvSearchChird = holder.itemView.findViewById(R.id.rv_search_chird);
                GridLayoutManager manager = new GridLayoutManager(getActivity(), 4);
                manager.setOrientation(RecyclerView.VERTICAL);
                rvSearchChird.setLayoutManager(manager);
                CommonRvAdapter mAdapter = new CommonRvAdapter<HomeMerchant.ListBean.AppCommodityDTOSBean>(true, BR.home_shop_chird, getActivity(), it.getAppCommodityDTOS(), R.layout.item_search_chird, 4) {
                    @Override
                    public void convert(CommonViewHolderRv holder, HomeMerchant.ListBean.AppCommodityDTOSBean booth, int position) {

                            holder.setImageUrl(R.id.iv_serch_chird, booth.getCommodityHeaderUri(), 5);
                            holder.setText(R.id.tv_activitySalePrice, "¥" + booth.getRules().get(0).getActivitySalePrice());


                    }
                };


                mAdapter.setOnItemClickListener(i -> {
                    if (mType == 1) {
                        SupplyListActivity.startActivity(getActivity(), it.getSiteName(), it.getSiteHeaderUri(), it.getId());
                    } else {
                        HomeMerchant.ListBean listBean = mCommonRvAdapter.getData().get(position);
                        ShopDetailsActivity.startActivity(getActivity(), listBean.getSiteName(), listBean.getId(), categoryId, mTitle);
                    }

                });
                mAdapter.setNeedEmptyView(false);
                rvSearchChird.setNestedScrollingEnabled(false);
                rvSearchChird.setAdapter(mAdapter);


            }
        };

        mCommonRvAdapter.setOnItemClickListener((position1) -> {
            HomeMerchant.ListBean it = mCommonRvAdapter.getData().get(position1);
            if (mType == 1) {
                SupplyListActivity.startActivity(getActivity(), it.getSiteName(), it.getSiteHeaderUri(), it.getId());
            }else{
                ShopDetailsActivity.startActivity(getActivity(), it.getSiteName(), it.getId(), categoryId, mTitle);
            }

        });

        rvSearch.setAdapter(mCommonRvAdapter);
        rvSearch.addItemDecoration(new SimpleDividerItemDecoration(getActivity(), dp2px(getActivity(), 0), 10));

    }


}
