package com.power.fresh.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.databinding.library.baseAdapters.BR;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.amap.api.location.AMapLocation;
import com.chen.concise.RxHttp;
import com.hjq.toast.ToastUtils;
import com.power.common_opensurce.App;
import com.power.common_opensurce.utils.LocationUtil;
import com.power.fresh.MainActivity;
import com.power.fresh.R;
import com.power.fresh.adapter.BannerImageAdapter;
import com.power.fresh.api.FreshService;
import com.power.common_opensurce.utils.LocationDataBean;
import com.power.fresh.bean.event.EventSort;
import com.power.fresh.bean.home.HomeBanner;
import com.power.fresh.bean.home.HomeBooth;
import com.power.fresh.bean.home.HomeGouponBean;
import com.power.fresh.bean.home.HomeMerchant;
import com.power.fresh.bean.home.HomeSeckill;
import com.power.fresh.bean.home.HomeSeckill2;
import com.power.fresh.ui.CommodityDetailsActivity;
import com.power.fresh.ui.LimitActivity;
import com.power.fresh.ui.SearchActivity;
import com.power.fresh.ui.SeckillActivity;
import com.power.fresh.ui.ShopDetailsActivity;
import com.power.fresh.ui.WebViewActivity;
import com.power.fresh.ui.business.BusinessListActivity2;
import com.power.fresh.utils.Constant;
import com.power.fresh.utils.dialog.HomeDialog;
import com.powerrich.common.base.BaseObserver;
import com.powerrich.common.base.MyLazyFragment;
import com.powerrich.common.base.adapter.CommonRvAdapter;
import com.powerrich.common.base.adapter.CommonViewHolderRv;
import com.powerrich.common.helper.LogUtils;
import com.powerrich.common.helper.PublicUtil;
import com.powerrich.common.helper.SPUtils;
import com.powerrich.common.image.GlideLoad;
import com.powerrich.common.permission.CcPermissions;
import com.powerrich.common.permission.Consumer;
import com.powerrich.common.permission.PermissionName;
import com.powerrich.common.widget.SimpleDividerItemDecoration;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.indicator.RoundLinesIndicator;
import com.youth.banner.util.BannerUtils;
import com.yzq.zxinglibrary.android.CaptureActivity;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;
import static com.powerrich.common.helper.PublicUtil.dp2px;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends MyLazyFragment implements CommonRvAdapter.OnItemClickListener {

    private static final int REQUEST_CODE_SCAN = 111;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.indicator)
    RoundLinesIndicator indicator;
    @BindView(R.id.rv_home_booth)
    RecyclerView rvHomeBooth;
    @BindView(R.id.tv_location_name)
    TextView tvLocationName;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    @BindView(R.id.ll_ad_list)
    LinearLayout llAdList;
    @BindView(R.id.ll_seckill_container)
    LinearLayout llSeckillContainer;
    @BindView(R.id.iv_seckill_10)
    ImageView ivSeckill10;
    @BindView(R.id.iv_seckill_14)
    ImageView ivSeckill14;
    @BindView(R.id.iv_seckill_20)
    ImageView ivSeckill20;
    @BindView(R.id.ll_limit_content)
    LinearLayout llLimitContent;
    @BindView(R.id.rv_home_limit)
    RecyclerView rvHomeLimit;
    @BindView(R.id.rv_home_fjsj)
    RecyclerView rvHomeFjsj;
    @BindView(R.id.iv_ad_01)
    ImageView ivAd01;
    @BindView(R.id.iv_ad_02)
    ImageView ivAd02;
    @BindView(R.id.iv_ad_03)
    ImageView ivAd03;
    @BindView(R.id.banner_ad)
    Banner bannerAd;
    @BindView(R.id.indicator_ad)
    RoundLinesIndicator indicatorAd;


    private CommonRvAdapter<HomeBooth.ListBean> mHomeBoothAdapter;
    private HomeBanner mBanner3;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    protected void initData() {

        getRequest();
        initLocation();
        // initNearbyShops();
    }

    private void getRequest() {
        requestBanner();
        requestBooth();
        requestADList();
        requestADList3();
        requestSeckill();
        requestLimit();
    }


    @Override
    public boolean statusBarDarkFont() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected int getTitleId() {
        return R.id.rl_title_search;
    }


    @Override
    protected void initView() {
        initDialog();
        initBannerAd(null, false);

        refresh.setOnRefreshListener(() -> postDelayed(
                () -> {
                    initLocation();
                    getRequest();
                    refresh.setRefreshing(false);
                }, 1500)
        );


    }


    private void initLocation() {
        LocationUtil.getInstance().getLocation(getActivity(), mILocationListener);
    }


    LocationUtil.ILocationListener mILocationListener = new LocationUtil.ILocationListener() {
        @Override
        public void getAMapLocation(AMapLocation it) {

            Log.e("jsc", "定位-HomeFragment-getAMapLocation:定位信息" + it.getCityCode());

            SPUtils.put(Constant.LNG, it.getLongitude());
            SPUtils.put(Constant.LAT, it.getLatitude());
            SPUtils.put(Constant.CITYCODE, it.getCityCode());
            LocationDataBean locationDataBean = new LocationDataBean();
            locationDataBean.setLatitude(it.getLatitude());
            locationDataBean.setLongitude(it.getLongitude());
            locationDataBean.setProvider(it.getProvince());
            locationDataBean.setCity(it.getCity());
            locationDataBean.setDistrict(it.getDistrict());

            locationDataBean.setAoiname(it.getAoiName());
            locationDataBean.setPoiname(it.getPoiName());
            locationDataBean.setStreetNum(it.getStreetNum());
            locationDataBean.setRoad(it.getRoad());
            locationDataBean.setStreet(it.getStreet());
            locationDataBean.setCityCode(it.getCityCode());
            locationDataBean.setAdCode(it.getAdCode());

            SPUtils.put(Constant.CITYENTITY, App.getmGson().toJson(locationDataBean));
            tvLocationName.setText(it.getCity());
            LocationUtil.getInstance().getLocation(getActivity(), null);
            requestNearbyShops(it.getLongitude(), it.getLatitude());
        }
    };

    private boolean isNearbyFrist = true;

    private void initDialog() {
        int id = App.getUserInfo().getUserBase().getId();
        if (id > 0) {
            if ((boolean) SPUtils.get(Constant.NEWHAND_FLAG + id, true)) {
                requestDialog();
            }
        }
    }

    /**
     * 请求当日优惠券
     * 请求失败 暂不操作
     */
    private void requestDialog() {
        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).homeCouponLis())
                .subscribe(new BaseObserver<HomeGouponBean>() {
                    @Override
                    public void onSuccess(HomeGouponBean it) {
                        String s = App.getmGson().toJson(it);
                        Log.e("jsc", "Home待完成-优惠券onSuccess:" + s);
                        if (it.getList().size() > 0) {
                            new HomeDialog.Builder(getActivity()).setListBean(it.getList()).show();
                        }
                    }
                });
    }


    private void requestBooth() {
        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).homeCategoryList("1", "1", "10"))
                .subscribe(new BaseObserver<HomeBooth>() {
                    @Override
                    public void onSuccess(HomeBooth it) {
                        initBooth(it.getList());
                    }
                });
    }

    private void initBooth(List<HomeBooth.ListBean> booths) {
        boolean isRefresh = false;
        if (isRefresh) {
            mHomeBoothAdapter.setData(booths);
        } else {
            if (rvHomeBooth==null) {
                return;
            }

            GridLayoutManager manager = new GridLayoutManager(getActivity(), 5);
            manager.setOrientation(RecyclerView.VERTICAL);
            rvHomeBooth.setLayoutManager(manager);
            mHomeBoothAdapter = new CommonRvAdapter<HomeBooth.ListBean>(getActivity(), booths, R.layout.item_home_booth, 10) {
                @Override
                public void convert(CommonViewHolderRv holder, HomeBooth.ListBean booth, int position) {
                    holder.setText(R.id.tv_title, booth.getCategoryName());
                    ImageView imageView = holder.itemView.findViewById(R.id.iv_icon);
                    GlideLoad.loadImage(imageView, booth.getImage());
                }
            };
            mHomeBoothAdapter.setNeedEmptyView(false);
            rvHomeBooth.setNestedScrollingEnabled(false);
            rvHomeBooth.setAdapter(mHomeBoothAdapter);
            mHomeBoothAdapter.setOnItemClickListener(position -> {
//                    叶旺(972686318) 18:06:33
//                    都是分类
//                    叶旺(972686318) 18:06:54
//                    跳商家列表
                //   ShopListActivity.startActivity(getActivity(), booths.get(position).getCategoryName(), String.valueOf(booths.get(position).getId()));

                if (getActivity() instanceof MainActivity) {

                    MainActivity mainActivity = (MainActivity) getActivity();
                    mainActivity.setCurrentViewPager(1);
                    String categoryName = mHomeBoothAdapter.getData().get(position).getCategoryName();

                    postDelayed(() -> EventBus.getDefault().post(new EventSort(categoryName, position)), 500);


                }


            });
        }
    }


    private void requestBanner() {
        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).homeAdList(1, "1"))
                .subscribe(new BaseObserver<HomeBanner>() {
                    @Override
                    public void onSuccess(HomeBanner it) {
                        initBanner(it.getList());
                    }
                });
    }


    /**
     * llSeckillContainer
     * 秒杀
     */
    private void requestSeckill() {
        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).homeActivityList("1", 3))
                .subscribe(new BaseObserver<HomeSeckill>() {
                    @Override
                    public void onSuccess(HomeSeckill it) {
                        if (it == null) {
                            return;
                        }
                        if (it.getListCount() > 0) {
                            if (!PublicUtil.isEmpty(it.getList().get(0))) {
                                ivSeckill10.setVisibility(View.VISIBLE);
                                GlideLoad.loadImage(ivSeckill10, it.getList().get(0).getImage(), 5);
                            } else {
                                ivSeckill10.setVisibility(View.INVISIBLE);
                            }
                            if (it.getList().size() >= 2) {
                                ivSeckill14.setVisibility(View.VISIBLE);
                                GlideLoad.loadImage(ivSeckill14, it.getList().get(1).getImage(), 5);
                            } else {
                                ivSeckill14.setVisibility(View.INVISIBLE);
                            }
                            if (it.getList().size() >= 3) {
                                ivSeckill20.setVisibility(View.VISIBLE);
                                GlideLoad.loadImage(ivSeckill20, it.getList().get(2).getImage(), 5);
                            } else {
                                ivSeckill20.setVisibility(View.INVISIBLE);
                            }
                        } else {
                            llSeckillContainer.setVisibility(View.GONE);
                        }

                    }
                });
    }

    /**
     * 限时热销 4-27
     */
    private void requestLimit() {

        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).homeActivityList("2", 3))
                .subscribe(new BaseObserver<HomeSeckill>() {
                    @Override
                    public void onSuccess(HomeSeckill it) {
                        if (!PublicUtil.isEmpty(it.getList().get(0).getAppActivityCommodityDTOS())) {
                            llLimitContent.setVisibility(View.VISIBLE);
                            initLimit(it.getList().get(0).getAppActivityCommodityDTOS());
                        } else {
                            llLimitContent.setVisibility(View.GONE);
                        }
                    }
                });
    }

    /**
     * 限时热销
     */
    private void initLimit(List<HomeSeckill2.ListBean> listBeans) {


        findViewById(R.id.tv_limit_more).setOnClickListener((v) -> {
            startActivity(LimitActivity.class);
        });


        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
        manager.setOrientation(RecyclerView.VERTICAL);
        rvHomeLimit.setLayoutManager(manager);

        CommonRvAdapter limitAdapter = new CommonRvAdapter<HomeSeckill2.ListBean>(getActivity(), listBeans, R.layout.item_home_limit, 3) {
            @Override
            public void convert(CommonViewHolderRv holder, HomeSeckill2.ListBean booth, int position) {

                holder.setImageUrl(R.id.iv_limit01, booth.getCommodityHeaderUri());
                holder.setText(R.id.tv_limit01_name, booth.getName());
                holder.setText(R.id.tv_limit01_english, booth.getSimpleInfo());
                if (!PublicUtil.isEmpty(booth.getRules().get(0))) {
                    holder.setText(R.id.tv_limit01_price_unit, "￥" + booth.getMinPrice() + "(" + booth.getRules().get(0).getNormsRule() + ")");
                }
                holder.setViewClickListener(R.id.iv_limit, (view) -> {
                    Constant.get().saveCart(booth.getBusinessId(), booth.getCommodityId(), booth.getRules().get(0).getId(), 1, true, null);

                });

            }
        };
        limitAdapter.setNeedEmptyView(false);
        rvHomeLimit.setNestedScrollingEnabled(false);
        rvHomeLimit.setAdapter(limitAdapter);
        limitAdapter.setOnItemClickListener(new CommonRvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });

    }


    private void requestNearbyShops(double lng, double lat) {
//        if (!isNearbyFrist) {
//            return;
//        }

        exeHttp(RxHttp.getRetrofitService(FreshService.class).homeBusinessHomeList(null, lng, lat, 4, 10, 1))
                .subscribe(new BaseObserver<HomeMerchant>() {
                    @Override
                    public void onSuccess(HomeMerchant it) {
                        if (!PublicUtil.isEmpty(it.getList())) {
                            initNearbyShops(it.getList());
                            isNearbyFrist = false;
                        }
                    }
                });
    }

    CommonRvAdapter<HomeMerchant.ListBean> mCommonRvAdapter;

    /**
     * 附近商家
     *
     * @param
     */
    private void initNearbyShops(List<HomeMerchant.ListBean> list) {


        mCommonRvAdapter = new CommonRvAdapter<HomeMerchant.ListBean>(true, BR.homeshops, getActivity(), list, R.layout.item_search) {
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
                    int commodityId = mCommonRvAdapter.getData().get(position).getAppCommodityDTOS().get(index).getCommodityId();
                    //    ShopDetailsActivity.startActivity(getActivity(), listBean.getSiteName(), listBean.getId(), "", "");
                    CommodityDetailsActivity.startActivity(getActivity(), commodityId);

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

//                CommodityDetailsActivity.startActivity(getActivity(), commodityId);

            }
        });

        mCommonRvAdapter.setNeedEmptyView(false);
        rvHomeFjsj.setAdapter(mCommonRvAdapter);
        rvHomeFjsj.addItemDecoration(new SimpleDividerItemDecoration(getActivity(), dp2px(getActivity(), 0), 10));

    }

    /**
     * 广告列表
     */
    private void requestADList() {
        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).homeAdList(2, "1"))
                .subscribe(new BaseObserver<HomeBanner>() {
                    @Override
                    public void onSuccess(HomeBanner it) {
                        initBannerAd(it.getList(), true);

//                        if (it.getListCount() > 0) {
//                            llAdList.removeAllViews();
//                            for (int i = 0; i < it.getList().size(); i++) {
//                                HomeBanner.ListBean listBean = it.getList().get(i);
//                                ImageView imageView = new ImageView(getActivity());
//                                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
//                                        PublicUtil.dip2px(getActivity(), 135));
//                                imageView.setLayoutParams(params);
//                                imageView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.picture_color_white));
//                                imageView.setPadding(PublicUtil.dip2px(getActivity(), 17), PublicUtil.dip2px(getActivity(), 10), PublicUtil.dip2px(getActivity(), 17), 0);
//                                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//                                GlideLoad.loadImage(imageView, listBean.getImgUrl());
//                                llAdList.addView(imageView);
//                            }
//                        }

                    }
                });
    }



    private void initBannerAd(List<HomeBanner.ListBean> listBeans,boolean isRefresh) {

        if (isRefresh) {
            bannerAd.getAdapter().setDatas(listBeans);
            bannerAd.getAdapter().notifyDataSetChanged();
        } else {
            //设置适配器
            bannerAd.setAdapter(new BannerImageAdapter(listBeans));
            //设置指示器
            bannerAd.setIndicator(new CircleIndicator(getActivity()));
            try {
                //设置点击事件
                bannerAd.setOnBannerListener((data, position) -> {


                    HomeBanner.ListBean listBean = (HomeBanner.ListBean) bannerAd.getAdapter().getData(position);
                    adClick(listBean);


                });
            } catch (Exception e) {
                e.printStackTrace();
            }

            //圆角
            bannerAd.setBannerRound(BannerUtils.dp2px(5));

            //添加画廊效果，可以参考我给的参数自己调试(不要和其他PageTransformer同时使用)
            bannerAd.setBannerGalleryEffect(25, 4, 0.94f);
            bannerAd.start();
        }
    }


    /**
     * 广告列表 首页分类下面加广告(宫格2*2)
     */
    private void requestADList3() {
        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).homeAdList(3, "1"))
                .subscribe(new BaseObserver<HomeBanner>() {
                    @Override
                    public void onSuccess(HomeBanner it) {
                        mBanner3 = it;
                        if (it.getListCount() > 0) {

                            for (int i = 0; i < it.getList().size(); i++) {
                                HomeBanner.ListBean listBean = it.getList().get(i);
                                switch (i) {
                                    case 0:
                                        GlideLoad.loadImage(ivAd01, listBean.getImgUrl(), 3);
                                        break;
                                    case 1:
                                        GlideLoad.loadImage(ivAd02, listBean.getImgUrl(), 3);
                                        break;
                                    case 2:
                                        GlideLoad.loadImage(ivAd03, listBean.getImgUrl(), 3);
                                        break;

                                }

                            }
                        }

                    }
                });
    }


    private void initBanner(List<HomeBanner.ListBean> listBeans) {
        boolean isRefresh = false;
        if (isRefresh) {
            banner.getAdapter().setDatas(listBeans);
        } else {
            //设置适配器
            banner.setAdapter(new BannerImageAdapter(listBeans));
            //设置指示器
            banner.setIndicator(new CircleIndicator(getActivity()));

            //设置点击事件
            banner.setOnBannerListener((data, position) -> {
//                Snackbar.make(banner, String.valueOf(position), Snackbar.LENGTH_SHORT).show();
//                有一个type
//                老油条鲜豆浆<weilenimen@foxmail.com> 18:01:01
//                1 只想 商品
//                老油条鲜豆浆<weilenimen@foxmail.com> 18:01:05
//                2 指向 商家
//                老油条鲜豆浆<weilenimen@foxmail.com> 18:01:14
//                3 指向http
                HomeBanner.ListBean listBean = listBeans.get(position);

                adClick(listBean);

            });

            //圆角
            banner.setBannerRound(BannerUtils.dp2px(5));

            //添加画廊效果，可以参考我给的参数自己调试(不要和其他PageTransformer同时使用)
            banner.setBannerGalleryEffect(25, 4, 0.94f);
            banner.start();
        }
    }


    /***
     * 广告点击
     */
    private void adClick(HomeBanner.ListBean listBean) {
        if (listBean.getType() == 1) {
            Integer integer = null;
            try {
                integer = Integer.valueOf(listBean.getContent());
            } catch (Exception e) {
                integer=0;
                ToastUtils.show("字段Content不能转Int");
                e.printStackTrace();
            }
            CommodityDetailsActivity.startActivity(getActivity(), integer);
        } else if (listBean.getType() == 2) {
            Integer businessId = 0;
            try {
                businessId = Integer.parseInt(listBean.getContent());
            } catch (Exception e) {
                businessId=0;
                ToastUtils.show("字段Content不能转Int");
                e.printStackTrace();
            }
            ShopDetailsActivity.startActivity(getActivity(), "", businessId, "", "");
        } else if (listBean.getType() == 3) {
            ToastUtils.show("跳指向http");
            WebViewActivity.startActivity(getActivity(), listBean.getContent());
        } else {
            LogUtils.i("jsc", "没有按照格式返回:" + listBean.getType());
        }
    }


    @Override
    public void onStop() {
        super.onStop();
        banner.stop();
    }

    @Override
    public void onDestroy() {
        banner.destroy();
        super.onDestroy();
    }


    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {

                String content = data.getStringExtra(com.yzq.zxinglibrary.common.Constant.CODED_CONTENT);

                LogUtils.i("jsc", "扫码内容:" + content);

                if (content.contains("http") && content.contains("nbningjiang.com/ningjiangshengxian")) {

                    if (content.contains("https://app.nbningjiang.com/ningjiangshengxian/h5/nj/index.html#/?recommend=")) {
                        ToastUtils.show("这是邀请码，不可以扫");
                    } else {
                        requestURL(content);
                    }

                } else if (content.contains("http")) {
                    WebViewActivity.startActivity(getActivity(), content);
                } else {


                    try {
                        JSONObject jsonObject = new JSONObject(content);
                        int businessId = jsonObject.optInt("businessId");
                        if (businessId > 0) {
                            ShopDetailsActivity.startActivity(getActivity(), "", businessId, "", "");
                            return;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    ToastUtils.show("地址格式有误");
                }


            }
        }
    }


    private void requestURL(String url) {

        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).qrUrl(url))
                .subscribe(new BaseObserver<Object>() {
                    @Override
                    public void onSuccess(Object it) {

                    }

                    @Override
                    protected void onError(String errorStr, int code) {
                        super.onError(errorStr, code);
                    }
                });

    }

    @OnClick({R.id.tv_buss_more, R.id.tv_seckill_more, R.id.iv_ad_01, R.id.iv_ad_02, R.id.iv_ad_03, R.id.iv_homepage_new_black, R.id.iv_seckill_10, R.id.iv_seckill_14, R.id.iv_seckill_20, R.id.ll_home_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {


            case R.id.tv_buss_more:
                //   BusinessListActivity2.startActivity(getActivity(),0,"");

              BusinessListActivity2.startActivity(getActivity(),0,"",1);
                //SeckillActivity.startActivity(getActivity(), 0);
                break;
            case R.id.tv_seckill_more:
                SeckillActivity.startActivity(getActivity(), 0);
                break;

            case R.id.iv_ad_01:
                try {
                    if (null!=mBanner3) {
                        adClick(mBanner3.getList().get(0));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;

            case R.id.iv_ad_02:
                try {
                    if (null!=mBanner3) {
                        adClick(mBanner3.getList().get(0));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.iv_ad_03:
                try {
                    if (null!=mBanner3) {
                        adClick(mBanner3.getList().get(0));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;


            case R.id.iv_homepage_new_black:

                CcPermissions.with(getActivity()).permission(PermissionName.CAMERA)
                        .request(new Consumer() {
                            @Override
                            public void accept(List<String> granted, boolean isAll) {
                                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                                startActivityForResult(intent, REQUEST_CODE_SCAN);
                            }
                        });


                break;
            case R.id.iv_seckill_10:
                SeckillActivity.startActivity(getActivity(), 0);
                break;
            case R.id.iv_seckill_14:
                SeckillActivity.startActivity(getActivity(), 1);
                break;
            case R.id.iv_seckill_20:
                SeckillActivity.startActivity(getActivity(), 2);
                break;
//            case R.id.ll_limit01:
//                startActivity(LimitActivity.class);
//                break;
//            case R.id.ll_limit02:
//                startActivity(LimitActivity.class);
//                break;
//            case R.id.ll_limit03:
//                startActivity(LimitActivity.class);
            //               break;
            case R.id.ll_home_search:
               // startActivity(SearchActivity.class);

                if (getActivity() instanceof MainActivity) {

                    MainActivity mainActivity = (MainActivity) getActivity();
                    mainActivity.setCurrentViewPager(1);
//                    String categoryName = mHomeBoothAdapter.getData().get(position).getCategoryName();
//
//                    postDelayed(() -> EventBus.getDefault().post(new EventSort(categoryName, position)), 500);


                }

                break;
        }
    }


}
