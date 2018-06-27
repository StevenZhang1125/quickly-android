package com.zzh.myframework.net.download;

/**
 * <pre>
 *     author : ZhangZhanghua
 *     time   : 2018/04/24
 *     desc   : 进度监听
 * </pre>
 */
public interface ProgressListener {

    void onProgress(long progress, long total, boolean done);
}
