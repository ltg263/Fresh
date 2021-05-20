package com.power.fresh.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.power.fresh.R;

/**
 * 购物车
 * @author AlienChao
 * @date 2020/05/15 10:42
 */
public class ShopAddand extends FrameLayout implements View.OnClickListener {
    private TextView mImage1;
    private TextView mImage2;
    private TextView mText;
    int value;

    public ShopAddand(@NonNull Context context) {
        this(context, null);
    }

    public ShopAddand(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShopAddand(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        findView(context);
    }

    private void findView(Context context) {
        View view = View.inflate(context, R.layout.add_shop, this);

        mImage1 = view.findViewById(R.id.image1);
        mImage2 = view.findViewById(R.id.image2);
        mText = view.findViewById(R.id.text);

        value = getValue();

        setValue(value);

        mImage1.setOnClickListener(this);
        mImage2.setOnClickListener(this);
    }

    private int vs = 1;

    public int getValue() { //获取值

        String trim = mText.getText().toString().trim();
        if (!TextUtils.isEmpty(trim)) {
            Integer.valueOf(vs);
        }
        return vs;
    }

    public void setValue(int value) {
        vs =value;
        mText.setText(value + "");
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.image1:
                jian();
                break;
            case R.id.image2:
                add();
                break;
        }
    }

    private void jian() {
        if (vs > 0) {
            vs--;
            setValue(vs);
        }
        if (mOnNumberChangedListener != null) {
            mOnNumberChangedListener.OnNumberChanged(vs);
        }
    }

    private void add() {


        vs++;
        setValue(vs);

        if (mOnNumberChangedListener != null) {
            mOnNumberChangedListener.OnNumberChanged(vs);
        }
    }

    public interface OnNumberChangedListener {
        void OnNumberChanged(int vs);
    }

    private OnNumberChangedListener mOnNumberChangedListener;

    public void setOnNumberChangedListener(OnNumberChangedListener onNumberChangedListener) {
        mOnNumberChangedListener = onNumberChangedListener;
    }
}
