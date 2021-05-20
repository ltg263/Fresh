package com.power.fresh.indicator;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.power.fresh.indicator.abs.IPagerNavigator;

public class CommonIndicator  extends FrameLayout{
    private IPagerNavigator mNavigator;

    public CommonIndicator(Context context) {
        super(context);
    }

    public CommonIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (mNavigator != null) {
            mNavigator.onPageScrolled(position, positionOffset, positionOffsetPixels);
        }
    }

    public void onPageSelected(int position) {
        if (mNavigator != null) {
            mNavigator.onPageSelected(position);
        }
    }

    public void onPageScrollStateChanged(int state) {
        if (mNavigator != null) {
            mNavigator.onPageScrollStateChanged(state);
        }
    }

    public IPagerNavigator getNavigator() {
        return mNavigator;
    }

    public void setNavigator(IPagerNavigator navigator) {
        if (mNavigator == navigator) {
            return;
        }
        if (mNavigator != null) {
            mNavigator.onDetachFromMagicIndicator();
        }
        mNavigator = navigator;
        removeAllViews();
        if (mNavigator instanceof View) {
            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
            addView((View) mNavigator, lp);
            mNavigator.onAttachToMagicIndicator();
        }
    }
}
