package com.android.library;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDexApplication;

public class MyApplication extends MultiDexApplication {

    private static Context mAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = getApplicationContext();
    }

    /**
     * 获取系统上下文
     */
    public static Context getAppContext() {
        return mAppContext;
    }

}