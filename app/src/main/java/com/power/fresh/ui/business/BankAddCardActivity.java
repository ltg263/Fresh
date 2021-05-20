package com.power.fresh.ui.business;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.chen.concise.RxHttp;
import com.hjq.toast.ToastUtils;
import com.power.fresh.R;
import com.power.fresh.api.FreshService;
import com.power.fresh.bean.bussiness.选择银行;
import com.power.fresh.bean.reponse.BankReponse;
import com.power.fresh.utils.OptionUtil;
import com.power.fresh.utils.option.OptionBean;
import com.power.fresh.widget.DeletableEditText;
import com.powerrich.common.base.BaseObserver;
import com.powerrich.common.base.UIActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 添加银行卡
 */
public class BankAddCardActivity extends UIActivity {


    @BindView(R.id.et_name)
    DeletableEditText etName;
    @BindView(R.id.et_bank_card_no)
    DeletableEditText etBankCardNo;
    @BindView(R.id.tv_select_bank)
    TextView tvSelectBank;
    @BindView(R.id.et_branch_name)
    DeletableEditText etBranchName;
    @BindView(R.id.et_mobile)
    DeletableEditText etMobile;
    @BindView(R.id.cb_default)
    CheckBox cbDefault;
    private String bankId;

    private List<OptionBean> mOptions1Items = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_bank_card;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).bankListAll())
                .subscribe(new BaseObserver<List<选择银行>>() {
                    @Override
                    public void onSuccess(List<选择银行> it) {
                        for (int i = 0; i < it.size(); i++) {
                            mOptions1Items.add(new OptionBean().setName(it.get(i).getBank()).setTag(String.valueOf(it.get(i).getId())));

                        }

                    }
                });


    }



    private boolean isEmpty() {
        if (TextUtils.isEmpty(etName.getText().toString())) {
            ToastUtils.show("请输入持卡人姓名");
            return true;
        } else if (TextUtils.isEmpty(etBankCardNo.getText().toString())) {
            ToastUtils.show("请输入银行卡卡号");
            return true;
        } else if (TextUtils.isEmpty(tvSelectBank.getText().toString())) {
            ToastUtils.show("请选择银行");
            return true;
        } else if (TextUtils.isEmpty(etBranchName.getText().toString())) {
            ToastUtils.show("请输入开户行名称");
            return true;
        } else if (TextUtils.isEmpty(etMobile.getText().toString())) {
            ToastUtils.show("请输入预留手机号");
            return true;
        }

        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }

    @OnClick({R.id.rl_select_bank, R.id.btn_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_select_bank:
                if (mOptions1Items.size()>0) {
                    OptionUtil.getInstance().showOption(getActivity(), mOptions1Items, new OnOptionsSelectListener() {
                        @Override
                        public void onOptionsSelect(int options1, int options2, int options3, View v) {
                            bankId =mOptions1Items.get(options1).getTag();
                            tvSelectBank.setText(mOptions1Items.get(options1).getName());
                        }
                    });
                }else{
                    ToastUtils.show("数据加载中");
                }

                break;
            case R.id.btn_add:
                addBank();
                break;
        }
    }

    private  void addBank() {

        if (isEmpty()) {
            return;
        }


        BankReponse bankReponse = new BankReponse();
        bankReponse.setName(etName.getText().toString());
        bankReponse.setBankNo(etBankCardNo.getText().toString());
        bankReponse.setBankId(bankId);
        bankReponse.setBankBranch(etBranchName.getText().toString());
        bankReponse.setMobile(etMobile.getText().toString());
        if (cbDefault.isChecked()) {
            bankReponse.setHasDefault("1");
        } else {
            bankReponse.setHasDefault("2");
        }

        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userBusinessBankSave(bankReponse))
                .subscribe(new BaseObserver<Object>() {
                    @Override
                    public void onSuccess(Object it) {
                        ToastUtils.show("添加成功");
                        finish();

                    }
                });

    }
}
