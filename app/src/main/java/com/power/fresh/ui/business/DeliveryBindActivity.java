package com.power.fresh.ui.business;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.chen.concise.RxHttp;
import com.hjq.toast.ToastUtils;
import com.power.common_opensurce.dialog.EvaluationDialog;
import com.power.common_opensurce.utils.KeyboardUtils;
import com.power.fresh.R;
import com.power.fresh.api.FreshService;
import com.power.fresh.bean.bussiness.配送管理;
import com.power.fresh.utils.Constant;
import com.powerrich.common.base.BaseDialog;
import com.powerrich.common.base.BaseObserver;
import com.powerrich.common.base.UIActivity;
import com.powerrich.common.base.adapter.CommonRvAdapter;
import com.powerrich.common.base.adapter.CommonViewHolderRv;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 配送员绑定
 */
public class DeliveryBindActivity extends UIActivity {

    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    private CommonRvAdapter<配送管理.ListBean> mCommonRvAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_delivery_bind;
    }

    @Override
    protected void initView() {
          initRecycle();

        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (TextUtils.isEmpty(etSearch.getText().toString())) {
                        ToastUtils.show("搜搜商品不能为空");
                    } else {
                        String s = etSearch.getText().toString();
                        requestSearch(s);

                    }
                    //点击搜索的时候隐藏软键盘
                    KeyboardUtils.hideKeyboard(etSearch);
                    // 在这里写搜索的操作,一般都是网络请求数据
                    return true;
                }

                return false;
            }
        });

    }

    private void initRecycle() {
        mCommonRvAdapter = new CommonRvAdapter<配送管理.ListBean>(getActivity(),null,R.layout.item_delivery_manager) {
            @Override
            public void convert(CommonViewHolderRv holder, 配送管理.ListBean item, int position) {
                holder.setText(R.id.tv_name, item.getName());
                holder.setText(R.id.tv_specification, Constant.get().encryptPhoneNum(item.getMobile()));
                holder.setText(R.id.tv_bind, "绑定");
            }
        };

        mCommonRvAdapter.setOnItemClickListener(new CommonRvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                int id = mCommonRvAdapter.getData().get(position).getId();

                DeliverySaveShow(id);
            }
        });
        rvContent.setAdapter(mCommonRvAdapter);

    }

    private void DeliverySaveShow(int id) {
        BaseDialog baseDialog = new EvaluationDialog.Builder(this).setMessage("确定解绑该配送员吗?").setConfirm("确定").setCancel("取消").setListener(new EvaluationDialog.OnListener() {
            @Override
            public void onConfirm(Dialog dialog) {
                exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userBusinessDeliverySave(id))
                        .subscribe(new BaseObserver<Object>() {
                            @Override
                            public void onSuccess(Object it) {
                                ToastUtils.show("绑定成功");
                                finish();
                            }
                        });
            }


        }).create();
        baseDialog.show();
    }





    private  void requestSearch(String content) {
        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).deliverySearch(content))
                .subscribe(new BaseObserver<配送管理>() {
                    @Override
                    public void onSuccess(配送管理 it) {

                        mCommonRvAdapter.setData(it.getList());
                    }
                });

    }


    @Override
    protected void initData() {

    }
}
