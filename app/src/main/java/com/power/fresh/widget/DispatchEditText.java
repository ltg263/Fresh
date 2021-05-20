package com.power.fresh.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import com.noober.background.BackgroundFactory;

/**
 * @author AlienChao
 * @date 2020/05/29 14:38
 */
public class DispatchEditText extends androidx.appcompat.widget.AppCompatEditText {

    boolean isTouch=true;

    public void setTouch(boolean touch) {
        isTouch = touch;
    }

    public DispatchEditText(Context context) {
        super(context);
    }

    public DispatchEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public DispatchEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (isTouch) {
            return super.dispatchTouchEvent(event);
        }
        return false;
    }

    private void init(Context context, AttributeSet attrs){
        BackgroundFactory.setViewBackground(context, attrs, this);
    }
}
