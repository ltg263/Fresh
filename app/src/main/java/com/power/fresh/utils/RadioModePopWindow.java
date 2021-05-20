package com.power.fresh.utils;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.zhouwei.library.CustomPopWindow;
import com.power.fresh.R;


public class RadioModePopWindow {
    private Context mContext;
    private CustomPopWindow mCustomPopWindow;
    PopupWindow.OnDismissListener mOnDismissListener;

    public interface OnItemClickListener {
        void onItemClick(String content,int position);

    }

    public RadioModePopWindow(Context context) {
        this.mContext = context;
    }
    public RadioModePopWindow(Context context, PopupWindow.OnDismissListener listener) {
        this.mContext = context;
        this.mOnDismissListener = listener;
    }

    public void showBottomView(OnItemClickListener onItemClickListener) {
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.public_radio_mode_pop, null);
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
            if (view.getId() == R.id.ll_mode_001) {
                onItemClickListener.onItemClick("仅退款",1);
            } else if (view.getId() == R.id.ll_mode_002) {
                onItemClickListener.onItemClick("退货",1);
            }else if (view.getId() == R.id.ll_mode_003) {
                onItemClickListener.onItemClick("换货",3);
            }
            mCustomPopWindow.dissmiss();
        };
        contentView.findViewById(R.id.ll_mode_001).setOnClickListener(listener);
        contentView.findViewById(R.id.ll_mode_002).setOnClickListener(listener);
        contentView.findViewById(R.id.ll_mode_003).setOnClickListener(listener);

    }
}
