package com.example.login.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.common.activity.SecondaryActivity;
import com.example.common.activity.Show;
import com.example.common.constant.ARouterPath;
import com.example.login.R;
import com.example.login.login.forget.Login_ForgetActivity;


public class Login_LoginActivity extends SecondaryActivity {
    private EditText account1;
    private EditText password1;
    private Button login;
    private TextView forget;
    private TextView register;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_login_activity);
        bingView();
        setActionBar2(toolbar);
        login.setOnClickListener(view -> {
            String account = account1.getText().toString();
            String password = password1.getText().toString();
            if (account.length() == 0 || password.length() == 0) {
                return;
            }
            Show.show("登录");
            ARouter.getInstance().build(ARouterPath.Home_MainActivity).navigation();
        });
        register.setOnClickListener(view -> finish());
        forget.setOnClickListener(view -> startActivity(new Intent(this, Login_ForgetActivity.class)));
    }

    private void bingView() {
        account1 = findViewById(R.id.login_login_account);
        password1 = findViewById(R.id.login_login_password);
        login = findViewById(R.id.login_login_login);
        forget = findViewById(R.id.login_login_forget);
        register = findViewById(R.id.login_login_register);
        toolbar = findViewById(R.id.login_login_toolbar);
    }

    /*private void setActionBar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("");
        }
    }*/
}