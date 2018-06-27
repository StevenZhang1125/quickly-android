package com.zzh.myframework.net.download;

import com.zzh.myframework.util.LogUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * <pre>
 *     author : ZhangZhanghua
 *     time   : 2018/04/24
 *     desc   : 进度帮助类
 * </pre>
 */
public class ProgressHelper {

    private static ProgressBean progressBean = new ProgressBean();
    private static ProgressHandler mProgressHandler;

    public static OkHttpClient.Builder addProgress(OkHttpClient.Builder okBuilder) {
        if (okBuilder == null) {
            okBuilder = new OkHttpClient.Builder();
        }

        final ProgressListener progressListener = new ProgressListener() {
            @Override
            public void onProgress(long progress, long total, boolean done) {
                LogUtils.dTag("download--progress", String.format("%d%% done\n", (100 * progress) / total));
                if (mProgressHandler == null) return;

                progressBean.bytesRead = progress;
                progressBean.contentLength = total;
                progressBean.done = done;
                mProgressHandler.sendMessage(progressBean);
            }
        };

        okBuilder.networkInterceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response = chain.proceed(chain.request());
                return response.newBuilder().body(new ProgressResponseBody(response.body(), progressListener)).build();
            }
        });

        return okBuilder;
    }

    public static void setmProgressHandler(ProgressHandler progressHandler) {
        mProgressHandler = progressHandler;
    }
}
