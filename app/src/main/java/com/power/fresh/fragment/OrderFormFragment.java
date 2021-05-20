package com.power.fresh.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.power.fresh.R;
import com.power.fresh.adapter.OrderListFragmentAdapter;
import com.power.fresh.indicator.CommonIndicator;
import com.power.fresh.indicator.CommonNavigator;
import com.power.fresh.indicator.LinePagerIndicator;
import com.power.fresh.indicator.ViewPagerHelper;
import com.power.fresh.indicator.abs.CommonNavigatorAdapter;
import com.power.fresh.indicator.abs.IPagerIndicator;
import com.power.fresh.indicator.abs.IPagerTitleView;
import com.power.fresh.indicator.title.ColorFlipPagerTitleView;
import com.power.fresh.indicator.title.SimplePagerTitleView;
import com.power.fresh.utils.Constant;
import com.powerrich.common.base.MyLazyFragment;
import com.powerrich.common.helper.PublicUtil;
import com.powerrich.common.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 订单
 * A simple {@link Fragment} subclass.
 */
public class OrderFormFragment extends MyLazyFragment {

    @BindView(R.id.indicator)
    CommonIndicator indicator;
    @BindView(R.id.vpOrderType)
    ViewPager mViewPager;
    @BindView(R.id.mTitleBar)
    TitleBar mTitleBar;
    private List<String> mDataList= new ArrayList<>();;

    boolean isActivitFlag = false;
    int mIndex = 0;
    private String mTitle;

    /**
     * 0：普通用户 1 销售订单
     */
    int mIntentType;


    @Override
    public boolean statusBarDarkFont() {
        return false;
    }


    public static OrderFormFragment newInstance(int intentType) {
        Bundle args = new Bundle();
        args.putInt("intent_type", intentType);
        OrderFormFragment fragment = new OrderFormFragment();
        fragment.setArguments(args);
        return fragment;

    }

    public void isActivity(int position, String title) {
        mIndex = position;
        isActivitFlag = true;
        mTitle = title;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_order_form;
    }

    @Override
    protected int getTitleId() {
        return R.id.mTitleBar;
    }

    @Override
    protected void initView() {
        if (isActivitFlag) {
            mTitleBar.setIvLeftBack(true);
            mTitleBar.setLeftResId(R.mipmap.icon_public_arrow_back_white);
            mTitleBar.setTitle(mTitle);
        }
        Bundle bundle = getArguments();
        if (null != bundle) {
            mIntentType = bundle.getInt("intent_type",-1);
        }



        if (mIntentType == Constant.销售订单) {
            mDataList.add("全部");
            mDataList.add("待配送");
            mDataList.add("已发货");
            mDataList.add("已发送");
        }else {
            mDataList.add("全部");
            mDataList.add("待付款");
            mDataList.add("待收货");
            mDataList.add("待评价");
        }


        mViewPager.setAdapter(new OrderListFragmentAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, mIntentType,mDataList.size()));
        indicator.setBackgroundColor(Color.WHITE);

        CommonNavigator commonNavigator7 = new CommonNavigator(getContext());
        commonNavigator7.setScrollPivotX(0.65f);
        commonNavigator7.setAdjustMode(true);
        commonNavigator7.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorFlipPagerTitleView(context);
                simplePagerTitleView.setText(mDataList.get(index));
                simplePagerTitleView.setTextSize(14);
                simplePagerTitleView.setNormalColor(context.getResources().getColor(R.color.public_black));
                simplePagerTitleView.setSelectedColor(context.getResources().getColor(R.color.app_main_body));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(PublicUtil.dip2px(context, 4));
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                indicator.setRoundRadius(PublicUtil.dip2px(context, 3));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setColors(context.getResources().getColor(R.color.app_main_body));
                return indicator;
            }
        });
        indicator.setNavigator(commonNavigator7);
        ViewPagerHelper.setup(indicator, mViewPager);
    }

    /**
     * 结算
     */
    private void closeAnAccount() {


    }

    @Override
    protected void initData() {
        mViewPager.setCurrentItem(mIndex);
        //indicator.onPageSelected(index);
    }
}
