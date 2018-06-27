package com.zzh.myframework;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.annotation.StringRes;

import com.zzh.myframework.util.Utils;

import java.util.List;

/**
 * <pre>
 *     author : ZhangZhanghua
 *     time   : 2018/04/20
 *     desc   : application基类
 * </pre>
 */
public class MyApplication extends Application {
    protected static String TAG;

    @Override
    public void onCreate() {
        super.onCreate();
        TAG = getClass().getSimpleName();
        Utils.init(getAppContext());

    }

    /**
     * 从配置资源获取字符串
     *
     * @param resId 资源id
     * @return 字符串
     */
    public static String getResString(@StringRes int resId) {
        Context context = getAppContext();
        if (null == context) {
            return null;
        }
        return context.getResources().getString(resId);
    }

    /**
     * 获取应用名称
     * <p>框架层无法直接获取应用层的String资源，因此在框架需要应用名称的时候可以通过此方法获取</p>
     *
     * @return String 应用名称
     */
    public static String getApplicationName() {
        PackageManager packageManager = null;
        ApplicationInfo applicationInfo;
        try {
            packageManager = getAppContext().getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(getAppContext().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            applicationInfo = null;
        }
        return (String) packageManager.getApplicationLabel(applicationInfo);
    }

    /**
     * 通过反射获取AppContext，避免直接使用static context
     *
     * @return appContext
     */
    public static Context getAppContext() {
        Application application = null;
        try {
            application = (Application) Class.forName("android.app.ActivityThread")
                    .getMethod("currentApplication")
                    .invoke(null, (Object[]) null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return application;
    }

    /**
     * 检查App是否处于前台
     *
     * @return {@code true}: 处于前台 {@code false}: 不处于前台
     */
    public static boolean isAppForeground() {
        Context context = getAppContext();
        if (null == context) {
            return false;
        }
        ActivityManager activityManager =
                (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses =
                activityManager.getRunningAppProcesses();
        if (null == appProcesses) {
            return false;
        }
        final String packageName = context.getPackageName();
        for (ActivityManager.RunningAppProcessInfo appProcessInfo : appProcesses) {
            if (!appProcessInfo.processName.equals(packageName)) continue;
            int foreground = ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND;
            if (appProcessInfo.importance == foreground) return true;
        }
        return false;
    }
}