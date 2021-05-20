package com.power.fresh.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hjq.toast.ToastUtils;
import com.power.fresh.R;
import com.power.fresh.bean.shoppingcar.ShoppingCarBean;
import com.power.fresh.ui.CommodityDetailsActivity;
import com.powerrich.common.base.adapter.BaseRecyclerViewAdapter;
import com.powerrich.common.image.GlideLoad;
import com.yanzhenjie.recyclerview.OnItemMenuClickListener;
import com.yanzhenjie.recyclerview.SwipeMenu;
import com.yanzhenjie.recyclerview.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.SwipeMenuItem;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;

import java.util.List;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

/**
 * 购物车商店item
 */
public class ShoppingCartAdapter extends BaseRecyclerViewAdapter<ShoppingCarBean.ListBean, ShoppingCartAdapter.ShoppingCardViewHolder> {

    private Context context;
    private OnCheckedChangeListener mOnCheckedChangeListener;

    private boolean isFrist = true;


    public void setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        mOnCheckedChangeListener = onCheckedChangeListener;
    }

    public ShoppingCartAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public ShoppingCartAdapter.ShoppingCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_adapter_item_shopping_cart, parent, false);
        return new ShoppingCardViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingCartAdapter.ShoppingCardViewHolder holder, int position) {
        ShoppingCarBean.ListBean listBean = getData().get(position);

        holder.tv_shopping_name.setText(listBean.getBaseBusiness().getBusinessName());
        GlideLoad.loadImage(holder.iv_head, listBean.getBaseBusiness().getBusinessAvatar(), 50);

        holder.rvShoppingItem.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });


        Log.e("jsc", isFrist+"ShoppingCartAdapter-onBindViewHolder:"+   holder.rvShoppingItem.isItemViewSwipeEnabled());


        List<ShoppingCarBean.ListBean.UserCartDTOSBean> userCartDTOS = listBean.getUserCartDTOS();


        if (isFrist) {
            isFrist=false;
            // 设置监听器。
            holder.rvShoppingItem.setSwipeMenuCreator(mSwipeMenuCreator);
            // 菜单点击监听。
            holder.rvShoppingItem.setOnItemMenuClickListener(new OnItemMenuClickListener() {
                @Override
                public void onItemClick(SwipeMenuBridge menuBridge, int adapterPosition) {
                    menuBridge.closeMenu();

                    // 左侧还是右侧菜单：
                    int direction = menuBridge.getDirection();
                    // 菜单在Item中的Position：
                    int menuPosition = menuBridge.getPosition();


                    try {
                        Log.e("jsc", "索引:"+adapterPosition+"-购物车数量:"+userCartDTOS.size());
                        mOnCheckedChangeListener.removeCart(userCartDTOS.get(adapterPosition).getCartId());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }
            });
        }




        ShoppingGoodItemAdapter shoppingGoodItemAdapter = new ShoppingGoodItemAdapter(getContext());
        shoppingGoodItemAdapter.setData(userCartDTOS);

        /** 是否 是编辑状态 */
        if (listBean.isEdit()) {
            holder.tv_delete_shop.setVisibility(View.VISIBLE);
            holder.rvShoppingItem.setSwipeItemMenuEnabled(true);
            shoppingGoodItemAdapter.isEdit(true);
        }else{
            holder.tv_delete_shop.setVisibility(View.GONE);
            shoppingGoodItemAdapter.isEdit(false);
            holder.rvShoppingItem.setSwipeItemMenuEnabled(false);
        }

        final boolean[] isItemCheckAllArray = {listBean.isCheck()};


        holder.rvShoppingItem.setAdapter(shoppingGoodItemAdapter);
        shoppingGoodItemAdapter.setOnCheckedChangeListener(new ShoppingGoodItemAdapter.OnCheckedChildChangeListener() {
            @Override
            public void onCheckedChildChanged(CompoundButton buttonView, boolean isChecked, int index) {
                //***************************** 遍历Item 父节点 *****************************
                List<ShoppingCarBean.ListBean.UserCartDTOSBean> childData = shoppingGoodItemAdapter.getData();
                boolean isItemCheckAll =true;
                for (int i = 0; i < childData.size(); i++) {
                    if (!childData.get(i).isCheck()) {
                        isItemCheckAll =false;
                        break;
                    }
                }
                isItemCheckAllArray[0] = isItemCheckAll;
                listBean.setCheck(isItemCheckAll);
                holder.cbSelect.setChecked(isItemCheckAll);

                mOnCheckedChangeListener.onCheckedChildChanged(buttonView,isChecked,index);
            }

            @Override
            public void onItemClick(int position) {
                int commodityId = shoppingGoodItemAdapter.getData().get(position).getBaseInfo().getCommodityId();
                CommodityDetailsActivity.startActivity(context,commodityId);
            }

            @Override
            public void ChildNumberChange(int cartId, int number) {
                mOnCheckedChangeListener.ChildNumberChange(cartId,number);
            }
        });





        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isItemCheckAllArray[0]) {
                    isItemCheckAllArray[0] =false;
                }else{
                    isItemCheckAllArray[0] =true;
                }

                shoppingGoodItemAdapter.addAllCheck(isItemCheckAllArray[0]);
                holder.cbSelect.setChecked(isItemCheckAllArray[0]);
                listBean.setCheck(isItemCheckAllArray[0]);
                mOnCheckedChangeListener.onCheckedChanged(holder.cbSelect, isItemCheckAllArray[0], position);


            }
        });




        holder.cbSelect.setChecked(listBean.isCheck());

        holder.tv_delete_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnCheckedChangeListener != null) {
                    mOnCheckedChangeListener.onDelete(position);
                }
            }
        });


    }


    public class ShoppingCardViewHolder extends BaseRecyclerViewAdapter.ViewHolder {
        SwipeRecyclerView rvShoppingItem;
        TextView tv_shopping_name,tv_delete_shop;
        ImageView iv_head;
        CheckBox cbSelect;

        public ShoppingCardViewHolder(@NonNull View itemView) {
            super(itemView);
            rvShoppingItem = (SwipeRecyclerView) findViewById(R.id.rvShoppingItem);
            iv_head = (ImageView) findViewById(R.id.iv_head);
            tv_shopping_name = (TextView) findViewById(R.id.tv_shopping_name);
            tv_delete_shop = (TextView) findViewById(R.id.tv_delete_shop);
            cbSelect = (CheckBox) findViewById(R.id.cb_select);
        }
    }

    public interface OnCheckedChangeListener {

        void onCheckedChanged(CompoundButton buttonView, boolean isChecked, int position);
        void onCheckedChildChanged(CompoundButton buttonView, boolean isChecked, int position);
        void ChildNumberChange(int cartId,int number);

        void onDelete(int postion);
        void removeCart(int cartId);
    }





    // 创建菜单：
    SwipeMenuCreator mSwipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu leftMenu, SwipeMenu rightMenu, int position) {
            SwipeMenuItem deleteItem = new SwipeMenuItem(context)
                    .setBackgroundColor(getResources().getColor(R.color.public_red)) // 背景颜色
                    .setText("删除") // 文字。
                    .setTextColor(Color.WHITE) // 文字颜色。
                    .setTextSize(16) // 文字大小。
                    .setWidth(144) // 宽
                    .setHeight(MATCH_PARENT); //高（MATCH_PARENT意为Item多高侧滑菜单多高 （推荐使用））
            rightMenu.addMenuItem(deleteItem);// 添加一个按钮到右侧侧菜单。
            // 注意：哪边不想要菜单，那么不要添加即可。
        }
    };

    OnItemMenuClickListener mItemMenuClickListener = new OnItemMenuClickListener() {

        @Override
        public void onItemClick(SwipeMenuBridge menuBridge, int position) {
            // 任何操作必须先关闭菜单，否则可能出现Item菜单打开状态错乱。

        }
    };

}
