package com.power.fresh.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chen.concise.RxHttp;
import com.google.android.material.tabs.TabLayout;
import com.power.common_opensurce.App;
import com.power.fresh.R;
import com.power.fresh.adapter.BannerImageAdapter;
import com.power.fresh.api.FreshService;
import com.power.fresh.bean.CommodityDetails;
import com.power.fresh.bean.CommodityList;
import com.power.fresh.bean.home.HomeBanner;
import com.power.fresh.utils.Constant;
import com.powerrich.common.base.BaseObserver;
import com.powerrich.common.base.UIActivity;
import com.powerrich.common.base.adapter.CommonRvAdapter;
import com.powerrich.common.base.adapter.CommonViewHolderRv;
import com.powerrich.common.helper.SPUtils;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnPageChangeListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 商品详情
 */
public class CommodityDetailsActivity extends UIActivity {


    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.scrollView)
    NestedScrollView scrollView;
    @BindView(R.id.ll_commondity_category)
    LinearLayout llCommondityCategory;
    @BindView(R.id.ll_recommend_category)
    LinearLayout llRecommendCategory;
    @BindView(R.id.ll_details_category)
    LinearLayout llDetailsCategory;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tv_banner_index)
    TextView tvBannerIndex;
    @BindView(R.id.rv_recommend)
    RecyclerView rvRecommend;
    @BindView(R.id.iv_title_left)
    ImageView ivTitleLeft;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_state)
    TextView tvState;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_simpleinfo)
    TextView tvSimpleinfo;
    @BindView(R.id.tv_sitename)
    TextView tvSitename;
    @BindView(R.id.iv_shop)
    ImageView ivShop;
    @BindView(R.id.ll_spike)
    LinearLayout llSpike;
    @BindView(R.id.webView)
    WebView webView;


    private ArrayList<String> tab_title_list = new ArrayList<>();//存放标签页标题
    private ArrayList<Fragment> fragment_list = new ArrayList<>();//存放ViewPager下的Fragment

    /**
     * 上个页面的商品id
     */
    private int commondityId;
    private int distributorId;
    /**
     * 上个页面 规则id
     */
    private int normsId;

    @Override
    protected int statusBarColor() {
        return R.color.public_white;
    }

    @Override
    public boolean statusBarDarkFont() {
        return true;
    }

    public static void startActivity(Context context, int data) {
        Intent intent = new Intent(context, CommodityDetailsActivity.class);
        intent.putExtra("ID", data);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_commodity_details;
    }

    @Override
    protected int getTitleId() {
        return R.id.ll_title_bar;
    }

    @Override
    protected void initView() {

        commondityId = getIntent().getIntExtra("ID", 0);

        ivTitleLeft.setOnClickListener(v -> finish());

        ///秒杀 隐藏
        llSpike.setVisibility(View.GONE);

        tab_title_list.add("商品");
        tab_title_list.add("推荐");
        tab_title_list.add("详情");

        tablayout.addTab(tablayout.newTab().setText(tab_title_list.get(0)));
        tablayout.addTab(tablayout.newTab().setText(tab_title_list.get(1)));
        tablayout.addTab(tablayout.newTab().setText(tab_title_list.get(2)));

        tablayout.setTabIndicatorFullWidth(false);


        WebSettings webSetting = webView.getSettings();
        webSetting.setJavaScriptEnabled(true);
        // 缩放至屏幕的大小
        webSetting.setLoadWithOverviewMode(true);

        String token = (String) SPUtils.get("token", "token");

        String url = String.format("https://app.nbningjiang.com/ningjiangshengxian/h5/nj/index.html?token=%s&productId=%s#/productDefails", token, commondityId);


        Log.e("jsc", "CommodityDetailsActivity-initView:" + url);
        webView.loadUrl(url);

        initEvent();

        requestData();

        // initBanner();
        // initRecommend();
    }

    private void requestData() {
        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).commodityGet(commondityId))
                .subscribe(new BaseObserver<CommodityDetails>() {
                    @Override
                    public void onSuccess(CommodityDetails it) {
                        distributorId = it.getSiteId();
                        requestApplist(String.valueOf(it.getSiteId()));
                        initBanner(it.gettCommodityImgs());
                        tvName.setText(it.getName());
                        tvState.setText(it.getSimple());
                        tvPrice.setText(String.valueOf(it.getSalePrice()));
                        tvSimpleinfo.setText(it.getBusiness().getSimpleInfo());
                        tvSitename.setText(it.getBusiness().getSiteName());
                        normsId = it.getCommodityNormsRuleDTOS().get(0).getId();
                    }
                });
    }

    private void requestApplist(String businessId) {

        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).commodityAppList("1", businessId, null, null))
                .subscribe(new BaseObserver<CommodityList>() {
                    @Override
                    public void onSuccess(CommodityList it) {
                        initRecommend(it.getList());

                    }
                });


    }


    private void initRecommend(List<CommodityList.ListBean> listBeans) {
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
        manager.setOrientation(RecyclerView.VERTICAL);
        if (rvRecommend == null) {
            finish();
        }
        try {
            if (rvRecommend.getLayoutManager() == null) {
                rvRecommend.setLayoutManager(manager);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return;
        }


        CommonRvAdapter<CommodityList.ListBean> limitAdapter = new CommonRvAdapter<CommodityList.ListBean>(getActivity(), listBeans, R.layout.item_commodity_recomment, 6) {
            @Override
            public void convert(CommonViewHolderRv holder, CommodityList.ListBean it, int position) {
                holder.setImageUrl(R.id.iv_icon, it.getCommodityHeaderUri());
                holder.setText(R.id.tv_name, it.getName() + "\t\t" + it.getRules().get(0).getNormsRule());
                holder.setText(R.id.tv_price, String.valueOf(it.getRules().get(0).getSalePrice()));
                holder.itemView.findViewById(R.id.iv_limit).setOnClickListener(v -> {

                    Constant.get().saveCart(distributorId, it.getCommodityId(), it.getRules().get(0).getId(), 1, true, null);


                });

            }
        };
        limitAdapter.setNeedEmptyView(false);
        rvRecommend.setNestedScrollingEnabled(false);
        rvRecommend.setAdapter(limitAdapter);
        limitAdapter.setOnItemClickListener(new CommonRvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                int commodityId = limitAdapter.getData().get(position).getCommodityId();
                CommodityDetailsActivity.startActivity(getActivity(), commodityId);
            }
        });
    }


    private void initBanner(List<CommodityDetails.TCommodityImgsBean> list) {
        List<HomeBanner.ListBean> listBeans = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            HomeBanner.ListBean listBean = new HomeBanner.ListBean();
            listBean.setImgUrl(list.get(i).getImg());
            listBeans.add(listBean);
        }
        if (tvBannerIndex == null) {
            finish();
            return;
        }

        tvBannerIndex.setText(1 + "/" + listBeans.size());
        //设置适配器
        banner.setAdapter(new BannerImageAdapter(listBeans));

        banner.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tvBannerIndex.setText(position + 1 + "/" + listBeans.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        }).isAutoLoop(false);


    }


    @Override
    protected void initData() {

    }

    private int tabIndex = 0;//tablayout所处的下标
    private boolean scrollviewFlag = false;//标记是否是scrollview在滑动


    private void tabSelect(TabLayout.Tab tab) {
        if (!scrollviewFlag) {
            switch (tab.getPosition()) {
                case 0://scrollview移动到tv1的顶部坐标处
                    scrollView.scrollTo(0, llCommondityCategory.getTop());
                    break;
                case 1://scrollview移动到tv2的顶部坐标处
                    scrollView.scrollTo(0, llRecommendCategory.getTop());
                    break;
                case 2://scrollview移动到tv3的顶部坐标处
                    scrollView.scrollTo(0, llDetailsCategory.getTop());
                    break;
            }
            scrollView.stopNestedScroll();
        }
        //用户点击tablayout时，标记不是scrollview主动滑动
        scrollviewFlag = false;
    }

    private void initEvent() {
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.e("jsc2", "CommodityDetailsActivity-onTabSelected:" + tab.getPosition() + "-scrollviewFlag:" + scrollviewFlag);
                tabSelect(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.e("jsc2", "CommodityDetailsActivity-onTabUnselected:" + tab.getPosition() + "-scrollviewFlag:" + scrollviewFlag);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.e("jsc2", "CommodityDetailsActivity-onTabReselected:" + tab.getPosition() + "-scrollviewFlag:" + scrollviewFlag);
                tabSelect(tab);

            }
        });


        //scrollview滑动事件监听
        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                scrollviewFlag = true;
                tabIndex = tablayout.getSelectedTabPosition();
