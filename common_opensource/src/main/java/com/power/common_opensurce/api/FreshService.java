package com.power.common_opensurce.api;


import com.chen.concise.Response;
import com.power.common_opensurce.OkHttpInterceptor;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @explain ： 请求分两种格式 。 文档标注了json就用application-json，  其他的就用x-www-form-urlencoded
 *             默认为json  ， 其他类型加 @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
 * @author AlienChao
 * @date 2020/04/24 15:48
 */
public interface FreshService {

    /**
     * 1.1.2 账号检测
     * //true表示账号已存在， 不可注册 | false 表示账号不存在，可以注册
     */
    @GET("user/verify/checkUser")
    Observable<Response<Boolean>> checkUser(@Query("loginName") String loginName);


    /**
     * 1.1.2 账号检测
     * //true表示账号已存在， 不可注册 | false 表示账号不存在，可以注册
     */

    @Headers("format:"+ OkHttpInterceptor.DEFAULT_FORMAT)
    @POST("user/verify/forgetPwd")
    Observable<Response<Integer>> forgetPwd(@Query("username") String username,@Query("verifyCode") String verifyCode,@Query("pwd") String pwd);



    /**
     * 1.23.6物流信息
     */
    @Headers("format:"+OkHttpInterceptor.DEFAULT_FORMAT)
    @POST("user/business/order/add-express")
    Observable<Response<Object>> userBusinessOrderAddExpress(@Query("orderId") int orderId, @Query("info") String info );




}
