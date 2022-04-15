package com.example.common.activity;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.common.BuildConfig;

public class BaseApplication extends Application {
    @SuppressLint("StaticFieldLeak")
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();
        // ARouter
        if (isDebug()) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }

    private boolean isDebug() {
        return BuildConfig.isDebug;
    }

    public static Context getContext() {
        return context;
    }
}
