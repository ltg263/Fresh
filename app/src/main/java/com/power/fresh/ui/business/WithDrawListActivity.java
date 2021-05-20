package com.power.fresh.ui.business;

import android.view.View;

import com.chen.concise.RxHttp;
import com.noober.background.view.BLTextView;
import com.power.fresh.R;
import com.power.fresh.api.FreshService;
import com.power.fresh.bean.bussiness.提现查看;
import com.power.fresh.utils.Constant;
import com.powerrich.common.base.BaseListActivity;
import com.powerrich.common.base.BaseObserver;
import com.powerrich.common.base.adapter.CommonRvAdapter;
import com.powerrich.common.base.adapter.CommonViewHolderRv;
import com.powerrich.common.widget.SimpleDividerItemDecoration;

import java.util.List;

import static com.powerrich.common.helper.PublicUtil.dp2px;

/**
 * 提现列表
 */
public class WithDrawListActivity extends BaseListActivity<提现查看.ListBean> {


    private CommonRvAdapter<提现查看.ListBean> mCommonRvAdapter;

    @Override
    protected CommonRvAdapter<提现查看.ListBean> getAdapter() {
        mCommonRvAdapter = new CommonRvAdapter<提现查看.ListBean>(getActivity(), null, R.layout.item_tixian) {
            @Override
            public void convert(CommonViewHolderRv holder, 提现查看.ListBean item, int position) {
                holder.setText(R.id.tv_price, Constant.￥ + item.getAmount());
                holder.setText(R.id.tv_bank_type, item.getParameterJSON().getBankName());
                holder.setText(R.id.tv_user_name, "姓名: " + item.getParameterJSON().getBankAccount());
                holder.setText(R.id.tv_bank_no, item.getParameterJSON().getBankNo());
                holder.setText(R.id.tv_bank_date, "申请时间: " + item.getCreateTime());


                BLTextView textView = holder.getItemView(R.id.tv_qxtx);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).businessCashOutCancel(item.getId()))
                                .subscribe(new BaseObserver<Object>() {
                                    @Override
                                    public void onSuccess(Object it) {
                                        refreshData();

                                    }
                                });


                    }
                });
                //1, //审核中, 2已完成 3 已取消
                if (item.getStatus() == 1) {
                    holder.setText(R.id.tv_status, "审核中");
                    textView.setVisibility(View.VISIBLE);
                } else if (item.getStatus() == 2) {
                    holder.setText(R.id.tv_status, "已完成");
                    textView.setVisibility(View.GONE);
                } else { //if (item.getStatus()==3)
                    holder.setText(R.id.tv_status, "已取消");
                    textView.setVisibility(View.GONE);
                }
            }
        };
        mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(getActivity(), dp2px(getActivity(), 0), dp2px(getActivity(), 10)));


        return mCommonRvAdapter;
    }

    @Override
    protected String getTitleText() {
        return "提现列表";
    }

    @Override
    protected void onLoadData(int index, int pageSize) {
        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userBusinessDashList(index, pageSize))
                .subscribe(new BaseObserver<提现查看>() {
                    @Override
                    public void onSuccess(提现查看 it) {
                        List<提现查看.ListBean> list = it.getList();
                        notifyDataChanged(list);

                    }
                });
    }

    private void cancelDash(int cashOutId) {


    }
}
