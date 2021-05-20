package com.power.fresh.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author AlienChao
 * @date 2020/05/12 15:54
 */
public class VerticalTextView  extends LinearLayout {

    public VerticalTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
        this.context = context;
    }

    private String text;
    private Context context;
    private int color;
    private int size;

    public VerticalTextView(Context context) {
        super(context);
        setOrientation(VERTICAL);
        this.context = context;
    }


    public void setText(String text) {
        this.text = text;
        addText();
    }

    private void addText() {
        removeAllViews();
        if (text != null) {
            char[] chara = text.toCharArray();
            for (int i = 0; i < chara.length; i++) {
                TextView oneText = new TextView(context);
                oneText.setTextColor(color);
                oneText.setText(text.substring(i, i + 1));
                addView(oneText);
            }
        }

    }

    public void setTextColor(int color) {
        this.color = color;
    }

    public void setTextSize(int size) {
        this.size = size;
    }

}
