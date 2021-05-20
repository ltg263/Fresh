package com.power.fresh.ui.business;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chen.concise.RxHttp;
import com.hjq.toast.ToastUtils;
import com.power.common_opensurce.App;
import com.power.common_opensurce.utils.KeyboardUtils;
import com.power.fresh.R;
import com.power.fresh.adapter.ShoppingGoodItemAdapter;
import com.power.fresh.api.FreshService;
import com.power.fresh.bean.AddressBean;
import com.power.fresh.bean.CommodityDetails;
import com.power.fresh.bean.CommodityList;
import com.power.fresh.bean.request.CreateOrderRequest;
import com.power.fresh.bean.shoppingcar.ShoppingCarBean;
import com.power.fresh.ui.CommodityDetailsActivity;
import com.power.fresh.ui.ShopIntroducedActivity;
import com.power.fresh.ui.my.AddAddressActivity;
import com.power.fresh.ui.order.ConfirmOrderActivity;
import com.power.fresh.utils.ChooseImgPopWindow;
import com.power.fresh.utils.Constant;
import com.power.fresh.utils.SupplyPopwindow;
import com.power.fresh.widget.DispatchEditText;
import com.powerrich.common.base.BaseListActivity;
import com.powerrich.common.base.BaseObserver;
import com.powerrich.common.base.adapter.CommonRvAdapter;
import com.powerrich.common.base.adapter.CommonViewHolderRv;
import com.powerrich.common.image.GlideLoad;
import com.powerrich.common.widget.SimpleDividerItemDecoration;
import com.yalantis.ucrop.util.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

import static com.powerrich.common.helper.PublicUtil.dp2px;

/**
 * 进货
 */
public class SupplyListActivity extends BaseListActivity<CommodityList.ListBean> {


    SparseArray<CommodityList.ListBean> mListBeanSparseArray = new SparseArray<>();

    private CommonRvAdapter<CommodityList.ListBean> mCommonRvAdapter;


    private String supplyName;
    private String supplyImaUrl;
    private int businessId;
    private TextView mTvTotal;
    private Button mBtnSettlement;

    public static void startActivity(Context context, String supplyName, String supplyImaUrl, int businessId) {
        Intent intent = new Intent(context, SupplyListActivity.class);
        intent.putExtra("supplyName", supplyName);
        intent.putExtra("supplyImaUrl", supplyImaUrl);
        intent.putExtra("businessId", businessId);
        context.startActivity(intent);
    }


    @Override
    protected String getTitleText() {
        supplyName = getIntent().getStringExtra("supplyName");
        supplyImaUrl = getIntent().getStringExtra("supplyImaUrl");
        businessId = getIntent().getIntExtra("businessId", 0);
        return "进货";
    }

    @Override
    public void onResume() {
        super.onResume();
        needResume = false;
    }

    @Override
    protected CommonRvAdapter<CommodityList.ListBean> getAdapter() {
        mCommonRvAdapter = new CommonRvAdapter<CommodityList.ListBean>(this, null, R.layout.item_supply) {
            @Override
            public void convert(CommonViewHolderRv holder, CommodityList.ListBean item, int position) {


                holder.setText(R.id.tv_shopping_name, supplyName);
                holder.setImageUrl(R.id.iv_head, supplyImaUrl, 30);
                if (position == 0) {
                    holder.getItemView(R.id.ll_first_item).setVisibility(View.VISIBLE);
                } else {
                    holder.getItemView(R.id.ll_first_item).setVisibility(View.GONE);
                }

                holder.setImageUrl(R.id.iv_goods_icon, item.getCommodityHeaderUri(), 4);
                holder.setText(R.id.tv_goods_name, item.getName());
                holder.setText(R.id.tv_goods_desc, item.getSimpleInfo());
                holder.setText(R.id.tv_price, "规格：" + Constant.￥ + item.getRules().get(0).getSalePrice() + "/" + item.getRules().get(0).getNormsRule());


                item.setShoCcPrice(item.getRules().get(0).getSalePrice());

                TextView tvItemPrice = holder.getItemView(R.id.tv_item_price);
                /** 数量 */
                DispatchEditText et_num = holder.getItemView(R.id.et_num);
                if (item.getRules().size() > 1) {
                    holder.getItemView(R.id.iv_down).setVisibility(View.VISIBLE);
                    et_num.setTouch(false);
                } else {
                    holder.getItemView(R.id.iv_down).setVisibility(View.GONE);
                }


                if (item.getShowCcNum()==0) {
                    et_num.setText("");
                }else{
                    et_num.setText(String.valueOf(item.getShowCcNum()));
                }

                et_num.setSelection(0);
                et_num.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        int num;
                        if (TextUtils.isEmpty(et_num.getText().toString())) {
                            num = 0;
                        } else {
                            num = Integer.valueOf(et_num.getText().toString());
                        }

                        tvItemPrice.setText(Constant.get().canvetDouble(num*item.getSalePrice()));

                        item.setShowCcNum(num);
                        int commodityId = item.getCommodityId();
                        mListBeanSparseArray.put(commodityId, item);
                        settlementTotal();
                    }
                });


