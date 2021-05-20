package com.power.common_opensurce.utils;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.zhouwei.library.CustomPopWindow;
import com.power.common_opensurce.R;


/**
 * 销售订单
 */
public class MarketOrderPopWindow {
    private Context mContext;
    private CustomPopWindow mCustomPopWindow;
    PopupWindow.OnDismissListener mOnDismissListener;

    public interface ChooseImgImpl {
        void wlps();

        void tvPsy();

        default void cancel() {

        }
    }

    public MarketOrderPopWindow(Context context) {
        this.mContext = context;
    }
    public MarketOrderPopWindow(Context context, PopupWindow.OnDismissListener listener) {
        this.mContext = context;
        this.mOnDismissListener = listener;
    }

    public void showBottomView(final ChooseImgImpl callBack) {
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.pop_market_select, null);
        mCustomPopWindow = new CustomPopWindow.PopupWindowBuilder(mContext)
                .size(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                .setView(contentView)
                .enableBackgroundDark(true)
                .setAnimationStyle(R.style.public_dialog_inout_anim)
                .setOnDissmissListener(mOnDismissListener)
                .create()

                .showAtLocation(((Activity) mContext).getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);




        //处理popWindow 显示内容
        View.OnClickListener listener = view -> {
            if (view.getId() == R.id.tv_psy) {
                callBack.tvPsy();
            } else if (view.getId() == R.id.tv_wlps) {
                callBack.wlps();
            } else if (view.getId() == R.id.tv_cancel) {
                callBack.cancel();
            }
            mCustomPopWindow.dissmiss();
        };
        contentView.findViewById(R.id.tv_psy).setOnClickListener(listener);
        contentView.findViewById(R.id.tv_wlps).setOnClickListener(listener);
        contentView.findViewById(R.id.tv_cancel).setOnClickListener(listener);
    }
}
