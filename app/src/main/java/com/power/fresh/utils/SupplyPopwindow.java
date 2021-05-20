package com.power.fresh.utils;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.example.zhouwei.library.CustomPopWindow;
import com.noober.background.view.BLEditText;
import com.power.common_opensurce.utils.KeyboardUtils;
import com.power.fresh.R;
import com.power.fresh.bean.CommodityList;
import com.powerrich.common.image.GlideLoad;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 文件名：
 * 描述：
 * 作者：梁帆
 * 时间：2019/3/29/029
 * 版权：
 */
public class SupplyPopwindow {


    private  FragmentActivity mContext;
    private CustomPopWindow mCustomPopWindow;
    PopupWindow.OnDismissListener mOnDismissListener;
    private CommodityList.ListBean mListBean;
    private int ruleIndex=0;
    SparseArray<CommodityList.ListBean.RulesBean> mListBeanSparseArray = new SparseArray<>();

    public interface ChooseImgImpl {


        void ok(CommodityList.ListBean.RulesBean rulesBean);

        default void cancel() {

        }
    }

    public SupplyPopwindow(FragmentActivity context, CommodityList.ListBean listBean) {
        this.mContext = context;
        mListBean = listBean;
    }


    public SupplyPopwindow(FragmentActivity context, PopupWindow.OnDismissListener listener) {
        this.mContext = context;
        this.mOnDismissListener = listener;
    }

    public void showBottomView(final ChooseImgImpl callBack) {
        KeyboardUtils.hideSoftInput(mContext);
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.public_pop_supply_select, null);
        mCustomPopWindow = new CustomPopWindow.PopupWindowBuilder(mContext)
                .setView(contentView)
                .size(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                .enableBackgroundDark(true)
                .setOutsideTouchable(false)
                .setAnimationStyle(R.style.public_dialog_inout_anim)
                .setOnDissmissListener(mOnDismissListener)
                .create()
                .showAtLocation(((Activity) mContext).getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);

        ViewHolder viewHolder = new ViewHolder(contentView);
        GlideLoad.loadImage(viewHolder.ivHead, mListBean.getCommodityHeaderUri(),4);
        viewHolder.tvCommodityName.setText(mListBean.getName());
        viewHolder.tvSimple.setText(mListBean.getSimpleInfo());




        for (int i = 0; i < mListBean.getRules().size(); i++) {
            CommodityList.ListBean.RulesBean rulesBean = mListBean.getRules().get(i);
            if (i == 0) {
                viewHolder.rb01.setVisibility(View.VISIBLE);
                viewHolder.rb01.setText(rulesBean.getNormsRule());
            }else if (i == 1) {
                viewHolder.rb02.setVisibility(View.VISIBLE);
                viewHolder.rb02.setText(rulesBean.getNormsRule());
            }else if (i == 2) {
                viewHolder.rb03.setVisibility(View.VISIBLE);
                viewHolder.rb03.setText(rulesBean.getNormsRule());
            }
        }
        viewHolder.tvPrice.setText(Constant.￥ + mListBean.getRules().get(ruleIndex).getSalePrice());
        viewHolder.tvKucun.setText("剩余库存："+ mListBean.getRules().get(ruleIndex).getStock());

        viewHolder.rvGuige.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_01) {
                    ruleIndex =0;
                } else if (checkedId == R.id.rb_02) {
                    ruleIndex =1;
                } else if (checkedId == R.id.rb_03) {
                    ruleIndex =2;
                }

                viewHolder.tvPrice.setText(Constant.￥ + mListBean.getRules().get(ruleIndex).getSalePrice());
                viewHolder.tvKucun.setText("剩余库存："+ mListBean.getRules().get(ruleIndex).getStock());

                if (mListBean.getRules().get(ruleIndex).getNumShowCc()==0) {
                    viewHolder.etSum.setText("");
                }else{
                    viewHolder.etSum.setText( String.valueOf(mListBean.getRules().get(ruleIndex).getNumShowCc()));

                }

            }
        });
        viewHolder.ivFork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCustomPopWindow.dissmiss();
            }
        });


        viewHolder.etSum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    String s1 = s.toString();
                    int num = Integer.valueOf(s1);
                    mListBean.getRules().get(ruleIndex).setNumShowCc(num);
                    mListBeanSparseArray.put(mListBean.getRules().get(ruleIndex).getId(), mListBean.getRules().get(ruleIndex));
                    settlementTotal(viewHolder.tvTotalNum,viewHolder.tvTotalPrice);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

            }
        });

        viewHolder.btnSettlement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack!=null) {
                    callBack.ok( mListBean.getRules().get(ruleIndex));
                }
                mCustomPopWindow.dissmiss();
            }
        });

//        //处理popWindow 显示内容
//        View.OnClickListener listener = view -> {
//            if (view.getId() == R.id.tv_take_photos) {
//                callBack.openCamera();
//            } else if (view.getId() == R.id.tv_select) {
//                callBack.chooseImg();
//            } else if (view.getId() == R.id.tv_cancel) {
//                callBack.cancel();
//            }
//            mCustomPopWindow.dissmiss();
//        };
//        contentView.findViewById(R.id.tv_take_photos).setOnClickListener(listener);
//        contentView.findViewById(R.id.tv_select).setOnClickListener(listener);
//        contentView.findViewById(R.id.tv_cancel).setOnClickListener(listener);
    }


    /**
     * 结算金额 与 数量
     */
    private void settlementTotal(TextView tvNum,TextView tvPrice) {
        int num = 0;
        double totalPrice = 0.0;
        for (int i = 0; i < mListBeanSparseArray.size(); i++) {
            CommodityList.ListBean.RulesBean rulesBean = mListBeanSparseArray.valueAt(i);
            totalPrice += rulesBean.getNumShowCc() * rulesBean.getSalePrice();
            if (totalPrice > 0) {
                num += rulesBean.getNumShowCc();
            }
        }

        tvNum.setText("共" + num + "件");
        tvPrice.setText(Constant.￥ + totalPrice);
    }

    class ViewHolder {
        @BindView(R.id.iv_head)
        ImageView ivHead;
        @BindView(R.id.iv_fork)
        ImageView ivFork;
        @BindView(R.id.tv_commodity_name)
        TextView tvCommodityName;
        @BindView(R.id.tv_simple)
        TextView tvSimple;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_kucun)
        TextView tvKucun;
        @BindView(R.id.et_sum)
        BLEditText etSum;
        @BindView(R.id.rv_guige)
        RadioGroup rvGuige;
        @BindView(R.id.tv_total_num)
        TextView tvTotalNum;
        @BindView(R.id.tv_total_price)
        TextView tvTotalPrice;
        @BindView(R.id.btn_settlement)
        TextView btnSettlement;

        @BindView(R.id.rb_01)
        RadioButton rb01;
        @BindView(R.id.rb_02)
        RadioButton rb02;
        @BindView(R.id.rb_03)
        RadioButton rb03;


        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
