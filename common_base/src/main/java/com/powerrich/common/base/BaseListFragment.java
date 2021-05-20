package com.powerrich.common.base;

import android.util.Log;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.hjq.common.R;
import com.powerrich.common.base.adapter.CommonRvAdapter;
import com.powerrich.common.widget.TitleBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

public abstract class BaseListFragment<T> extends MyLazyFragment {


    LinearLayout llBg;
    private CommonRvAdapter<T> mCommonRvAdapter;



    private List<T> mData;
    private int page = 10;
    private int mIndex;
    private SmartRefreshLayout mRefreshLayout;
    protected RecyclerView mRecyclerView;
    private TitleBar mTitleBar;


    @Override
    public TitleBar getTitleBar() {
        return mTitleBar;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.public_activity_base_list;
    }


    protected abstract CommonRvAdapter<T> getAdapter();

    protected abstract String getTitleText();

    protected abstract void onLoadData(int index, int pageSize);

    protected  void setBgColor(int colorResourceId){
        llBg.setBackgroundColor(ContextCompat.getColor(getActivity(),colorResourceId));
    }

    public SmartRefreshLayout getRefreshLayout() {
        return mRefreshLayout;
    }

    @Override
    protected void initView() {
        mTitleBar = (TitleBar) findViewById(R.id.title_bar);
        mRefreshLayout = (SmartRefreshLayout) findViewById(R.id.refresh_layout);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        llBg = (LinearLayout) findViewById(R.id.ll_bg);

        mTitleBar.setTitle(getTitleText());
        mCommonRvAdapter = getAdapter();
        mRecyclerView.setAdapter(mCommonRvAdapter);
        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

                refreshData();

            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

                mIndex++;
                onLoadData(mIndex, page);

            }

        });


    }



    @Override
    protected void initData() {
        Log.e("jsc", "BaseListFragment-initData-替换:"+isFragmentVisible()+"-是否显示："+isFragmentVisible());
        if (isReplaceFragment()) {
            refreshData();
        }

    }


    @Override
    public void onResume() {
        super.onResume();
        Log.e("jsc", "BaseListFragment-onResume:-替换:"+isFragmentVisible()+"-是否显示："+isFragmentVisible());
        if (isFragmentVisible()|!isReplaceFragment()) {
            refreshData();
        }

    }

    @Override
    protected void onRestart() {
        Log.e("jsc", "BaseListFragment-onRestart:-替换:"+isFragmentVisible()+"-是否显示："+isFragmentVisible());
        super.onRestart();
        initData();
    }

    public void refreshData(){

        mIndex = 1;
        onLoadData(mIndex, page);

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


}
