package com.power.fresh.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.hjq.toast.ToastUtils;
import com.power.fresh.R;
import com.power.fresh.utils.Constant;

/**
 * @author AlienChao
 * @date 2020/06/13 20:33
 */
public class MarkiView extends LinearLayout {
    protected Context mContext;
    private TextView tvFruitName;
    private TextView tvNormsName;
    private TextView tvTotalPrice;
    private TextView tvNum;




    OnClickListener mOnClickListener;

    public interface MarkViewOnClickListener{
        void onItemClick();
    }

    @Override
    public void setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    public MarkiView(Context context, Build build) {
        this(context, null, 0);
        tvFruitName.setText(build.getTvFruitNameStr());
        tvNormsName.setText(build.getTvNormsNameStr());
        tvTotalPrice.setText(build.getTvTotalPriceStr());
        tvNum.setText(build.getTvNumStr());
    }



    public MarkiView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.item_marki_order_chird, this);
        tvFruitName = (TextView) findViewById(R.id.tv_fruit_name);
        tvNormsName = (TextView) findViewById(R.id.tv_normsName);
        tvTotalPrice = (TextView) findViewById(R.id.tv_totalPrice);
        tvNum = (TextView) findViewById(R.id.tv_num);
        LinearLayout linearLayout =findViewById(R.id.ll_order_chird);
        linearLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mOnClickListener!=null) {
                    mOnClickListener.onClick(v);
                }
            }
        });
    }

    public static class Build{
        private String tvFruitNameStr;
        private String tvNormsNameStr;
        private String tvTotalPriceStr;
        private String tvNumStr;


        public Build() {
            this.tvFruitNameStr = "黄金圣女果";
            this.tvNormsNameStr = "20斤";
            this.tvTotalPriceStr = "20";
            this.tvNumStr = "X1";
        }

        public MarkiView build(Context context){
            return new MarkiView(context,this);
        }


        public String getTvFruitNameStr() {
            return tvFruitNameStr == null ? "" : tvFruitNameStr;
        }

        public String getTvNormsNameStr() {
            return tvNormsNameStr == null ? "" : tvNormsNameStr;
        }

        public String getTvTotalPriceStr() {
            return tvTotalPriceStr == null ? "" : tvTotalPriceStr;
        }

        public String getTvNumStr() {
            return tvNumStr == null ? "" : tvNumStr;
        }

        public Build setTvFruitNameStr(String tvFruitNameStr) {
            this.tvFruitNameStr = tvFruitNameStr;
            return this;
        }

        public Build setTvNormsNameStr(String tvNormsNameStr) {
            this.tvNormsNameStr = tvNormsNameStr;
            return this;
        }

        public Build setTvTotalPriceStr(double tvTotalPriceStr) {
            this.tvTotalPriceStr = Constant.￥+ String.valueOf(tvTotalPriceStr);
            return this;
        }

        public Build setTvNumStr(int tvNumStr) {
            this.tvNumStr = "X"+tvNumStr;
            return this;
        }
    }




}
