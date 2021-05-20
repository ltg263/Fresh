package com.power.fresh.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chen.concise.RxHttp;
import com.hjq.toast.ToastUtils;
import com.power.common_opensurce.App;
import com.power.fresh.MainActivity;
import com.power.fresh.R;
import com.power.fresh.adapter.ShoppingCartAdapter;
import com.power.fresh.api.FreshService;
import com.power.fresh.bean.request.CreateOrderRequest;
import com.power.fresh.bean.shoppingcar.ShoppingCarBean;
import com.power.fresh.ui.order.ConfirmOrderActivity;
import com.power.fresh.utils.Constant;
import com.powerrich.common.base.BaseObserver;
import com.powerrich.common.base.MyLazyFragment;
import com.powerrich.common.helper.LogUtils;
import com.powerrich.common.widget.TitleBar;
import com.tencent.bugly.crashreport.CrashReport;
import com.yanzhenjie.recyclerview.OnItemMenuClickListener;
import com.yanzhenjie.recyclerview.SwipeMenu;
import com.yanzhenjie.recyclerview.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.SwipeMenuItem;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

/**
 * 购物车
 */
public class ShoppingCartFragment extends MyLazyFragment {

    @BindView(R.id.mTitleBar)
    TitleBar mTitleBar;
    @BindView(R.id.rvShopping)
    RecyclerView rvShopping;
    boolean isActivitFlag = false;


    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;

    ShoppingCartAdapter mShoppingCartAdapter;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.btn_settlement)
    Button btnSettlement;
    @BindView(R.id.cb_total)
    CheckBox cbTotal;

    /**
     * 商铺id  对应的
     */
    private Map<Integer, List<CreateOrderRequest.CheckCartDTOSBean>> mBeanMap = new HashMap<>();

    boolean isAllCheck = false;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shopping_cart;
    }

    public static ShoppingCartFragment newInstance() {
        ShoppingCartFragment fragment = new ShoppingCartFragment();
        return fragment;
    }

    @Override
    protected int getTitleId() {
        return R.id.mTitleBar;
    }

    public void isActivity() {
        isActivitFlag = true;
    }

    @Override
    public boolean statusBarDarkFont() {
        return false;
    }
    boolean isEdit;
    @Override
    public void onRightClick() {

        String rightTitle = mTitleBar.getRightTitle();

        if (rightTitle.equals("编辑")) {
            isEdit = true;
            mTitleBar.setRightTitle("完成");
        } else {
            isEdit = false;
            mTitleBar.setRightTitle("编辑");
        }

        List<ShoppingCarBean.ListBean> data = mShoppingCartAdapter.getData();
        for (int i = 0; i < data.size(); i++) {
            data.get(i).setEdit(isEdit);
        }
        mShoppingCartAdapter.notifyDataSetChanged();

    }

    @Override
    protected void initView() {
        if (isActivitFlag) {
            mTitleBar.setIvLeftBack(true);
            mTitleBar.setLeftResId(R.mipmap.icon_public_arrow_back_white);
        }

        mTitleBar.setRightTitle("编辑");






//        // 设置监听器。
//        rvShopping.setSwipeMenuCreator(mSwipeMenuCreator);
//// 菜单点击监听。
//        rvShopping.setOnItemMenuClickListener(mItemMenuClickListener);




        mShoppingCartAdapter = new ShoppingCartAdapter(getContext());
        DividerItemDecoration divider = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.shopping_list_status_custom_divider));
        rvShopping.addItemDecoration(divider);
        rvShopping.setNestedScrollingEnabled(true);
        rvShopping.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvShopping.setAdapter(mShoppingCartAdapter);



        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                Log.e("jsc", "ShoppingCartFragment-onRefresh:");

                requestDialog();

            }
        });

      //  refresh.setOnRefreshListener(() -> postDelayed(() ->  refresh.setRefreshing(false), 1500));






        initEvent();


