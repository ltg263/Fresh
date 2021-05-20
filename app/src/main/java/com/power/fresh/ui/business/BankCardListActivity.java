package com.power.fresh.ui.business;

import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chen.concise.RxHttp;
import com.hjq.toast.ToastUtils;
import com.power.common_opensurce.App;
import com.power.common_opensurce.dialog.EvaluationDialog;
import com.power.fresh.R;
import com.power.fresh.api.FreshService;
import com.power.fresh.bean.bussiness.银行卡列表;
import com.powerrich.common.base.BaseDialog;
import com.powerrich.common.base.BaseListActivity;
import com.powerrich.common.base.BaseObserver;
import com.powerrich.common.base.adapter.CommonRvAdapter;
import com.powerrich.common.base.adapter.CommonViewHolderRv;
import com.yalantis.ucrop.util.ScreenUtils;

/**
 * 银行卡管理
 */
public class BankCardListActivity extends BaseListActivity<银行卡列表.ListBean> {


    private CommonRvAdapter<银行卡列表.ListBean> mCommonRvAdapter;
    private int mType;

    @Override
    protected CommonRvAdapter<银行卡列表.ListBean> getAdapter() {
        mCommonRvAdapter = new CommonRvAdapter<银行卡列表.ListBean>(getActivity(),null,R.layout.item_bank_list) {
            @Override
            public void convert(CommonViewHolderRv holder, 银行卡列表.ListBean item, int position) {
                holder.setImageUrl(R.id.iv_bank,item.getBankLogo());
                holder.setText(R.id.tv_bank_code,item.getBankCode());
                holder.setText(R.id.tv_bank_no,item.getBankNo());
                holder.getItemView(R.id.tv_unbound).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int id = mCommonRvAdapter.getData().get(position).getId();
                        BaseDialog baseDialog = new EvaluationDialog.Builder(getActivity()).setMessage("确定解绑吗?").setConfirm("确定").setCancel("取消").setListener(dialog
                                ->  exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userBusinessBankRemove(id))
                                .subscribe(new BaseObserver<Object>() {
                                    @Override
                                    public void onSuccess(Object it) {
                                        ToastUtils.show("解绑成功");
                                        refreshData();
                                    }
                                })).create();
                        baseDialog.show();
                    }
                });

            }
        };

        mCommonRvAdapter.setOnItemClickListener(new CommonRvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                if (mType==1) {
                    Intent intent = new Intent();
                    intent.putExtra("data", App.getmGson().toJson(mCommonRvAdapter.getData().get(position)));
                    setResult(RESULT_OK,intent);
                    finish();
                    return;
                }
                int id = mCommonRvAdapter.getData().get(position).getId();
                BaseDialog baseDialog = new EvaluationDialog.Builder(getActivity()).setMessage("确定解绑吗?").setConfirm("确定").setCancel("取消").setListener(dialog
                        ->  exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userBusinessBankRemove(id))
                        .subscribe(new BaseObserver<Object>() {
                            @Override
                            public void onSuccess(Object it) {
                                ToastUtils.show("解绑成功");
                                refreshData();
                            }
                        })).create();
                baseDialog.show();



            }
        });

        return mCommonRvAdapter;
    }

    @Override
    protected String getTitleText() {
        mType = getIntent().getIntExtra("type", 0);
        return "我的银行卡";
    }

    @Override
    protected void onLoadData(int index, int pageSize) {
        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userBusinessBankList(index,pageSize))
                .subscribe(new BaseObserver<银行卡列表>() {
                    @Override
                    public void onSuccess(银行卡列表 it) {
                        notifyDataChanged(it.getList());

                    }
                });
    }


    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        FrameLayout pLayout = findViewById(R.id.bottom_content);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ScreenUtils.dip2px(this,50));
        TextView tv = new TextView(this);
        tv.setBackgroundColor(getResources().getColor(R.color.public_color_056839));
        tv.setGravity(Gravity.CENTER);
        tv.setText("添加银行卡");
        tv.setTextColor(getResources().getColor(R.color.picture_color_white));
        tv.setTextSize(16);
        tv.setLayoutParams(params);
        pLayout.addView(tv);
        tv.setOnClickListener(v->{
            startActivity(new Intent(this, BankAddCardActivity.class));
        });
    }
}
