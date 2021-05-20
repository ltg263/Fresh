package com.power.fresh.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.chen.concise.RxHttp;
import com.hjq.toast.ToastUtils;
import com.power.fresh.R;
import com.power.fresh.api.FreshService;
import com.power.fresh.bean.home.HomeSeckill;
import com.power.fresh.bean.home.HomeSeckill2;
import com.power.fresh.utils.Constant;
import com.power.fresh.widget.ProgressView;
import com.powerrich.common.base.BaseObserver;
import com.powerrich.common.base.adapter.CommonRvAdapter;
import com.powerrich.common.base.adapter.CommonViewHolderRv;
import com.powerrich.common.base.UIActivity;
import com.powerrich.common.image.GlideLoad;
import com.powerrich.common.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 限时热销
 */
public class LimitActivity extends UIActivity implements CommonRvAdapter.OnItemClickListener {
    @BindView(R.id.rv_limit_content)
    RecyclerView rvLimitContent;
    @BindView(R.id.mTitleBar)
    TitleBar mTitleBar;
    @BindView(R.id.iv_head)
    ImageView ivHead;

    private CommonRvAdapter<HomeSeckill2.ListBean> mCommonRvAdapter;

    List<HomeSeckill2.ListBean> booths = new ArrayList<>();


    @Override
    protected int getLayoutId() {
        return R.layout.activity_limit;
    }

    @Override
    protected void initView() {
        mTitleBar.setLeftResId(R.mipmap.icon_public_arrow_back_white);
    }

    @Override
    protected void initData() {
        initRecycle();
        requestLimit();
    }


    /**
     * 限时热销 4-27
     */
    private void requestLimit() {

        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).homeActivityList("2", 20))
                .subscribe(new BaseObserver<HomeSeckill>() {
                    @Override
                    public void onSuccess(HomeSeckill it) {
                        GlideLoad.loadImage(ivHead,it.getList().get(0).getImage());

                        List<HomeSeckill2.ListBean> appActivityCommodityDTOS = it.getList().get(0).getAppActivityCommodityDTOS();
                        mCommonRvAdapter.setData(appActivityCommodityDTOS);
                    }
                });
    }


    private void initRecycle() {


        mCommonRvAdapter = new CommonRvAdapter<HomeSeckill2.ListBean>(getActivity(), booths, R.layout.item_seckill) {
            @Override
            public void convert(CommonViewHolderRv holder, HomeSeckill2.ListBean it, int position) {

                holder.setImageUrl(R.id.iv_icon, it.getCommodityHeaderUri());
                holder.setText(R.id.tv_name, it.getName());
                holder.setText(R.id.tv_simple_info, it.getSimpleInfo());
                holder.setText(R.id.tv_price, it.getSalePrice() + " /" + it.getRules().get(0).getNormsRule());
                ProgressView pv = holder.getItemView(R.id.pv);
                pv.setProgress((int) it.getStockPercent());

                TextView tv_action = holder.getItemView(R.id.tv_action);
                if (it.getStockPercent() <= 0) {
                    tv_action.setEnabled(false);
                    holder.setText(R.id.tv_action, "限时热销\n已抢完");
                } else {
                    tv_action.setEnabled(true);
                    tv_action.setText("限时热销\n马上抢");
                }

            }
        };

        mCommonRvAdapter.setOnItemClickListener(new CommonRvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                HomeSeckill2.ListBean bean = mCommonRvAdapter.getData().get(position);
                if (bean.getStockPercent() >0) {
                    Constant.get().saveCart(bean.getBusinessId(), bean.getCommodityId(), bean.getRules().get(0).getId(), 1, true, null);
                }
            }
        });
        rvLimitContent.setNestedScrollingEnabled(false);
        rvLimitContent.setAdapter(mCommonRvAdapter);


    }


    @Override
    public void onItemClick(int position) {
        ToastUtils.show(position);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }
}
