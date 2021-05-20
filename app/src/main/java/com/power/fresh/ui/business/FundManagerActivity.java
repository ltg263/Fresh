package com.power.fresh.ui.business;

import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.chen.concise.RxHttp;
import com.hjq.toast.ToastUtils;
import com.power.fresh.R;
import com.power.fresh.api.FreshService;
import com.power.fresh.bean.TempBean;
import com.power.fresh.bean.bussiness.LiuShuiBean;
import com.power.fresh.bean.bussiness.YuE_Bean;
import com.power.fresh.bean.bussiness.ZjglLiuShuiBean;
import com.power.fresh.utils.Constant;
import com.powerrich.common.base.BaseObserver;
import com.powerrich.common.base.UIActivity;
import com.powerrich.common.base.adapter.CommonRvAdapter;
import com.powerrich.common.base.adapter.CommonViewHolderRv;
import com.powerrich.common.widget.SimpleDividerItemDecoration;
import com.powerrich.common.widget.TitleBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.powerrich.common.helper.PublicUtil.dp2px;

/**
 * 资金管理
 */
public class FundManagerActivity extends UIActivity {

    @BindView(R.id.mTitleBar)
    TitleBar mTitleBar;
    @BindView(R.id.tv_zjzc)
    TextView tvZjzc;
    @BindView(R.id.tv_yue_value)
    TextView tvYueValue;

    @BindView(R.id.tv_zjsr)
    TextView tvZjsr;
    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    @BindView(R.id.rg_content)
    RadioGroup rgContent;
    /**1支出 2收入  */
    private int type =2;
    private CommonRvAdapter mCommonRvAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fund_manager;
    }

    @Override
    protected void initView() {
        initRecycle();
        rgContent.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_01) {
                    type=2;
                    requestLiuShuiAdapter();
                } else {
                    type=1;
                    requestLiuShuiAdapter();
                }
            }
        });
    }

    private void initRecycle() {
        mCommonRvAdapter = new CommonRvAdapter<ZjglLiuShuiBean.ListBean>(getActivity(), null, R.layout.item_busy_yu_e) {

            @Override
            public void convert(CommonViewHolderRv holder, ZjglLiuShuiBean.ListBean item, int position) {
                holder.setText(R.id.tv_info,item.getInfo());
                holder.setText(R.id.tv_create_time,item.getCreateTime());
                String frefixUnit=item.getType()==1?"-":"+";
                holder.setText(R.id.tv_price,frefixUnit+Constant.get().canvetDouble(item.getAmount()));

            }
        };
        rvContent.addItemDecoration(new SimpleDividerItemDecoration(getActivity(), dp2px(getActivity(), 0), 1));
        rvContent.setNestedScrollingEnabled(false);
        rvContent.setAdapter(mCommonRvAdapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        requestYuE();
        requestLiuShui();
        requestLiuShuiAdapter();
    }

    private  void requestYuE() {
        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userBusinessGetBalance())
                .subscribe(new BaseObserver<YuE_Bean>() {
                    @Override
                    public void onSuccess(YuE_Bean it) {

                        tvYueValue.setText(Constant.get().canvetDouble(it.getBalance()));
                    }
                });
    }


    private  void requestLiuShui() {
        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userBusinessBalancelogStatistics())
                .subscribe(new BaseObserver<LiuShuiBean>() {
                    @Override
                    public void onSuccess(LiuShuiBean it) {
                        tvZjsr.setText(Constant.get().canvetDouble(it.getSumAmountInsert()));
                        tvZjzc.setText(Constant.get().canvetDouble(it.getSumAmountSub()));
                    }
                });
    }


    private  void requestLiuShuiAdapter() {
        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userBusinessBalanceList(1,200,type))
                .subscribe(new BaseObserver<ZjglLiuShuiBean>() {
                    @Override
                    public void onSuccess(ZjglLiuShuiBean it) {
                        mCommonRvAdapter.setData(it.getList());
                    }
                });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }

    @OnClick(R.id.tv_tixian)
    public void onViewClicked() {
        startActivity(BankCardWithDrawActivity.class);
    }
}
