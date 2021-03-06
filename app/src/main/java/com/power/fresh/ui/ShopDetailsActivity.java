package com.power.fresh.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chen.concise.RxHttp;
import com.hjq.toast.ToastUtils;
import com.power.common_opensurce.App;
import com.power.common_opensurce.utils.KeyboardUtils;
import com.power.fresh.R;
import com.power.fresh.api.FreshService;
import com.power.fresh.bean.CommodityList;
import com.power.fresh.bean.commodity.CommodityCategory;
import com.power.fresh.bean.commodity.CommodityInfo;
import com.power.fresh.bean.shoppingcar.SaveCartResponse;
import com.power.fresh.bean.shoppingcar.ShoppingCarBean;
import com.power.fresh.ui.order.ConfirmOrderActivity;
import com.power.fresh.utils.Constant;
import com.power.fresh.utils.KeyboardWatcher;
import com.power.fresh.utils.SortPopwindow;
import com.power.fresh.widget.Addand;
import com.powerrich.common.base.BaseObserver;
import com.powerrich.common.base.UIActivity;
import com.powerrich.common.base.adapter.CommonRvAdapter;
import com.powerrich.common.base.adapter.CommonViewHolderRv;
import com.powerrich.common.image.GlideLoad;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * //???????????????
 * txt1.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
 * ???????????? + ????????????-?????????
 * <p>
 * Question??? ????????????????????????????????? ???????????????????????????+??????id ??????key ???????????????
 */
public class ShopDetailsActivity extends UIActivity implements KeyboardWatcher.SoftKeyboardStateListener {


    @BindView(R.id.rv_category_parent)
    RecyclerView rvCategoryParent;
    @BindView(R.id.rv_category_child)
    RecyclerView rvCategoryChirld;
    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    @BindView(R.id.public_toolbar_title)
    TextView publicToolbarTitle;
    @BindView(R.id.rg_search)
    RadioGroup rgSearch;
    @BindView(R.id.rb_04)
    CheckBox rb04;
    @BindView(R.id.rb_01)
    RadioButton rb01;
    @BindView(R.id.rb_02)
    RadioButton rb02;
    @BindView(R.id.rb_03)
    RadioButton rb03;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;
    /**
     * ????????????
     */
    private CommonRvAdapter<CommodityCategory.ListBean> mParentCategoryAdapter;
    //List<CommodityCategory.ListBean> mParentListBeans = new ArrayList<>();
    /**
     * ????????????
     */
    private CommonRvAdapter<CommodityCategory.ListBean.ChildrenBean> mChildCategoryAdapter;

    private CommonRvAdapter<CommodityList.ListBean> mContentRvAdapter;
    private int businessId;
    private String categoryId;
    private String categoryName;
    private String searchContent;
    /**
     * key :??????id+??????id  value:num
     */
    private SparseArray<CommodityList.ListBean> sContainer = new SparseArray<>();

    /**
     * ????????????
     */
    private Boolean hotOrder = false;


    /**
     * ???????????? 0 ?????? 1 ??????
     */
    private Integer priceOrder = null;
    /**
     * ????????????
     */
    private Boolean specialOrder;
    /**
     * ???????????????(??????) 0,?????????(?????????) 1,??????(??????)  ????????????????????????
     */
    private Integer hasHot;
    /**
     * ??????id
     */
    private Integer pageCategoryId;

    /**
     * 1????????? 2????????? 3????????? 4?????????  ??????????????????
     */
    private int headType = 1;


    /** ??????????????? headType ???????????????????????? */
    //SparseArray<Boolean>  headTypeArray = new SparseArray<>(4);


