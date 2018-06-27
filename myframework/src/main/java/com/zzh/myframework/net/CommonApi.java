package com.zzh.myframework.net;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * <pre>
 *     author : ZhangZhanghua
 *     time   : 2018/04/24
 *     desc   : 文件下载api
 * </pre>
 */
public interface CommonApi {

    @Streaming
    @GET
    Observable<ResponseBody> download(@Url String url);
}
