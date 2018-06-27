package com.zzh.myframework.net.download;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * <pre>
 *     author : ZhangZhanghua
 *     time   : 2018/04/24
 *     desc   : 进度handler
 * </pre>
 */
public abstract class ProgressHandler {

    protected abstract void sendMessage(ProgressBean progressBean);

    protected abstract void handleMessage(Message message);

    protected abstract void onProgress(long progress, long total, boolean done);

    protected static class ResponseHandler extends Handler {
        private ProgressHandler mProgressHandler;

        ResponseHandler(ProgressHandler progressHandler, Looper looper) {
            super(looper);
            this.mProgressHandler = progressHandler;
        }

        @Override
        public void handleMessage(Message msg) {
            mProgressHandler.handleMessage(msg);
        }
    }
}
