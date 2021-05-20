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


/**
 * 文件名：
 * 描述：
 * 作者：梁帆
 * 时间：2019/3/29/029
 * 版权：
 */
public class ChooseSharePopWindow {
    private Context mContext;
    private CustomPopWindow mCustomPopWindow;
    PopupWindow.OnDismissListener mOnDismissListener;

    public interface ChooseImgImpl {
        void shareFriend();

        void circleOfFriends();
        void collect();

        default void cancel() {

        }
    }

    public ChooseSharePopWindow(Context context) {
        this.mContext = context;
    }
    public ChooseSharePopWindow(Context context, PopupWindow.OnDismissListener listener) {
        this.mContext = context;
        this.mOnDismissListener = listener;
    }

    public void showBottomView(final ChooseImgImpl callBack) {
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.public_share_pop_picture, null);
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
            if (view.getId() == R.id.tv_share_friend) {
                callBack.shareFriend();
            } else if (view.getId() == R.id.tv_circleOfFriends) {
                callBack.circleOfFriends();
            }else if (view.getId() == R.id.tv_collect) {
                callBack.collect();
            } else if (view.getId() == R.id.tv_cancel) {
                callBack.cancel();
            }
            mCustomPopWindow.dissmiss();
        };
        contentView.findViewById(R.id.tv_share_friend).setOnClickListener(listener);
        contentView.findViewById(R.id.tv_circleOfFriends).setOnClickListener(listener);
        contentView.findViewById(R.id.tv_collect).setOnClickListener(listener);
        contentView.findViewById(R.id.tv_cancel).setOnClickListener(listener);
    }
}
