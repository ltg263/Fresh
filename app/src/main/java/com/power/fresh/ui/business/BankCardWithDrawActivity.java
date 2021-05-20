package com.power.fresh.ui.business;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chen.concise.RxHttp;
import com.hjq.toast.ToastUtils;
import com.power.common_opensurce.App;
import com.power.fresh.R;
import com.power.fresh.api.FreshService;
import com.power.fresh.bean.bussiness.YuE_Bean;
import com.power.fresh.bean.bussiness.银行卡列表;
import com.power.fresh.utils.Constant;
import com.powerrich.common.base.BaseObserver;
import com.powerrich.common.base.UIActivity;
import com.powerrich.common.image.GlideLoad;
import com.powerrich.common.widget.TitleBar;

import java.math.BigDecimal;
import java.math.RoundingMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 银行卡 "提现"
 */
public class BankCardWithDrawActivity extends UIActivity {


    @BindView(R.id.mTitleBar)
    TitleBar mTitleBar;
    @BindView(R.id.et_price)
    EditText etPrice;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.iv_bank)
    ImageView ivBank;
    @BindView(R.id.tv_bank_code)
    TextView tvBankCode;
    @BindView(R.id.ll_bank_data)
    LinearLayout llBankData;
    @BindView(R.id.tv_bank_no)
    TextView tvBankNo;
    @BindView(R.id.tv_select_bank)
    TextView tvSelectBank;

    int bankId = 0;
    private String mPrice="";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_with_draw;
    }


    @Override
    public void onRightClick() {
        startActivity(WithDrawListActivity.class);
    }

    @Override
    protected void initView() {
        mTitleBar.setIvRight(R.mipmap.tixian_white);
        llBankData.setVisibility(View.GONE);
        tvBankNo.setVisibility(View.GONE);
        tvSelectBank.setVisibility(View.VISIBLE);

    }

    @Override
    protected void initData() {
        getData();
    }

    private void getData() {


        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userBusinessGetBalance())
                .subscribe(new BaseObserver<YuE_Bean>() {
                    @Override
                    public void onSuccess(YuE_Bean it) {
                        mPrice = Constant.get().canvetDouble(it.getBalance());
                        tvPrice.setText(Constant.￥ + Constant.get().canvetDouble(it.getBalance()));
                    }
                });

        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userBusinessBankDefault())
                .subscribe(new BaseObserver<银行卡列表.ListBean>() {
                    @Override
                    public void onSuccess(银行卡列表.ListBean listBean) {
                        if (listBean != null) {

                            llBankData.setVisibility(View.VISIBLE);
                            tvBankNo.setVisibility(View.VISIBLE);
                            tvSelectBank.setVisibility(View.GONE);

                            GlideLoad.loadImage(ivBank, listBean.getBankLogo());
                            bankId = listBean.getId();
                            tvBankCode.setText(listBean.getBankCode());
                            tvBankNo.setText(listBean.getBankNo());
                        }
                    }
                });
    }

    @OnClick({R.id.cl_select_bank, R.id.tv_total_tixian, R.id.btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cl_select_bank:
                Intent intent = new Intent(getActivity(), BankCardListActivity.class);
                intent.putExtra("type", 1);
                startActivityForResult(intent, (resultCode, intent1) -> {
                    if (resultCode == RESULT_OK) {
                        llBankData.setVisibility(View.VISIBLE);
                        tvBankNo.setVisibility(View.VISIBLE);
                        tvSelectBank.setVisibility(View.GONE);
                        String data = intent1.getStringExtra("data");
                        银行卡列表.ListBean listBean = App.getmGson().fromJson(data, 银行卡列表.ListBean.class);
                        GlideLoad.loadImage(ivBank, listBean.getBankLogo());
                        bankId = listBean.getId();
                        tvBankCode.setText(listBean.getBankCode());
                        tvBankNo.setText(listBean.getBankNo());
                    }
                });
                break;
            case R.id.tv_total_tixian:
                try {
                    etPrice.setText(mPrice);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_commit:
                if (bankId == 0) {
                    ToastUtils.show("请选择提现银行卡");
                    return;
                }
                if (TextUtils.isEmpty(etPrice.getText().toString())) {
                    ToastUtils.show("请输入提现金额");
                    return;
                }

                Double aDouble = Double.valueOf(etPrice.getText().toString());
                BigDecimal bd = new BigDecimal(aDouble);
                bd = bd.setScale(2, RoundingMode.HALF_UP);
                double v = bd.doubleValue();// * 100
                exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).businessCashoutCreate(bankId, v))
                        .subscribe(new BaseObserver<Object>() {
                            @Override
                            public void onSuccess(Object it) {
                                ToastUtils.show("提现申请已提交");
                                finish();
                            }
                        });


                break;
        }
    }
}
