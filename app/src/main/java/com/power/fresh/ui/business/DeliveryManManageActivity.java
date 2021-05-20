package com.power.fresh.ui.business;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.chen.concise.RxHttp;
import com.hjq.toast.ToastUtils;
import com.power.common_opensurce.dialog.EvaluationDialog;
import com.power.fresh.R;
import com.power.fresh.api.FreshService;
import com.power.fresh.bean.CommodityDetails;
import com.power.fresh.bean.bussiness.配送管理;
import com.power.fresh.utils.Constant;
import com.powerrich.common.base.BaseDialog;
import com.powerrich.common.base.BaseListActivity;
import com.powerrich.common.base.BaseObserver;
import com.powerrich.common.base.UIActivity;
import com.powerrich.common.base.adapter.CommonRvAdapter;
import com.powerrich.common.base.adapter.CommonViewHolderRv;

import java.util.List;

/**
 * 配送管理
 */
public class DeliveryManManageActivity extends BaseListActivity<配送管理.ListBean> {

    /**
     *  1 : 销售订单
     */
    private int intentType = 0;
    private int mOrderId;


    public static void startActivity(Context context, String title) {
        Intent intent = new Intent(context, DeliveryManManageActivity.class);
        intent.putExtra("Title", title);
        context.startActivity(intent);
    }


    private CommonRvAdapter<配送管理.ListBean> mCommonRvAdapter;

    @Override
    protected CommonRvAdapter<配送管理.ListBean> getAdapter() {
        mCommonRvAdapter = new CommonRvAdapter<配送管理.ListBean>(getActivity(), null, R.layout.item_delivery_manager) {
            @Override
            public void convert(CommonViewHolderRv holder, 配送管理.ListBean item, int position) {
                holder.setText(R.id.tv_name, item.getName());
                holder.setText(R.id.tv_specification, Constant.get().encryptPhoneNum(item.getMobile()));
                holder.setText(R.id.tv_bind, "解除绑定");
            }
        };

        mCommonRvAdapter.setOnItemClickListener(new CommonRvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {


                int id = mCommonRvAdapter.getData().get(position).getDeliveryId();

                deliveryRemoveShow(id);


            }
        });

        return mCommonRvAdapter;
    }


    private void deliveryRemoveShow(int id) {

        String dialogContent;
        if (intentType == 1) {
            dialogContent = "确定选择该配送员吗?";
        } else {
            dialogContent = "确定绑定该配送员吗?";
        }

        BaseDialog baseDialog = new EvaluationDialog.Builder(this).setMessage(dialogContent).setConfirm("确定").setCancel("取消").setListener(dialog
                        ->
                {

                    if (intentType == 1) {


                        Constant.get().userBusinessOeliveryOrderSending(id, mOrderId, new Constant.IListener() {
                            @Override
                            public void onSuccess(Object o) {
                                setResult(RESULT_OK);
                                finish();
                            }
                        });




                    } else {
                        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userBusinessDeliveryRemove(id))
                                .subscribe(new BaseObserver<Object>() {
                                    @Override
                                    public void onSuccess(Object it) {
                                        ToastUtils.show("解绑成功");
                                        refreshData();
                                    }
                                });
                    }
                }

        ).create();
        baseDialog.show();
    }


    @Override
    public void onRightClick() {
        startActivity(DeliveryBindActivity.class);
    }

    @Override
    protected String getTitleText() {
        getTitleBar().setIvRight(R.mipmap.search_white_busy);

        String title = getIntent().getStringExtra("Title");
        mOrderId = getIntent().getIntExtra("OrderId", 0);

        if (!TextUtils.isEmpty(title)) {
            intentType = 1;
            return title;
        }

        return "配送员管理";
    }

    @Override
    protected void onLoadData(int index, int pageSize) {
        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).businessDeliveryList(index, pageSize))
                .subscribe(new BaseObserver<配送管理>() {
                    @Override
                    public void onSuccess(配送管理 it) {

                        notifyDataChanged(it.getList());
                    }
                });
    }
}