    /**
     * ????????????List
     */
    private void requestApplist() {
        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).commodityAppList(true, priceOrder, businessId, pageCategoryId,
                searchContent, hotOrder, specialOrder, hasHot))
                .subscribe(new BaseObserver<CommodityList>() {
                    @Override
                    public void onSuccess(CommodityList it) {

                        mContentRvAdapter.setData(it.getList());

                    }
                });

    }

    /**
     * ??????????????????
     */
    private void requestCommodityInfo() {
        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).appBusinessGetSimple(businessId))
                .subscribe(new BaseObserver<CommodityInfo>() {
                    @Override
                    public void onSuccess(CommodityInfo it) {
                        if (it == null) {
                            return;
                        }
                        String siteName = it.getSiteName();
                        publicToolbarTitle.setText(siteName);
                        GlideLoad.loadImage(ivHead, it.getTranslate());
                    }
                });

    }


    @Override
    protected int getLayoutId() {


        return R.layout.activity_shop_details;
    }

    @Override
    protected int getTitleId() {
        return R.id.rl_title;
    }

    @Override
    protected void initView() {
        String title = getIntent().getStringExtra("title");
        publicToolbarTitle.setText(title);
        businessId = getIntent().getIntExtra("businessId", 0);
        categoryId = getIntent().getStringExtra("categoryId");
        categoryName = getIntent().getStringExtra("categoryName");

        initParentCategoryRecycle();
        initChildCategoryRecycle();
        initContentRecycle();
        initEvent();

        KeyboardWatcher.with(this)
                .setListener(this);
    }

    private void initEvent() {
        mParentCategoryAdapter.setOnItemClickListener(new CommonRvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                List<CommodityCategory.ListBean> data = mParentCategoryAdapter.getData();
                setCheck(data, position);
                mParentCategoryAdapter.setData(data);


                List<CommodityCategory.ListBean.ChildrenBean> children = data.get(position).getChildren();
                if (children.size() > 0) {
                    children.get(0).setCheck(true);
                    pageCategoryId = children.get(0).getCategoryId();
                    requestApplist();
                }
                mChildCategoryAdapter.setData(children);
            }
        });

        mChildCategoryAdapter.setOnItemClickListener(new CommonRvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                List<CommodityCategory.ListBean.ChildrenBean> data = mChildCategoryAdapter.getData();
                setChildCheck(data, position);
                mChildCategoryAdapter.setData(data);
                pageCategoryId = data.get(position).getCategoryId();
                requestApplist();
            }
        });


        rgSearch.setOnCheckedChangeListener((group, checkedId) -> {

            if (rbChange) {
                return;
            }

            rgChange = true;

            setDrawableRight(0);
            rb04.setBackgroundResource(R.drawable.home_search_tagview_shape);
            rb04.setChecked(false);


            /** ?????? */
            if (checkedId == R.id.rb_01) {
                headType = 1;
                setZongheParams();
                /** ?????? */
            } else if (checkedId == R.id.rb_02) {
                headType = 2;
                priceOrder = null;
                hotOrder = null;
                specialOrder = null;
                hasHot = null;
                /** ?????? */
            } else if (checkedId == R.id.rb_03) {
                headType = 3;
                priceOrder = null;
                hotOrder = null;
                specialOrder = true;
                hasHot = null;
            }
            requestApplist();
            rgChange = false;

        });

        rb04.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (rgChange) {
                    return;
                }
                rbChange = true;
                rgSearch.clearCheck();


                headType = 4;

                if (b) {
                    priceOrder = 1;
                    hotOrder = null;
                    specialOrder = null;
                    hasHot = null;

                    rb04.setBackgroundResource(R.drawable.home_search_tagview_shape22);
                    setDrawableRight(R.mipmap.sd_down_arrow);
                } else {
                    priceOrder = 0;
                    hotOrder = null;
                    specialOrder = null;
                    hasHot = null;
                    setDrawableRight(R.mipmap.sd_full_up);
                }
                requestApplist();
                rbChange = false;

            }
        });

        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//                    if (TextUtils.isEmpty(etSearch.getText().toString())) {
