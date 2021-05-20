package com.power.fresh.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.power.fresh.R;
import com.power.fresh.bean.home.HomeBanner;
import com.powerrich.common.helper.GlideRoundTransform;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.util.BannerUtils;

import java.util.List;

/**
 * @author AlienChao
 * @date 2020/04/17 16:06
 */
public class BannerImageAdapter  extends BannerAdapter<HomeBanner.ListBean,BannerImageAdapter.ImageHolder> {
    public BannerImageAdapter(List<HomeBanner.ListBean> mDatas) {
        super(mDatas);
    }

    @Override
    public ImageHolder onCreateHolder(ViewGroup parent, int viewType) {
        ImageView imageView = (ImageView) BannerUtils.getView(parent, R.layout.banner_image);
        return new ImageHolder(imageView);
    }

    @Override
    public void onBindView(ImageHolder holder, HomeBanner.ListBean data, int position, int size) {

//        Glide.with(holder.itemView)
//                .load(data.imageUrl)
//                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
//                .into(holder.imageView);

        Glide.with(holder.itemView.getContext())
                .load(data.getImgUrl())
                .apply(new RequestOptions()
                        .placeholder(R.mipmap.app_icon)
                        .error(R.mipmap.app_icon)
                        .transform(new GlideRoundTransform(holder.itemView.getContext(), 5)))
                .into(holder.imageView);
//        //设置图片圆角角度
//        RoundedCorners roundedCorners = new RoundedCorners(20);
//        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners).override(0, 0);
//        Glide.with(holder.itemView.getContext()).load(data.imageUrl).apply(options).into(holder.imageView);



    }

    public class ImageHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public ImageHolder(@NonNull View view) {
            super(view);
            this.imageView = (ImageView) view;
        }
    }

}
