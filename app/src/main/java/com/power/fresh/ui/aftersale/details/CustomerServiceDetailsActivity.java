package com.power.fresh.ui.aftersale.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chen.concise.RxHttp;
import com.hjq.toast.ToastUtils;
import com.power.fresh.R;
import com.power.fresh.adapter.CustomerServiceDetailsGoodItemAdapter;
import com.power.fresh.api.FreshService;
import com.power.fresh.bean.RefundGet;
import com.power.fresh.ui.ShopIntroducedActivity;
import com.power.fresh.utils.Constant;
import com.power.fresh.widget.LineTime;
import com.powerrich.common.base.BaseObserver;
import com.powerrich.common.base.UIActivity;
import com.powerrich.common.base.adapter.BaseRecyclerViewAdapter;
import com.powerrich.common.base.adapter.CommonRvAdapter;
import com.powerrich.common.base.adapter.CommonViewHolderRv;
import com.powerrich.common.image.GlideLoad;
import com.powerrich.common.widget.TitleBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 售后详情
 */
public class CustomerServiceDetailsActivity extends UIActivity {

    @BindView(R.id.rvGoods)
    RecyclerView rvGoods;
    @BindView(R.id.mTitleBar)
    TitleBar mTitleBar;
    @BindView(R.id.line_time)
    LineTime lineTime;
    @BindView(R.id.tv_tuikuang_jine)
    TextView tvTuikuangJine;
    @BindView(R.id.tv_dianpu_head)
    ImageView tvDianpuHead;
    @BindView(R.id.tv_dianpu_name)
    TextView tvDianpuName;
    @BindView(R.id.tv_tksl)
    TextView tvTkje;
    @BindView(R.id.tv_tkyy)
    TextView tvTkyy;
    @BindView(R.id.tv_tksm)
    TextView tvTksm;
    @BindView(R.id.tv_tkdh)
    TextView tvSqdh;
    @BindView(R.id.iv_info_head)
    ImageView ivInfoHead;
    @BindView(R.id.tv_info_name)
    TextView tvInfoName;
    @BindView(R.id.tv_guige_name)
    TextView tvGuigeName;
    @BindView(R.id.tv_info_price)
    TextView tvInfoPrice;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.tv_tkfs)
    TextView tvTkfs;
    @BindView(R.id.rv_certificate)
    RecyclerView rvCertificate;

    private CustomerServiceDetailsGoodItemAdapter adapter;
    private int mRefundId;

    private CommonRvAdapter<String> mAdapter;

    @Override
    public boolean statusBarDarkFont() {
        return false;
    }

    public static void startActivity(Context context, int data) {
        Intent intent = new Intent(context, CustomerServiceDetailsActivity.class);
        intent.putExtra("refundId", data);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_customer_service_details;
    }

    @Override
    protected int getTitleId() {
        return R.id.mTitleBar;
    }

    @Override
    protected void initView() {
        setTitle(R.string.activity_customer_service_details_title);

        mRefundId = getIntent().getIntExtra("refundId", 0);

        mTitleBar.setLeftResId(R.mipmap.icon_public_arrow_back_white);
        mTitleBar.setOnTitleBarListener(new TitleBar.OnTitleBarListener() {
            @Override
            public void onLeftClick() {
                finish();
            }

            @Override
            public void onRightClick() {

            }
        });
        rvGoods.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        adapter = new CustomerServiceDetailsGoodItemAdapter(this);
        adapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView recyclerView, View itemView, int position) {

            }
        });
        rvGoods.setAdapter(adapter);
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.order_list_status_custom_divider));
        rvGoods.addItemDecoration(divider);


        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
        manager.setOrientation(RecyclerView.VERTICAL);
        rvCertificate.setLayoutManager(manager);


    }


    RefundGet mRefundGet;

    @Override
    protected void initData() {

        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userRefundGet(mRefundId))
                .subscribe(new BaseObserver<RefundGet>() {
                    @Override
                    public void onSuccess(RefundGet it) {

                        mRefundGet = it;
                        lineTime.setType(it);

                        tvTuikuangJine.setText("共计：" + Constant.get().canvetDouble(it.getRefundPrice()));

                        GlideLoad.loadImage(tvDianpuHead, it.getUserAvatar());
                        tvDianpuName.setText(it.getBusinessName());

                        GlideLoad.loadImage(ivInfoHead, it.getJsonOrderCommodity().getCommodityHeaderUri());
                        tvInfoName.setText(it.getJsonOrderCommodity().getCommodityName());
                        tvGuigeName.setText(it.getJsonOrderCommodity().getNormsName());
                        tvInfoPrice.setText(Constant.￥ + Constant.get().canvetDouble(it.getRefundPrice()));


                        tvNum.setText("X" + it.getNum());

                        tvTkje.setText(it.getNum() + "件");
                        tvTkyy.setText(it.getReason());


                        int refundType = it.getRefundType();
                        if (refundType == 1) {
                            tvTkfs.setText("仅退款");
                        } else if (refundType == 2) {
                            tvTkfs.setText("退货");
                        } else if (refundType == 3) {
                            tvTkfs.setText("调货");
                        }

                        tvTksm.setText(it.getRemark());
                        tvSqdh.setText(it.getRefundNo());

                        setAdapter(it.getImages());

                    }
                });
    }


    private void setAdapter(List<String> certificateData) {

        if (null != certificateData && certificateData.size() == 1) {
            String s = certificateData.get(0);
            if (s.contains(",")) {
                String[] split = s.split(",");
                certificateData.clear();
                for (int i = 0; i < split.length; i++) {
                    certificateData.add(split[i]);
                }
            }
        }


        mAdapter = new CommonRvAdapter<String>(this, certificateData, R.layout.item_business_entity_data) {
            @Override
            public void convert(CommonViewHolderRv holder, String item, int position) {

                ImageView viewById = holder.itemView.findViewById(R.id.iv_icon);
                ImageView ivDelete = holder.itemView.findViewById(R.id.iv_right_delete);
                ivDelete.setVisibility(View.GONE);
                GlideLoad.loadImage(viewById, item,3);

            }
        };

        rvCertificate.setAdapter(mAdapter);
    }

    @OnClick({R.id.tv_dianpu_name,R.id.tv_copy, R.id.ll_constact_call, R.id.ll_constact_kefu})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.tv_dianpu_name:
                ShopIntroducedActivity.startActivity(this,mRefundGet.getBusinessId());

                break;

            case R.id.tv_copy:
                boolean copy = Constant.get().copy(getActivity(), tvSqdh.getText().toString());
                if (copy) {
                    ToastUtils.show("订单号已复制");
                }

                break;
            case R.id.ll_constact_call:

                if (mRefundGet != null) {
                    Constant.get().callPhone(this, mRefundGet.getBusinessPhone());
                }


                break;
            case R.id.ll_constact_kefu:
                Constant.get().startKeFu(this);
                break;
        }
    }
}
