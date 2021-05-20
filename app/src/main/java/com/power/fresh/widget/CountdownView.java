package com.power.fresh.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.hjq.toast.ToastUtils;

/**
 * 验证码倒计时
 */
public final class CountdownView extends AppCompatTextView implements Runnable {

    /**
     * 倒计时秒数
     */
    private int mTotalSecond = 60;
    /**
     * 秒数单位文本
     */
    private static final String TIME_UNIT = "s";

    /**
     * 当前秒数
     */
    private int mCurrentSecond;
    /**
     * 记录原有的文本
     */
    private CharSequence mRecordText;

    public CountdownView(Context context) {
        super(context);
    }

    public CountdownView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CountdownView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 设置倒计时总秒数
     */
    public void setTotalTime(int totalTime) {
        this.mTotalSecond = totalTime;
    }

    /**
     * 开始倒计时
     */
    public void start() {
//        ToastUtils.show("验证码已发送，请注意查收");
        mRecordText = getText();
        setEnabled(false);
        mCurrentSecond = mTotalSecond;
        post(this);
    }

    /**
     * 结束倒计时
     */
    public void stop() {
        setText(mRecordText);
        setEnabled(true);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        // 设置点击的属性
        setClickable(true);
    }

    @Override
    protected void onDetachedFromWindow() {
        // 移除延迟任务，避免内存泄露
        removeCallbacks(this);
        super.onDetachedFromWindow();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void run() {
        if (mCurrentSecond == 0) {
            stop();
        } else {
            mCurrentSecond--;

            String str = "<font color='#066839'>" + mCurrentSecond+TIME_UNIT + "</font>"+"<font color='#808080'>后重发</font>";
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                setText(Html.fromHtml(str, Html.FROM_HTML_MODE_LEGACY));
            } else {
                setText(Html.fromHtml(str));
            }
            postDelayed(this, 1000);
        }
    }
}