                holder.getItemView(R.id.ll_rule).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        CommodityList.ListBean item = mCommonRvAdapter.getData().get(position);

                        /** 规格大于1 点击弹出框 */
                        if (item.getRules().size() > 1) {
                            new SupplyPopwindow(getActivity(),item).showBottomView(new SupplyPopwindow.ChooseImgImpl() {
                                @Override
                                public void ok(CommodityList.ListBean.RulesBean rulesBean) {
                                    holder.setText(R.id.tv_price, "规格：" + Constant.￥ + rulesBean.getSalePrice() + "/" +rulesBean.getNormsRule());
                                    et_num.setText(String.valueOf(rulesBean.getNumShowCc()));
                                }
                            });
                        } else {
                            llBg.setFocusable(true);
                            llBg.setFocusableInTouchMode(true);
                            llBg.requestFocus();
                            KeyboardUtils.hideSoftInput(getActivity());
                        }

                    }
                });


            }
        };

        SimpleDividerItemDecoration simpleDividerItemDecoration = new SimpleDividerItemDecoration(getActivity(), 0, dp2px(getActivity(), 10));
        mCommonRvAdapter.setDividerItemDecoration(simpleDividerItemDecoration);


        mCommonRvAdapter.setOnItemClickListener(new CommonRvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
             //   CommodityDetailsActivity.startActivity(getActivity(), mCommonRvAdapter.getData().get(position).getCommodityId());
                ShopIntroducedActivity.startActivity(getActivity(), businessId);

            }
        });



        return mCommonRvAdapter;
    }

    /**
     * 结算金额 与 数量
     */
    private void settlementTotal() {
        int num = 0;
        double totalPrice = 0.0;
        for (int i = 0; i < mListBeanSparseArray.size(); i++) {
            CommodityList.ListBean listBean = mListBeanSparseArray.valueAt(i);
            totalPrice += listBean.getShowCcNum() * listBean.getShoCcPrice();
            if (totalPrice > 0) {
                num += listBean.getShowCcNum();
            }
        }
        mTvTotal.setText(Constant.￥ + Constant.get().canvetDouble(totalPrice));
        mBtnSettlement.setText("结算(" + num + ")");

    }


    @Override
    protected void onLoadData(int index, int pageSize) {
        if (index == 1) {
            mListBeanSparseArray.clear();
        }

        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).commoditySupplyList(businessId, "2", index, pageSize))
                .subscribe(new BaseObserver<CommodityList>() {
                    @Override
                    public void onSuccess(CommodityList it) {
                        notifyDataChanged(it.getList());
                    }
                });
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        FrameLayout pLayout = findViewById(R.id.bottom_content);
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.bottom_jinhuo_total_price, null, false);
        pLayout.addView(inflate);

        mTvTotal = inflate.findViewById(R.id.tv_total);
        mBtnSettlement = inflate.findViewById(R.id.btn_settlement);

        mBtnSettlement.setOnClickListener(v -> {
            ConfirmOrderActivity.startSupplyActivity(getActivity(), businessId, canverData());
        });
    }

    private String canverData() {


        List<CreateOrderRequest.CheckCartDTOSBean> checkCartDTOSBeans = new ArrayList<>();
        for (int i = 0; i < mListBeanSparseArray.size(); i++) {
            CommodityList.ListBean listBean = mListBeanSparseArray.valueAt(i);
            if (listBean.getShowCcNum() == 0) {
                continue;
            }
            CreateOrderRequest.CheckCartDTOSBean checkCartDTOSBean = new CreateOrderRequest.CheckCartDTOSBean();
            checkCartDTOSBean.setNum(listBean.getShowCcNum());
            checkCartDTOSBean.setCartId(listBean.getCardId()==0?null:listBean.getCardId());
            checkCartDTOSBean.setCommodityId(listBean.getCommodityId());
            checkCartDTOSBean.setNormsId(listBean.getRules().get(0).getId());
            checkCartDTOSBeans.add(checkCartDTOSBean);
        }
        if (checkCartDTOSBeans.size() == 0) {
            return "";
        }
        return App.getmGson().toJson(checkCartDTOSBeans);

    }
}