//                        ToastUtils.show("????????????????????????");
//                    } else {
//
//                    }
                    searchContent = etSearch.getText().toString();
                    requestApplist();

                    //????????????????????????????????????
                    KeyboardUtils.hideKeyboard(etSearch);
                    // ???????????????????????????,??????????????????????????????
                    return true;
                }

                return false;
            }
        });


    }

    private void setZongheParams() {
        priceOrder = null;
        hotOrder = true;
        specialOrder = null;
        hasHot = null;
    }

    // rgoup ??????
    private boolean rgChange = false;
    // checkButton ??????
    private boolean rbChange = false;


    private void setDrawableRight(int resouceId) {
        if (resouceId == 0) {
            rb04.setCompoundDrawables(null, null, null, null);
            return;
        }
        Drawable drawable = getResources().getDrawable(
                resouceId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                drawable.getMinimumHeight());

        rb04.setCompoundDrawables(null, null, drawable, null);
    }


    @Override
    protected void initData() {
        requestCategory();
        requestCommodityInfo();
    }

    public static void startActivity(Context context, String title, int businessId, String categoryId, String categoryName) {
        Intent intent = new Intent(context, ShopDetailsActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("businessId", businessId);
        intent.putExtra("categoryId", categoryId);
        intent.putExtra("categoryName", categoryName);
        context.startActivity(intent);
    }


    private void requestCategory() {
        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).siteCategoryListAll(businessId, categoryId, categoryName))
                .subscribe(new BaseObserver<CommodityCategory>() {
                    @Override
                    public void onSuccess(CommodityCategory it) {


                        /** ????????? */
                        if (it.getList().size() > 0) {
                            it.getList().get(0).setCheck(true);
                        }
                        mParentCategoryAdapter.setData(it.getList());
                        /** ????????? */
                        List<CommodityCategory.ListBean.ChildrenBean> children = it.getList().get(0).getChildren();
                        if (children.size() > 0) {

                            children.get(0).setCheck(true);
                            pageCategoryId = children.get(0).getCategoryId();
                            setZongheParams();
                            requestApplist();
                        }
                        mChildCategoryAdapter.setData(children);


                    }
                });
    }

    /** ?????? */
    private void initParentCategoryRecycle() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        rvCategoryParent.setLayoutManager(linearLayoutManager);
        mParentCategoryAdapter = new CommonRvAdapter<CommodityCategory.ListBean>(getActivity(), null, R.layout.item_shop_category_parent) {
            @Override
            public void convert(CommonViewHolderRv holder, CommodityCategory.ListBean it, int position) {
                holder.setText(R.id.tv_parent_name, it.getCategoryName());
                if (it.isCheck()) {
                    holder.setTextColor(R.id.tv_parent_name, R.color.app_main_body);
                } else {
                    holder.setTextColor(R.id.tv_parent_name, R.color.picture_color_black);
                }

            }
        };


        rvCategoryParent.setNestedScrollingEnabled(false);
        rvCategoryParent.setAdapter(mParentCategoryAdapter);

    }

    /**
     * ??????
     */
    private void setCheck(List<CommodityCategory.ListBean> listBeans, int position) {
        for (int i = 0; i < listBeans.size(); i++) {
            if (i == position) {
                listBeans.get(i).setCheck(true);
            } else {
                listBeans.get(i).setCheck(false);
            }
        }
    }

    /**
     * ??????
     */
    private void setChildCheck(List<CommodityCategory.ListBean.ChildrenBean> listBeans, int position) {
        for (int i = 0; i < listBeans.size(); i++) {
            if (i == position) {
                listBeans.get(i).setCheck(true);
            } else {
                listBeans.get(i).setCheck(false);
            }
        }
    }


    /** ???????????? */
    private void initChildCategoryRecycle() {

        mChildCategoryAdapter = new CommonRvAdapter<CommodityCategory.ListBean.ChildrenBean>(getActivity(), null, R.layout.item_shop_category_child) {
            @Override
            public void convert(CommonViewHolderRv holder, CommodityCategory.ListBean.ChildrenBean it, int position) {
                holder.setText(R.id.tv_chirld_name, it.getCategoryName());

                if (it.isCheck()) {
                    holder.setTextColor(R.id.tv_chirld_name, R.color.picture_color_black);
                    holder.itemView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.picture_color_white));
                } else {
                    holder.setTextColor(R.id.tv_chirld_name, R.color.public_color_4D4D4D);
                    holder.itemView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.public_color_F4F5F9));
                }

            }
        };

        mChildCategoryAdapter.setNeedEmptyView(false);
        rvCategoryChirld.setNestedScrollingEnabled(false);
        rvCategoryChirld.setAdapter(mChildCategoryAdapter);


    }


    private void initContentRecycle() {


        mContentRvAdapter = new CommonRvAdapter<CommodityList.ListBean>(getActivity(), null, R.layout.item_shop_contnt) {

            @Override
            public void convert(CommonViewHolderRv holder, CommodityList.ListBean it, int position) {
                holder.setImageUrl(R.id.iv_commodity, it.getCommodityHeaderUri());
                holder.setText(R.id.tv_content_title, it.getName());
                holder.setText(R.id.tv_simpleinfo, it.getSimpleInfo());
                holder.setText(R.id.tv_yuanjia, String.valueOf(it.getSalePrice()));

                TextView tvOldPrice = holder.getItemView(R.id.tv_old_price);
                tvOldPrice.setText(String.valueOf(it.getSalePrice()));
                tvOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

                holder.setText(R.id.tv_activity, it.getRules().get(0).getNormsRule());

                View itemView = holder.getItemView(R.id.tv_activity);
                /** 0,????????????
                 1,????????? */
                if (it.getActivityStatus() == 0) {
                    // itemView.setVisibility(View.GONE);
                    tvOldPrice.setVisibility(View.GONE);
                } else {
                    // itemView.setVisibility(View.VISIBLE);
                    tvOldPrice.setVisibility(View.VISIBLE);
                }
                //it.get

                Addand addand = holder.getItemView(R.id.add);
                int id = Integer.valueOf(it.getCommodityId() + "" + headType + +(pageCategoryId == null ? 0 : pageCategoryId));

                Log.e("jsc", "ShopDetailsActivity-convert:" + id);

                //  Boolean aBoolean = headTypeArray.valueAt(headType);

                //    if(aBoolean==null){
                addand.setValue(it.getTotalCartNum());

                if (it.getTotalCartNum()>0) {
                    int id01 =   getOnlyId(it.getCommodityId());
                    it.setShowCcNum(it.getTotalCartNum());
                    it.setCardId(it.getRules().get(0).getCartId());
                    sContainer.put(id01, it);
                    closeAnAccount();
                }




                //    headTypeArray.put(headType,false);
//                }else{
//                    addand.setValue(sContainer.get(id, new CommodityList.ListBean()).getShowCcNum());
//                }


                addand.setOnNumberChangedListener(new Addand.OnNumberChangedListener() {
                    @Override
                    public void OnNumberChanged(int vs, boolean isAdd) {

                        List<CommodityList.ListBean.RulesBean> rules = it.getRules();
                        //??????????????????1 ?????????
                        if (rules.size() > 1) {
                            new SortPopwindow(getActivity(), it).showBottomView(new SortPopwindow.ChooseImgImpl() {
                                @Override
                                public void ok(CommodityList.ListBean.RulesBean rulesBean) {
                                        Log.w("mContentRvAdapter",rules.get(0).getSelectNum()+"");
//                                    addand.setShowValue(1);
//                                    addSize(it, 1);
                                }
                            });
                        } else {
                            if (isAdd && vs == 1) {
                                Constant.get().saveCart(businessId, it.getCommodityId(), it.getRules().get(0).getId(), 1, true, new Constant.IListener<SaveCartResponse>() {
                                    @Override
                                    public void onSuccess(SaveCartResponse saveCartResponse) {
                                        it.getRules().get(0).setCartId(saveCartResponse.getCartId());
                                        int id =   getOnlyId(it.getCommodityId());
                                        Log.e("jsc", "ShopDetailsActivity-??????:" + id);
                                        it.setShowCcNum(vs);
                                        it.setCardId(it.getRules().get(0).getCartId());
                                        sContainer.put(id, it);
                                        closeAnAccount();
                                    }
                                });

                            } else {
                                Constant.get().requestCartUpdateNum(it.getRules().get(0).getCartId(), vs, new Constant.IListener<Object>() {
                                    @Override
                                    public void onSuccess(Object o) {
                                        int id =   getOnlyId(it.getCommodityId());
                                        Log.e("jsc", "ShopDetailsActivity-????????????Id:" + id);
                                        it.setShowCcNum(vs);
                                        it.setCardId(it.getRules().get(0).getCartId());
                                        sContainer.put(id, it);
                                        closeAnAccount();
                                    }

                                });

                            }
                        }

                    }
                });
            }

        };


        mContentRvAdapter.setOnItemClickListener(new CommonRvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                int commodityId = mContentRvAdapter.getData().get(position).getCommodityId();
                CommodityDetailsActivity.startActivity(getActivity(), commodityId);
            }
        });


        rvContent.setNestedScrollingEnabled(false);
        rvContent.setAdapter(mContentRvAdapter);
    }

    private void addSize(CommodityList.ListBean it, int vs) {
        // businessId
        Constant.get().saveCart(0, it.getCommodityId(), it.getRules().get(0).getId(), 1, true, new Constant.IListener<SaveCartResponse>() {
            @Override
            public void onSuccess(SaveCartResponse saveCartResponse) {
                it.getRules().get(0).setCartId(saveCartResponse.getCartId());
                int id = getOnlyId(it.getCommodityId());
                Log.e("jsc", "ShopDetailsActivity-??????:" + id);
                it.setShowCcNum(vs);
                it.setCardId(it.getRules().get(0).getCartId());
                sContainer.put(id, it);
                //closeAnAccount();
            }
        });
    }
    private  int getOnlyId(int commodityId) {
       // int id = Integer.valueOf(it.getCommodityId() + "" + headType + +(pageCategoryId == null ? 0 : pageCategoryId));
        return Integer.valueOf(commodityId + "" + headType + +(pageCategoryId == null ? 0 : pageCategoryId));

    }


    /**
     * ??????
     */
    private void closeAnAccount() {
        int num = 0;
        double totalPrice = 0.0;
        for (int i = 0; i < sContainer.size(); i++) {
            CommodityList.ListBean listBean = sContainer.valueAt(i);
            totalPrice += listBean.getShowCcNum() * listBean.getSalePrice();
            if (totalPrice > 0) {
                num += listBean.getShowCcNum();
            }
        }
        tvNum.setText(String.format("???%s???", num));
        tvTotalPrice.setText(Constant.??? + Constant.get().canvetDouble(totalPrice));
    }


    @OnClick({R.id.btn_settlement, R.id.iv_back, R.id.public_toolbar_title, R.id.iv_shop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_settlement:

                boolean isStart = false;
                for (int i = 0; i < sContainer.size(); i++) {
                    CommodityList.ListBean listBean = sContainer.valueAt(i);
                    if (listBean.getShowCcNum() > 0) {
                        isStart = true;
                        break;
                    }
                }

                if (isStart) {
                    ConfirmOrderActivity.startActivity(getActivity(), businessId, App.getmGson().toJson(getData()));
                } else {
                    ToastUtils.show("???????????????????????????????????????");
                }


                break;
            case R.id.public_toolbar_title:
                ShopIntroducedActivity.startActivity(getActivity(), businessId);
                break;
            case R.id.iv_shop:
                startActivity(ShoppingCartActivity.class);

                //getPopupWindow();
                break;
        }
    }

    private List<ShoppingCarBean.ListBean.UserCartDTOSBean> getData() {
        List<ShoppingCarBean.ListBean.UserCartDTOSBean> intentList = new ArrayList<>();
        for (int i = 0; i < sContainer.size(); i++) {
            CommodityList.ListBean listBean = sContainer.valueAt(i);
            if (listBean.getShowCcNum() > 0) {
                ShoppingCarBean.ListBean.UserCartDTOSBean userCartDTOSBean = new ShoppingCarBean.ListBean.UserCartDTOSBean();
                userCartDTOSBean.setNum(listBean.getShowCcNum());
                userCartDTOSBean.setCartId(listBean.getCardId());
                ShoppingCarBean.ListBean.UserCartDTOSBean.BaseInfoBean baseInfoBean = new ShoppingCarBean.ListBean.UserCartDTOSBean.BaseInfoBean();
                baseInfoBean.setCommodityId(listBean.getCommodityId());
                userCartDTOSBean.setBaseInfo(baseInfoBean);
                ShoppingCarBean.ListBean.UserCartDTOSBean.BaseNormsBean baseNormsBean = new ShoppingCarBean.ListBean.UserCartDTOSBean.BaseNormsBean();
                baseNormsBean.setNormsId(listBean.getRules().get(0).getId());
                userCartDTOSBean.setBaseNorms(baseNormsBean);
                intentList.add(userCartDTOSBean);
            }
        }
        return intentList;
    }


    private void getPopupWindow() {
        //?????????
        View viewSire = View.inflate(this, R.layout.activity_shop_details, null);
        //?????????
        View viewSon = View.inflate(this, R.layout.item_seckill, null);
        final PopupWindow popupWindow = new PopupWindow(viewSon, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setTouchable(true);//??????????????????
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);//???????????????????????????   ?????????setBackgroundDrawable?????????
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //???????????????????????????
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int heightPixels = metric.heightPixels;
        popupWindow.setHeight(Math.round(heightPixels * 0.8f));
        popupWindow.setAnimationStyle(R.style.pop_shop_anim);
        popupWindow.showAtLocation(viewSire, Gravity.BOTTOM, 0, 0);//????????????????????????
//        tv_details = (TextView) viewSon.findViewById(R.id.tv_details);
//        tv_comment = (TextView) viewSon.findViewById(R.id.tv_comment);
//        cinema_recycler_details = (RecyclerView) viewSon.findViewById(R.id.cinema_recycler_details);
//        relativeLayout_cinema = (RelativeLayout) viewSon.findViewById(R.id.relativeLayout_cinema);
//        tv_details.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//            }
//        });
//        tv_comment.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                cinema_recycler_details.setVisibility(view.VISIBLE);
//                relativeLayout_cinema.setVisibility(view.GONE);
//            }
//        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation

    }


    @Override
    public void onSoftKeyboardOpened(int keyboardHeight) {
        llBottom.setVisibility(View.GONE);
    }

    @Override
    public void onSoftKeyboardClosed() {
        llBottom.setVisibility(View.VISIBLE);

    }
}
