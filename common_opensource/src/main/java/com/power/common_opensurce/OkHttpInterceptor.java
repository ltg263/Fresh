package com.power.common_opensurce;

import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;


import com.powerrich.common.helper.SPUtils;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import butterknife.internal.Utils;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * @author AlienChao
 * @date 2019/03/06 17:57
 */
public class OkHttpInterceptor implements Interceptor {

    private static final MediaType JSON = MediaType.parse("application/json;charset=utf-8");
    public static final String DEFAULT_FORMAT = "application/x-www-form-urlencoded";

//    private OkHttpInterceptor() {
//
//    }
//    private static OkHttpInterceptor mLotteryOAInterceptor;
//
//    public static OkHttpInterceptor getInstance() {
//        if (null == mLotteryOAInterceptor) {
//            mLotteryOAInterceptor = new OkHttpInterceptor();
//        }
//        return mLotteryOAInterceptor;
//    }


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String format = request.header("format");

        HttpUrl httpUrl = request.url();
        Request.Builder requestBuilder = request.newBuilder();

        String token= (String) SPUtils.get("token","token");
        Log.e("jsc", "OkHttpInterceptor-intercept:");


        if ((format!=null&&format.equals(DEFAULT_FORMAT)) |request.method().equals("DELETE")) {
            Response originalResponse = chain.proceed(requestBuilder.addHeader("App-Token",token).build());
            return originalResponse;
        }



        Set<String> queryList = httpUrl.queryParameterNames();
        HttpUrl.Builder httpUrlBuilder = httpUrl.newBuilder();
        Iterator<String> iterator = queryList.iterator();
        Map<String, String> map = new HashMap<>();
        Log.e("jsc", "OkHttpInterceptor-intercept:" + httpUrl.url().getPath());
        //获取到请求地址api
        HttpUrl httpUrlurl = request.url();
        String url = httpUrlurl.toString();

        if ("GET".equals(request.method())) {
//            String path = request.url().toString();
//            if (path.contains("?")) {
//                String newPath = path.substring(0, path.indexOf("?"));
//                String url = newPath + "&clientType=3";
//
//                requestBuilder.get().url(url);
//            }
            /**  */

            HashMap<String, Object> rootMap = new HashMap<>();
            //通过请求地址(最初始的请求地址)获取到参数列表
            Set<String> parameterNames = httpUrlurl.queryParameterNames();
            for (String key : parameterNames) {  //循环参数列表
                rootMap.put(key, httpUrlurl.queryParameter(key));
            }
            rootMap.put("clientType", 3);
//            String newJsonParams = App.getmGson().toJson(rootMap);  //装换成json字符串
//

            int index = url.indexOf("?");
            if (index > 0) {
                url = url + "&clientType=3";
            } else {
                url = url + "?clientType=3";
            }

            requestBuilder.addHeader("App-Token",token).url(url).build();  //重新构建请求


        } else {
            // 构造新的请求表单
//            FormBody.Builder formBodyBuiler = new FormBody.Builder();
//
//            for (Map.Entry<String, String> entry : map.entrySet()) {
//                formBodyBuiler.add(entry.getKey(), entry.getValue());
//            }
//            formBodyBuiler.add("clientType", "3");
//
//            requestBuilder
//                    .url(httpUrlBuilder.build())
//                    .post(formBodyBuiler.build());

            /**  */

            String newUrl;

            if (url.indexOf("?") > 0) {
                newUrl = url.substring(0, url.indexOf("?"));
            } else {
                newUrl = url;
            }


            RequestBody requestBody = request.body();

            //body 的拆分
            String bodyToString = requestBodyToString(requestBody);
            Map paramsMap = App.getmGson().fromJson(bodyToString, Map.class);
            if (null == paramsMap) {
                paramsMap = new HashMap();
            }
            paramsMap.put("clientType", 3);

            Set<String> parameterNames = httpUrl.queryParameterNames();
            //循环参数列表,添加至map中
            for (String key : parameterNames) {
                paramsMap.put(key, httpUrl.queryParameter(key));
            }


            //装换成json字符串
            String newJsonParams = App.getmGson().toJson(paramsMap);


            requestBuilder.url(newUrl).addHeader("App-Token",token).post(RequestBody.create(JSON, newJsonParams));


//            HashMap<String, Object> rootMap = new HashMap<>();
//            if (requestBody instanceof FormBody) {
//                for (int i = 0; i < ((FormBody) requestBody).size(); i++) {
//                    rootMap.put(((FormBody) requestBody).encodedName(i), ((FormBody) requestBody).encodedValue(i));
//                }
//                rootMap.put("clientType", 3);
//            } else {
//                //buffer流
//                Buffer buffer = new Buffer();
//                requestBody.writeTo(buffer);
//                String oldParamsJson = buffer.readUtf8();
//                rootMap = App.getmGson().fromJson(oldParamsJson, HashMap.class);  //原始参数
//                rootMap.put("clientType", 3);  //重新组装
//                String newJsonParams =  App.getmGson().toJson(rootMap);  //装换成json字符串
//                requestBuilder.post(RequestBody.create(JSON, newJsonParams));
//            }


        }


        Response originalResponse = chain.proceed(requestBuilder.build());
        return originalResponse;
    }


    private static String requestBodyToString(RequestBody requestBody) {
        Buffer buffer = new Buffer();
        try {
            requestBody.writeTo(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer.readUtf8();
    }


}