//        cbTotal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//
//                Log.e("jsc111", "ShoppingCartFragment-onCheckedChanged:");
//
//                List<ShoppingCarBean.ListBean> data = mShoppingCartAdapter.getData();
//                for (int i = 0; i < data.size(); i++) {
//                    data.get(i).setCheck(isChecked);
//                }
//                mShoppingCartAdapter.notifyDataSetChanged();
//            }
//        });

    }





    private void initEvent() {

        mShoppingCartAdapter.setOnCheckedChangeListener(new ShoppingCartAdapter.OnCheckedChangeListener() {
            /** 父类Check 改变 */
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked, int position) {
                isCheckAll();
                closeAnAccount();
            }

            /** 子类Check 改变 */
            @Override
            public void onCheckedChildChanged(CompoundButton buttonView, boolean isChecked, int position) {
                isCheckAll();
                closeAnAccount();
            }

            /** 1.10.3 购物车修改数量  */
            @Override
            public void ChildNumberChange(int cartId, int number) {
                requestCartUpdateNum(cartId, number);
            }

            /** 店铺删除 */
            @Override
            public void onDelete(int postion) {
//                mShoppingCartAdapter.getData().remove(postion);
//                mShoppingCartAdapter.notifyDataSetChanged();
                int businessId = mShoppingCartAdapter.getData().get(postion).getBaseBusiness().getBusinessId();
                removeBusinessCart(businessId);
            }

            @Override
            public void removeCart(int cartId) {
                userCartRemove(cartId);

            }
        });


        cbTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isAllCheck) {
                    isAllCheck = false;
                    cbTotal.setChecked(isAllCheck);
                } else {
                    isAllCheck = true;
                    cbTotal.setChecked(isAllCheck);
                }


                List<ShoppingCarBean.ListBean> data = mShoppingCartAdapter.getData();
                for (int i = 0; i < data.size(); i++) {
                    data.get(i).setCheck(isAllCheck);

                    for (int j = 0; j < data.get(i).getUserCartDTOS().size(); j++) {
                        data.get(i).getUserCartDTOS().get(j).setCheck(isAllCheck);
                    }

                }
                mShoppingCartAdapter.notifyDataSetChanged();


                Log.e("jsc111", "ShoppingCartFragment-onClick:");
            }
        });
    }


    /**
     * 全选是否要选中
     */
    private void isCheckAll() {

        boolean isCheckAll = true;

        List<ShoppingCarBean.ListBean> data = mShoppingCartAdapter.getData();
        for (int i = 0; i < data.size(); i++) {
            ShoppingCarBean.ListBean listBean = data.get(i);
            if (!listBean.isCheck()) {
                isCheckAll = false;
                break;
            }

            for (int j = 0; j < listBean.getUserCartDTOS().size(); j++) {
                ShoppingCarBean.ListBean.UserCartDTOSBean userCartDTOSBean = listBean.getUserCartDTOS().get(j);
                if (!userCartDTOSBean.isCheck()) {
                    isCheckAll = false;
                    break;
                }
            }
        }
        cbTotal.setChecked(isCheckAll);

    }


    /**
     * 总价
     */
    private double total;
    /**
     * 数量
     */
    private int goodsSize;

    /**
     * 结算
     */
    private void closeAnAccount() {
        //第三种：推荐，尤其是容量大时
        total = 0.00;
        goodsSize = 0;
//        for(Map.Entry<Integer, ShoppingCarBean.ListBean> entry: mBeanMap.entrySet())
//        {
//            entry.getValue().getUserCartDTOS();
//            total += entry.getValue().get
//        }

        List<ShoppingCarBean.ListBean> data = mShoppingCartAdapter.getData();
        for (int i = 0; i < data.size(); i++) {
            //商店是否被选中
            boolean shoppingCheck = false;
            ShoppingCarBean.ListBean listBean = data.get(i);

            if (listBean.isCheck()) {
                shoppingCheck = true;
            }

            for (int j = 0; j < listBean.getUserCartDTOS().size(); j++) {
                ShoppingCarBean.ListBean.UserCartDTOSBean userCartDTOSBean = listBean.getUserCartDTOS().get(j);
                // 商品是否选中
                if (shoppingCheck || userCartDTOSBean.isCheck()) {
                    total += userCartDTOSBean.getBaseNorms().getSalePrice() * userCartDTOSBean.getNum();
                    goodsSize++;
                }

            }

        }

        tvTotal.setText(Constant.get().canvetDouble(total));

        btnSettlement.setText(String.format("结算(%s)", goodsSize));

    }


    @Override
    protected void initData() {
        Log.e("jsc", "ShoppingCartFragment-initData:");

        if (isReplaceFragment()) {
            requestDialog();
        }


    }


    @Override
    protected void onRestart() {
        Log.e("jsc", "ShoppingCartFragment-onRestart:");
        super.onRestart();
        requestDialog();

    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.i("jsc", "ShoppingCartFragment-onResume:" + this.getClass().getSimpleName() + "-是否是replace Fragment的形式:" + isReplaceFragment() + " -Fragment 是否可见:" + isFragmentVisible());
        Log.e("jsc", "ShoppingCartFragment-onResume:");
        if (isFragmentVisible()|!isReplaceFragment()) {
            requestDialog();
        }

    }

    private void requestDialog() {
        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userCartList(1, 20))
                .subscribe(new BaseObserver<ShoppingCarBean>() {
                    @Override
                    public void onSuccess(ShoppingCarBean it) {
                        List<ShoppingCarBean.ListBean> list = it.getList();

                        for (int i = 0; i < list.size(); i++) {
                            list.get(i).setEdit(isEdit);
                        }

                        mShoppingCartAdapter.setData(list);


                        if (refresh.isRefreshing()) {
                            refresh.setRefreshing(false);
                        }
                        tvTotal.setText("");
                        btnSettlement.setText(String.format("结算(%s)", 0));
                        cbTotal.setChecked(false);


                        Log.e("jsc", "待完成-优惠券onSuccess:" + it.toString());
                    }

                    @Override
                    protected void onError(String errorStr, int code) {
                        if (refresh.isRefreshing()) {
                            refresh.setRefreshing(false);
                        }

                        tvTotal.setText("");
                        btnSettlement.setText(String.format("结算(%s)", 0));
                        cbTotal.setChecked(false);
//                        ToastUtils.show(errorStr);
//                        if (code == 101) {
//                            SPUtils.clear();
//                            startActivity(LoginActivity.class);
//                            finish();
//                        }
                    }
                });
    }


    private void requestCartUpdateNum(int cardId, int num) {

        Constant.get().requestCartUpdateNum(cardId, num, new Constant.IListener<Object>() {
            @Override
            public void onSuccess(Object o) {
                if (num==0) {
                    requestDialog();

                }
            }
        });


    }



    private void userCartRemove(int cardId) {
        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).userCartRemove(cardId))
                .subscribe(new BaseObserver<Object>() {
                    @Override
                    public void onSuccess(Object it) {
                        requestDialog();
//                        mShoppingCartAdapter.notifyDataSetChanged();
                    }


                });
    }


    private void removeBusinessCart(int businessId) {
        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).removeBusinessCart(businessId))
                .subscribe(new BaseObserver<Object>() {
                    @Override
                    public void onSuccess(Object it) {
                        requestDialog();
//                        mShoppingCartAdapter.notifyDataSetChanged();
                    }


                });
    }


    @OnClick(R.id.btn_settlement)
    public void onViewClicked() {

        List<CreateOrderRequest.CheckCartDTOSBean> checkCartDTOSBeans = new ArrayList<>();


        LinkedHashMap<Integer, List<ShoppingCarBean.ListBean.UserCartDTOSBean>> listLinkedHashMap = new LinkedHashMap<>();


        /** 商铺数量 */
        int shops = 0;

        for (int i = 0; i < mShoppingCartAdapter.getData().size(); i++) {
            List<ShoppingCarBean.ListBean.UserCartDTOSBean> userCartDTOSBeanList = new ArrayList<>();
            /** 需要添加到集合中去？ */

            boolean isAdd = false;
            ShoppingCarBean.ListBean shopList = mShoppingCartAdapter.getData().get(i);
            /** 遍历商品 */
            for (int j = 0; j < shopList.getUserCartDTOS().size(); j++) {
                if (shopList.getUserCartDTOS().get(j).isCheck()) {
                    isAdd = true;
                    userCartDTOSBeanList.add(shopList.getUserCartDTOS().get(j));
                }
            }

            if (isAdd) {
                listLinkedHashMap.put(shopList.getBaseBusiness().getBusinessId(), userCartDTOSBeanList);
            }
        }


        shops = listLinkedHashMap.size();


        if (shops == 0) {
            ToastUtils.show("请选择结算商品");
        } else if (shops > 1) {
            ToastUtils.show("多个店铺商品不能同时结算，请选择单个店铺进行结算");
        } else {
            int businessId = 0;
            List<ShoppingCarBean.ListBean.UserCartDTOSBean> userCartDTOSBeanList = new ArrayList<>();
            for (Map.Entry<Integer, List<ShoppingCarBean.ListBean.UserCartDTOSBean>> entry : listLinkedHashMap.entrySet()) {
                businessId = entry.getKey();
                userCartDTOSBeanList = entry.getValue();
            }
            //     List<ShoppingCarBean.ListBean.UserCartDTOSBean> userCartDTOSBeanList = listLinkedHashMap.get(0);
            ConfirmOrderActivity.startActivity(getActivity(), businessId, App.getmGson().toJson(userCartDTOSBeanList));
        }


    }
}
