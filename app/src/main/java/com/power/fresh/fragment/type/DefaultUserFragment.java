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
 * 默认用户登录
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
     * 会临时变动 在当前角色和用户之间
     */
    private int mAuthStatus;
    private UserInfoCc mIt;

    public static DefaultUserFragment newInstance() {


        DefaultUserFragment fragment = new DefaultUserFragment();
        return fragment;
    }
//    登陆端口
//    用户登陆：1，
//    经销商登陆：2，
//    供应商登陆：3，
//    配送员登陆：4

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
        tv_user_type.setText(App.getUserInfo().getUserBase().getUserType() == 0 ? "普通用户" : "一级会员");
        GlideLoad.loadImage(tvHead, App.getUserInfo().getUserBase().getPortraitUri(), 10);
        initRecycleOrder();
        initRecycle();
        initEvent();
    }


    private void initEvent() {

    }


    /**
     * 订单
     */
    private void initRecycleOrder() {
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 4);
        manager.setOrientation(RecyclerView.VERTICAL);
        rvMeOrder.setLayoutManager(manager);
        List<MeBooth> booths = new ArrayList<>();

        booths.add(new MeBooth().setTitle("全部订单").setResourceId(R.mipmap.me_qbdd));
        booths.add(new MeBooth().setTitle("待支付").setResourceId(R.mipmap.me_dzf));
        booths.add(new MeBooth().setTitle("待收货").setResourceId(R.mipmap.me_dsh));
        booths.add(new MeBooth().setTitle("待评价").setResourceId(R.mipmap.me_dpj));
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
        booths.add(new MeBooth().setTitle("优惠券").setResourceId(R.mipmap.me_yhq));
        booths.add(new MeBooth().setTitle("客服").setResourceId(R.mipmap.me_kf));
        booths.add(new MeBooth().setTitle("收货地址").setResourceId(R.mipmap.me_shdz));
        booths.add(new MeBooth().setTitle("售后退款").setResourceId(R.mipmap.me_shtk));
        booths.add(new MeBooth().setTitle("邀请有礼").setResourceId(R.mipmap.me_yqyl));

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
            //设置

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
                        /** 10商家认证中
                         11商家成功
                         12商家失败
                         13商家信息更替
                         20供应商
                         21
                         22
                         23
                         30配送员
                         31
                         32
                         33
                         1 状态初始化未认证 */
                        mIt =  SPUtils.get(App.USERINFOCC, UserInfoCc.class);
                        mAuthStatus = mIt.getAuthStatus();
                        Log.e("jsc", "用户状态:" + mAuthStatus);

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
            tv_user_type.setText("普通用户");
         //   textJuese.setVisibility(View.GONE);
          //  textJuese.setText("普通端");
        } else if (mIt.getLogPort() == 2) {
            tv_user_type.setText("欢迎入驻");
            textJuese.setVisibility(View.VISIBLE);
            textJuese.setText("经销商");
            if (mAuthStatus == 10) {
                textJuese.setText("用户端");
            }
        } else if (mIt.getLogPort() == 3) {
            tv_user_type.setText("欢迎入驻");
            textJuese.setVisibility(View.VISIBLE);
            textJuese.setText("供应商");
        } else if (mIt.getLogPort() == 4) {
            tv_user_type.setText("欢迎入驻");
            textJuese.setVisibility(View.VISIBLE);
            textJuese.setText("配送员");
        }


        if (mIt.getLogPort() == Constant.供应商登陆 || mIt.getLogPort() == Constant.经销商登陆) {
            tv_user_type.setText("有效期至：" + TimeTool.getApplyListDate(mIt.getExpireTime()));
            ivQr.setVisibility(View.VISIBLE);
            requestBusinessQr();
        }

    }

    private void setBelowStatusView(int authStatus) {
        /**10.. 认证中 */
        if (authStatus == 10 || authStatus == 20 || authStatus == 30) {

            if (authStatus == 10) {
                tvShangjiaRuzhu01.setText("商家认证中");
            } else if (authStatus == 20) {
                tvShangjiaRuzhu01.setText("供应商认证中");
            } else if (authStatus == 30) {
                tvShangjiaRuzhu01.setText("配送员认证中");
            }

            tvShangjiaRuzhu01.setVisibility(View.VISIBLE);
            tvGongyingshangRuzhu02.setVisibility(View.GONE);
            tvPeisongyuanRuzhu03.setVisibility(View.GONE);
        }
        /** 11、13.. 认证成功 、信息更替 */
        else if (authStatus == 11 || authStatus == 21 || authStatus == 31 || authStatus == 13 || authStatus == 23 || authStatus == 33) {

            if (authStatus == 11 || authStatus == 13) {
                tvShangjiaRuzhu01.setText("商家审核");
                tvGongyingshangRuzhu02.setText("修改商家信息");
            } else if (authStatus == 21 || authStatus == 23) {
                tvShangjiaRuzhu01.setText("供应商审核");
                tvGongyingshangRuzhu02.setText("修改供应商信息");
            } else if (authStatus == 31 || authStatus == 33) {
                tvShangjiaRuzhu01.setText("配送员审核");
                tvGongyingshangRuzhu02.setText("修改配送员信息");
            }

            tvShangjiaRuzhu01.setVisibility(View.VISIBLE);
            tvGongyingshangRuzhu02.setVisibility(View.VISIBLE);
            tvPeisongyuanRuzhu03.setVisibility(View.GONE);
        } else {

            tvShangjiaRuzhu01.setText(getString(R.string.sjrz_string));
            tvShangjiaRuzhu01.setVisibility(View.VISIBLE);
            tvGongyingshangRuzhu02.setText(getResources().getString(R.string.gys_string));
            tvGongyingshangRuzhu02.setVisibility(View.VISIBLE);
            tvPeisongyuanRuzhu03.setText("配送员入驻");
            tvPeisongyuanRuzhu03.setVisibility(View.VISIBLE);

        }
    }


    /**
     * 常规状态 true  用户状态 false
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
                    textJuese.setText("用户端");
                    App.getUserInfoCc().setLogPort(Constant.用户登陆);
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
            /** 商家入驻 */
            case R.id.tv_shangjia_ruzhu01:
                int authStatus = App.getUserInfoCc().getAuthStatus();
                if (authStatus == 1) {
                    BusinessEnterActivity.startActivity(getActivity(), getString(R.string.sjrz_string));
                } else {
                    AuditActivity.startActivity(getActivity());
                }
                break;
            /** 供应商入驻 */
            case R.id.tv_gongyingshang_ruzhu02:
                /** 修改商家信息 */
                if (mIt.getAuthStatus() >= 30) {
                    startActivity(DeliveryManActivity.class);
                } else if (mIt.getAuthStatus() == 11) {
                    BusinessEnterActivity.startActivity(getActivity(), getString(R.string.sjrz_string));
                } else {
                    BusinessEnterActivity.startActivity(getActivity(), getString(R.string.gys_string));
                }

                break;
            /** 配送员入驻 */
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
                MyOrderActivity.startActivity(getActivity(), position, "我的订单", Constant.用户订单2);
                return;
            }


            /** 11商家成功  21供应商 */
            if (mAuthStatus == 11 | mAuthStatus == 13 | mAuthStatus == 21 | mAuthStatus == 23) {
                switch (position) {
                    /** order_待付款 */
                    case 0:
                        CommonOrdersActivity.startActivity(getActivity(), Constant.order_采购订单_待付款);
                        //MyOrderActivity.startActivity(getActivity(), Constant.order_采购订单_待付款, "采购订单",Constant.采购订单);
                        break;
                    /** order_待发货 */
                    case 1:
                        CommonOrdersActivity.startActivity(getActivity(), Constant.order_采购订单_待发货);
                        // MyOrderActivity.startActivity(getActivity(), Constant.order_待发货, "采购订单",Constant.采购订单);
                        break;
                    /** order_待收货 */
                    case 2:
                        CommonOrdersActivity.startActivity(getActivity(), Constant.order_采购订单_待收货);
                        // MyOrderActivity.startActivity(getActivity(), Constant.order_待收货, "采购订单",Constant.采购订单);
                        break;
                    /** 售后 */
                    case 3:
                        startActivity(AfterSaleCcActivity.class);
                        break;
                }
            }
            /** 配送员 */
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
                MyOrderActivity.startActivity(getActivity(), position, "我的订单", Constant.用户订单2);
            }

