package com.power.fresh.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.power.fresh.R;
import com.power.fresh.bean.home.HomeGouponBean;

import java.util.List;

/**
 * @author AlienChao
 * @date 2020/04/27 14:24
 */
public class HomeDialogAdapter extends BaseQuickAdapter<HomeGouponBean.ListBean, BaseViewHolder> {


    public HomeDialogAdapter(List data) {
        super(R.layout.item_dialog_home, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, HomeGouponBean.ListBean item) {

        helper.setText(R.id.tv_type,item.getType() == 1 ? "满减券" : "打折券");
        helper.setText(R.id.tv_pairce,String.valueOf(item.getSubAmount()));
        helper.setText(R.id.tv_desc,item.getNote());
        helper.setText(R.id.tv_name,item.getCouponName());
        helper.setText(R.id.tv_name_desc,item.getUseType() == 1 ? "全场通用" : "部分商品使用");
        helper.setText(R.id.tv_end_time,"有效期至 " + item.getExpireTime());
//        helper.addOnClickListener(R.id.)
    }
}
