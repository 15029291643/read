package com.example.common.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

/*
* 实现了状态栏透明
* 实现了activity关闭方法
* 实现了setActionBar重写
* */
public class BaseActivity extends AppCompatActivity {
    private static Activity activity;

    public static Activity getActivity() {
        return activity;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        setStatusBar();
    }

    private void setStatusBar() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);  // 透明
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);  // 黑色
    }

    public void setActionBar2(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("");
        }
    }

    public static void toFinish() {
        Activity activity = getActivity();
        if (activity != null) {
            activity.finish();
        }
    }
}