//                startActivity(CommoditySoldOutActivity.class);
        }
    };


    CommonRvAdapter.OnItemClickListener mFunctionOnItemClickListener = new CommonRvAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(int position) {

            Log.e("jsc", "DefaultUserFragment-onItemClick:" + mAuthStatus);
            /** 11商家成功  21供应商 */
            if (mAuthStatus == 11 | mAuthStatus == 21) {
                switch (position) {
                    /** 配送管理 */
                    case 0:
                        startActivity(DeliveryManManageActivity.class);
                        break;
                    /** 商品管理 */
                    case 1:
                        startActivity(GoodsManagerActivity.class);
                        break;
                    /** 订单管理 */
                    case 2:
                        startActivity(OrderManagerActivity.class);
                        break;
                    /** 银行卡 */
                    case 5:
                        startActivity(BankCardListActivity.class);
                        break;
                    /** 账户余额 */
                    case 4:
                        startActivity(FundManagerActivity.class);
                        break;
                    /** 会员数 */
                    case 3:
                        BusinessUserActivity.startActivity(getActivity());
                        break;
                    /** 收货地址 */
                    case 6:
                        startActivity(ShippingListAddressActivity.class);
                        break;
                    /** 客服 */
                    case 7:
                        Constant.get().startKeFu(getActivity());
                        break;
                }

            }
            /** 配送员 */
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
                    WebViewActivity.startActivity(getActivity(), RxHttp.KEFU_URL, "在线客服");
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
     * 订单 适配器
     */
    private void setOrderCommonRvAdapter(int authStatus) {
        List<MeBooth> booths = new ArrayList<>();
        /** 11商家成功  21 供应商成功 */
        if (authStatus == 11 | authStatus == 13 | authStatus == 21 | authStatus == 23) {
            tvMeOrder.setText("采购订单");
            booths.add(new MeBooth().setTitle("待支付").setResourceId(R.mipmap.me_dzf));
            booths.add(new MeBooth().setTitle("待发货").setResourceId(R.mipmap.me_qbdd));
            booths.add(new MeBooth().setTitle("待收货").setResourceId(R.mipmap.me_dsh));
            booths.add(new MeBooth().setTitle("售后退款").setResourceId(R.mipmap.me_shtk));
        }
        /** 31 配送员成功 */
        else if (authStatus == 31 | authStatus == 33) {
            tvMeOrder.setText("配送订单");
            booths.add(new MeBooth().setTitle("全部").setResourceId(R.mipmap.me_qbdd));
            booths.add(new MeBooth().setTitle("已接单").setResourceId(R.mipmap.me_ddgl));
//            booths.add(new MeBooth().setTitle("配送中").setResourceId(R.mipmap.me_dsh));
            booths.add(new MeBooth().setTitle("已送达").setResourceId(R.mipmap.me_shtk));
        } else {
            tvMeOrder.setText("我的订单");
            booths.add(new MeBooth().setTitle("全部订单").setResourceId(R.mipmap.me_qbdd));
            booths.add(new MeBooth().setTitle("待支付").setResourceId(R.mipmap.me_dzf));
            booths.add(new MeBooth().setTitle("待收货").setResourceId(R.mipmap.me_dsh));
            booths.add(new MeBooth().setTitle("待评价").setResourceId(R.mipmap.me_dpj));
        }
        mOrderCommonRvAdapter.setData(booths);

        setFunctionCommonRvAdapter(authStatus);
    }

    /**
     * 功能 适配器
     */
    private void setFunctionCommonRvAdapter(int authStatus) {
        List<MeBooth> booths = new ArrayList<>();


        /** 11商家成功  21 供应商成功 */
        if (authStatus == 11 | authStatus == 13 | authStatus == 21 | authStatus == 23) {
            tvMeFunction.setText("常用功能");
            booths.add(new MeBooth().setTitle("配送管理").setResourceId(R.mipmap.me_psgl));
            booths.add(new MeBooth().setTitle("商品管理").setResourceId(R.mipmap.me_spgl));
            booths.add(new MeBooth().setTitle("订单管理").setResourceId(R.mipmap.me_ddgl));
            booths.add(new MeBooth().setTitle("会员管理").setResourceId(R.mipmap.me_tuandui));

            booths.add(new MeBooth().setTitle("账户余额").setResourceId(R.mipmap.me_zhye));
            booths.add(new MeBooth().setTitle("银行卡").setResourceId(R.mipmap.me_yhk));
            booths.add(new MeBooth().setTitle("收货地址").setResourceId(R.mipmap.me_shdz));
            booths.add(new MeBooth().setTitle("客服").setResourceId(R.mipmap.me_kf));
        }
        /** 31 配送员成功 */
        else if (authStatus == 31 | authStatus == 33) {
            tvMeFunction.setText("常用功能");
//            booths.add(new MeBooth().setTitle("余额").setResourceId(R.mipmap.me_zhye));
//            booths.add(new MeBooth().setTitle("银行卡").setResourceId(R.mipmap.me_yhk));
            booths.add(new MeBooth().setTitle("客服").setResourceId(R.mipmap.me_kf));
        } else {
            tvMeFunction.setText("常用功能");
            booths.add(new MeBooth().setTitle("优惠券").setResourceId(R.mipmap.me_yhq));
            booths.add(new MeBooth().setTitle("客服").setResourceId(R.mipmap.me_kf));
            booths.add(new MeBooth().setTitle("收货地址").setResourceId(R.mipmap.me_shdz));
            booths.add(new MeBooth().setTitle("售后退款").setResourceId(R.mipmap.me_shtk));
            booths.add(new MeBooth().setTitle("邀请有礼").setResourceId(R.mipmap.me_yqyl));
        }

        mFunctionCommonRvAdapter.setData(booths);
    }

}
