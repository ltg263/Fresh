package com.power.fresh.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.powerrich.common.image.GlideLoad;

/**
 * @author AlienChao
 * @date 2020/05/29 15:41
 */

public class ImageViewAttrAdapter {

//    @BindingAdapter({"app:imageUrl"})
//    public static void loadImage(ImageView imageView, String url) {
//        GlideLoad.loadImage(imageView,url);
//    }

    @BindingAdapter(value = {"android:imageUrl", "android:placeHolder", "android:error"}, requireAll = false)
    public static void loadImage(ImageView view, String url, Drawable error, Drawable placeHolder) {
        Glide.with(view.getContext()).load(url).into(view);
    }
}
