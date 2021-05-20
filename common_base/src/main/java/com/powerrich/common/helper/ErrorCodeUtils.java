package com.powerrich.common.helper;

import android.content.Intent;

import com.powerrich.common.base.BaseApplication;

/**
 * @author AlienChao
 * @date 2020/04/24 17:19
 */
public class ErrorCodeUtils {

    public static String getErrorCodeMsg(int errorCode) {

        String msg;

        switch (errorCode) {
            case 101:
                msg = "页面已过期, 请重新打开";
                SPUtils.clear();
                Intent intent = new Intent("LoginActivity");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                BaseApplication.getContext().startActivity(intent);
                break;
            case 500102:
                msg = "账号已存在";
                break;
            case 500103:
                msg = "账号或密码错误";
                break;
            case 500104:
                msg = "旧密码输入错误";
                break;
            case 500105:
                msg = "用户未查询到";
                break;
            default:
                msg = "" ;
                break;
        }
        return msg;

    }

}
