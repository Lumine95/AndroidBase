package com.android.base;

import android.app.Application;

import com.android.library.utils.U;

/**
 * Created by ZMM on 2018/3/2.
 */

public class TestApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        U.init(this);
    }
}
