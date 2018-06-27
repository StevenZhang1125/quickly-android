package com.zzh.myframework.net;

import retrofit2.Retrofit;

/**
 * <pre>
 *     author : ZhangZhanghua
 *     time   : 2018/04/24
 *     desc   : 创建api工具类
 * </pre>
 */
public class MyApi {
    public static <T> T getApiService(Class<T> cls, String baseUrl) {
        Retrofit retrofit = RetrofitUtils.getRetrofitBuilder(baseUrl).build();
        return retrofit.create(cls);
    }

    public static <T> T getTextApiService(Class<T> cls, String baseUrl) {
        Retrofit retrofit = RetrofitUtils.getTextRetrofitBuilder(baseUrl).build();
        return retrofit.create(cls);
    }
}
