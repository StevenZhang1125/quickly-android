package com.zzh.myframework.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * <pre>
 *     author : ZhangZhanghua
 *     time   : 2018/04/11
 *     desc   :
 * </pre>
 */
interface IBaseView extends View.OnClickListener {

    /**
     * 绑定布局
     *
     * @return 布局Id
     */
    @LayoutRes
    int bindLayout();

    /**
     * 初始化数据
     *
     * @param bundle 传递过来的bundle
     */
    void initData(@NonNull final Bundle bundle);

    /**
     * 初始化 view
     */
    void initView(final Bundle savedInstanceState, final View contentView);

    /**
     * 业务操作
     */
    void doBusiness();

    /**
     * 视图点击事件
     *
     * @param view 视图
     */
    void onWidgetClick(final View view);
}
