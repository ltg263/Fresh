package com.power.fresh.fragment;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;

import com.power.common_opensurce.App;
import com.power.common_opensurce.UserInfo;
import com.power.fresh.R;
import com.power.fresh.fragment.type.DefaultUserFragment;
import com.power.fresh.fragment.type.DeliveryManFragment;
import com.power.fresh.fragment.type.DistributorFragment;
import com.power.fresh.fragment.type.SupplierFragment;
import com.power.fresh.ui.login.LoginActivity;
import com.powerrich.common.base.MyLazyFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends MyLazyFragment {


    @BindView(R.id.rl_un_login)
    RelativeLayout rlUnLoginLayout;

    @BindView(R.id.fl_content)
    FrameLayout flContent;

    public static MeFragment newInstance() {
        MeFragment fragment = new MeFragment();
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
        return R.layout.fragment_me;
    }

    @Override
    protected int getTitleId() {
        return 0;
    }

    @OnClick({R.id.rl_un_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_un_login:
                startActivity(LoginActivity.class);
                break;
        }
    }

    @Override
    protected void initView() {

    }





    @Override
    protected void initData() {
        App globalApp = App.getGlobalApp();
        if (globalApp != null) {
            UserInfo userInfo = globalApp.getUserInfo();
            if (userInfo != null) {
                //设置
                rlUnLoginLayout.setVisibility(View.GONE);
                flContent.setVisibility(View.VISIBLE);
                UserInfo.UserBaseBean userBase = userInfo.getUserBase();
                getChildFragmentManager().beginTransaction().replace(R.id.fl_content, DefaultUserFragment.newInstance()).commit();
//                if (userBase.getLogPort() == 1) {
//                    //普通会员登录
//                    getChildFragmentManager().beginTransaction().replace(R.id.fl_content, DefaultUserFragment.newInstance()).commit();
//                } else if (userBase.getLogPort() == 2) {
//                    //经销商登录
//                    getChildFragmentManager().beginTransaction().replace(R.id.fl_content, DistributorFragment.newInstance()).commit();
//
//                } else if (userBase.getLogPort() == 3) {
//                    //供应商登陆：3，
//                    getChildFragmentManager().beginTransaction().replace(R.id.fl_content, SupplierFragment.newInstance()).commit();
//
//                } else if (userBase.getLogPort() == 4) {
//                    //配送员登陆：4
//                    getChildFragmentManager().beginTransaction().replace(R.id.fl_content, DeliveryManFragment.newInstance()).commit();
//                }

            } else {
                rlUnLoginLayout.setVisibility(View.VISIBLE);
                flContent.setVisibility(View.GONE);
            }
        }
    }
}
