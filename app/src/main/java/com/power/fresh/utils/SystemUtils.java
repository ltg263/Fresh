package com.power.fresh.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;

import com.powerrich.common.permission.CcPermissions;
import com.powerrich.common.permission.Consumer;
import com.powerrich.common.permission.PermissionName;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import static android.content.Context.TELEPHONY_SERVICE;


public class SystemUtils {

    /**
     * 可参考
     * Android 10获取设备标识方案探究 https://www.jianshu.com/p/e8b6cafa91d5
     * 获取设备唯一标识完美解决方案 https://blog.csdn.net/auccy/article/details/87940066
     * <p>
     * [参考链接：https://www.jianshu.com/p/c31e4f94d64c]
     * IMEI(International Mobile Equipment Identity)是国际移动设备身份码的缩写，由15位数字组成的电子串号与每台手机一一对应，且该码全世界唯一。
     * MEID(Mobile Equipment Identifier)移动设备识别码，是CDMA手机的身份识别码，也是每台CDMA手机或通讯平板唯一的识别码，由14位数字组成。
     * 全网通双卡手机有两个IMEI和一个MEID，Android6.0的API中提供了这样的方法getDeviceId(int slotIndex)
     *
     * @param context
     * @return
     */


    public static String getPhoneIMEI(FragmentActivity context) {
        String deviceId;
//        if (Build.VERSION.SDK_INT >Build.VERSION_CODES.P) {
        deviceId = Settings.System.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
//        } else {
//            TelephonyManager tm = (TelephonyManager) context.getSystemService(TELEPHONY_SERVICE);
             //这东西换卡 会出现不一致 电信+小米
//            deviceId = tm.getDeviceId();
//        }
    //    test();
        //ANDROID_ID(恢复出厂+刷机会变) + 序列号(android 10会unknown)+品牌 +机型

        String s = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            s = deviceId + Build.getSerial()+Build.BRAND+ Build.MODEL;
        }else{
            s = deviceId + Build.SERIAL+Build.BRAND+ Build.MODEL;
        }

        Log.e("jsc", "SystemUtils-getPhoneIMEI:"+toMD5(s));
        return toMD5(s);
    }


    public static String toMD5(String text){
        Log.e("jsc", "SystemUtils-toMD5:"+text);
        //获取摘要器 MessageDigest
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        //通过摘要器对字符串的二进制字节数组进行hash计算
        byte[] digest = messageDigest.digest(text.getBytes());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digest.length; i++) {
            //循环每个字符 将计算结果转化为正整数;
            int digestInt = digest[i] & 0xff;
            //将10进制转化为较短的16进制
            String hexString = Integer.toHexString(digestInt);
            //转化结果如果是个位数会省略0,因此判断并补0
            if (hexString.length() < 2) {
                sb.append(0);
            }
            //将循环结果添加到缓冲区
            sb.append(hexString);
        }
        //返回整个结果
        return sb.toString().substring(8,24);
    }




    private static void test() {
        String m_szDevIDShort = "35" + //we make this look like a valid IMEI

                Build.BOARD.length() % 10 +   //MHA
                Build.BRAND.length() % 10 +    //品牌
                Build.CPU_ABI.length() % 10 +  //CPU
                Build.DEVICE.length() % 10 +
                Build.DISPLAY.length() % 10 +
                Build.HOST.length() % 10 +
                Build.ID.length() % 10 +
                Build.MANUFACTURER.length() % 10 +
                Build.MODEL.length() % 10 +
                Build.PRODUCT.length() % 10 +
                Build.TAGS.length() % 10 +
                Build.TYPE.length() % 10 +
                Build.USER.length() % 10; //13 digits
    }

    /**
     * 获取设备唯一ID
     * @param context
     * @return
     */
//    public static String getDeviceUniqID(Context context) {
//        android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//        String unique_id ;
//        unique_id = tm.getDeviceId();
//        if (TextUtils.isEmpty(unique_id)) {
//            unique_id=android.os.Build.SERIAL;
//        }
//        return unique_id;
//    }


    /**
     * 待论证 应该没问题的
     *
     * @param context
     * @return
     */
    public static String getPhoneIMEI2(FragmentActivity context) {
        final String[] deviceId = {""};
        CcPermissions.with(context)
                .permission(PermissionName.READ_PHONE_STATE)
                .request(new Consumer() {
                    @Override
                    public void accept(List<String> granted, boolean isAll) {
                        if (Build.VERSION.SDK_INT >= 29) {
                            deviceId[0] = Settings.System.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
                        } else {
                            TelephonyManager tm = (TelephonyManager) context.getSystemService(TELEPHONY_SERVICE);
                            deviceId[0] = tm.getDeviceId();
                        }
                    }
                });


        return deviceId[0];
    }

    public static boolean isAppAlive(Context context, String packageName) {
        //通过ActivityManager我们可以获得系统里正在运行的activities
        //包括进程(Process)等、应用程序/包、服务(Service)、任务(Task)信息。
        ActivityManager am = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        //利用一个增强for循环取出手机里的所有进程
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            //通过比较进程的包名判断进程里是否存在该App
            if (packageName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }
}
