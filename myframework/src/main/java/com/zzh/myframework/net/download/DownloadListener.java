package com.zzh.myframework.net.download;

import okhttp3.ResponseBody;

/**
 * <pre>
 *     author : ZhangZhanghua
 *     time   : 2018/04/24
 *     desc   : 文件下载监听
 * </pre>
 */
public interface DownloadListener {

    void onProgress(int progress);

    void onSuccess(ResponseBody responseBody);

    void onFail(String msg);

    void onComplete();
}