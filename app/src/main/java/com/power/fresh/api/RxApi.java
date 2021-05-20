package com.power.fresh.api;

import com.chen.concise.RxHttp;

import static com.chen.concise.RxHttp.BaseUrl;

/**
 * @author AlienChao
 * @date 2020/04/24 15:50
 */
public class RxApi {


    public static FreshService getDefaultService() {
        return RxHttp.getInstance().getRetrofit(BaseUrl).create(FreshService.class);
    }
}
