package com.powerrich.common.base;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hjq.common.R;
import com.powerrich.common.base.adapter.CommonRvAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.List;

/**
 * 构造函数 初始
 * Created on 2019/8/22
 */
public class BaseListFragmentConstructor<T> extends MyLazyFragment {

    private CommonRvAdapter<T> mCommonRvAdapter;
    RecyclerView mRecyclerView;
    SmartRefreshLayout mRefreshLayout;

    private int pageSize = 10;
    private int mIndex;

    private OnLoadDataListener mOnLoadDataListener;
    private List<T> mData;
    private String mType;


    public static BaseListFragmentConstructor newInstance(CommonRvAdapter commonRvAdapter, OnLoadDataListener listener, String type) {
        if (listener==null) {
            throw new IllegalStateException("BaseListFragmentConstructor listener 不能为空");
        }

        Bundle args = new Bundle();
        args.putString("type", type);
        BaseListFragmentConstructor fragment = new BaseListFragmentConstructor();
        fragment.setArguments(args);
        fragment.setOnLoadDataListener(listener);
        fragment.setCommonRvAdapter(commonRvAdapter);
        return fragment;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            setLazyLoad(false);
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    protected interface OnLoadDataListener {

        void onLoadData(int index,int pageSize ,String type);


    }




    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        if (null != bundle) {
            mType = bundle.getString("type");
        }
        if (null != mOnLoadDataListener) {
            mIndex = 1;
            mOnLoadDataListener.onLoadData(mIndex,pageSize, mType);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_apply_list;
    }

    @Override
    protected int getTitleId() {
        return 0;
    }


    @Override
    protected void initView() {

        mRecyclerView = findViewById(R.id.recycler_view);
        mRefreshLayout = findViewById(R.id.refresh_layout);

        mRecyclerView.setAdapter(mCommonRvAdapter);
        mRefreshLayout.setOnRefreshLoadMoreListener(new com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener() {

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                if (null != mOnLoadDataListener) {
                    mIndex = 1;
                    mOnLoadDataListener.onLoadData(mIndex,pageSize, mType);
                }
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (null != mOnLoadDataListener) {
                    mIndex++;
                    mOnLoadDataListener.onLoadData(mIndex,pageSize, mType);
                }
            }

        });
    }



    public void refreshData(){

        mIndex = 1;
        mOnLoadDataListener.onLoadData(mIndex, pageSize,mType);
    }




    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    protected void notifyDataChanged(List<T> data) {
        if (mIndex == 1) {
            mData = data;
            if (null != mRefreshLayout) {
                mRefreshLayout.finishRefresh();
            }
        } else {
            mData.addAll(data);
            if (data.size() == 0) {
                mRefreshLayout.finishLoadMoreWithNoMoreData();
            } else {
                mRefreshLayout.finishLoadMore();
            }
        }
        mCommonRvAdapter.setData(mData);
    }

    private void setCommonRvAdapter(CommonRvAdapter<T> commonRvAdapter) {
        mCommonRvAdapter = commonRvAdapter;
    }

    private void setOnLoadDataListener(OnLoadDataListener onLoadDataListener) {
        mOnLoadDataListener = onLoadDataListener;
    }
}
