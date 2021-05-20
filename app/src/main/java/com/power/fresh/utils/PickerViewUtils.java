package com.power.fresh.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.core.content.ContextCompat;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.power.fresh.R;


import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class PickerViewUtils {

    /**
     * 年月日
     *
     * @param context
     * @param listener
     */
    public static void showDatePickerView(Context context, String time, OnTimeSelectListener listener) {
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();

        //正确设置方式 原因：注意事项有说明
        startDate.set(2000, 0, 1);
        endDate.set(2100, 11, 31);

        new TimePickerBuilder(context, listener)
                .setDate(strToCalendar(time, "yyyy-MM-dd"))
                .setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setCancelColor(Color.GRAY)
                .setTextColorCenter(ContextCompat.getColor(context, R.color.color_106AEF))
                .setSubmitText("确定")//确认按钮文字
                .setContentTextSize(18)//滚轮文字大小
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .setTitleBgColor(Color.WHITE)//标题文字颜色
                .setSubmitColor(Color.BLUE)//确定按钮文字颜色
                .setLineSpacingMultiplier(2f)//文字行间距
                .setDividerColor(Color.BLUE)//选中边框颜色
                .setRangDate(startDate, endDate)//起始终止年月日设定
                .setLabel("年", "月", "日", "时", "分", "秒")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .build()
                .show();
    }


    /**
     * 年月日 十分
     *
     * @param context
     * @param listener
     */
    public static void showDatePickerViewAll(Context context, String time, OnTimeSelectListener listener) {
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();

        //正确设置方式 原因：注意事项有说明
        startDate.set(2000, 0, 1);
        endDate.set(2100, 11, 31);

        new TimePickerBuilder(context, listener)
                .setDate(strToCalendar(time))
                .setType(new boolean[]{true, true, true, true, true, false})// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setCancelColor(Color.GRAY)
                .setSubmitText("确定")//确认按钮文字
                .setContentTextSize(18)//滚轮文字大小
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .setTitleBgColor(Color.WHITE)//标题文字颜色
                .setSubmitColor(Color.BLUE)//确定按钮文字颜色
                .setLineSpacingMultiplier(2f)//文字行间距
                .setDividerColor(Color.BLUE)//选中边框颜色
                .setRangDate(startDate, endDate)//起始终止年月日设定
                .setLabel("年", "月", "日", "时", "分", "秒")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .build()
                .show();
    }


    public interface OnTimeSelectAndCancel extends OnTimeSelectListener {
        default void onCancel(){};
    }


    /**
     * 年月日 十分
     *
     * @param context
     * @param listener
     */
    public static void showDatePickerViewCustom(Context context, String time, String format, boolean year, boolean month, boolean day, boolean hour, boolean minute, boolean second, final OnTimeSelectAndCancel listener) {

        hideSoftKeyboard((Activity) context);

        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();

        //正确设置方式 原因：注意事项有说明
        startDate.set(2000, 0, 1);
        endDate.set(2100, 11, 31);

        new TimePickerBuilder(context, listener)
                .setDate(strToCalendar(time, format))
                .setType(new boolean[]{year, month, day, hour, minute, second})// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .addOnCancelClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onCancel();
                    }
                })
                .setCancelColor(Color.GRAY)
                .setSubmitText("确定")//确认按钮文字
                .setContentTextSize(18)//滚轮文字大小
                .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
                .setTitleBgColor(Color.WHITE)//标题文字颜色
                .setSubmitColor(Color.BLUE)//确定按钮文字颜色
                .setLineSpacingMultiplier(2f)//文字行间距
                .setDividerColor(Color.BLUE)//选中边框颜色
                .setRangDate(startDate, endDate)//起始终止年月日设定
                .setLabel("年", "月", "日", "时", "分", "秒")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .build()
                .show();
    }


    /**
     * 时间选择器 日时分
     */
    public static void showTimePickerView(Context context, OnTimeSelectListener listener) {
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();

        //正确设置方式 原因：注意事项有说明
        startDate.set(2000, 0, 1);
        endDate.set(2100, 11, 31);

        new TimePickerBuilder(context, listener)
                .setType(new boolean[]{false, false, true, true, true, false})// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setCancelColor(Color.GRAY)
                .setSubmitText("确定")//确认按钮文字
                .setContentTextSize(18)//滚轮文字大小
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .setTitleBgColor(Color.WHITE)//标题文字颜色
                .setSubmitColor(Color.BLUE)//确定按钮文字颜色
                .setLineSpacingMultiplier(2f)//文字行间距
                .setDividerColor(Color.BLUE)//选中边框颜色
                .setRangDate(startDate, endDate)//起始终止年月日设定
                .setLabel("年", "月", "日", "时", "分", "秒")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .build()
                .show();
    }

    /**
     * 条件选择器
     */
    public static void showOptionsPickerView(Context context, OnOptionsSelectListener listener, List<String> optionsItems) {

        OptionsPickerView<String> build = new OptionsPickerBuilder(context, listener)
                .setSubmitText("确定")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
                .setSubCalSize(18)//确定和取消文字大小
                .setSubmitColor(Color.BLUE)//确定按钮文字颜色
                .setCancelColor(Color.BLUE)//取消按钮文字颜色
                .setTitleBgColor(Color.WHITE)//标题背景颜色 Night mode
                .setBgColor(Color.WHITE)//滚轮背景颜色 Night mode
                .setContentTextSize(18)//滚轮文字大小
                .isCenterLabel(true) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setCyclic(true, false, false)//循环与否
                .setSelectOptions(1)  //设置默认选中项
                .setOutSideCancelable(true)//点击外部dismiss default true
                .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
                .build();
        build.setPicker(optionsItems);
        build.show();
    }

    /**
     * 提供条件选择器的控件样式
     *
     * @param context
     * @param listener
     * @return
     */
    public static OptionsPickerBuilder getOptionsPickerView(Context context, OnOptionsSelectListener listener) {
        return new OptionsPickerBuilder(context, listener)
                .setSubmitText("确定")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
                .setTitleText("选择项目类型")
                .setSubCalSize(18)//确定和取消文字大小
                .setTextColorCenter(Color.BLUE)
                .setSubmitColor(R.color.color_333333)//确定按钮文字颜色
                .setCancelColor(R.color.color_333333)//取消按钮文字颜色
                .setTitleBgColor(Color.WHITE)//标题背景颜色 Night mode
                .setBgColor(Color.WHITE)//滚轮背景颜色 Night mode
                .setContentTextSize(18)//滚轮文字大小
                .isCenterLabel(true) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setCyclic(false, false, false)//循环与否
                .setSelectOptions(1)  //设置默认选中项
                .setOutSideCancelable(true)//点击外部dismiss default true
                .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
                ;
    }

    public static String getTime(Date date, String format) {//可根据需要自行截取数据显示
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }


    public static String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }


    private static Calendar strToCalendar(String strDate) {
        Calendar calendar = Calendar.getInstance();
        Date date = strToDate(strDate);
        if (date != null) {
            calendar.setTime(date);
        }
        return calendar;
    }


    public static Calendar strToCalendar(String strDate, String format) {
        Calendar calendar = Calendar.getInstance();
        Date date = strToDate(strDate, format);
        if (date != null) {
            calendar.setTime(date);
        }
        return calendar;
    }


    private static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        ParsePosition pos = new ParsePosition(0);
        return formatter.parse(strDate, pos);
    }


    public static Date strToDate(String strDate, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        ParsePosition pos = new ParsePosition(0);
        return formatter.parse(strDate, pos);
    }


    /**
     * 由一种时间类型 转换为另一种时间格式类型
     *
     * @param strDate
     * @return
     */
    public static String strToDateInLeavaDate(String strDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date dBegin = null;
        try {
            dBegin = sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dBegin2 = sdf2.format(dBegin);
        return dBegin2;

    }

    /**
     * 由一种时间类型 转换为另一种时间格式类型
     *
     * @return
     */
    public static String date2Date(Date date) {
        if (date == null) return "";
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dBegin2 = sdf2.format(date);
        return dBegin2;

    }

    public static Date strToDateLong(String strDate, String formart) {
        SimpleDateFormat formatter = new SimpleDateFormat(formart);
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 跟8：30 对比
     *
     * @param inTime
     * @return
     */
    public static boolean comparison(String inTime) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        Date now = null;
        Date date = strToDateLong(inTime, "yyyy-MM-dd HH:mm:ss");
        String time = format.format(date);

        ParsePosition pos = new ParsePosition(0);
        now = format.parse(time, pos);
        Date ten = null;
        try {
            ten = format.parse("8:30");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ten.after(now);
    }


    /**
     * 跟11:59:59 对比
     *
     * @param inTime
     * @return
     */
    public static boolean comparisonAfter(String inTime) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date now = null;
        Date date = strToDateLong(inTime, "yyyy-MM-dd HH:mm:ss");
        String time = format.format(date);

        ParsePosition pos = new ParsePosition(0);
        now = format.parse(time, pos);
        Date ten = null;
        try {
            ten = format.parse("23:59:59");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ten.after(now);
    }


    /**
     * 是否是半小时内
     *
     * @param dateStr 对比时间、当前时间
     * @param format  当前时间 和 对比时间 半小时后对比
     * @return
     */
    public static boolean halfHourIn(String dateStr, String currentDateStr, String format) {

        Date dateYMDHMS = strToDateLong(dateStr, "yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String formatTime = formatter.format(dateYMDHMS);
        System.out.println(formatTime);
        ParsePosition pos = new ParsePosition(0);
        ParsePosition pos2 = new ParsePosition(0);
        Date date = formatter.parse(formatTime, pos);
        Date currentDate = formatter.parse(currentDateStr, pos2);

        long time = date.getTime();
        time += 30 * 60 * 1000;
        //半小时后的时间
        Date date2 = new Date(time);

        return currentDate.before(date2);

    }

    /**
     * 隐藏软键盘
     */
    private static void hideSoftKeyboard(Activity activity) {
        // 隐藏软键盘，避免软键盘引发的内存泄露
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager manager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (manager != null) {
                manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }


    public static void main(String[] args) throws ParseException {


        //    System.out.println(strToDate2("2019-10-15 13:53","yyyy-MM-dd HH:mm:ss"));
    }


}
