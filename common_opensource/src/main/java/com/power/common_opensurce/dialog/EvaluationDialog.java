package com.power.common_opensurce.dialog;

import android.app.Dialog;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.power.common_opensurce.R;
import com.powerrich.common.base.BaseDialog;
import com.powerrich.common.base.BaseDialogFragment;
import com.powerrich.common.dialog.MessageDialog;

public class EvaluationDialog {
    public static final class Builder
            extends BaseDialogFragment.Builder<MessageDialog.Builder>
            implements View.OnClickListener {

        private EvaluationDialog.OnListener mListener;
        private boolean mAutoDismiss = true; // 设置点击按钮后自动消失

        private TextView mTitleView;
        private TextView mMessageView;

        private TextView mCancelView;
        private View mLineView;
        private TextView mConfirmView;

        public Builder(FragmentActivity activity) {
            super(activity);

            setContentView(R.layout.open_source_public_dialog_message);
            setAnimStyle(BaseDialog.AnimStyle.IOS);
            setGravity(Gravity.CENTER);

            mTitleView = findViewById(com.hjq.common.R.id.tv_dialog_message_title);
            mMessageView = findViewById(com.hjq.common.R.id.tv_dialog_message_message);

            mCancelView  = findViewById(com.hjq.common.R.id.tv_dialog_message_cancel);
            mLineView = findViewById(com.hjq.common.R.id.v_dialog_message_line);
            mConfirmView  = findViewById(com.hjq.common.R.id.tv_dialog_message_confirm);

            mCancelView.setOnClickListener(this);
            mConfirmView.setOnClickListener(this);
        }

        public EvaluationDialog.Builder setTitle(int resId) {
            return setTitle(getText(resId));
        }
        public EvaluationDialog.Builder setTitle(CharSequence text) {
            mTitleView.setText(text);
            return this;
        }

        public EvaluationDialog.Builder setMessage(int resId) {
            return setMessage(getText(resId));
        }
        public EvaluationDialog.Builder setMessage(CharSequence text) {
            mMessageView.setText(text);
            return this;
        }

        public EvaluationDialog.Builder setCancel(int resId) {
            return setCancel(getText(resId));
        }
        public EvaluationDialog.Builder setCancel(CharSequence text) {
            mCancelView.setText(text);

            mCancelView.setVisibility((text == null || "".equals(text.toString())) ? View.GONE : View.VISIBLE);
            mLineView.setVisibility((text == null || "".equals(text.toString())) ? View.GONE : View.VISIBLE);
            mConfirmView.setBackgroundResource((text == null || "".equals(text.toString())) ? R.drawable.open_source_dialog_message_one_button : R.drawable.open_source_dialog_message_right_button);
            return this;
        }

        public EvaluationDialog.Builder setConfirm(int resId) {
            return setConfirm(getText(resId));
        }
        public EvaluationDialog.Builder setConfirm(CharSequence text) {
            mConfirmView.setText(text);
            return this;
        }

        public EvaluationDialog.Builder setAutoDismiss(boolean dismiss) {
            mAutoDismiss = dismiss;
            return this;
        }

        public EvaluationDialog.Builder setListener(EvaluationDialog.OnListener l) {
            mListener = l;
            return this;
        }

        @Override
        public BaseDialog create() {
            // 如果标题为空就隐藏
            if ("".equals(mTitleView.getText().toString())) {
                mTitleView.setVisibility(View.GONE);
            }
            return super.create();
        }

        /**
         * {@link View.OnClickListener}
         */
        @Override
        public void onClick(View v) {
            if (mAutoDismiss) {
                dismiss();
            }

            if (mListener == null) return;

            if (v == mConfirmView) {
                mListener.onConfirm(getDialog());
            }else if (v == mCancelView) {
                dismiss();
                mListener.onCancel(getDialog());
            }
        }
    }

    public interface OnListener {

        /**
         * 点击确定时回调
         */
        void onConfirm(Dialog dialog);

        /**
         * 点击取消时回调
         */
        default void onCancel(Dialog dialog){}
    }
}
