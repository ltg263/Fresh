package com.power.fresh.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;


public class ProgressView extends View {

    private Paint mPaint;
    private float mProgress = 40;
    private int width = 0;
    private int height = 0;
    private int max = 100;

    public ProgressView(Context context) {
        this(context,null);
    }
    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }
    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    public void setProgress(int progress){
        if(progress >= max){
            this.mProgress=max;
        }else if(progress < 0){
            this.mProgress = 0;
        }else{
            this.mProgress = (float) progress;
        }
        invalidate();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = getWidth();
        height = getHeight();
        mPaint.setColor(Color.parseColor("#CCCCCC"));
        canvas.drawRoundRect(0,0,width,height,height/2,height/2,mPaint);
        mPaint.setColor(Color.parseColor("#DAF3DA"));
        canvas.drawRoundRect(0,0,width*mProgress/100,height,height/2,height/2,mPaint);
        mPaint.setColor(Color.parseColor("#333333"));
        mPaint.setTextSize(35);
        mPaint.setTextScaleX(1.1f);
        canvas.drawText("库存 "+mProgress+"%", width/5, height/4*3, mPaint);
    }
}