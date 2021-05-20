package com.powerrich.common.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.hjq.common.R;
import com.powerrich.common.helper.LogUtils;
import com.powerrich.common.helper.PublicUtil;

/**
 * @author AlienChao
 * @date 2019/07/29 15:05
 */
public class TitleBar extends LinearLayout {
    private Context mContext;
    private View mRootView = null;
    private OnTitleBarListener mOnTitleBarListener;
    private TextView mTvTitle;
    private ImageView ivTitleLeft;
    private FrameLayout public_toolbar;
    private View view_line;
    private TextView tvTitleLeft;
    private TextView publicToolbarTitle;
    private TextView publicToolbarRight;
    private ImageView publicIvRight;
    private RelativeLayout publicToolbarContent;

    public void setLeftResId(int resId) {
        this.ivTitleLeft.setImageResource(resId);
    }

    public void setOnTitleBarListener(OnTitleBarListener onTitleBarListener) {
        mOnTitleBarListener = onTitleBarListener;
    }

    public TitleBar(Context context) {
        this(context, null);
    }

    public TitleBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        mRootView = LayoutInflater.from(mContext).inflate(R.layout.public_include_title, this);
        initView();
        ivTitleLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.i("jsc", "TitleBar-onClick:");
                if (mOnTitleBarListener != null) {
                    mOnTitleBarListener.onLeftClick();
                }
            }
        });
        publicIvRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.i("jsc", "TitleBar-onClick:");
                if (mOnTitleBarListener != null) {
                    mOnTitleBarListener.onRightClick();
                }
            }
        });

        publicToolbarRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnTitleBarListener != null) {
                    mOnTitleBarListener.onRightClick();
                }
            }
        });
        getAttributes(context, attrs, defStyleAttr);
    }


    public void getAttributes(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.PublicTitleBar);
        //是否开启默认样式
        String title = array.getString(R.styleable.PublicTitleBar_publicTitle);
        boolean showDownLine = array.getBoolean(R.styleable.PublicTitleBar_publicShowDownLine,false);
        view_line.setVisibility(showDownLine?View.VISIBLE:View.GONE);

        boolean showLeft = array.getBoolean(R.styleable.PublicTitleBar_publicShowLeft,true);
        int bgColor = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            bgColor = array.getColor(R.styleable.PublicTitleBar_publicBgColor,context.getResources().getColor(R.color.public_title_bg,context.getTheme()));
        }else{
            bgColor = array.getColor(R.styleable.PublicTitleBar_publicBgColor,context.getResources().getColor(R.color.public_title_bg));
        }
        int fontColor = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            fontColor = array.getColor(R.styleable.PublicTitleBar_publicTextColor,context.getResources().getColor(R.color.public_white,context.getTheme()));
        }else{
            fontColor = array.getColor(R.styleable.PublicTitleBar_publicTextColor,context.getResources().getColor(R.color.public_white));
        }
        setTitle(title);
        mRootView.setBackgroundColor(bgColor);
        publicToolbarContent.setBackgroundColor(bgColor);
        mTvTitle.setTextColor(fontColor);
        ivTitleLeft.setVisibility(showLeft?View.VISIBLE:View.GONE);
    }

    public void setIvLeftBack(boolean showLeft){
        ivTitleLeft.setVisibility(showLeft?View.VISIBLE:View.GONE);
    }


    public String getTitle() {
        return mTvTitle.getText().toString();
    }

    public void setTitle(CharSequence title) {
        mTvTitle.setText(TextUtils.isEmpty(title) ? "" : title);
    }

   // setBackgroundColor



    public void setTitleBarBackgroundColor(int color) {
        publicToolbarContent.setBackgroundColor(color);
    }

    private void initView() {
        ivTitleLeft = (ImageView) findViewById(R.id.iv_title_left);
        public_toolbar =  findViewById(R.id.public_toolbar);
        view_line =  findViewById(R.id.view_line);
        tvTitleLeft = (TextView) findViewById(R.id.tv_title_left);
        mTvTitle = (TextView) findViewById(R.id.public_toolbar_title);
        publicToolbarTitle = (TextView) findViewById(R.id.public_toolbar_title);
        publicToolbarRight = (TextView) findViewById(R.id.public_toolbar_right);
        publicIvRight = (ImageView) findViewById(R.id.public_iv_right);
        publicToolbarContent = findViewById(R.id.public_toolbar_content);

    }


    public TextView getTvTitleLeft() {
        return tvTitleLeft;
    }


    public void setLeftTitle(String title) {
        ivTitleLeft.setVisibility(View.GONE);
        tvTitleLeft.setVisibility(View.VISIBLE);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) tvTitleLeft.getLayoutParams();
        layoutParams.leftMargin = PublicUtil.dip2px(mContext, 10);
        tvTitleLeft.setLayoutParams(layoutParams);
        tvTitleLeft.setText(title);
    }


    public void setIvRight(int resouceId) {
        publicIvRight.setImageResource(resouceId);
    }

    public void setRightTitle(String title) {
        publicToolbarRight.setText(title);
    }

    public String getRightTitle(){
       return publicToolbarRight.getText().toString();
    }


    public interface OnTitleBarListener {
        /**
         * 左边点击
         */
        void onLeftClick();


        /**
         * 右边点击
         */
        void onRightClick();


        /**
         * 设置左边的标题
         *
         * @param title
         */
        default void setLeftTitle(String title) {
        }

        ;

    }

}
