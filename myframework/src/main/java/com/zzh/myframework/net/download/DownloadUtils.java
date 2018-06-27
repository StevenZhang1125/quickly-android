package com.zzh.myframework.net.download;

import android.support.annotation.NonNull;

import com.zzh.myframework.net.CommonApi;
import com.zzh.myframework.net.RetrofitUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;

/**
 * <pre>
 *     author : ZhangZhanghua
 *     time   : 2018/04/24
 *     desc   : 文件下载工具类
 * </pre>
 */
public class DownloadUtils {
    private static final String TAG = "DownLoadUtils";
    private DownloadListener mDownloadListener;
    private CompositeDisposable mDisposable;

    public DownloadUtils() {
        mDisposable = new CompositeDisposable();
    }

    public void download(@NonNull String url, DownloadListener downloadListener) {
        mDownloadListener = downloadListener;

    }

    public void cancelDownload() {
        mDisposable.clear();
    }

    private CommonApi geteApi() {
        OkHttpClient.Builder okClientBuilder = RetrofitUtils.getOkhttpClientBuilder();
        ProgressHelper.addProgress(okClientBuilder);
        CommonApi api = RetrofitUtils.getRetrofitBuilder("")
                .client(okClientBuilder.build())
                .build()
                .create(CommonApi.class);
        ProgressHelper.setmProgressHandler(new DownloadProgressHandler() {
            @Override
            protected void onProgress(long progress, long total, boolean done) {
                mDownloadListener.onProgress((int) ((100 * progress) / total));
            }
        });
        return api;
    }

    private Consumer<ResponseBody> getComsumer() {
        return new Consumer<ResponseBody>() {
            @Override
            public void accept(ResponseBody responseBody) throws Exception {
                mDownloadListener.onSuccess(responseBody);
            }
        };
    }

    private Observer<ResponseBody> getObserver() {
        return new Observer<ResponseBody>() {
            @Override
            public void onSubscribe(Disposable d) {
                mDisposable.add(d);
            }

            @Override
            public void onNext(ResponseBody responseBody) {

            }

            @Override
            public void onError(Throwable e) {
                mDownloadListener.onFail(e.getMessage());
                mDisposable.clear();
            }

            @Override
            public void onComplete() {
                mDownloadListener.onComplete();
                mDisposable.clear();
            }
        };
    }
}