//                Log.e("jsc", "CommodityDetailsActivity-onScrollChange:" + scrollY + "-商品：" + llCommondityCategory.getBottom() + "-推荐：" + llRecommendCategory.getTop() + "-详细：" + llDetailsCategory.getTop());
                if (scrollY < llCommondityCategory.getBottom()) {
                    //  if (tabIndex != 0) {//增加判断，如果滑动的区域是tableIndex=0对应的区域，则不改变tablayout的状态
                    //   Log.e("jsc", "CommodityDetailsActivity-onScrollChange:0");
                    // tablayout.selectTab(tablayout.getTabAt(0));
                    tablayout.setScrollPosition(0, 0, true);
                    //  }
                } else if (scrollY >= llRecommendCategory.getTop() && scrollY < llDetailsCategory.getTop()) {
                    //  if (tabIndex != 1) {
                    //   Log.e("jsc", "CommodityDetailsActivity-onScrollChange:1");
                    // tablayout.selectTab(tablayout.getTabAt(1));
                    tablayout.setScrollPosition(1, 0, true);
                    //   }
                } else if (scrollY >= llDetailsCategory.getTop()) {
                    //   if (tabIndex != 2) {
                    //  Log.e("jsc", "CommodityDetailsActivity-onScrollChange:2");
                    //   tablayout.selectTab(tablayout.getTabAt(2));
                    tablayout.setScrollPosition(2, 0, true);
                    //   }
                }
                scrollviewFlag = false;
            }
        });


    }


    @OnClick({R.id.ll_shop_detail, R.id.iv_shop, R.id.tv_shop, R.id.tv_join_car})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_shop_detail:
                ShopIntroducedActivity.startActivity(getActivity(), distributorId);

                break;
            case R.id.iv_shop:
                startActivity(ShoppingCartActivity.class);
                break;
            case R.id.tv_shop:
                startActivity(ShoppingCartActivity.class);
                break;
            case R.id.tv_join_car:

                Constant.get().saveCart(distributorId, commondityId, normsId, 1, true, null);


                break;
        }
    }
}
