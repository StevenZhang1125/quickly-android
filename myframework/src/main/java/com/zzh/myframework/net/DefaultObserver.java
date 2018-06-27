package com.zzh.myframework.net;

import com.google.gson.JsonParseException;
import com.zzh.myframework.Constants;
import com.zzh.myframework.R;
import com.zzh.myframework.net.exception.ServerResponseException;
import com.zzh.myframework.util.ActivityUtils;
import com.zzh.myframework.util.LogUtils;
import com.zzh.myframework.util.ToastUtils;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.ParseException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * <pre>
 *     author : ZhangZhanghua
 *     time   : 2018/04/24
 *     desc   : 自定义observer
 * </pre>
 */
public abstract class DefaultObserver<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        LogUtils.eTag("retrofit", e.getMessage());
        if (e instanceof HttpException) {
            onException(Constants.BAD_NETWORK);
        } else if (e instanceof ConnectException || e instanceof UnknownHostException) {
            onException(Constants.CONNECT_ERROR);
        } else if (e instanceof InterruptedIOException) {
            onException(Constants.CONNECT_TIMEOUT);
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            onException(Constants.PARSE_ERROR);
        } else if (e instanceof ServerResponseException) {
            ServerResponseException exception = (ServerResponseException) e;
            onFail(exception.errorCode, exception.errorMsg);
        } else {
            onException(Constants.UNKNOWN_ERROR);
        }
    }

    @Override
    public void onComplete() {

    }

    /**
     * 请求成功
     *
     * @param response 服务端返回数据
     */
    public abstract void onSuccess(T response);

    public void onFail(String code, String message) {
        ToastUtils.showShort("错误码：" + code + ",错误信息：" + message);
    }

    private void onException(int exceptionCode) {
        switch (exceptionCode) {
            case Constants.CONNECT_ERROR:
                ToastUtils.showShort(R.string.connect_error);
                break;
            case Constants.CONNECT_TIMEOUT:
                ToastUtils.showShort(R.string.connect_timeout);
                break;
            case Constants.BAD_NETWORK:
                ToastUtils.showShort(R.string.bad_network);
                break;
            case Constants.PARSE_ERROR:
                ToastUtils.showShort(R.string.parse_error);
                break;
            case Constants.UNKNOWN_ERROR:
                ToastUtils.showShort(R.string.unknown_error);
                break;
        }
    }
}