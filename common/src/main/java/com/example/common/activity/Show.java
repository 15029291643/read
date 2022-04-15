package com.example.common.activity;

import android.util.Log;
import android.widget.Toast;

import com.example.common.activity.BaseApplication;

public class Show {
    private static final String TAG = "show";
    private static long lastTime;
    private static long firstTime;

    public static void log(String s) {
        Log.e(TAG, "log: " + s);
    }

    public static void toast(String s) {
        Toast.makeText(BaseApplication.getContext(), s, Toast.LENGTH_SHORT).show();
    }

    public static void show(String s) {
        log(s);
        toast(s);
    }

    private static long getTime() {
        return System.currentTimeMillis();
    }

    public static void getMistiming(String s) {
        if (lastTime == 0) {
            lastTime = getTime();
            firstTime = lastTime;
            log(s + ": 0");
            return;
        }
        log(s + ": " + (getTime() - lastTime));
    }

    public static void getAllTime() {
        show("总耗时: " + (getTime() - firstTime));
    }
}
