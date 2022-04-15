package com.example.common.activity;

import android.view.KeyEvent;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;

/*
* 不允许直接返回
* */
public class PrimaryActivity extends BaseActivity {
    private FragmentManager manager = getSupportFragmentManager();
    private long firstTime;// 记录点击返回时第一次的时间毫秒值

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {// 点击了返回按键
            if (manager.getBackStackEntryCount() != 0) {
                manager.popBackStack();
            } else {
                exitApp(2000);// 退出应用
            }
            return true;// 返回true，防止该事件继续向下传播
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exitApp(long timeInterval) {
        if (System.currentTimeMillis() - firstTime >= timeInterval) {
            Toast.makeText(this, "再按一次退出[金蛋小说]", Toast.LENGTH_SHORT).show();
            firstTime = System.currentTimeMillis();
        } else {
            finish();// 销毁当前activity
            System.exit(0);// 完全退出应用
        }
    }
}
