package com.powerrich.common.base.adapter;

import android.graphics.Bitmap;

import android.util.SparseArray;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.powerrich.common.image.GlideLoad;


/**
 * @author chenhao
 * @date 2018/7/17
 * RecyclerView通用的ViewHolder
 */
public class CommonViewHolderRv extends RecyclerView.ViewHolder{

    private SparseArray<View> mViews = new SparseArray<>();
    private View mConvertView;
    private ViewDataBinding binding;
    //构造函数
    private CommonViewHolderRv(View itemView) {
        super(itemView);
        this.mConvertView = itemView;
     //   AutoUtils.auto(mConvertView);
    }


    public ViewDataBinding getBinding() {
        return binding;
    }


    //构造函数
    private CommonViewHolderRv(ViewDataBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
        this.mConvertView = binding.getRoot();
        //   AutoUtils.auto(mConvertView);
    }

    //获取一个ViewHolder
    public static CommonViewHolderRv getHolder(View view) {
        CommonViewHolderRv holder = new CommonViewHolderRv(view);
        return holder;
    }

    //获取一个ViewHolder
    public static CommonViewHolderRv getHolder(ViewDataBinding binding) {

        CommonViewHolderRv holder = new CommonViewHolderRv(binding);
        return holder;
    }

    //通过控件的id获取对应的控件，如果没有则加入mViews;记住 <T extends View> T 这种用法
    public <T extends View> T getItemView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }



    public <T extends EditText> T getEditTextView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        EditText editText = (T) view;
       // editText.addTextChangedListener();
        return (T) view;
    }


    /**
     * 为TextView赋值
     */
    public void setText(int viewId, String text) {
        TextView view = getItemView(viewId);
        view.setText(text);
    }

    /**
     * 为TextView赋值
     *  notice： 没有直接从R.String 取
     */
    public void setText(int viewId, int text) {
        TextView view = getItemView(viewId);
        view.setText(String.valueOf(text));
    }

    public void setText(int viewId, Object text) {
        TextView view = getItemView(viewId);
        view.setText(String.valueOf(text));
    }

    /**
     * 为TextView赋值
     */
    public void setTextColor(int viewId, int resoutId) {
        TextView view = getItemView(viewId);
        view.setTextColor(ContextCompat.getColor(mConvertView.getContext(),resoutId));
    }

    /**
     * 为ImageView赋值——drawableId
     */
    public void setImageResource(int viewId, int drawableId) {
        ImageView view = getItemView(viewId);
        view.setImageResource(drawableId);
    }

    /**
     * 为ImageView赋值——drawableId
     */
    public void setImageUrl(int viewId, String url) {
        ImageView view = getItemView(viewId);
        GlideLoad.loadImage(view,url);
    }
    /**
     * 为ImageView赋值——drawableId
     */
    public void setImageUrl(int viewId, String url,int round) {
        ImageView view = getItemView(viewId);
        GlideLoad.loadImage(view,url,round);
    }



    /**
     * 为ImageView赋值——bitmap
     */
    public void setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView view = getItemView(viewId);
        view.setImageBitmap(bitmap);
    }


    /**
     * 为ImageView赋值——bitmap
     */
    public void setViewClickListener(int viewId, View.OnClickListener l) {
        View itemView = getItemView(viewId);
        itemView.setOnClickListener(l);
    }


}
