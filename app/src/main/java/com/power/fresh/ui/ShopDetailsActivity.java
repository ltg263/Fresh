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
 * //添加删除线
 * txt1.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
 * 店铺详情 + 购买商品-多规格
 * <p>
 * Question： 切换分类，无法保存数量 （思路：将两个分类+商品id 作为key 保存起来）
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
     * 横向分类
     */
    private CommonRvAdapter<CommodityCategory.ListBean> mParentCategoryAdapter;
    //List<CommodityCategory.ListBean> mParentListBeans = new ArrayList<>();
    /**
     * 纵向分类
     */
    private CommonRvAdapter<CommodityCategory.ListBean.ChildrenBean> mChildCategoryAdapter;

    private CommonRvAdapter<CommodityList.ListBean> mContentRvAdapter;
    private int businessId;
    private String categoryId;
    private String categoryName;
    private String searchContent;
    /**
     * key :分类id+商品id  value:num
     */
    private SparseArray<CommodityList.ListBean> sContainer = new SparseArray<>();

    /**
     * 热门排序
     */
    private Boolean hotOrder = false;


    /**
     * 价格排序 0 升序 1 降序
     */
    private Integer priceOrder = null;
    /**
     * 特价排序
     */
    private Boolean specialOrder;
    /**
     * 是否为热门(推荐) 0,不热门(不推荐) 1,热门(推荐)  这里后台又改不要
     */
    private Integer hasHot;
    /**
     * 分类id
     */
    private Integer pageCategoryId;

    /**
     * 1：综合 2：推荐 3：特价 4：价格  添加标识用的
     */
    private int headType = 1;


    /** 记录对应的 headType 是否是第一次刷新 */
    //SparseArray<Boolean>  headTypeArray = new SparseArray<>(4);


    /**
     * 请求内容List
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
     * 获取商铺信息
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


            /** 综合 */
            if (checkedId == R.id.rb_01) {
                headType = 1;
                setZongheParams();
                /** 推荐 */
            } else if (checkedId == R.id.rb_02) {
                headType = 2;
                priceOrder = null;
                hotOrder = null;
                specialOrder = null;
                hasHot = null;
                /** 特价 */
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
//                        ToastUtils.show("搜搜商品不能为空");
//                    } else {
//
//                    }
                    searchContent = etSearch.getText().toString();
                    requestApplist();

                    //点击搜索的时候隐藏软键盘
                    KeyboardUtils.hideKeyboard(etSearch);
                    // 在这里写搜索的操作,一般都是网络请求数据
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

    // rgoup 转换
    private boolean rgChange = false;
    // checkButton 转换
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


                        /** 横向点 */
                        if (it.getList().size() > 0) {
                            it.getList().get(0).setCheck(true);
                        }
                        mParentCategoryAdapter.setData(it.getList());
                        /** 纵向点 */
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

    /** 横向 */
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
     * 父类
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
     * 子类
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


    /** 纵向分类 */
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
                /** 0,没有活动
                 1,有活动 */
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
                        //如果规格大于1 就弹框
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
                                        Log.e("jsc", "ShopDetailsActivity-添加:" + id);
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
                                        Log.e("jsc", "ShopDetailsActivity-修改数量Id:" + id);
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
                Log.e("jsc", "ShopDetailsActivity-添加:" + id);
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
     * 结算
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
        tvNum.setText(String.format("共%s件", num));
        tvTotalPrice.setText(Constant.￥ + Constant.get().canvetDouble(totalPrice));
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
                    ToastUtils.show("还未选购商品，无法进行结算");
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
        //父布局
        View viewSire = View.inflate(this, R.layout.activity_shop_details, null);
        //子布局
        View viewSon = View.inflate(this, R.layout.item_seckill, null);
        final PopupWindow popupWindow = new PopupWindow(viewSon, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setTouchable(true);//设置窗口触摸
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);//设置窗口外部可触摸   要结合setBackgroundDrawable来使用
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //获取手机屏幕的高度
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int heightPixels = metric.heightPixels;
        popupWindow.setHeight(Math.round(heightPixels * 0.8f));
        popupWindow.setAnimationStyle(R.style.pop_shop_anim);
        popupWindow.showAtLocation(viewSire, Gravity.BOTTOM, 0, 0);//相对于父窗体底部
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
