package com.zzh.myframework.net.exception;

/**
 * <pre>
 *     author : ZhangZhanghua
 *     time   : 2018/04/24
 *     desc   :
 * </pre>
 */
public class ServerResponseException extends RuntimeException {
    public String errorCode;
    public String errorMsg;

    public ServerResponseException(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}
