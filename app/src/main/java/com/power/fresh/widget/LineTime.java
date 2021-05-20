package com.power.fresh.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.power.common_opensurce.widget.ProgressView;
import com.power.fresh.R;
import com.power.fresh.bean.RefundGet;
import com.powerrich.common.helper.TimeTool;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author AlienChao
 * @date 2020/08/07 10:38
 */
public class LineTime extends LinearLayout {
    @BindView(R.id.progressview_progress)
    ProgressView progressviewProgress;
    @BindView(R.id.tv_time_01)
    TextView tvTime01;
    @BindView(R.id.tv_time_2)
    TextView tvTime2;
    @BindView(R.id.tv_time_03)
    TextView tvTime03;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.view01)
    View view01;
    @BindView(R.id.view03)
    View view03;
    @BindView(R.id.tv_name01)
    TextView tvName01;
    @BindView(R.id.tv_name02)
    TextView tvName02;
    @BindView(R.id.tv_name_03)
    TextView tvName03;

    public LineTime(Context context) {
        this(context, null, 0);
    }

    public LineTime(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LineTime(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View inflate = LayoutInflater.from(context).inflate(R.layout.time_line_layout, this);
        ButterKnife.bind(inflate);
        initView();
    }

    private void initView() {
        progressviewProgress.setColor(R.color.public_title_bg);


    }

    public void setType(RefundGet it) {

        String time = TimeTool.getApplyListDate(it.getCreateTime());
        /** 已申请 */
        if (it.getRefundStatus().equals("APPLYING")) {
            tvName01.setVisibility(View.VISIBLE);
            tvTime01.setVisibility(View.VISIBLE);
            view01.setVisibility(View.VISIBLE);
            view01.setBackgroundResource(R.drawable.home_banner_select);
            tvName01.setText("已申请");
            tvTime01.setText(time);
            tvName03.setText("审核结果");
        }

        //** 申请结果 */
        else if (it.getRefundStatus().equals("SUCCESS")) {
            申请结果("申请成功");
        }
        //** 申请结果 */
        else if (it.getRefundStatus().equals("FAIl")) {
            申请结果("申请失败");
        }

        //** 审核中 *//
        else if (it.getRefundStatus().equals("ACCEPTING")) {
            审核中("审核中");
        }

        //** 申请结果 */
        else if (it.getRefundStatus().equals("FINISH")) {

            申请结果("已完成");
        }
        //** 申请结果 */
        else  {   //if (it.getRefundStatus().equals("CANCEL"))
            申请结果("已取消");
        }



    }


    private void 审核中(String text) {

        tvName03.setText("申请结果");

        tvName02.setVisibility(View.VISIBLE);

        view2.setVisibility(View.VISIBLE);

        tvName02.setText(text);

        view01.setBackgroundResource(R.drawable.home_banner_select);
        view2.setBackgroundResource(R.drawable.home_banner_select);

        progressviewProgress.setProgress(50);
    }

    private void 申请结果(String text) {

        view03.setVisibility(View.VISIBLE);
        tvName03.setText(text);


        view01.setBackgroundResource(R.drawable.home_banner_select);
        view2.setBackgroundResource(R.drawable.home_banner_select);
        view03.setBackgroundResource(R.drawable.home_banner_select);

        progressviewProgress.setProgress(100);
    }


}
