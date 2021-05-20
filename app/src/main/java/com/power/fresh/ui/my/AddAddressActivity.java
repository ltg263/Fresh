package com.power.fresh.ui.my;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.chen.concise.RxHttp;
import com.hjq.toast.ToastUtils;
import com.power.common_opensurce.App;
import com.power.fresh.R;
import com.power.fresh.api.FreshService;
import com.power.fresh.bean.AddressBean;
import com.power.common_opensurce.utils.LocationDataBean;
import com.power.fresh.utils.Constant;
import com.power.fresh.utils.OptionUtil1;
import com.power.fresh.widget.DeletableEditText;
import com.powerrich.common.base.BaseObserver;
import com.powerrich.common.base.UIActivity;
import com.powerrich.common.helper.SPUtils;
import com.powerrich.common.other.TextMaxInputWatcher;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 增加收货地址
 */
public class AddAddressActivity extends UIActivity {

    @BindView(R.id.et_name)
    DeletableEditText etName;
    @BindView(R.id.et_phone)
    DeletableEditText etPhone;
    @BindView(R.id.tv_ssq)
    TextView tvSsq;

    @BindView(R.id.et_adress_detail)
    DeletableEditText etAdressDetail;
    @BindView(R.id.cb_default)
    CheckBox cbDefault;

    private int districtId;
    private int id;
    private double lng, lat;

    private boolean isEdit = false;

    public static void startActivity(Context context, String data) {
        Intent intent = new Intent(context, AddAddressActivity.class);
        intent.putExtra("DATA", data);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_the_invitation;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        String data = getIntent().getStringExtra("DATA");
        etAdressDetail.addTextChangedListener(new TextMaxInputWatcher(etAdressDetail, 180));
        if (!TextUtils.isEmpty(data)) {
            isEdit = true;
            AddressBean.ListBean listBean = App.getmGson().fromJson(data, AddressBean.ListBean.class);
            id = listBean.getId();
            etName.setText(listBean.getAcceptName());
            etPhone.setText(listBean.getMobile());
            tvSsq.setText(listBean.getProvince() + listBean.getCity() + listBean.getRegion());
            etAdressDetail.setText(listBean.getLocation());
            cbDefault.setChecked(listBean.getIsDefault() == 1 ? true : false);
            districtId = listBean.getDistrictId();
        }


    }

    private void getLocation() {
        String s = (String) SPUtils.get(Constant.CITYENTITY, "");

        try {
            LocationDataBean aMapLocation = App.getmGson().fromJson(s, LocationDataBean.class);
            lng = aMapLocation.getLongitude();
            lat = aMapLocation.getLatitude();
            tvSsq.setText(aMapLocation.getProvince() + aMapLocation.getCity() + aMapLocation.getDistrict());
            etAdressDetail.setText(aMapLocation.getRoad() + aMapLocation.getPoiname() + aMapLocation.getStreet() + aMapLocation.getStreetNum());
            districtId = Integer.valueOf(aMapLocation.getAdCode());
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtils.show("获取定位失败");
        }

    }


    private void addAdress() {
        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userAddressSave(cbDefault.isChecked() ? 1 : 0, etName.getText().toString(), etPhone.getText().toString(), tvSsq.getText().toString() + etAdressDetail.getText().toString(), districtId))
                .subscribe(new BaseObserver<String>() {
                    @Override
                    public void onSuccess(String it) {
                        ToastUtils.show("添加成功");
                        finish();
                    }
                });
    }

    /**
     * 更新地址
     */
    private void updateAdress() {

        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userAddressUpdate(id, cbDefault.isChecked() ? 1 : 0, etName.getText().toString(), etPhone.getText().toString(), tvSsq.getText().toString() + etAdressDetail.getText().toString(), districtId))
                .subscribe(new BaseObserver<String>() {
                    @Override
                    public void onSuccess(String it) {
                        ToastUtils.show("添加成功");
                        finish();
                    }
                });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }


    private boolean isEmpty() {
        boolean flag =true;
        if (TextUtils.isEmpty(etName.getText())) {
              ToastUtils.show("请填写收货人");
        } else if (TextUtils.isEmpty(etPhone.getText())) {
            ToastUtils.show("请填写联系电话");
        } else if (TextUtils.isEmpty(tvSsq.getText())) {
            ToastUtils.show("请填写配送地址");
        }else if (TextUtils.isEmpty(etAdressDetail.getText())) {
            ToastUtils.show("请填写详细地址");
        }else{
            flag =false;
        }
        return flag;
    }


    @OnClick({R.id.rlt_location, R.id.btn_login, R.id.rlt_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rlt_location:
                getLocation();
                break;
            case R.id.btn_login:

                if (!isEmpty()) {
                    if (isEdit) {
                        updateAdress();
                    } else {
                        addAdress();
                    }
                }


                break;

            case R.id.rlt_address:

                OptionUtil1.getInstance().setCityListerner((a, b, c) -> {
                    districtId = c.id;
                    tvSsq.setText(a.name + "-" + b.name + '-' + c.name);
                    etAdressDetail.setText("");
                }).showAdressView(this);
                break;


        }
    }
}
