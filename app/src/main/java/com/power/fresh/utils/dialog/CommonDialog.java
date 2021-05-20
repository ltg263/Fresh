package com.power.fresh.utils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.power.fresh.R;


public class CommonDialog extends Dialog implements View.OnClickListener {

    private static CommonDialog mCommonDialog;
    private TextView contentTxt;
    private TextView titleTxt;
    private TextView submitTxt;
    private TextView cancelTxt;

    private Context mContext;
    private String content;
    private OnCloseListener listener;
    private String positiveName;
    private String negativeName;
    private String title;
    private boolean isShowCancelText = true;

    public CommonDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    public CommonDialog(Context context, String content) {
        super(context, R.style.public_common_dialog);
        this.mContext = context;
        this.content = content;
    }

    public CommonDialog(Context context, int themeResId, String content) {
        super(context, themeResId);
        this.mContext = context;
        this.content = content;
    }

    public CommonDialog(Context context, int themeResId, String content, OnCloseListener listener) {
        super(context, themeResId);
        this.mContext = context;
        this.content = content;
        this.listener = listener;
    }

    protected CommonDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }

    public CommonDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    public CommonDialog setCancelTextVisible() {
        this.isShowCancelText = false;
        return this;
    }


    public CommonDialog setPositiveButton(String name) {
        this.positiveName = name;
        return this;
    }


    /**
     * 取消的文字内容
     *
     * @param name
     * @return
     */
    public CommonDialog setNegativeButton(String name) {
        this.negativeName = name;
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.public_dialog_commom);
        setCanceledOnTouchOutside(false);
        initView();
    }

    private void initView() {
        contentTxt = findViewById(R.id.content);
      ///  titleTxt = findViewById(R.id.title);
        submitTxt = findViewById(R.id.submit);
        submitTxt.setOnClickListener(this);
        cancelTxt = findViewById(R.id.cancel);
        cancelTxt.setOnClickListener(this);
        cancelTxt.setVisibility(isShowCancelText ? View.VISIBLE : View.GONE);
        contentTxt.setText(content);
        if (!TextUtils.isEmpty(positiveName)) {
            submitTxt.setText(positiveName);
        }

        if (!TextUtils.isEmpty(negativeName)) {
            cancelTxt.setText(negativeName);
        }

//        if (!TextUtils.isEmpty(title)) {
//            titleTxt.setText(title);
//        } else {
//            titleTxt.setVisibility(View.GONE);
//        }


    }

    public static CommonDialog showDialog1(Context context, String title, String content, final InterfaceClick click) {
        //弹出提示框
        mCommonDialog = new CommonDialog(context, R.style.public_common_dialog, content, new CommonDialog.OnCloseListener() {
            @Override
            public void onClick(Dialog dialog, boolean confirm) {
                if (confirm) {
                    dialog.dismiss();
                    click.click();
                }
            }
        });
        mCommonDialog.setTitle(title);
        mCommonDialog.show();
        mCommonDialog.setCanceledOnTouchOutside(true);
        return mCommonDialog;
    }


    public static CommonDialog showDialog1(Context context, String title, String content, String positiveName, final InterfaceClick click) {
        //弹出提示框
        mCommonDialog = new CommonDialog(context, R.style.public_common_dialog, content, new CommonDialog.OnCloseListener() {
            @Override
            public void onClick(Dialog dialog, boolean confirm) {
                if (confirm) {
                    dialog.dismiss();
                    click.click();
                }
            }
        });
        mCommonDialog.setPositiveButton(positiveName);
        mCommonDialog.setTitle(title);
        mCommonDialog.show();
        mCommonDialog.setCanceledOnTouchOutside(true);
        return mCommonDialog;
    }

    public static void showDialog(Context context, String title, String content, final InterfaceClick click) {
        //弹出提示框
        mCommonDialog = new CommonDialog(context, R.style.public_common_dialog, content, new CommonDialog.OnCloseListener() {
            @Override
            public void onClick(Dialog dialog, boolean confirm) {
                if (confirm) {
                    dialog.dismiss();
                    click.click();
                }
            }
        });
        mCommonDialog.setTitle(title);
        mCommonDialog.show();
        mCommonDialog.setCanceledOnTouchOutside(true);
    }


    /**
     * 弹出框 单选
     *
     * @param context
     * @param title
     * @param content
     * @param positiveName
     * @param click
     */
    public static void showDetermineDialog(Context context, String title, String content, String positiveName, final InterfaceClick click) {
        //弹出提示框
        mCommonDialog = new CommonDialog(context, R.style.public_common_dialog, content, new CommonDialog.OnCloseListener() {
            @Override
            public void onClick(Dialog dialog, boolean confirm) {
                if (confirm) {
                    dialog.dismiss();
                    if (click != null) {
                        click.click();
                    }
                }
            }
        });

        mCommonDialog.setPositiveButton(positiveName);
        mCommonDialog.setCancelTextVisible();
        mCommonDialog.setTitle(title);
        mCommonDialog.show();
        mCommonDialog.setCanceledOnTouchOutside(false);
    }


    public interface InterfaceClick {
        void click();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.cancel) {
            if (listener != null) {
                listener.onClick(this, false);
            }
            this.dismiss();
        } else if (v.getId() == R.id.submit) {
            if (listener != null) {
                listener.onClick(this, true);
            }

        }
    }

    public interface OnCloseListener {
        void onClick(Dialog dialog, boolean confirm);
    }
}
