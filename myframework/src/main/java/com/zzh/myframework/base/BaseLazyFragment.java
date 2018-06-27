package com.zzh.myframework.base;

import android.util.Log;

/**
 * <pre>
 *     author : ZhangZhanghua
 *     time   : 2018/04/13
 *     desc   :
 * </pre>
 */
public abstract class BaseLazyFragment extends BaseFragment {
    private static final String TAG = "BaseLazyFragment";
    private boolean isDataLoaded;

    public abstract void doLazyBusiness();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Log.d(TAG, "setUserVisibleHint: " + isVisibleToUser);
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && mContentView != null && !isDataLoaded) {
            doLazyBusiness();
            isDataLoaded = true;
        }
    }

    @Override
    public void doBusiness() {
        if (getUserVisibleHint()) {
            doLazyBusiness();
            isDataLoaded = true;
        }
    }
}
