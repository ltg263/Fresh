package com.power.fresh.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.chen.concise.RxHttp;
import com.hjq.toast.ToastUtils;
import com.power.common_opensurce.App;
import com.power.fresh.R;
import com.power.fresh.api.FreshService;
import com.power.fresh.bean.CommodityDetails;
import com.power.fresh.bean.MessageBean;
import com.power.fresh.bean.MessageDetails;
import com.power.fresh.ui.business.WithDrawListActivity;
import com.power.fresh.ui.marki.MarkiOrderActivity;
import com.power.fresh.ui.order.MyOrderActivity;
import com.power.fresh.ui.order.OrderDetailsActivity;
import com.power.fresh.utils.Constant;
import com.powerrich.common.base.BaseListActivity;
import com.powerrich.common.base.BaseObserver;
import com.powerrich.common.base.UIActivity;
import com.powerrich.common.base.adapter.CommonRvAdapter;
import com.powerrich.common.base.adapter.CommonViewHolderRv;
import com.powerrich.common.helper.LogUtils;
import com.powerrich.common.widget.SimpleDividerItemDecoration;

import static com.powerrich.common.helper.PublicUtil.dp2px;

/**
 * 消息
 */
public class MessageActivity extends BaseListActivity<MessageBean.ListBean> {


    @Override
    public void onResume() {
        super.onResume();
        needResume = false;
    }

    @Override
    protected CommonRvAdapter<MessageBean.ListBean> getAdapter() {
        CommonRvAdapter<MessageBean.ListBean> commonRvAdapter = new CommonRvAdapter<MessageBean.ListBean>(this, null, R.layout.item_message) {

            @Override
            public void convert(CommonViewHolderRv holder, MessageBean.ListBean item, int position) {
                holder.setText(R.id.tv_title, item.getTitle());
                holder.setText(R.id.tv_time, item.getCreateTime());
                holder.setText(R.id.tv_content, item.getMsg());
                int status = item.getStatus();
                View itemView = holder.getItemView(R.id.tv_db_num);
                if (status == 1) {
                    itemView.setVisibility(View.GONE);
                } else {
                    itemView.setVisibility(View.VISIBLE);
                }
            }
        };

        commonRvAdapter.setOnItemClickListener(new CommonRvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                MessageBean.ListBean listBean = commonRvAdapter.getData().get(position);
                int type = listBean.getType();
                LogUtils.i("jsc", "MessageActivity-onItemClick:" + type);
                readNotice(listBean.getId());
                //31 跳转商家销售订单详情 32 跳转供应商销售订单详情
                if (type == 31 || type == 32) {
                    OrderDetailsActivity.startActivity(getActivity(), listBean.getParamId());
                    return;
                    //10 认证状态查询
                } else if (type == 10) {
                    AuditActivity.startActivity(getActivity());
                    return;
                }
                //40 用户订单列表 订单配送
                else if (type == 40) {
                    MyOrderActivity.startActivity(getActivity(), 0, "我的订单", Constant.用户订单2);
                    return;
                }
                /** 有新的配送订单  配送员订单（41 只有配送员才有） */
                else if (type == 41) {
                    if (App.getUserInfoCc().getLogPort() == Constant.配送员登陆) {
                        MarkiOrderActivity.startActivity(getActivity(), 0);
                    } else {
                        ToastUtils.show("只有配送员才有：" + type);
                    }
                    return;
                    //60 提现信息
                } else if (type == 60) {
                    startActivity(WithDrawListActivity.class);
                    return;
                } else {
                    //ToastUtils.show("此消息请到后台操作："+type);
                    return;
                    //OrderDetailsActivity.startActivity(getActivity(),listBean.getParamId());
                }
            }
        });

        commonRvAdapter.setDividerItemDecoration(new SimpleDividerItemDecoration(getActivity(), 0, dp2px(getActivity(), 2)));

        return commonRvAdapter;
    }

    @Override
    protected String getTitleText() {
        return "我的消息";
    }

    @Override
    protected void onLoadData(int index, int pageSize) {
        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).commodityGet(index, pageSize))
                .subscribe(new BaseObserver<MessageBean>() {
                    @Override
                    public void onSuccess(MessageBean it) {
                        notifyDataChanged(it.getList());
                    }
                });
    }


    private void readNotice(int noticeId) {
        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userNoticeGet(noticeId))
                .subscribe(new BaseObserver<MessageDetails>() {
                    @Override
                    public void onSuccess(MessageDetails it) {

                    }
                });

    }

}
