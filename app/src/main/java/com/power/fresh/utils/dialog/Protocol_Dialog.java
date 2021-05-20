package com.power.fresh.utils.dialog;

import android.text.Html;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.power.fresh.R;
import com.powerrich.common.base.BaseDialog;
import com.powerrich.common.base.BaseDialogFragment;


/**
 * @author MingPeng
 * Created on 2019/8/29
 */
public class Protocol_Dialog extends BaseDialogFragment.Builder<Protocol_Dialog> implements View.OnClickListener {


    private final FragmentActivity mActivity;


    private TextView mCancelView;
    private TextView mConfirmView;
    private TextView et_dialog_message_message;
    private OnListener mOnListener;

    public void setOnListener(OnListener onListener) {

        mOnListener = onListener;
    }


//    public void dismiss() {
//        super.dismiss();
//    }

    public Protocol_Dialog(FragmentActivity activity) {
        super(activity);
        this.mActivity = activity;
        setContentView(R.layout.dialog_protocol);
        setAnimStyle(BaseDialog.AnimStyle.IOS);
        setGravity(Gravity.CENTER);

        mCancelView = findViewById(R.id.tv_dialog_message_cancel);
        et_dialog_message_message = findViewById(R.id.tv_dialog_update_message);
        et_dialog_message_message.setText(Html.fromHtml(getString(R.string.name_protocol)));
        mConfirmView = findViewById(R.id.tv_dialog_message_confirm);
        mCancelView.setOnClickListener(this);
        mConfirmView.setOnClickListener(this);
    }

    public Protocol_Dialog setmCancelView(boolean isShow){
        if (!isShow) {
            mCancelView.setVisibility(View.GONE);
            setCancelable(false);
        }

        return this;
    }

    public Protocol_Dialog setTitle(String content) {
        if (!TextUtils.isEmpty(content)) {
            et_dialog_message_message.setText( content); //"更新内容:\n" +
        }
        return this;
    }


    /**
     * {@link View.OnClickListener}
     */
    @Override
    public void onClick(View v) {
        if (v == mConfirmView) {
            if (null!=mOnListener) {
                mOnListener.onConfirm();
                dismiss();
            }

        } else if (v == mCancelView) {
            dismiss();
        }
    }

    public interface OnListener{
        void onConfirm();
    }


}

