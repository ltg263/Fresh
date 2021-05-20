package com.power.fresh.ui.business;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.chen.concise.RxHttp;
import com.hjq.toast.ToastUtils;
import com.power.fresh.R;
import com.power.fresh.api.FreshService;
import com.power.fresh.bean.bussiness.待入库;
import com.power.fresh.bean.bussiness.提现查看;
import com.power.fresh.utils.dialog.HomeDialog;
import com.power.fresh.utils.dialog.PendingDialog;
import com.powerrich.common.base.BaseDialog;
import com.powerrich.common.base.BaseListActivity;
import com.powerrich.common.base.BaseObserver;
import com.powerrich.common.base.adapter.CommonRvAdapter;
import com.powerrich.common.base.adapter.CommonViewHolderRv;
import com.powerrich.common.helper.PublicUtil;
import com.powerrich.common.image.GlideLoad;
import com.powerrich.common.widget.SimpleDividerItemDecoration;

import java.util.List;

import static com.powerrich.common.helper.PublicUtil.dp2px;

/**
 * 待入库
 */
public class PendingActivity extends BaseListActivity<待入库.ListBean> {


    @Override
    protected CommonRvAdapter<待入库.ListBean> getAdapter() {
        CommonRvAdapter<待入库.ListBean> commonRvAdapter = new CommonRvAdapter<待入库.ListBean>(getActivity(), null, R.layout.item_pend) {
            @Override
            public void convert(CommonViewHolderRv holder, 待入库.ListBean item, int position) {
                holder.setImageUrl(R.id.iv_goods_icon,item.getImage());
                holder.setText(R.id.tv_shop_name,"供应商名称:"+item.getSupplyName());
                holder.setText(R.id.tv_goods_name,item.getCommodityName());
                holder.setText(R.id.tv_guige,"规格:"+item.getNorms());
                holder.setText(R.id.tv_sum,"待入库数量:"+item.getNum());
                holder.getItemView(R.id.btn_ruku_qr_code).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BaseDialog dialog = new PendingDialog.Builder(getActivity(), R.layout.dialog_pending).setDialogListener(new PendingDialog.Builder.DialogListener() {
                            @Override
                            public void canvetView(View view) {
                                ImageView viewById = view.findViewById(R.id.iv_content);
                                GlideLoad.loadImage(viewById, item.getQrLine());
                            }
                        }).show();
                        WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
                        attributes.width=PublicUtil.dip2px(getActivity(),230);
                        attributes.height = PublicUtil.dip2px(getActivity(),300);
                        dialog.getWindow().setAttributes(attributes);

                    }
                });

                holder.getItemView(R.id.btn_confirm).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        ruku(item.getLogNo());
                    }
                });
            }
        };
        SimpleDividerItemDecoration simpleDividerItemDecoration = new SimpleDividerItemDecoration(getActivity(), 0, dp2px(getActivity(), 1));
        commonRvAdapter.setDividerItemDecoration(simpleDividerItemDecoration);

        return commonRvAdapter;
    }


    private  void ruku(String logNo) {
        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userBusinessStockData(logNo))
                .subscribe(new BaseObserver<Object>() {
                    @Override
                    public void onSuccess(Object it) {


                    }
                });
    }

    @Override
    protected String getTitleText() {
        return "待入库";
    }

    @Override
    protected void onLoadData(int index, int pageSize) {
        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userBusinessStockBusinessList(index, pageSize))
                .subscribe(new BaseObserver<待入库>() {
                    @Override
                    public void onSuccess(待入库 it) {
                        List<待入库.ListBean> list = it.getList();
                        notifyDataChanged(list);

                    }
                });
    }
}
