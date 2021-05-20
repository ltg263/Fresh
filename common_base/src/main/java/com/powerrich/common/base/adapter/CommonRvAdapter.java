package com.powerrich.common.base.adapter;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hjq.common.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenhao
 * @date 2018/7/17
 * 通用的RecyclerView适配器
 */
public abstract class CommonRvAdapter<T> extends RecyclerView.Adapter<CommonViewHolderRv> implements View.OnClickListener {

    private static final int VIEW_TYPE_EMPTY = 0;
    private static final int VIEW_TYPE_ITEM = 1;
    protected List<T> mData;
    protected int mItemLayoutId;
    protected int mMaxCount;
    protected int mBr;
    // RecyclerView 对象
    private RecyclerView mRecyclerView;
    private boolean needEmptyView = true;

    private boolean useDataBind = false;


    private RecyclerView.ItemDecoration mDividerItemDecoration;

    public void setDividerItemDecoration(RecyclerView.ItemDecoration dividerItemDecoration) {
        mDividerItemDecoration = dividerItemDecoration;
    }


    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    public void setNeedEmptyView(boolean needEmptyView) {
        this.needEmptyView = needEmptyView;
    }

    protected Context mContext;


    public CommonRvAdapter(Context context, List<T> mData, int mItemLayoutId) {
        this.mData = mData;
        this.mItemLayoutId = mItemLayoutId;
        this.mContext = context;
    }

    public CommonRvAdapter(Context context, int mDataSize, int mItemLayoutId) {
        this.mData = new ArrayList<>();
        for (int i = 0; i < mDataSize; i++) {
            this.mData.add(null);
        }

        this.mItemLayoutId = mItemLayoutId;
        this.mContext = context;
    }


    public CommonRvAdapter(boolean useDataBind, int br, Context context, List<T> mData, int mItemLayoutId) {
        this.useDataBind = useDataBind;
        this.mBr = br;
        this.mData = mData;
        this.mItemLayoutId = mItemLayoutId;
        this.mContext = context;
    }


    public CommonRvAdapter(Context context, List<T> mData, int mItemLayoutId, int maxCount) {
        this.mData = mData;
        this.mItemLayoutId = mItemLayoutId;
        this.mMaxCount = maxCount;
        this.mContext = context;
    }

    public CommonRvAdapter(boolean useDataBind, int br, Context context, List<T> mData, int mItemLayoutId, int maxCount) {
        this.useDataBind = useDataBind;
        this.mBr = br;
        this.mData = mData;
        this.mItemLayoutId = mItemLayoutId;
        this.mMaxCount = maxCount;
        this.mContext = context;
    }


    public void removeItem(int position) {
        if (position <= mData.size() - 1) {
            mData.remove(position);
            //告诉适配器删除数据的位置，会有动画效果
            notifyItemRemoved(position);
//           // notifyDataSetChanged();
//            notifyItemRangeChanged(position, mData.size()-position);
        }
    }

    public void addItem(int position, T item) {
        if (mData == null) {
            mData = new ArrayList<>();
        }

        if (position < mData.size()) {
            mData.add(position, item);
        } else {
            mData.add(item);
            position = mData.size() - 1;
        }
        notifyItemInserted(position);
    }


    public void setData(List<T> data) {
        mData = data;

        notifyDataSetChanged();
    }

    public List<T> getData() {
        return mData;
    }

    @Override
    public CommonViewHolderRv onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //在这里根据不同的viewType进行引入不同的布局
        if (viewType == VIEW_TYPE_EMPTY && needEmptyView) {
            View emptyView = LayoutInflater.from(parent.getContext()).inflate(R.layout.public_empty_view, parent, false);
            return CommonViewHolderRv.getHolder(emptyView);
        }

        if (useDataBind) {
            ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), mItemLayoutId, parent, false);
            if (mItemClickListener != null) {
                binding.getRoot().setOnClickListener(this);
            }
            return CommonViewHolderRv.getHolder(binding);
        }

        //其他的引入正常的
        View view = LayoutInflater.from(parent.getContext()).inflate(mItemLayoutId, parent, false);

        /** 为了防止未知，暂时隐藏 */
        //  if (mItemClickListener!=null) {
        //设置item点击事件
        view.setOnClickListener(this);
        //    }

        //实例化一个ViewHolder
        return CommonViewHolderRv.getHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommonViewHolderRv holder, int position) {

        if (holder.getBinding() != null && mData != null && mData.size() > 0) {
            holder.getBinding().setVariable(mBr, mData.get(position));
            holder.itemView.setTag(position);
            convert(holder, mData.get(position), position);
        } else if (mData != null && mData.size() > 0) {
            holder.itemView.setTag(position);
            convert(holder, mData.get(position), position);
        }
    }


    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {


        mRecyclerView = recyclerView;

        if (mDividerItemDecoration != null) {
            if (mRecyclerView.getItemDecorationCount() < 1) {
                mRecyclerView.addItemDecoration(mDividerItemDecoration);
            }
        }

        //判断当前的布局管理器是否为空，如果为空则设置默认的布局管理器
        if (mRecyclerView.getLayoutManager() == null) {
            RecyclerView.LayoutManager manager = getDefaultLayoutManager(mContext);
            if (manager != null) {
                mRecyclerView.setLayoutManager(manager);
            }
        }
    }

    /**
     * 获取默认的布局摆放器
     */
    protected RecyclerView.LayoutManager getDefaultLayoutManager(Context context) {
        return new LinearLayoutManager(context);
    }


    @Override
    public int getItemCount() {
        //同时这里也需要添加判断，如果mData.size()为0的话，只引入一个布局，就是emptyView
        // 那么，这个recyclerView的itemCount为1
        if (needEmptyView) {
            if (mData == null || mData.size() == 0) {
                return 1;
            }
        }

        if (mData != null && mMaxCount > 0 && mData.size() > mMaxCount) {
            return mMaxCount;
        }

        //如果不为0，按正常的流程跑
        return mData == null ? 0 : mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        //在这里进行判断，如果我们的集合的长度为0时，我们就使用emptyView的布局
        if (needEmptyView) {
            if (mData == null || mData.size() == 0) {
                return VIEW_TYPE_EMPTY;
            }
        }
        //如果有数据，则使用ITEM的布局
        return VIEW_TYPE_ITEM;
    }

    public T getItem(int position) {
        return mData == null ? null : mData.get(position);
    }

    /**
     * 对外公布了一个convert方法，并且还把ViewHolder和本item对应的Bean对象给传出去
     * 现在convert方法里面需要干嘛呢？通过ViewHolder把View找到，通过Item设置值
     */
    public abstract void convert(CommonViewHolderRv holder, T item, int position);

    /**
     * 设置点击事件回调
     */
    @Override
    public void onClick(View v) {

        if (null != mItemClickListener) {
            if (v.getTag() != null) {
                mItemClickListener.onItemClick((Integer) v.getTag());
            }
        }
    }

    private OnItemClickListener mItemClickListener;

    private ItemEditGetInfoListener mItemEditGetInfoListener;

    public void setItemEditGetInfoListener(ItemEditGetInfoListener itemEditGetInfoListener) {
        mItemEditGetInfoListener = itemEditGetInfoListener;
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public interface ItemEditGetInfoListener {
        void onItemClick(int oldPosition, int newPosition);
    }


}
