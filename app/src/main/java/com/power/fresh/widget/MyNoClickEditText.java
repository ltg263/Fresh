package com.power.fresh.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatEditText;

/**
 * @author AlienChao
 * @date 2019/03/01 10:32
 */
public class MyNoClickEditText extends AppCompatEditText {
    public MyNoClickEditText(Context context) {
        this(context, null);
    }

    public MyNoClickEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyNoClickEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private boolean isNoClick = true;

    public void setNoClick(boolean click) {
        isNoClick = click;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (isNoClick) {
            return false;
        }
        return super.dispatchTouchEvent(event);
    }
}
