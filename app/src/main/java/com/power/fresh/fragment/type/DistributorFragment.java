package com.power.fresh.fragment.type;

import com.power.common_opensurce.App;

import com.power.common_opensurce.UserInfo;
import com.power.fresh.R;
import com.powerrich.common.base.MyLazyFragment;

/**
 * 经销商登陆：2，Distributor
 */
public class DistributorFragment extends MyLazyFragment {


    public static DistributorFragment newInstance() {
        DistributorFragment fragment = new DistributorFragment();
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
        return R.layout.fragment_distributor;
    }

    @Override
    protected int getTitleId() {
        return 0;
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

            } else {

            }
        }
    }
}
