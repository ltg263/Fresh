package com.power.fresh.fragment;

import android.graphics.Paint;
import android.util.Log;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chen.concise.RxHttp;
import com.power.common_opensurce.utils.KeyboardUtils;
import com.power.fresh.R;
import com.power.fresh.api.FreshService;
import com.power.fresh.bean.CommodityList;
import com.power.fresh.bean.commodity.CommodityCategory;
import com.power.fresh.bean.event.EventSort;
import com.power.fresh.bean.shoppingcar.SaveCartResponse;
import com.power.fresh.ui.CommodityDetailsActivity;
import com.power.fresh.ui.business.BusinessListActivity;
import com.power.fresh.ui.business.BusinessListActivity2;
import com.power.fresh.utils.Constant;
import com.power.fresh.utils.SortPopwindow;
import com.power.fresh.widget.Addand;
import com.powerrich.common.base.BaseObserver;
import com.powerrich.common.base.MyLazyFragment;
import com.powerrich.common.base.adapter.CommonRvAdapter;
import com.powerrich.common.base.adapter.CommonViewHolderRv;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SortFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SortFragment extends MyLazyFragment {

    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.ll_title)
    LinearLayout llTitle;
    @BindView(R.id.rv_category_parent)
    RecyclerView rvCategoryParent;
    @BindView(R.id.rv_category_child)
    RecyclerView rvCategoryChirld;

    @BindView(R.id.rv_content)
    RecyclerView rvContent;


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


    /**
     * 分类id
     */
    private Integer pageCategoryId;

    /**
     * key :分类id+商品id  value:num
     */
    private SparseArray<CommodityList.ListBean> sContainer = new SparseArray<>();

    /**
     * 1：综合 2：推荐 3：特价 4：价格  添加标识用的
     */
    private int headType = 1;


    public static SortFragment newInstance() {
        SortFragment fragment = new SortFragment();
        return fragment;
    }


    @Override
    public boolean statusBarDarkFont() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sort;
    }

    @Override
    protected int getTitleId() {
        return R.id.ll_title;
    }

    @Override
    protected void initView() {


        Log.e("Fragment基类", "SortFragment-initView:");

        initParentCategoryRecycle();
        initChildCategoryRecycle();
        initContentRecycle();

        EventBus.getDefault().register(this);

        initEvent();

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBus(EventSort eventSort) {
        //    requestUserInfo();
        Log.e("Fragment基类", "SortFragment-onEventBus:" + eventSort.getPosition());
        for (int i = 0; i < mIt.size(); i++) {
            if (eventSort.getTitle().equals(mIt.get(i).getCategoryName())) {
                rvCategoryParent.smoothScrollToPosition(i);
                parentCategoryItemClick(i);
                return;
            }
        }

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void initData() {
        requestCategory();
    }

    private List<CommodityCategory.ListBean> mIt;

    private void requestCategory() {
        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).siteCategoryListAll())
                .subscribe(new BaseObserver<List<CommodityCategory.ListBean>>() {


                    @Override
                    public void onSuccess(List<CommodityCategory.ListBean> it) {

                        mIt = it;
                        Log.e("jsc", "SortFragment-onSuccess:" + it.size());


//                        /** 横向点 */
                        if (it.size() > 0) {
                            it.get(0).setCheck(true);
                        }
                        mParentCategoryAdapter.setData(it);
                        /** 纵向点 */
                        List<CommodityCategory.ListBean.ChildrenBean> children = it.get(0).getChildren();
                        if (children.size() > 0) {

                            children.get(0).setCheck(true);

                            // setZongheParams();
                            requestApplist(children.get(0).getId(), null);
                        }
                        mChildCategoryAdapter.setData(children);


                    }
                });
    }


    /**
     * 横向
     */
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
     * 纵向分类
     */
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
                holder.setText(R.id.tv_yuanjia, Constant.￥ + it.getMinPrice()); //it.getMinPrice()

                TextView tvOldPrice = holder.getItemView(R.id.tv_old_price);


                holder.setText(R.id.tv_activity, it.getRules().get(0).getNormsRule()); //it.getRules().get(0).getNormsRule()


//                /** 0,没有活动
//                 1,有活动 */
                if (it.getActivityStatus() == 0) {
                    // itemView.setVisibility(View.GONE);
                    tvOldPrice.setVisibility(View.GONE);
                } else {
                    tvOldPrice.setText(String.valueOf(it.getSalePrice()));
                    tvOldPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                    tvOldPrice.setVisibility(View.VISIBLE);
                }
