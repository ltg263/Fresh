package com.power.fresh.ui.my;

import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chen.concise.RxHttp;
import com.hjq.toast.ToastUtils;
import com.power.common_opensurce.App;
import com.power.fresh.R;
import com.power.fresh.api.FreshService;
import com.power.fresh.bean.AddressBean;
import com.powerrich.common.base.BaseListActivity;
import com.powerrich.common.base.BaseObserver;
import com.powerrich.common.base.adapter.CommonRvAdapter;
import com.powerrich.common.base.adapter.CommonViewHolderRv;
import com.yalantis.ucrop.util.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 送货地址
 */
public class ShippingListAddressActivity extends BaseListActivity<AddressBean.ListBean> {


    List<AddressBean.ListBean> list = new ArrayList<>();
    private CommonRvAdapter<AddressBean.ListBean> mCommonRvAdapter;


    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        FrameLayout pLayout = findViewById(R.id.bottom_content);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ScreenUtils.dip2px(this,50));
        TextView tv = new TextView(this);
        tv.setBackgroundColor(getResources().getColor(R.color.public_color_056839));
        tv.setGravity(Gravity.CENTER);
        tv.setText("+添加新地址");
        tv.setTextColor(getResources().getColor(R.color.picture_color_white));
        tv.setTextSize(16);
        tv.setLayoutParams(params);
        pLayout.addView(tv);
        tv.setOnClickListener(v->{
            startActivity(new Intent(this,AddAddressActivity.class));
        });
    }

    @Override
    protected CommonRvAdapter<AddressBean.ListBean> getAdapter() {

        mCommonRvAdapter = new CommonRvAdapter<AddressBean.ListBean>(this, list, R.layout.item_address_layout) {
            @Override
            public void convert(CommonViewHolderRv holder, AddressBean.ListBean item, int position) {
                CheckBox cbSelect = holder.getItemView(R.id.cb_select);
                cbSelect.setChecked(item.getIsDefault() == 1 ? true : false);
                holder.setText(R.id.tv_phone, item.getMobile());
                holder.setText(R.id.tv_content, item.getLocation());

                holder.setText(R.id.tv_receiver_user, "收货人："+item.getAcceptName());
                /** 编辑 */
                holder.getItemView(R.id.tv_edit).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AddAddressActivity.startActivity(getActivity(), App.getmGson().toJson(item));

                    }
                });
                /** 删除 */
                holder.getItemView(R.id.tv_del).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteAdress(position,item.getId());
                    }
                });
                /** 是否默认 */
                cbSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        update_default(item.getId(),isChecked?1:0);


                    }
                });


            }
        };

        return mCommonRvAdapter;
    }

    @Override
    protected String getTitleText() {
        return "收货地址";
    }

    @Override
    protected void onLoadData(int index, int page) {
        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userAddressList(index,page))
                .subscribe(new BaseObserver<AddressBean>() {
                    @Override
                    public void onSuccess(AddressBean it) {
                        notifyDataChanged(it.getList());
                    }
                });
    }


    private void deleteAdress(int position,int userAddressId){
        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userAddressRemove(userAddressId))
                .subscribe(new BaseObserver<String>() {
                    @Override
                    public void onSuccess(String it) {
                      ToastUtils.show("删除成功");
                      mCommonRvAdapter.getData().remove(position);
                      mCommonRvAdapter.notifyDataSetChanged();
                    }
                });
    }

    private void update_default(int userAddressId,int defaultStatus){
        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userAddressUpdateDefault(userAddressId,defaultStatus))
                .subscribe(new BaseObserver<String>() {
                    @Override
                    public void onSuccess(String it) {
                        initData();
                    }
                });
    }


}
