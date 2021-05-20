package com.power.fresh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.hjq.toast.ToastUtils;
import com.power.fresh.R;
import com.power.fresh.bean.UploadBean;
import com.powerrich.common.base.adapter.BaseRecyclerViewAdapter;
import com.powerrich.common.image.GlideLoad;

public class PhotoAdapter extends BaseRecyclerViewAdapter<UploadBean, PhotoAdapter.PhotoHolder> {

    public PhotoAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public PhotoAdapter.PhotoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PhotoHolder(LayoutInflater.from(getContext()).inflate(R.layout.layout_adapter_item_photo_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoAdapter.PhotoHolder holder, int position) {
        UploadBean uploadBean = getData().get(position);
        ImageView ivContent = (ImageView) holder.findViewById(R.id.iv_content);
        RelativeLayout rlContent = (RelativeLayout) holder.findViewById(R.id.rl_content);
        if (uploadBean.getType() != -1) {
            rlContent.setVisibility(View.VISIBLE);
            GlideLoad.loadImage(ivContent, uploadBean.getUrl());
        } else {
            rlContent.setVisibility(View.GONE);
        }
        rlContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mItemClickListener != null) {
                    mItemClickListener.onItemRemove(position);
                    return;
                }
            }
        });
    }

    public class PhotoHolder extends BaseRecyclerViewAdapter.ViewHolder {

        public PhotoHolder(View itemView) {
            super(itemView);
        }
    }
}
