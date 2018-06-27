package com.zzh.myframework.net.entity;

/**
 * <pre>
 *     author : ZhangZhanghua
 *     time   : 2018/04/24
 *     desc   : 服务端返回数据基类
 * </pre>
 */
public class BaseResponse<T> {
    public String code;
    public String msg;
    public T data;
}
