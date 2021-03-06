package com.power.fresh.fragment.type;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chen.concise.RxHttp;
import com.noober.background.view.BLTextView;
import com.power.common_opensurce.App;
import com.power.common_opensurce.UserInfo;
import com.power.common_opensurce.UserInfoCc;
import com.power.fresh.R;
import com.power.fresh.api.FreshService;
import com.power.fresh.bean.event.EventTimeOut;
import com.power.fresh.bean.me.MeBooth;
import com.power.fresh.ui.AuditActivity;
import com.power.fresh.ui.MessageActivity;
import com.power.fresh.ui.SettingActivity;
import com.power.fresh.ui.ShopQRCodeActivity;
import com.power.fresh.ui.WebViewActivity;
import com.power.fresh.ui.aftersale.AfterSaleCcActivity;
import com.power.fresh.ui.business.BankCardListActivity;
import com.power.fresh.ui.business.BusinessUserActivity;
import com.power.fresh.ui.business.CommonOrdersActivity;
import com.power.fresh.ui.business.DeliveryManManageActivity;
import com.power.fresh.ui.business.FundManagerActivity;
import com.power.fresh.ui.business.GoodsManagerActivity;
import com.power.fresh.ui.business.OrderManagerActivity;
import com.power.fresh.ui.marki.MarkiOrderActivity;
import com.power.fresh.ui.my.DiscountActivity;
import com.power.fresh.ui.my.FeedBackActivity;
import com.power.fresh.ui.my.PersonalDataActivity;
import com.power.fresh.ui.my.ShippingListAddressActivity;
import com.power.fresh.ui.my.enter.BusinessEnterActivity;
import com.power.fresh.ui.my.enter.DeliveryManActivity;
import com.power.fresh.ui.order.MyOrderActivity;
import com.power.fresh.utils.Constant;
import com.powerrich.common.base.BaseObserver;
import com.powerrich.common.base.MyLazyFragment;
import com.powerrich.common.base.adapter.CommonRvAdapter;
import com.powerrich.common.base.adapter.CommonViewHolderRv;
import com.powerrich.common.helper.SPUtils;
import com.powerrich.common.helper.TimeTool;
import com.powerrich.common.image.GlideLoad;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * ??????????????????
 */
public class DefaultUserFragment extends MyLazyFragment {


    @BindView(R.id.rv_default)
    RecyclerView rvDefault;
    @BindView(R.id.rv_me_order)
    RecyclerView rvMeOrder;
    @BindView(R.id.tv_me_nickname)
    TextView tvMeNickname;
    @BindView(R.id.tv_user_type)
    TextView tv_user_type;
    @BindView(R.id.tv_head)
    ImageView tvHead;
    @BindView(R.id.tv_shangjia_ruzhu01)
    TextView tvShangjiaRuzhu01;
    @BindView(R.id.tv_gongyingshang_ruzhu02)
    TextView tvGongyingshangRuzhu02;
    @BindView(R.id.tv_peisongyuan_ruzhu03)
    TextView tvPeisongyuanRuzhu03;
    @BindView(R.id.tv_me_order)
    TextView tvMeOrder;
    @BindView(R.id.tv_me_function)
    TextView tvMeFunction;
    @BindView(R.id.text_juese)
    BLTextView textJuese;
    @BindView(R.id.iv_qr)
    ImageView ivQr;
    private CommonRvAdapter<MeBooth> mOrderCommonRvAdapter;
    private CommonRvAdapter<MeBooth> mFunctionCommonRvAdapter;
    /**
     * ??????????????? ??????????????????????????????
     */
    private int mAuthStatus;
    private UserInfoCc mIt;

    public static DefaultUserFragment newInstance() {


        DefaultUserFragment fragment = new DefaultUserFragment();
        return fragment;
    }
//    ????????????
//    ???????????????1???
//    ??????????????????2???
//    ??????????????????3???
//    ??????????????????4

