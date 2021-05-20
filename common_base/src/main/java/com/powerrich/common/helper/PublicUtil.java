package com.powerrich.common.helper;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Field;
import java.util.Collection;

/**
 * 常用工具类
 * @author AlienChao
 * @date 2019/08/01 09:16
 */
public class PublicUtil {


    /**
     * 配置 RecyclerView
     *
     * @param recyclerView
     * @param layoutManager
     */
    public static void configRecyclerView(final RecyclerView recyclerView
            , RecyclerView.LayoutManager layoutManager) {
        recyclerView.setLayoutManager(layoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * 改变Recycler的滑动速度
     *
     * @param recyclerView
     * @param velocity     //滑动速度默认是8000dp
     */
    public static void setMaxFlingVelocity(RecyclerView recyclerView, int velocity) {
        try {
            Field field = recyclerView.getClass().getDeclaredField("mMaxFlingVelocity");
            field.setAccessible(true);
            field.set(recyclerView, velocity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * dp 转 px
     *
     * @param context {@link Context}
     * @param dpValue {@code dpValue}
     * @return {@code pxValue}
     */
    public static int dip2px(@NonNull Context context, float dpValue) {
        final float scale = getResources(context).getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int dp2px(Context context, float value) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, context.getResources().getDisplayMetrics()) + 0.5f);
    }


//    public static int dp2px(Context context, float value) {
//        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, context.getResources().getDisplayMetrics()) + 0.5f);
//    }
//
//    public static int sp2px(Context context, float value) {
//        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, value, context.getResources().getDisplayMetrics()) + 0.5f);
//    }

    /**
     * px 转 dp
     *
     * @param context {@link Context}
     * @param pxValue {@code pxValue}
     * @return {@code dpValue}
     */
    public static int pix2dip(@NonNull Context context, int pxValue) {
        final float scale = getResources(context).getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * sp 转 px
     *
     * @param context {@link Context}
     * @param spValue {@code spValue}
     * @return {@code pxValue}
     */
    public static int sp2px(@NonNull Context context, float spValue) {
        final float fontScale = getResources(context).getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * px 转 sp
     *
     * @param context {@link Context}
     * @param pxValue {@code pxValue}
     * @return {@code spValue}
     */
    public static int px2sp(@NonNull Context context, float pxValue) {
        final float fontScale = getResources(context).getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 判断数据是否为空
     *
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj) {
        boolean flag = true;
        if (obj != null) {
            if (obj instanceof String) {
                flag = (obj.toString().trim().length() == 0);
            } else if (obj instanceof Collection<?>) {
                flag = ((Collection) obj).size() == 0;
            } else {
                flag = false;
            }
        }
        return flag;
    }

    /**
     * 方法说明：<br>
     * 判断字符串
     *
     * @param str
     * @return
     */
    public static boolean isEmptyStr(String str) {
        return str == null || "null".equals(str.toLowerCase()) || str.trim().length() == 0;
    }



    /**
     * 获得资源
     */
    public static Resources getResources(Context context) {
        return context.getResources();
    }

}
