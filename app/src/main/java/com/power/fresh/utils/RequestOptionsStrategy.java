package com.power.fresh.utils;

import android.annotation.SuppressLint;

import com.bumptech.glide.request.RequestOptions;
import com.power.fresh.R;

/**
 * @author AlienChao
 * @date 2020/04/17 17:12
 */
public class RequestOptionsStrategy   extends RequestOptions {
    @SuppressLint("CheckResult")
    public RequestOptionsStrategy() {
        this.error(R.color.public_color_06CB7E)
                .placeholder(R.color.public_color_06CB7E);
    }
}
