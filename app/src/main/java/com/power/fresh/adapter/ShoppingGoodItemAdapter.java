package com.power.fresh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.hjq.toast.ToastUtils;
import com.power.fresh.R;
import com.power.fresh.bean.shoppingcar.ShoppingCarBean;
import com.power.fresh.ui.CommodityDetailsActivity;
import com.power.fresh.utils.Constant;
import com.power.fresh.widget.ShopAddand;
import com.powerrich.common.base.adapter.BaseRecyclerViewAdapter;
import com.powerrich.common.image.GlideLoad;

/**
 * 商品item详情
 */
public class ShoppingGoodItemAdapter extends BaseRecyclerViewAdapter<ShoppingCarBean.ListBean.UserCartDTOSBean, ShoppingGoodItemAdapter.ShoppingGoodsItemViewHolder> {
    private OnCheckedChildChangeListener mOnCheckedChangeListener;

    public void setOnCheckedChangeListener(OnCheckedChildChangeListener onCheckedChangeListener) {
        mOnCheckedChangeListener = onCheckedChangeListener;
    }


    public interface OnCheckedChildChangeListener {

        void onCheckedChildChanged(CompoundButton buttonView, boolean isChecked, int position);
        void onItemClick(int position);
        void ChildNumberChange(int cartId, int number);

    }


    public ShoppingGoodItemAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public ShoppingGoodItemAdapter.ShoppingGoodsItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_adapter_item_shopping_goods_item, parent, false);
        return new ShoppingGoodsItemViewHolder(inflate);
    }

    /**
     * 是否为编辑状态
     */
    public void isEdit(boolean isEdit) {
        for (int i = 0; i < getData().size(); i++) {
            getData().get(i).setEdit(isEdit);
        }
    }


    /**
     * 设置全部true  或者 false
     */
    public void addAllCheck(boolean isCheck) {
        for (int i = 0; i < getData().size(); i++) {
            getData().get(i).setCheck(isCheck);
        }
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(@NonNull ShoppingGoodItemAdapter.ShoppingGoodsItemViewHolder holder, int position) {

        ShoppingCarBean.ListBean.UserCartDTOSBean userCartDTOSBean = getData().get(position);

        GlideLoad.loadImage(holder.iv_head, userCartDTOSBean.getBaseInfo().getCommodityHeaderUri());
        holder.tv_goods_name.setText(userCartDTOSBean.getBaseInfo().getCommodityName());
        holder.tv_goods_desc.setText(userCartDTOSBean.getBaseNorms().getNormsName());
        holder.tv_price.setText(Constant.￥ + userCartDTOSBean.getBaseNorms().getSalePrice());
        holder.tv_num.setText(String.valueOf("X" + userCartDTOSBean.getNum()));
        holder.mShopAddand.setValue(userCartDTOSBean.getNum());

        final boolean[] isItemCheckAllArray = {userCartDTOSBean.isCheck()};
        holder.cbSelect.setChecked(userCartDTOSBean.isCheck());

        if (userCartDTOSBean.isEdit()) {
            holder.mShopAddand.setVisibility(View.VISIBLE);
            holder.tv_num.setVisibility(View.GONE);
        } else {
            holder.mShopAddand.setVisibility(View.GONE);
            holder.tv_num.setVisibility(View.VISIBLE);
        }

        if (mOnCheckedChangeListener != null) {

        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // ToastUtils.show("Click:" + position);
//                if (isItemCheckAllArray[0]) {
//                    isItemCheckAllArray[0] =false;
//                }else{
//                    isItemCheckAllArray[0] =true;
//                }
//                holder.cbSelect.setChecked(isItemCheckAllArray[0]);
//                userCartDTOSBean.setCheck( isItemCheckAllArray[0]);
//                mOnCheckedChangeListener.onCheckedChildChanged(holder.cbSelect,  isItemCheckAllArray[0], position);
//

                mOnCheckedChangeListener.onItemClick(position);

            }
        });

        holder.cbSelect.setOnCheckedChangeListener((buttonView, isChecked) -> {
          //  ToastUtils.show("cbSelect:" + position);
//            userCartDTOSBean.setCheck(isChecked);
//            mOnCheckedChangeListener.onCheckedChildChanged(buttonView, isChecked, position);

            if (isChecked) {
                isItemCheckAllArray[0] = true;
            } else {
                isItemCheckAllArray[0] = false;
            }
            holder.cbSelect.setChecked(isItemCheckAllArray[0]);
            userCartDTOSBean.setCheck(isItemCheckAllArray[0]);
            mOnCheckedChangeListener.onCheckedChildChanged(holder.cbSelect, isItemCheckAllArray[0], position);


        });

        holder.mShopAddand.setOnNumberChangedListener(new ShopAddand.OnNumberChangedListener() {
            @Override
            public void OnNumberChanged(int vs) {
                userCartDTOSBean.setNum(vs);
                mOnCheckedChangeListener.ChildNumberChange(userCartDTOSBean.getCartId(), vs);
            }
        });


//        cb_select.setVisibility(View.GONE);
//        cb_select.setChecked(false);
    }

//    @Override
//    public int getItemCount() {
//        return 3;
//    }

    public class ShoppingGoodsItemViewHolder extends BaseRecyclerViewAdapter.ViewHolder {
        TextView tv_goods_name, tv_goods_desc, tv_price, tv_num;
        ImageView iv_head;
        CheckBox cbSelect;
        ShopAddand mShopAddand;

        public ShoppingGoodsItemViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_head = (ImageView) findViewById(R.id.iv_goods_icon);
            cbSelect = (CheckBox) findViewById(R.id.cb_select);
            tv_goods_name = (TextView) findViewById(R.id.tv_goods_name);
            tv_goods_desc = (TextView) findViewById(R.id.tv_goods_desc);
            tv_price = (TextView) findViewById(R.id.tv_price);
            tv_num = (TextView) findViewById(R.id.tv_num);
            mShopAddand = (ShopAddand) findViewById(R.id.view_shopadd);
        }
    }
}
