package com.power.fresh.ui.my;

import android.content.Intent;

import com.hjq.toast.ToastUtils;
import com.power.fresh.R;
import com.power.fresh.bean.home.HomeGouponBean;
import com.power.fresh.utils.Constant;
import com.powerrich.common.base.BaseListActivity;
import com.powerrich.common.base.adapter.CommonRvAdapter;
import com.powerrich.common.base.adapter.CommonViewHolderRv;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的优惠券
 */
public class DiscountActivity extends BaseListActivity<HomeGouponBean.ListBean> {
    List<HomeGouponBean.ListBean> list = new ArrayList<>();

    private CommonRvAdapter<HomeGouponBean.ListBean> mCommonRvAdapter;

    @Override
    protected CommonRvAdapter<HomeGouponBean.ListBean> getAdapter() {

        setBgColor(R.color.public_color_F2F2F2);
        // getRefreshLayout().setEnableLoadMore(false);
        mCommonRvAdapter = new CommonRvAdapter<HomeGouponBean.ListBean>(this, list, R.layout.item_coupons) {
            @Override
            public void convert(CommonViewHolderRv holder, HomeGouponBean.ListBean item, int position) {
                holder.setText(R.id.tv_type, item.getType() == 1 ? "满减券" : "打折券");
                holder.setText(R.id.tv_note, item.getNote());
                holder.setText(R.id.tv_price, item.getSubAmount());
                holder.setText(R.id.tv_name, item.getCouponName());
                holder.setText(R.id.tv_user_type, item.getUseType() == 1 ? "全场通用" : "部分商品使用");
                holder.setText(R.id.tv_expiretime, "有效期至 " + item.getExpireTime());
            }
        };

        mCommonRvAdapter.setOnItemClickListener(new CommonRvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (getIntent().getIntExtra("TYPE", 0) == 1) {
                    double totalPrice = getIntent().getDoubleExtra("TotalPrice", 0.0);

                    if (list.get(position).getSubAmount()>totalPrice) {
                        ToastUtils.show("优惠券金额不能大于商品价格");
                    }else if (list.get(position).getSubAmount()==totalPrice) {
                        ToastUtils.show("优惠券金额不能等于商品价格");
                    }else{
                        setResult(RESULT_OK, new Intent().putExtra("item_bean", list.get(position).toString()));
                        finish();
                    }


                }
            }
        });

        return mCommonRvAdapter;
    }

    @Override
    protected String getTitleText() {
        return "我的优惠券";
    }

    @Override
    protected void onLoadData(int index, int pageSize) {
        Constant.get().getUserCouponList(index, pageSize, homeGouponBean -> {
            list = homeGouponBean.getList();
            notifyDataChanged(list);
        });
    }


}
