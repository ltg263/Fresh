package com.power.fresh.push;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.chen.concise.RxHttp;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.hjq.toast.ToastUtils;
import com.power.fresh.R;
import com.power.fresh.ui.AuditActivity;
import com.power.fresh.ui.order.OrderDetailsActivity;
import com.power.fresh.utils.SystemUtils;
import com.powerrich.common.base.BaseObserver;
import com.powerrich.common.other.RxSchedulers;

import cn.jpush.android.api.CmdMessage;
import cn.jpush.android.api.CustomMessage;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.JPushMessage;
import cn.jpush.android.api.NotificationMessage;
import cn.jpush.android.service.JPushMessageReceiver;

/**
 * @author AlienChao
 * @date 2020/01/14 09:14
 */
public class JPushReceiver extends JPushMessageReceiver {


    private static final String TAG = "jsc-PushMessageReceiver";
    private static final int TYPE_CLOCK = 1;
    private static final int TYPE_BUSINESS = 2;
    private Context mContext;

    @Override
    public void onMessage(Context context, CustomMessage customMessage) {
        Log.e(TAG, "[onMessage] " + customMessage);
        processCustomMessage(context, customMessage);
        customMessage(context, customMessage);
    }

    @Override
    public void onNotifyMessageOpened(Context context, NotificationMessage message) {
        this.mContext = context;
        Log.e(TAG, "[onNotifyMessageOpened] " + message);
        Gson gson = new Gson();
        String notificationExtras = message.notificationExtras;
        try {
            NotificationExtra extra = gson.fromJson(notificationExtras, NotificationExtra.class);

            /**
             //31 跳转商家销售订单详情
             //32 跳转供应商销售订单详情
             跳详情 文档上写的是跳列表  */
            if (extra.getParam_type().equals("31")||extra.getParam_type().equals("32")) {
                OrderDetailsActivity.startActivity(context,extra.getParam_key());
            }else if(extra.getParam_type().equals("10")){
                AuditActivity.startActivity(context);
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }


    }



    private void launchActivity(int type) {
        //判断app进程是否存活
//        if (SystemUtils.isAppAlive(mContext, "com.powerrich.fcoa")) {
//            //如果存活的话，就直接启动目标Activity，但要考虑一种情况，就是app的进程虽然仍然在
//            //但Task栈已经空了，比如用户点击Back键退出应用，但进程还没有被系统回收，如果直接启动
//            //目标Activity,再按Back键就不会返回MainActivity了。所以在启动
//            //目标Activity前，要先启动HomeActivity。
//            Log.i("JPushReceiver", "the app process is alive");
//            Intent mainIntent = new Intent(mContext, HomeActivity.class);
//            //将HomeActivity的launchMode设置成SingleTask, 或者在下面flag中加上Intent.FLAG_CLEAR_TOP,
//            //如果Task栈中有HomeActivity的实例，就会把它移到栈顶，把在它之上的Activity都清理出栈，
//            //如果Task栈不存在HomeActivity实例，则在栈顶创建
//            mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            mContext.startActivity(mainIntent);
//            if (type == TYPE_BUSINESS && pushInfoBean != null) {
//                //启动指定的activity
//                JPush2ActivityUtil.startDetailActivity(mContext, pushInfoBean);
//            } else if (type == TYPE_CLOCK) {
//                //启动考勤打卡的activity
//                Intent clockIntent = new Intent(mContext, ClockActivity2.class);
//                clockIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                mContext.startActivity(clockIntent);
//            } else {
//                ToastUtils.show("数据类型有误");
//            }
//        } else {
//            //如果app进程已经被杀死，先重新启动app，将目标Activity的启动参数传入HomeActivity，
//            // 此时app的初始化已经完成，在HomeActivity中就可以根据传入参数跳转到目标Activity中去了
//            Log.i("JPushReceiver", "the app process is dead");
//            Intent launchIntent = mContext.getPackageManager().
//                    getLaunchIntentForPackage("com.powerrich.fcoa");
//            launchIntent.setFlags(
//                    Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
//            launchIntent.putExtra("pushInfoBean", pushInfoBean);
//            mContext.startActivity(launchIntent);
//        }
    }


    @Override
    public void onMultiActionClicked(Context context, Intent intent) {
        Log.e(TAG, "[onMultiActionClicked] 用户点击了通知栏按钮");
        String nActionExtra = intent.getExtras().getString(JPushInterface.EXTRA_NOTIFICATION_ACTION_EXTRA);

        //开发者根据不同 Action 携带的 extra 字段来分配不同的动作。
        if (nActionExtra == null) {
            Log.d(TAG, "ACTION_NOTIFICATION_CLICK_ACTION nActionExtra is null");
            return;
        }
        if (nActionExtra.equals("my_extra1")) {
            Log.e(TAG, "[onMultiActionClicked] 用户点击通知栏按钮一");
        } else if (nActionExtra.equals("my_extra2")) {
            Log.e(TAG, "[onMultiActionClicked] 用户点击通知栏按钮二");
        } else if (nActionExtra.equals("my_extra3")) {
            Log.e(TAG, "[onMultiActionClicked] 用户点击通知栏按钮三");
        } else {
            Log.e(TAG, "[onMultiActionClicked] 用户点击通知栏按钮未定义");
        }
    }

    @Override
    public void onNotifyMessageArrived(Context context, NotificationMessage message) {
        Log.e(TAG, "[onNotifyMessageArrived] " + message);
    }

    @Override
    public void onNotifyMessageDismiss(Context context, NotificationMessage message) {
        Log.e(TAG, "[onNotifyMessageDismiss] " + message);
    }

    @Override
    public void onRegister(Context context, String registrationId) {
        Log.e(TAG, "[onRegister] " + registrationId);
    }

    @Override
    public void onConnected(Context context, boolean isConnected) {
        Log.e(TAG, "[onConnected] " + isConnected);
    }

    @Override
    public void onCommandResult(Context context, CmdMessage cmdMessage) {
        Log.e(TAG, "[onCommandResult] " + cmdMessage);
    }

    @Override
    public void onTagOperatorResult(Context context, JPushMessage jPushMessage) {
        //  TagAliasOperatorHelper.getInstance().onTagOperatorResult(context,jPushMessage);
        super.onTagOperatorResult(context, jPushMessage);
    }

    @Override
    public void onCheckTagOperatorResult(Context context, JPushMessage jPushMessage) {
        //  TagAliasOperatorHelper.getInstance().onCheckTagOperatorResult(context,jPushMessage);
        super.onCheckTagOperatorResult(context, jPushMessage);
    }

    @Override
    public void onAliasOperatorResult(Context context, JPushMessage jPushMessage) {
        // TagAliasOperatorHelper.getInstance().onAliasOperatorResult(context,jPushMessage);
        super.onAliasOperatorResult(context, jPushMessage);
    }

    @Override
    public void onMobileNumberOperatorResult(Context context, JPushMessage jPushMessage) {
        // TagAliasOperatorHelper.getInstance().onMobileNumberOperatorResult(context,jPushMessage);
        super.onMobileNumberOperatorResult(context, jPushMessage);
    }

    //send msg to MainActivity
    private void processCustomMessage(Context context, CustomMessage customMessage) {
//        if (MainActivity.isForeground) {
//            String message = customMessage.message;
//            String extras = customMessage.extra;
//            Intent msgIntent = new Intent(MainActivity.MESSAGE_RECEIVED_ACTION);
//            msgIntent.putExtra(MainActivity.KEY_MESSAGE, message);
//            if (!ExampleUtil.isEmpty(extras)) {
//                try {
//                    JSONObject extraJson = new JSONObject(extras);
//                    if (extraJson.length() > 0) {
//                        msgIntent.putExtra(MainActivity.KEY_EXTRAS, extras);
//                    }
//                } catch (JSONException e) {
//
//                }
//
//            }
//            LocalBroadcastManager.getInstance(context).sendBroadcast(msgIntent);
//        }

    }

    private void customMessage(Context context, CustomMessage customMessage) {
        Log.e(TAG, "JPushReceiver-customMessage:");
        String message = customMessage.message;
        String extras = customMessage.extra;
        //自定义通知Notification:弹出通知,当用户点击通知的时候跳转到浏览器打开页面
//        NotificationUtils notificationUtils = new NotificationUtils(context, R.mipmap.app_logo, "消息", message);
//        //  notificationUtils.notify(new Intent(context,NotificationClickReceiver.class));
//        notificationUtils.notify(new Intent(context, HomeActivity.class));
    }

    @Override
    public void onNotificationSettingsCheck(Context context, boolean isOn, int source) {
        super.onNotificationSettingsCheck(context, isOn, source);
        Log.e(TAG, "[onNotificationSettingsCheck] isOn:" + isOn + ",source:" + source);
    }


}
