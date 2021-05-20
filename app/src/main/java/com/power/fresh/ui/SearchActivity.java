package com.power.fresh.ui;

import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chen.concise.RxHttp;
import com.hjq.toast.ToastUtils;
import com.power.common_opensurce.utils.KeyboardUtils;
import com.power.fresh.R;
import com.power.fresh.api.FreshService;
import com.power.fresh.bean.home.HomeMerchant;
import com.power.fresh.utils.Constant;
import com.powerrich.common.base.BaseObserver;
import com.powerrich.common.base.adapter.CommonRvAdapter;
import com.powerrich.common.base.adapter.CommonViewHolderRv;
import com.powerrich.common.base.UIActivity;
import com.powerrich.common.helper.PublicUtil;
import com.powerrich.common.helper.SPUtils;
import com.powerrich.common.image.GlideLoad;
import com.powerrich.common.widget.SimpleDividerItemDecoration;


import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.powerrich.common.helper.PublicUtil.dp2px;

/**
 * 搜索页面
 * desc: 跟商家列表的逻辑一样 ShopListActivity
 */
public class SearchActivity extends UIActivity {


    @BindView(R.id.et_search)
    EditText etSearch;
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
    private double lng, lat;

    private CommonRvAdapter mCommonRvAdapter = null;

    @Override
    protected int statusBarColor() {
        return R.color.public_white;
    }

    @Override
    public boolean statusBarDarkFont() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected boolean isXUI() {
        return true;
    }

    @Override
    protected int getTitleId() {
        return R.id.ll_title;
    }


    @Override
    protected void initData() {
        requestNearbyShops("");
    }

    @Override
    protected void initView() {
        lng = (double) SPUtils.get(Constant.LNG, 0.0);
        lat = (double) SPUtils.get(Constant.LAT, 0.0);
        initRecycle();
        KeyboardUtils.showSoftInput(this, etSearch);


        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (TextUtils.isEmpty(etSearch.getText().toString())) {
                        ToastUtils.show("搜搜商品不能为空");
                    } else {
                        requestNearbyShops(etSearch.getText().toString());
                    }
                    //点击搜索的时候隐藏软键盘
                    KeyboardUtils.hideKeyboard(etSearch);
                    // 在这里写搜索的操作,一般都是网络请求数据
                    return true;
                }

                return false;
            }
        });
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
            requestNearbyShops(etSearch.getText().toString());

        });

    }


    //综合
    private boolean zhongheFlag = true;
    private boolean scoreFlag;
    //推荐排序
    private boolean tuijianFlag;

    private void requestNearbyShops(String content) {

        if (lng == 0.0 && lat == 0.0) {
            ToastUtils.show("未获取到定位信息！");
            return;
        }

        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).homeBusinessList(content, lng, lat, 4, zhongheFlag, scoreFlag, tuijianFlag))
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
                CommonRvAdapter<HomeMerchant.ListBean.AppCommodityDTOSBean> mAdapter = new CommonRvAdapter<HomeMerchant.ListBean.AppCommodityDTOSBean>(true, BR.home_shop_chird, getActivity(), it.getAppCommodityDTOS(), R.layout.item_search_chird, 4) {
                    @Override
                    public void convert(CommonViewHolderRv holder, HomeMerchant.ListBean.AppCommodityDTOSBean booth, int position) {
                        holder.setImageUrl(R.id.iv_serch_chird, booth.getCommodityHeaderUri(), 5);
                        holder.setText(R.id.tv_activitySalePrice, "¥" + booth.getRules().get(0).getActivitySalePrice());
                    }
                };
                mAdapter.setOnItemClickListener((index) -> {


                    int commodityId = mAdapter.getData().get(index).getCommodityId();
                    CommodityDetailsActivity.startActivity(getActivity(),commodityId);



                });

                mAdapter.setNeedEmptyView(false);
                rvSearchChird.setNestedScrollingEnabled(false);
                rvSearchChird.setAdapter(mAdapter);


            }
        };

        rvSearch.setAdapter(mCommonRvAdapter);
        rvSearch.addItemDecoration(new SimpleDividerItemDecoration(getActivity(), dp2px(getActivity(), 0), 10));

    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }


}
