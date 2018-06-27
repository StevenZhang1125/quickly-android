package com.zzh.myframework;

/**
 * <pre>
 *     author : ZhangZhanghua
 *     time   : 2018/04/24
 *     desc   : 常量
 * </pre>
 */
public interface Constants {
    /**
     * 默认超时时间
     */
    int DEFAULT_TIMEOUT = 40 * 1000;

    //exception code

    /**
     * 连接错误
     */
    int CONNECT_ERROR = 1001;
    /**
     * 连接超时
     */
    int CONNECT_TIMEOUT = 1002;
    /**
     * 网络问题
     */
    int BAD_NETWORK = 1003;
    /**
     * 解析数据失败
     */
    int PARSE_ERROR = 1004;
    /**
     * 未知错误
     */
    int UNKNOWN_ERROR = 1005;
}
