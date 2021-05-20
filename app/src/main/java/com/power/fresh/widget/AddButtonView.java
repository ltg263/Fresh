package com.power.fresh.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.hjq.toast.ToastUtils;
import com.power.fresh.R;
import com.power.fresh.utils.Constant;
import com.yalantis.ucrop.util.ScreenUtils;

/**
 *  订单等界面下面的button
 * @author AlienChao
 * @date 2020/06/13 20:33
 */
public class AddButtonView extends LinearLayout {
    protected Context mContext;
    private IClickListener mIClickListener;
    private String btnText="";


    public AddButtonView(Context context) {
        this(context, null, 0,"确认发货");
    }
    public AddButtonView(Context context,String btnText) {
        this(context, null, 0,btnText);
    }

    public AddButtonView(Context context, @Nullable AttributeSet attrs, int defStyleAttr,String btnText) {
        super(context, attrs, defStyleAttr);
        this.btnText = btnText;
        this.mContext = context;
        initView();
    }

    public AddButtonView setBtnText(String btnText) {
        this.btnText = btnText;
        return this;
    }

    public void setIClickListener(IClickListener IClickListener) {
        mIClickListener = IClickListener;
    }

    private void initView() {
      //  LayoutInflater.from(mContext).inflate(R.layout.item_marki_order_chird, this);


        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ScreenUtils.dip2px(getActivity(),70), ScreenUtils.dip2px(getActivity(),22));
        TextView tv = new TextView(getActivity());
        tv.setBackgroundResource(R.drawable.order_list_status_type_button_selector);
        tv.setGravity(Gravity.CENTER);
        tv.setText(btnText);
        tv.setTextColor(ContextCompat.getColor(getActivity(),R.color.public_color_FF6C75));
        tv.setTextSize(12);
        params.rightMargin = ScreenUtils.dip2px(getActivity(),15);
        tv.setLayoutParams(params);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mIClickListener!=null) {
                    mIClickListener.onClick();
                }
            }
        });


        addView(tv);

    }


    public interface IClickListener{
        void onClick();
    }


    private Context getActivity() {
        return mContext;
    }


}
