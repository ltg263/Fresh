package com.power.fresh.utils;

import android.app.Activity;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zhouwei.library.CustomPopWindow;
import com.hjq.toast.ToastUtils;
import com.power.common_opensurce.utils.KeyboardUtils;
import com.power.fresh.R;
import com.power.fresh.adapter.CommodityAdapter;
import com.power.fresh.bean.CommodityList;
import com.power.fresh.bean.order.OrderListBean;
import com.power.fresh.bean.shoppingcar.SaveCartResponse;
import com.power.fresh.widget.Addand;
import com.powerrich.common.base.adapter.CommonRvAdapter;
import com.powerrich.common.base.adapter.CommonViewHolderRv;
import com.powerrich.common.image.GlideLoad;
import com.powerrich.common.widget.SimpleDividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.powerrich.common.helper.PublicUtil.dp2px;


/**
 * 文件名：
 * 描述：
 * 作者：梁帆
 * 时间：2019/3/29/029
 * 版权：
 */
public class SortPopwindow {


    private FragmentActivity mContext;
    private CustomPopWindow mCustomPopWindow;
    private List<CommodityList.ListBean.RulesBean> mListBean;
    private int ruleIndex = 0;
    SparseArray<CommodityList.ListBean.RulesBean> mListBeanSparseArray = new SparseArray<>();
    private CommodityList.ListBean mIt;

    public interface ChooseImgImpl {


        void ok(CommodityList.ListBean.RulesBean rulesBean);

        default void cancel() {

        }
    }

    public SortPopwindow(FragmentActivity context, CommodityList.ListBean rulesBeans) {
        this.mContext = context;
        mIt = rulesBeans;
        mListBean = rulesBeans.getRules();
    }

    public void showBottomView(final ChooseImgImpl callBack) {
        KeyboardUtils.hideSoftInput(mContext);
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.public_pop_sort_select, null);
        mCustomPopWindow = new CustomPopWindow.PopupWindowBuilder(mContext)
                .setView(contentView)
                .size(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                .enableBackgroundDark(true)
                .setOutsideTouchable(false)
                .setAnimationStyle(R.style.public_dialog_inout_anim)
                .create()
                .showAtLocation(((Activity) mContext).getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
        SortPopwindow.ViewHolder viewHolder = new SortPopwindow.ViewHolder(contentView);

        GlideLoad.loadImage(viewHolder.ivHead, mIt.getCommodityHeaderUri(), 4);
        viewHolder.tvCommodityName.setText(mIt.getName());
        CommonRvAdapter adapterChild = new CommonRvAdapter<CommodityList.ListBean.RulesBean>(mContext, mListBean, R.layout.item_sort_pop) {
            @Override
            public void convert(CommonViewHolderRv holderChild, CommodityList.ListBean.RulesBean childItem, int position) {
                holderChild.setText(R.id.tv_title, childItem.getNormsRule());
                holderChild.setText(R.id.tv_unit, Constant.￥ + childItem.getSalePrice());
                holderChild.setText(R.id.tv_stock, "库存：" + childItem.getStock());
                Addand mAddand = holderChild.getItemView(R.id.add);
                mAddand.setShowValue(childItem.getCartNum());
                mAddand.setOnNumberChangedListener(new Addand.OnNumberChangedListener() {
                    @Override
                    public void OnNumberChanged(int vs, boolean isAdd) {
                        childItem.setSelectNum(vs);
                    }
                });
            }
        };

        viewHolder.rvContent.setAdapter(adapterChild);
        viewHolder.rvContent.addItemDecoration(new SimpleDividerItemDecoration(mContext, dp2px(mContext, 0), 1));
        adapterChild.setOnItemClickListener(new CommonRvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
//                mCustomPopWindow.onDismiss();
//                callBack.ok(mListBean.get(position));
            }
        });

        viewHolder.ivFork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCustomPopWindow.onDismiss();
            }
        });

        viewHolder.btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCustomPopWindow.onDismiss();
                callBack.ok(null);
            }
        });

    }


    static
    class ViewHolder {
        @BindView(R.id.rv_content)
        RecyclerView rvContent;
        @BindView(R.id.btn_exit)
        Button btnExit;
        @BindView(R.id.iv_head)
        ImageView ivHead;
        @BindView(R.id.tv_commodity_name)
        TextView tvCommodityName;
        @BindView(R.id.iv_fork)
        ImageView ivFork;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
