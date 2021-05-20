package com.power.common_opensurce.widget.alpha;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * 在 pressed 和 disabled 时改变 View 的透明度
 */
public class CommonAlphaLinearLayout extends LinearLayout implements CommonAlphaViewInf {

    private CommonAlphaViewHelper mAlphaViewHelper;

    public CommonAlphaLinearLayout(Context context) {
        super(context);
    }

    public CommonAlphaLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CommonAlphaLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private CommonAlphaViewHelper getAlphaViewHelper() {
        if (mAlphaViewHelper == null) {
            mAlphaViewHelper = new CommonAlphaViewHelper(this);
        }
        return mAlphaViewHelper;
    }

    @Override
    public void setPressed(boolean pressed) {
        super.setPressed(pressed);
        getAlphaViewHelper().onPressedChanged(this, pressed);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        getAlphaViewHelper().onEnabledChanged(this, enabled);
    }

    /**
     * 设置是否要在 press 时改变透明度
     *
     * @param changeAlphaWhenPress 是否要在 press 时改变透明度
     */
    @Override
    public void setChangeAlphaWhenPress(boolean changeAlphaWhenPress) {
        getAlphaViewHelper().setChangeAlphaWhenPress(changeAlphaWhenPress);
    }

    /**
     * 设置是否要在 disabled 时改变透明度
     *
     * @param changeAlphaWhenDisable 是否要在 disabled 时改变透明度
     */
    @Override
    public void setChangeAlphaWhenDisable(boolean changeAlphaWhenDisable) {
        getAlphaViewHelper().setChangeAlphaWhenDisable(changeAlphaWhenDisable);
    }

}
