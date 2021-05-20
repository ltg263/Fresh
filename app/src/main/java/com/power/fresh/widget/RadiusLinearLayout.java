package com.power.fresh.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.power.fresh.R;

public class RadiusLinearLayout extends LinearLayout {
    public RadiusLinearLayout(Context context) {
        super(context);
    }

    public RadiusLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs,0);
    }

    public RadiusLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public RadiusLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context,attrs,defStyleAttr);
    }
    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        RoundLayoutDrawable bg = fromAttributeSet(context, attrs, 0);
        setBackgroundKeepingPadding(this,bg);

    }

    private RoundLayoutDrawable fromAttributeSet(Context context, AttributeSet attrs, int i) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RadiusLinearLayout);
        ColorStateList colorBg = typedArray.getColorStateList(R.styleable.RadiusLinearLayout_public_backgroundColor);
        ColorStateList colorBorder = typedArray.getColorStateList(R.styleable.RadiusLinearLayout_public_borderColor);
        int borderWidth = typedArray.getDimensionPixelSize(R.styleable.RadiusLinearLayout_public_borderWidth, 0);
        boolean isRadiusAdjustBounds = typedArray.getBoolean(R.styleable.RadiusLinearLayout_public_isRadiusAdjustBounds, false);
        int mRadius = typedArray.getDimensionPixelSize(R.styleable.RadiusLinearLayout_public_radius, 0);
        int mRadiusTopLeft = typedArray.getDimensionPixelSize(R.styleable.RadiusLinearLayout_public_radiusTopLeft, 0);
        int mRadiusTopRight = typedArray.getDimensionPixelSize(R.styleable.RadiusLinearLayout_public_radiusTopRight, 0);
        int mRadiusBottomLeft = typedArray.getDimensionPixelSize(R.styleable.RadiusLinearLayout_public_radiusBottomLeft, 0);
        int mRadiusBottomRight = typedArray.getDimensionPixelSize(R.styleable.RadiusLinearLayout_public_radiusBottomRight, 0);
        typedArray.recycle();

        RoundLayoutDrawable bg = new RoundLayoutDrawable();
        bg.setBgData(colorBg);
        bg.setStrokeData(borderWidth, colorBorder);
        if (mRadiusTopLeft > 0 || mRadiusTopRight > 0 || mRadiusBottomLeft > 0 || mRadiusBottomRight > 0) {
            float[] radii = new float[]{
                    mRadiusTopLeft, mRadiusTopLeft,
                    mRadiusTopRight, mRadiusTopRight,
                    mRadiusBottomRight, mRadiusBottomRight,
                    mRadiusBottomLeft, mRadiusBottomLeft
            };
            bg.setCornerRadii(radii);
            isRadiusAdjustBounds = false;
        } else {
            bg.setCornerRadius(mRadius);
            if(mRadius > 0){
                isRadiusAdjustBounds = false;
            }
        }
        bg.setIsRadiusAdjustBounds(isRadiusAdjustBounds);
        return bg;
    }

    public void setBackgroundKeepingPadding(View view, Drawable drawable){
        int[] padding = new int[]{view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom()};
        view.setBackground(drawable);
        view.setPadding(padding[0], padding[1], padding[2], padding[3]);
    }
}
