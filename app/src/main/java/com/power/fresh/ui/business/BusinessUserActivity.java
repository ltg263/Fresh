package com.power.fresh.ui.business;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import com.chen.concise.RxHttp;
import com.hjq.toast.ToastUtils;
import com.power.common_opensurce.utils.KeyboardUtils;
import com.power.fresh.R;

import com.power.fresh.api.FreshService;
import com.power.fresh.bean.BusinessUser;
import com.power.fresh.bean.CommodityList;
import com.power.fresh.ui.CommodityDetailsActivity;
import com.power.fresh.utils.Constant;
import com.power.fresh.utils.SupplyPopwindow;
import com.power.fresh.widget.DispatchEditText;
import com.powerrich.common.base.BaseListActivity;
import com.powerrich.common.base.BaseObserver;
import com.powerrich.common.base.adapter.CommonRvAdapter;
import com.powerrich.common.base.adapter.CommonViewHolderRv;
import com.powerrich.common.widget.SimpleDividerItemDecoration;

import static com.powerrich.common.helper.PublicUtil.dp2px;

/**
 * 会员管理
 */
public class BusinessUserActivity  extends BaseListActivity<BusinessUser.ListBean> {


    private CommonRvAdapter<BusinessUser.ListBean> mCommonRvAdapter;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, BusinessUserActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected CommonRvAdapter<BusinessUser.ListBean> getAdapter() {
        mCommonRvAdapter = new CommonRvAdapter<BusinessUser.ListBean>(this, null, R.layout.item_business_user) {
            @Override
            public void convert(CommonViewHolderRv holder, BusinessUser.ListBean item, int position) {

                holder.setImageUrl(R.id.iv_head,item.getPortraitUri());
                holder.setText(R.id.tv_user_name,item.getNickname());
                holder.setText(R.id.tv_time,item.getCreateTime());

            }
        };

        SimpleDividerItemDecoration simpleDividerItemDecoration = new SimpleDividerItemDecoration(getActivity(), 0, dp2px(getActivity(), 10));
        mCommonRvAdapter.setDividerItemDecoration(simpleDividerItemDecoration);




        return mCommonRvAdapter;
    }

    @Override
    protected String getTitleText() {
        return "会员数";
    }

    @Override
    protected void onLoadData(int index, int pageSize) {
        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userBusinessSserList(index, pageSize))
                .subscribe(new BaseObserver<BusinessUser>() {
                    @Override
                    public void onSuccess(BusinessUser it) {
                        notifyDataChanged(it.getList());
                    }
                });
    }


}