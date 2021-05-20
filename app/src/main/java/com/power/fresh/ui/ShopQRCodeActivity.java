package com.power.fresh.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chen.concise.RxHttp;
import com.power.common_opensurce.App;
import com.power.fresh.R;
import com.power.fresh.api.FreshService;
import com.power.fresh.utils.ChooseSharePopWindow;
import com.power.fresh.wxapi.ShareUtils;
import com.powerrich.common.base.BaseObserver;
import com.powerrich.common.base.UIActivity;
import com.powerrich.common.image.GlideLoad;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 店铺二维码
 */
public class ShopQRCodeActivity extends UIActivity {

    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_youhui)
    TextView tvYouhui;
    private int mBusinessId = 0;
    private String mBusinessName;

    public static void startActivity(Context context, int businessId, String businessName) {
        Intent intent = new Intent(context, ShopQRCodeActivity.class);
        intent.putExtra("BusinessId", businessId);
        intent.putExtra("BusinessName", businessName);
        context.startActivity(intent);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop_q_r_code;
    }

    @Override
    protected void initView() {
        tvUserName.setText(App.getUserInfo().getUserBase().getNickname());
        mBusinessId = getIntent().getIntExtra("BusinessId", 0);
        mBusinessName = getIntent().getStringExtra("BusinessName");
    }

    @Override
    protected void initData() {

        if (mBusinessId == 0) {

            exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).getQr())
                    .subscribe(new BaseObserver<String>() {
                        @Override
                        public void onSuccess(String it) {
                            GlideLoad.loadImage(ivIcon, it);
                        }
                    });


        } else if (mBusinessId==1) {

            tvUserName.setText("");
            exeHttp(RxHttp.getRetrofitService(FreshService.class).userBusinessGetBusiness())
                    .subscribe(new BaseObserver<String>() {
                        @Override
                        public void onSuccess(String it) {

                            GlideLoad.loadImage(ivIcon,it);

                        }
                    });


        } else {
            tvYouhui.setText("邀请好友一起来加入吧");
            tvUserName.setText(mBusinessName);
            exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).qrGetBusiness(mBusinessId))
                    .subscribe(new BaseObserver<String>() {
                        @Override
                        public void onSuccess(String it) {
                            GlideLoad.loadImage(ivIcon, it);
                        }
                    });
        }


    }



    @OnClick(R.id.btn_share)
    public void onViewClicked() {


        new ChooseSharePopWindow(this).showBottomView(new ChooseSharePopWindow.ChooseImgImpl() {

            String url = "http://116.62.8.116:8080/ningjiangshengxian//h5/nj/index.html?inviteCode=" + App.getUserInfoCc().getUserNo();

            @Override
            public void shareFriend() {
                ShareUtils.shareWeb(getActivity(), 1,
                        url);
            }

            @Override
            public void circleOfFriends() {
                ShareUtils.shareWeb(getActivity(), 2,
                        url);
            }

            @Override
            public void collect() {
                ShareUtils.shareWeb(getActivity(), 3,
                        url);
            }
        });


    }
}