    @Override
    public boolean statusBarDarkFont() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_default_user;
    }

    @Override
    protected int getTitleId() {
        return 0;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        tvMeNickname.setText(App.getUserInfo().getUserBase().getNickname());
        tv_user_type.setText(App.getUserInfo().getUserBase().getUserType() == 0 ? "????????????" : "????????????");
        GlideLoad.loadImage(tvHead, App.getUserInfo().getUserBase().getPortraitUri(), 10);
        initRecycleOrder();
        initRecycle();
        initEvent();
    }


    private void initEvent() {

    }


    /**
     * ??????
     */
    private void initRecycleOrder() {
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 4);
        manager.setOrientation(RecyclerView.VERTICAL);
        rvMeOrder.setLayoutManager(manager);
        List<MeBooth> booths = new ArrayList<>();

        booths.add(new MeBooth().setTitle("????????????").setResourceId(R.mipmap.me_qbdd));
        booths.add(new MeBooth().setTitle("?????????").setResourceId(R.mipmap.me_dzf));
        booths.add(new MeBooth().setTitle("?????????").setResourceId(R.mipmap.me_dsh));
        booths.add(new MeBooth().setTitle("?????????").setResourceId(R.mipmap.me_dpj));
        mOrderCommonRvAdapter = new CommonRvAdapter<MeBooth>(getActivity(), booths, R.layout.item_me_booth, 4) {
            @Override
            public void convert(CommonViewHolderRv holder, MeBooth booth, int position) {
                holder.setText(R.id.tv_title, booth.getTitle());
                holder.setImageResource(R.id.iv_icon, booth.getResourceId());
            }
        };
        mOrderCommonRvAdapter.setNeedEmptyView(false);
        rvMeOrder.setNestedScrollingEnabled(false);
        rvMeOrder.setAdapter(mOrderCommonRvAdapter);

        mOrderCommonRvAdapter.setOnItemClickListener(mOrderOnItemClickListener);

    }


    private void initRecycle() {
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 4);
        manager.setOrientation(RecyclerView.VERTICAL);
        rvDefault.setLayoutManager(manager);
        List<MeBooth> booths = new ArrayList<>();
        booths.add(new MeBooth().setTitle("?????????").setResourceId(R.mipmap.me_yhq));
        booths.add(new MeBooth().setTitle("??????").setResourceId(R.mipmap.me_kf));
        booths.add(new MeBooth().setTitle("????????????").setResourceId(R.mipmap.me_shdz));
        booths.add(new MeBooth().setTitle("????????????").setResourceId(R.mipmap.me_shtk));
        booths.add(new MeBooth().setTitle("????????????").setResourceId(R.mipmap.me_yqyl));

        mFunctionCommonRvAdapter = new CommonRvAdapter<MeBooth>(getActivity(), booths, R.layout.item_me_booth, 10) {
            @Override
            public void convert(CommonViewHolderRv holder, MeBooth booth, int position) {
                holder.setText(R.id.tv_title, booth.getTitle());
                holder.setImageResource(R.id.iv_icon, booth.getResourceId());
            }
        };
        mFunctionCommonRvAdapter.setNeedEmptyView(false);
        rvDefault.setNestedScrollingEnabled(false);
        rvDefault.setAdapter(mFunctionCommonRvAdapter);


        mFunctionCommonRvAdapter.setOnItemClickListener(mFunctionOnItemClickListener);
    }

    @Override
    protected void initData() {


        UserInfo userInfo = App.getUserInfo();
        if (userInfo != null) {
            //??????

        } else {

        }


        requestUserInfo();



    }

    private void requestBusinessQr() {
        exeHttp(RxHttp.getRetrofitService(FreshService.class).userBusinessGetBusiness())
                .subscribe(new BaseObserver<String>() {
                    @Override
                    public void onSuccess(String it) {

                        ivQr.setVisibility(View.VISIBLE);

                      //  GlideLoad.loadImage(ivQr,it);

                    }
                });
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBus(EventTimeOut eventTimeOut) {
           requestUserInfo();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    private void requestUserInfo() {
        exeHttp(RxHttp.getRetrofitService(FreshService.class).userGetUserInfo())
                .subscribe(new BaseObserver<UserInfoCc>() {
                    @Override
                    public void onSuccess(UserInfoCc it) {

                        if (it == null) {
                            return;
                        }


                        SPUtils.put(App.USERINFOCC, App.getmGson().toJson(it));
                        App.setmSpUser(it);
                        /** 10???????????????
                         11????????????
                         12????????????
                         13??????????????????
                         20?????????
                         21
                         22
                         23
                         30?????????
                         31
                         32
                         33
                         1 ???????????????????????? */
                        mIt =  SPUtils.get(App.USERINFOCC, UserInfoCc.class);
                        mAuthStatus = mIt.getAuthStatus();
                        Log.e("jsc", "????????????:" + mAuthStatus);

                        setHeadInfo();

                        tvMeNickname.setText(it.getNickname());
                        GlideLoad.loadImage(tvHead, it.getPortraitUri());

                        setOrderCommonRvAdapter(mAuthStatus);
                        setBelowStatusView(mAuthStatus);

                    }
                });
    }


    private void setHeadInfo() {


        if (mIt.getLogPort() == 1) {
            tv_user_type.setText("????????????");
         //   textJuese.setVisibility(View.GONE);
          //  textJuese.setText("?????????");
        } else if (mIt.getLogPort() == 2) {
            tv_user_type.setText("????????????");
            textJuese.setVisibility(View.VISIBLE);
            textJuese.setText("?????????");
            if (mAuthStatus == 10) {
                textJuese.setText("?????????");
            }
        } else if (mIt.getLogPort() == 3) {
            tv_user_type.setText("????????????");
            textJuese.setVisibility(View.VISIBLE);
            textJuese.setText("?????????");
        } else if (mIt.getLogPort() == 4) {
            tv_user_type.setText("????????????");
            textJuese.setVisibility(View.VISIBLE);
            textJuese.setText("?????????");
        }


        if (mIt.getLogPort() == Constant.??????????????? || mIt.getLogPort() == Constant.???????????????) {
            tv_user_type.setText("???????????????" + TimeTool.getApplyListDate(mIt.getExpireTime()));
            ivQr.setVisibility(View.VISIBLE);
            requestBusinessQr();
        }

    }

    private void setBelowStatusView(int authStatus) {
        /**10.. ????????? */
        if (authStatus == 10 || authStatus == 20 || authStatus == 30) {

            if (authStatus == 10) {
                tvShangjiaRuzhu01.setText("???????????????");
            } else if (authStatus == 20) {
                tvShangjiaRuzhu01.setText("??????????????????");
            } else if (authStatus == 30) {
                tvShangjiaRuzhu01.setText("??????????????????");
            }

            tvShangjiaRuzhu01.setVisibility(View.VISIBLE);
            tvGongyingshangRuzhu02.setVisibility(View.GONE);
            tvPeisongyuanRuzhu03.setVisibility(View.GONE);
        }
        /** 11???13.. ???????????? ??????????????? */
        else if (authStatus == 11 || authStatus == 21 || authStatus == 31 || authStatus == 13 || authStatus == 23 || authStatus == 33) {

            if (authStatus == 11 || authStatus == 13) {
                tvShangjiaRuzhu01.setText("????????????");
                tvGongyingshangRuzhu02.setText("??????????????????");
            } else if (authStatus == 21 || authStatus == 23) {
                tvShangjiaRuzhu01.setText("???????????????");
                tvGongyingshangRuzhu02.setText("?????????????????????");
            } else if (authStatus == 31 || authStatus == 33) {
                tvShangjiaRuzhu01.setText("???????????????");
                tvGongyingshangRuzhu02.setText("?????????????????????");
            }

            tvShangjiaRuzhu01.setVisibility(View.VISIBLE);
            tvGongyingshangRuzhu02.setVisibility(View.VISIBLE);
            tvPeisongyuanRuzhu03.setVisibility(View.GONE);
        } else {

            tvShangjiaRuzhu01.setText(getString(R.string.sjrz_string));
            tvShangjiaRuzhu01.setVisibility(View.VISIBLE);
            tvGongyingshangRuzhu02.setText(getResources().getString(R.string.gys_string));
            tvGongyingshangRuzhu02.setVisibility(View.VISIBLE);
            tvPeisongyuanRuzhu03.setText("???????????????");
            tvPeisongyuanRuzhu03.setVisibility(View.VISIBLE);

        }
    }


    /**
     * ???????????? true  ???????????? false
     */
    private boolean switchFlag = true;

    @OnClick({R.id.iv_qr,R.id.text_juese, R.id.tv_head, R.id.iv_setting, R.id.tv_feedback, R.id.tv_shangjia_ruzhu01, R.id.tv_gongyingshang_ruzhu02, R.id.tv_peisongyuan_ruzhu03, R.id.iv_homepage_new_black})
    public void onViewClicked(View view) {
        switch (view.getId()) {


            case R.id.iv_qr:
                ShopQRCodeActivity.startActivity(getActivity(),1,"");


                break;

            case R.id.text_juese:
                if (switchFlag) {
                    switchFlag = false;
                    textJuese.setText("?????????");
                    App.getUserInfoCc().setLogPort(Constant.????????????);
                    setOrderCommonRvAdapter(-1);
                    mAuthStatus = -1;
                } else {
                    switchFlag = true;
                    App.getUserInfoCc().setLogPort(mIt.getLogPort());
                    mAuthStatus = mIt.getAuthStatus();
                    setHeadInfo();
                    setOrderCommonRvAdapter(mAuthStatus);
                    setBelowStatusView(mAuthStatus);
                }
                break;
            case R.id.tv_head:
                startActivity(PersonalDataActivity.class);
                break;
            case R.id.iv_setting:
                startActivity(SettingActivity.class);
                break;
            case R.id.tv_feedback:
                startActivity(FeedBackActivity.class);
                break;
            /** ???????????? */
            case R.id.tv_shangjia_ruzhu01:
                int authStatus = App.getUserInfoCc().getAuthStatus();
                if (authStatus == 1) {
                    BusinessEnterActivity.startActivity(getActivity(), getString(R.string.sjrz_string));
                } else {
                    AuditActivity.startActivity(getActivity());
                }
                break;
            /** ??????????????? */
            case R.id.tv_gongyingshang_ruzhu02:
                /** ?????????????????? */
                if (mIt.getAuthStatus() >= 30) {
                    startActivity(DeliveryManActivity.class);
                } else if (mIt.getAuthStatus() == 11) {
                    BusinessEnterActivity.startActivity(getActivity(), getString(R.string.sjrz_string));
                } else {
                    BusinessEnterActivity.startActivity(getActivity(), getString(R.string.gys_string));
                }

                break;
            /** ??????????????? */
            case R.id.tv_peisongyuan_ruzhu03:

                startActivity(DeliveryManActivity.class);

                break;
            case R.id.iv_homepage_new_black:
                startActivity(MessageActivity.class);
                break;
        }
    }


    CommonRvAdapter.OnItemClickListener mOrderOnItemClickListener = new CommonRvAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(int position) {


            if (!switchFlag) {
                MyOrderActivity.startActivity(getActivity(), position, "????????????", Constant.????????????2);
                return;
            }


            /** 11????????????  21????????? */
            if (mAuthStatus == 11 | mAuthStatus == 13 | mAuthStatus == 21 | mAuthStatus == 23) {
                switch (position) {
                    /** order_????????? */
                    case 0:
                        CommonOrdersActivity.startActivity(getActivity(), Constant.order_????????????_?????????);
                        //MyOrderActivity.startActivity(getActivity(), Constant.order_????????????_?????????, "????????????",Constant.????????????);
                        break;
                    /** order_????????? */
                    case 1:
                        CommonOrdersActivity.startActivity(getActivity(), Constant.order_????????????_?????????);
                        // MyOrderActivity.startActivity(getActivity(), Constant.order_?????????, "????????????",Constant.????????????);
                        break;
                    /** order_????????? */
                    case 2:
                        CommonOrdersActivity.startActivity(getActivity(), Constant.order_????????????_?????????);
                        // MyOrderActivity.startActivity(getActivity(), Constant.order_?????????, "????????????",Constant.????????????);
                        break;
                    /** ?????? */
                    case 3:
                        startActivity(AfterSaleCcActivity.class);
                        break;
                }
            }
            /** ????????? */
            else if (mAuthStatus == 31 | mAuthStatus == 33) {

                switch (position) {
                    case 0:
                        MarkiOrderActivity.startActivity(getActivity(), 0);
                        break;
                    case 1:
                        MarkiOrderActivity.startActivity(getActivity(), 1);
                        break;
                    case 2:
                        MarkiOrderActivity.startActivity(getActivity(), 2);
                        break;

                }


            } else {
                MyOrderActivity.startActivity(getActivity(), position, "????????????", Constant.????????????2);
            }

//                startActivity(CommoditySoldOutActivity.class);
        }
    };


    CommonRvAdapter.OnItemClickListener mFunctionOnItemClickListener = new CommonRvAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(int position) {

            Log.e("jsc", "DefaultUserFragment-onItemClick:" + mAuthStatus);
            /** 11????????????  21????????? */
            if (mAuthStatus == 11 | mAuthStatus == 21) {
                switch (position) {
                    /** ???????????? */
                    case 0:
                        startActivity(DeliveryManManageActivity.class);
                        break;
                    /** ???????????? */
                    case 1:
                        startActivity(GoodsManagerActivity.class);
                        break;
                    /** ???????????? */
                    case 2:
                        startActivity(OrderManagerActivity.class);
                        break;
                    /** ????????? */
                    case 5:
                        startActivity(BankCardListActivity.class);
                        break;
                    /** ???????????? */
                    case 4:
                        startActivity(FundManagerActivity.class);
                        break;
                    /** ????????? */
                    case 3:
                        BusinessUserActivity.startActivity(getActivity());
                        break;
                    /** ???????????? */
                    case 6:
                        startActivity(ShippingListAddressActivity.class);
                        break;
                    /** ?????? */
                    case 7:
                        Constant.get().startKeFu(getActivity());
                        break;
                }

            }
            /** ????????? */
            else if (mAuthStatus == 31) {

                switch (position) {
                    case 0:
                        Constant.get().startKeFu(getActivity());
                        break;

                }


            } else {
                if (position == 0) {
                    startActivity(DiscountActivity.class);
                } else if (position == 1) {
                    //  startActivity(KeFuActivity.class);
                    WebViewActivity.startActivity(getActivity(), RxHttp.KEFU_URL, "????????????");
                } else if (position == 2) {
                    startActivity(ShippingListAddressActivity.class);
                } else if (position == 3) {
                    startActivity(AfterSaleCcActivity.class);
                } else if (position == 4) {
                    startActivity(ShopQRCodeActivity.class);
                }
            }
        }
    };


    /**
     * ?????? ?????????
     */
    private void setOrderCommonRvAdapter(int authStatus) {
        List<MeBooth> booths = new ArrayList<>();
        /** 11????????????  21 ??????????????? */
        if (authStatus == 11 | authStatus == 13 | authStatus == 21 | authStatus == 23) {
            tvMeOrder.setText("????????????");
            booths.add(new MeBooth().setTitle("?????????").setResourceId(R.mipmap.me_dzf));
            booths.add(new MeBooth().setTitle("?????????").setResourceId(R.mipmap.me_qbdd));
            booths.add(new MeBooth().setTitle("?????????").setResourceId(R.mipmap.me_dsh));
            booths.add(new MeBooth().setTitle("????????????").setResourceId(R.mipmap.me_shtk));
        }
        /** 31 ??????????????? */
        else if (authStatus == 31 | authStatus == 33) {
            tvMeOrder.setText("????????????");
            booths.add(new MeBooth().setTitle("??????").setResourceId(R.mipmap.me_qbdd));
            booths.add(new MeBooth().setTitle("?????????").setResourceId(R.mipmap.me_ddgl));
//            booths.add(new MeBooth().setTitle("?????????").setResourceId(R.mipmap.me_dsh));
            booths.add(new MeBooth().setTitle("?????????").setResourceId(R.mipmap.me_shtk));
        } else {
            tvMeOrder.setText("????????????");
            booths.add(new MeBooth().setTitle("????????????").setResourceId(R.mipmap.me_qbdd));
            booths.add(new MeBooth().setTitle("?????????").setResourceId(R.mipmap.me_dzf));
            booths.add(new MeBooth().setTitle("?????????").setResourceId(R.mipmap.me_dsh));
            booths.add(new MeBooth().setTitle("?????????").setResourceId(R.mipmap.me_dpj));
        }
        mOrderCommonRvAdapter.setData(booths);

        setFunctionCommonRvAdapter(authStatus);
    }

    /**
     * ?????? ?????????
     */
    private void setFunctionCommonRvAdapter(int authStatus) {
        List<MeBooth> booths = new ArrayList<>();


        /** 11????????????  21 ??????????????? */
        if (authStatus == 11 | authStatus == 13 | authStatus == 21 | authStatus == 23) {
            tvMeFunction.setText("????????????");
            booths.add(new MeBooth().setTitle("????????????").setResourceId(R.mipmap.me_psgl));
            booths.add(new MeBooth().setTitle("????????????").setResourceId(R.mipmap.me_spgl));
            booths.add(new MeBooth().setTitle("????????????").setResourceId(R.mipmap.me_ddgl));
            booths.add(new MeBooth().setTitle("????????????").setResourceId(R.mipmap.me_tuandui));

            booths.add(new MeBooth().setTitle("????????????").setResourceId(R.mipmap.me_zhye));
            booths.add(new MeBooth().setTitle("?????????").setResourceId(R.mipmap.me_yhk));
            booths.add(new MeBooth().setTitle("????????????").setResourceId(R.mipmap.me_shdz));
            booths.add(new MeBooth().setTitle("??????").setResourceId(R.mipmap.me_kf));
        }
        /** 31 ??????????????? */
        else if (authStatus == 31 | authStatus == 33) {
            tvMeFunction.setText("????????????");
//            booths.add(new MeBooth().setTitle("??????").setResourceId(R.mipmap.me_zhye));
//            booths.add(new MeBooth().setTitle("?????????").setResourceId(R.mipmap.me_yhk));
            booths.add(new MeBooth().setTitle("??????").setResourceId(R.mipmap.me_kf));
        } else {
            tvMeFunction.setText("????????????");
            booths.add(new MeBooth().setTitle("?????????").setResourceId(R.mipmap.me_yhq));
            booths.add(new MeBooth().setTitle("??????").setResourceId(R.mipmap.me_kf));
            booths.add(new MeBooth().setTitle("????????????").setResourceId(R.mipmap.me_shdz));
            booths.add(new MeBooth().setTitle("????????????").setResourceId(R.mipmap.me_shtk));
            booths.add(new MeBooth().setTitle("????????????").setResourceId(R.mipmap.me_yqyl));
        }

        mFunctionCommonRvAdapter.setData(booths);
    }

}
