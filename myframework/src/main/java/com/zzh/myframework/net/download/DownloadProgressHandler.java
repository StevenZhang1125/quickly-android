package com.zzh.myframework.net.download;

import android.os.Looper;
import android.os.Message;

/**
 * <pre>
 *     author : ZhangZhanghua
 *     time   : 2018/04/24
 *     desc   : 下载进度handler
 * </pre>
 */
public abstract class DownloadProgressHandler extends ProgressHandler {
    private static final int DOWNLOAD_PROGRESS = 1;

    ResponseHandler mHandler = new ResponseHandler(this, Looper.getMainLooper());

    @Override
    protected void sendMessage(ProgressBean progressBean) {
        mHandler.obtainMessage(DOWNLOAD_PROGRESS, progressBean).sendToTarget();
    }

    @Override
    protected void handleMessage(Message message) {
        switch (message.what) {
            case DOWNLOAD_PROGRESS:
                ProgressBean progressBean = (ProgressBean) message.obj;
                onProgress(progressBean.bytesRead, progressBean.contentLength, progressBean.done);
        }
    }
}