//

                Addand addand = holder.getItemView(R.id.add);
                addand.setVisibility(View.GONE);
                int id = Integer.valueOf(it.getCommodityId() + "" + headType + +(pageCategoryId == null ? 0 : pageCategoryId));

                Log.e("jsc", "ShopDetailsActivity-convert:" + id);

                //  Boolean aBoolean = headTypeArray.valueAt(headType);

                //    if(aBoolean==null){
                if (it.getTotalCartNum() == 0 && it.getRules().size() > 1) {
                    addand.setNotAdd(false);
                }

                addand.setValue(it.getTotalCartNum());

                if (it.getTotalCartNum() > 0) {
                    int id01 = getOnlyId(it.getCommodityId());
                    it.setShowCcNum(it.getTotalCartNum());
                    it.setCardId(it.getRules().get(0).getCartId());
                    sContainer.put(id01, it);
                    // closeAnAccount();
                }


                addand.setOnNumberChangedListener(new Addand.OnNumberChangedListener() {
                    @Override
                    public void OnNumberChanged(int vs, boolean isAdd) {

                        List<CommodityList.ListBean.RulesBean> rules = it.getRules();


                        /** 添加 且 为 1 */
                        if (isAdd && vs == 1) {

                            //如果规格大于1 就弹框
                            if (rules.size() > 1) {

                                new SortPopwindow(getActivity(), it).showBottomView(new SortPopwindow.ChooseImgImpl() {
                                    @Override
                                    public void ok(CommodityList.ListBean.RulesBean rulesBean) {
                                        addand.setShowValue(1);
                                        addSize(it, 1);
                                    }

                                });
                            } else {
                                addSize(it, vs);
                            }


                        } else {

                            /** 更新数据 */
                            Constant.get().requestCartUpdateNum(it.getRules().get(0).getCartId(), vs, new Constant.IListener<Object>() {
                                @Override
                                public void onSuccess(Object o) {
                                    int id = getOnlyId(it.getCommodityId());
                                    Log.e("jsc", "ShopDetailsActivity-修改数量Id:" + id);
                                    it.setShowCcNum(vs);
                                    it.setCardId(it.getRules().get(0).getCartId());
                                    sContainer.put(id, it);
                                    // closeAnAccount();
                                }


                            });

                        }

                    }
                });


            }

        };


        mContentRvAdapter.setOnItemClickListener(new CommonRvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //              int commodityId = mContentRvAdapter.getData().get(position).getCommodityId();
                String name = mContentRvAdapter.getData().get(position).getName();
               int categoryId = mContentRvAdapter.getData().get(position).getCategoryId();
//                CommodityDetailsActivity.startActivity(getActivity(), commodityId);
                BusinessListActivity2.startActivity(getActivity(),categoryId,name,0);
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


    private int getOnlyId(int commodityId) {
        // int id = Integer.valueOf(it.getCommodityId() + "" + headType + +(pageCategoryId == null ? 0 : pageCategoryId));
        return Integer.valueOf(commodityId + "" + headType + +(pageCategoryId == null ? 0 : pageCategoryId));

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

    /**
     * 请求内容List
     */
    private void requestApplist(Integer categoryId, String serchName) {
        exeHttpWithDialog(RxHttp.getRetrofitService(FreshService.class).commodityAppList(true, serchName, categoryId, true))
                .subscribe(new BaseObserver<CommodityList>() {
                    @Override
                    public void onSuccess(CommodityList it) {

                        mContentRvAdapter.setData(it.getList());

                    }
                });

    }


    private void parentCategoryItemClick(int position) {
        List<CommodityCategory.ListBean> data = mParentCategoryAdapter.getData();
        setCheck(data, position);
        mParentCategoryAdapter.setData(data);


        List<CommodityCategory.ListBean.ChildrenBean> children = data.get(position).getChildren();
        if (children.size() > 0) {
            children.get(0).setCheck(true);
            pageCategoryId = children.get(0).getId();
            requestApplist(pageCategoryId, null);
        }
        mChildCategoryAdapter.setData(children);
    }

    private void initEvent() {
        mParentCategoryAdapter.setOnItemClickListener(new CommonRvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                parentCategoryItemClick(position);

            }
        });

        mChildCategoryAdapter.setOnItemClickListener(new CommonRvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                List<CommodityCategory.ListBean.ChildrenBean> data = mChildCategoryAdapter.getData();
                setChildCheck(data, position);
                mChildCategoryAdapter.setData(data);
                pageCategoryId = data.get(position).getId();
                requestApplist(pageCategoryId, null);
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

//                    searchContent = etSearch.getText().toString();
                    requestApplist(null, etSearch.getText().toString());

                    //点击搜索的时候隐藏软键盘
                    KeyboardUtils.hideKeyboard(etSearch);
                    // 在这里写搜索的操作,一般都是网络请求数据
                    return true;
                }

                return false;
            }
        });


    }


}