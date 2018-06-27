package com.zzh.myframework.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.zzh.myframework.util.LogUtils;

/**
 * <pre>
 *     author : ZhangZhanghua
 *     time   : 2018/04/20
 *     desc   : activity基类
 * </pre>
 */
public abstract class BaseActivity extends RxAppCompatActivity implements IBaseView {
    protected String TAG;
    private boolean printLifeCycle = false;
    protected View mContentView;
    protected Context mContext;
    /**
     * 上次点击时间
     */
    private long lastClick = 0L;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getClass().getSimpleName();
        if (printLifeCycle) LogUtils.dTag(TAG, "onCreate");
        mContext = this;
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            initData(bundle);
        }
        beforeInitView();
        setBaseView(bindLayout());
        initView(savedInstanceState, mContentView);
        doBusiness();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (printLifeCycle) LogUtils.dTag(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (printLifeCycle) LogUtils.dTag(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (printLifeCycle) LogUtils.dTag(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (printLifeCycle) LogUtils.dTag(TAG, "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (printLifeCycle) LogUtils.dTag(TAG, "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (printLifeCycle) LogUtils.dTag(TAG, "onDestroy");
    }

    /**
     * 绑定布局之前的操作
     */
    protected void beforeInitView() {

    }

    protected void setBaseView(@LayoutRes int layoutId) {
        if (layoutId <= 0) return;
        setContentView(mContentView = LayoutInflater.from(this).inflate(layoutId, null));
    }

    private boolean isFastClick() {
        long now = System.currentTimeMillis();
        if (now - lastClick >= 200) {
            lastClick = now;
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        if (!isFastClick()) onWidgetClick(v);
    }


}