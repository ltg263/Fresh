package com.power.fresh.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
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
import com.powerrich.common.base.UIActivity;
import com.powerrich.common.base.adapter.CommonRvAdapter;
import com.powerrich.common.base.adapter.CommonViewHolderRv;
import com.powerrich.common.helper.PublicUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 限时热销 最新页面 2021
 */
public class SeckillActivity extends UIActivity implements CommonRvAdapter.OnItemClickListener, Runnable {


    @BindView(R.id.rv_seckill_content)
    RecyclerView rvSeckillContent;
    @BindView(R.id.tv_time_10)
    TextView tvTime10;
    @BindView(R.id.tv_end_10)
    TextView tvEnd10;
    @BindView(R.id.ll_10)
    LinearLayout ll10;
    @BindView(R.id.tv_time_14)
    TextView tvTime14;
    @BindView(R.id.tv_end_14)
    TextView tvEnd14;
    @BindView(R.id.ll_today_14)
    LinearLayout llToday14;
    @BindView(R.id.tv_time_20)
    TextView tvTime20;
    @BindView(R.id.tv_end_20)
    TextView tvEnd20;
    @BindView(R.id.ll_20)
    LinearLayout ll20;
    @BindView(R.id.tv_seckill_hour)
    TextView tvSeckillHour;
    @BindView(R.id.tv_seckill_minute)
    TextView tvSeckillMinute;
    @BindView(R.id.tv_seckill_second)
    TextView tvSeckillSecond;

    // 0:10点  1:14点 2:20点
    private int intentType;

    private CommonRvAdapter<HomeSeckill2.ListBean> mCommonRvAdapter;

