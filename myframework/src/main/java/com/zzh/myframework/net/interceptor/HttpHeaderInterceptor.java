package com.zzh.myframework.net.interceptor;

import com.zzh.myframework.MyApplication;
import com.zzh.myframework.util.SharedPreferencesHelper;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * <pre>
 *     author : ZhangZhanghua
 *     time   : 2018/04/24
 *     desc   : 自定义http请求头
 * </pre>
 */
public class HttpHeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        String token = (String) SharedPreferencesHelper.get(MyApplication.getAppContext(), "token", "");
        String username = (String) SharedPreferencesHelper.get(MyApplication.getAppContext(), "username", "");
        Request request = chain.request().newBuilder()
                .header("token", token)
                .header("username", username)
                .header("Content-Type", "application/json")
                .addHeader("Connection", "close")
                .addHeader("Accept-Encoding", "identity")
                .build();

        return chain.proceed(request);
    }
}