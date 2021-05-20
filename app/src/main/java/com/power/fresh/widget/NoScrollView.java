package com.power.fresh.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * @author AlienChao
 * @date 2020/07/30 16:44
 */
public  class NoScrollView  extends ScrollView {
    private boolean scroll = false;  //默认可以滑动

    public NoScrollView(Context context) {
        super(context);
    }

    public NoScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //传入true可滑动，传入false不可滑动
    public void setScroll(boolean scroll) {
        this.scroll = scroll;

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (scroll) {
            return super.onTouchEvent(ev);
        } else {
            return true;
        }
    }

}