    List<HomeSeckill2.ListBean> booths = new ArrayList<>();
    private List<HomeSeckill.ListBean> mList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_seckill;
    }

    @Override
    protected void initView() {
        intentType = getIntent().getIntExtra("Type", 0);

    }

    @Override
    protected void initData() {
        initRecycle();
        //  initCountDown();
        requestSeckill();

      //  requestSeckill2("16");
    }


    public static void startActivity(Context context, int type) {
        Intent intent = new Intent(context, SeckillActivity.class);
        intent.putExtra("Type", type);
        context.startActivity(intent);
    }

    private void initCountDown() {
        cnt = (int) SystemClock.currentThreadTimeMillis();
        post(this);

    }

    /**
     * llSeckillContainer
     * 秒杀
     */
    private void requestSeckill() {
        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).homeActivityList2("1", 20,17))
                .subscribe(new BaseObserver<HomeSeckill>() {
                    @Override
                    public void onSuccess(HomeSeckill it) {

                        if (it.getList().size() < 3) {
                            return;
                        }

                        List<HomeSeckill2.ListBean> appActivityCommodityDTOS = it.getList().get(intentType).getAppActivityCommodityDTOS();
                        mCommonRvAdapter.setData(appActivityCommodityDTOS);
                        mList = it.getList();

                        for (int i = 0; i < mList.size(); i++) {
                            HomeSeckill.ListBean listBean = mList.get(i);

                            if (i > 2) {
                                break;
                            }

//                            if (listBean.getLiveStatus() == 2) {
//                                initSeckill(i);
//                            }

                            if (i == 0) {
                                tvTime10.setText(listBean.getActivityName());
                                tvEnd10.setText(convetStatus(listBean.getLiveStatus()));
                                listBean.getId();

                            } else if (i == 1) {
                                tvTime14.setText(listBean.getActivityName());
                                tvEnd14.setText(convetStatus(listBean.getLiveStatus()));

                            } else if (i == 2) {
                                tvTime20.setText(listBean.getActivityName());
                                tvEnd20.setText(convetStatus(listBean.getLiveStatus()));

                            }
                        }


                    }
                });
    }



    private void requestSeckill2(int activityId) {
        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).commodityList(String.valueOf(activityId)))
                .subscribe(new BaseObserver<HomeSeckill2>() {
                    @Override
                    public void onSuccess(HomeSeckill2 it) {

//                        List<HomeSeckill.ListBean.AppActivityCommodityDTOSBean> appActivityCommodityDTOS = it.getList().get(intentType).getAppActivityCommodityDTOS();
                        mCommonRvAdapter.setData(it.getList());

                    }
                });
    }


    private String convetStatus(int status) {
        if (status == 1) {
            return "活动未开始";
        } else if (status == 2) {
            return "活动进行中";
        } else if (status == 3) {
            return "活动结束";
        }
        return "";
    }


    private void getNetTime(long cnt) {
        try {
            int hour = (int) (cnt / 3600);
            int min = (int) (cnt % 3600 / 60);
            int second = (int) (cnt % 60);
            String format = String.format(Locale.CHINA, "%02d:%02d:%02d", hour, min, second);
            if (!TextUtils.isEmpty(format)) {
                tvSeckillHour.setText(hour);
                tvSeckillMinute.setText(hour);
                tvSeckillSecond.setText(hour);
                // isHalfHourIn(format);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void initRecycle() {


        mCommonRvAdapter = new CommonRvAdapter<HomeSeckill2.ListBean>(getActivity(), booths, R.layout.item_seckill) {
            @Override
            public void convert(CommonViewHolderRv holder,HomeSeckill2.ListBean it, int position) {

                holder.setImageUrl(R.id.iv_icon, it.getCommodityHeaderUri());
                holder.setText(R.id.tv_name, it.getName());
                holder.setText(R.id.tv_simple_info, it.getSimpleInfo());
                holder.setText(R.id.tv_price, it.getSalePrice() + " /" + it.getRules().get(0).getNormsRule());
                ProgressView pv = holder.getItemView(R.id.pv);
                pv.setProgress((int) it.getStockPercent());

                TextView tv_action = holder.getItemView(R.id.tv_action);
                if (it.getStockPercent() <= 0) {
                    tv_action.setEnabled(false);
                    holder.setText(R.id.tv_action, "已抢完");
                } else {
                    tv_action.setEnabled(true);
                    tv_action.setText("立即抢");
                }


            }
        };

        mCommonRvAdapter.setOnItemClickListener(new CommonRvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                HomeSeckill2.ListBean bean = mCommonRvAdapter.getData().get(position);
                if (bean.getStockPercent() > 0) {
                    Constant.get().saveCart(bean.getBusinessId(), bean.getCommodityId(), bean.getRules().get(0).getId(), 1, true, null);
                }
            }
        });

        rvSeckillContent.setAdapter(mCommonRvAdapter);
        //  mCommonRvAdapter.setOnItemClickListener(this);
    }

    private void initSeckill(int intentType) {
        if (intentType == 0) {
            ll10.setBackgroundColor(ContextCompat.getColor(this,R.color.public_color_333333));
            llToday14.setBackgroundColor(ContextCompat.getColor(this,R.color.public_color_333333_check));
            ll20.setBackgroundColor(ContextCompat.getColor(this,R.color.public_color_333333_check));
        } else if (intentType == 1) {
            ll10.setBackgroundColor(ContextCompat.getColor(this,R.color.public_color_333333_check));
            llToday14.setBackgroundColor(ContextCompat.getColor(this,R.color.public_color_333333));
            ll20.setBackgroundColor(ContextCompat.getColor(this,R.color.public_color_333333_check));
        } else if (intentType == 2) {
            ll10.setBackgroundColor(ContextCompat.getColor(this,R.color.public_color_333333_check));
            llToday14.setBackgroundColor(ContextCompat.getColor(this,R.color.public_color_333333_check));
            ll20.setBackgroundColor(ContextCompat.getColor(this,R.color.public_color_333333));
        }

    }


    @Override
    public void onItemClick(int position) {
        ToastUtils.show(position);
    }

    private static final int MSG_ONE = 1;

    int cnt = 0;
    /**
     * 当前秒数
     */
    private int mCurrentSecond = 60;

    @Override
    public void run() {
        if (mCurrentSecond == 0) {
            return;
        } else {
            mCurrentSecond--;
        }

        tvSeckillSecond.setText(String.valueOf(mCurrentSecond));
        postDelayed(this, 1000);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }

    @OnClick({R.id.ll_10, R.id.ll_today_14, R.id.ll_20})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_10:
                initSeckill(0);
                if(!PublicUtil.isEmpty(mList.get(0))){
                    requestSeckill2(mList.get(0).getId());
                }
                break;
            case R.id.ll_today_14:
                initSeckill(1);
                if(!PublicUtil.isEmpty(mList.get(1))){
                    requestSeckill2(mList.get(1).getId());
                }
                break;
            case R.id.ll_20:
                initSeckill(2);
                if(!PublicUtil.isEmpty(mList.get(2))){
                    requestSeckill2(mList.get(2).getId());
                }
                break;
        }
    }
}
