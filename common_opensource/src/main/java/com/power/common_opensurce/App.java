package com.power.common_opensurce;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;

import com.chen.concise.RxHttp;
import com.powerrich.common.base.BaseApplication;
import com.powerrich.common.helper.SPUtils;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.mmkv.MMKV;

import cn.jpush.android.api.JPushInterface;


/**
 * @author AlienChao
 * @date 2020/04/13 10:06
 */
public class App extends BaseApplication {
    /** 登录返回的信息 */
    private static final String USER_KEY = "USERINFO";
    /** 获取用户信息接口 */
    public static final String USERINFOCC = "USERINFOCC";


    // APP_ID 替换为你的应用从官方网站申请到的合法appId
    public static final String APP_ID = "wx7ac935e2df71c059";

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        boolean debug = BuildConfig.DEBUG;
        MMKV.initialize(this);
        RxHttp.getInstance().setInterceptor(new OkHttpInterceptor());
        mSpUser= SPUtils.get(USERINFOCC, UserInfoCc.class);

        //不是debug 才收集奔溃信息
//        if (!debug) {
        CrashReport.initCrashReport(getApplicationContext(), "0db80ea02d", false);
//        }


        // 三个参数分别是上下文、应用的appId、是否检查签名（默认为false）
        IWXAPI mWxApi = WXAPIFactory.createWXAPI(this, APP_ID, true);
// 注册
        mWxApi.registerApp("你的appId");


        JPushInterface.setDebugMode(debug);
        JPushInterface.init(this);


    }

    /**
     * 获取登陆信息
     *
     * @return 返回登陆信息
     */
    public static UserInfo getUserInfo() {
        UserInfo spUser = SPUtils.get(USER_KEY, UserInfo.class);
        if (spUser != null) {
            return  spUser;
        }
        return new UserInfo();
    }

    static UserInfoCc mSpUser;

    public static void setmSpUser(UserInfoCc mSpUser) {
        App.mSpUser = mSpUser;
    }

    public static UserInfoCc getUserInfoCc() {

        if (mSpUser != null) {
            return  mSpUser;
        }else {
            mSpUser= SPUtils.get(USERINFOCC, UserInfoCc.class);
        }

        if (mSpUser==null) {
            return  new UserInfoCc();
        }else{
            return  mSpUser;
        }


    }


    /**
     * 设置用户信息
     *
     * @return 返回是否成功
     */
    public UserInfo setUserInfo(UserInfo info) {
        SPUtils.put(USER_KEY, info);
        return info;
    }

    private static App app;

    /**
     *
     * @return
     */
    public static App getGlobalApp() {
        return app;
    }

//    @Override
//    public Resources getResources() {
//        Resources resources = super.getResources();
//        Configuration newConfig = resources.getConfiguration();
//        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
//
//        if (resources != null && newConfig.fontScale != 1) {
//            newConfig.fontScale = 1;
//            if (Build.VERSION.SDK_INT >= 17) {
//                Context configurationContext = createConfigurationContext(newConfig);
//                resources = configurationContext.getResources();
//                displayMetrics.scaledDensity = displayMetrics.density * newConfig.fontScale;
//                newConfig.setToDefaults();
//            } else {
//                resources.updateConfiguration(newConfig, displayMetrics);
//            }
//        }
//        return resources;
//    }

}
