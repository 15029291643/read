package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.common.activity.SecondaryActivity;
import com.example.common.constant.ARouterPath;
import com.example.login.login.Login_LoginActivity;

@Route(path = ARouterPath.Login_MainActivity)
public class Login_MainActivity extends SecondaryActivity {
    private Button login;
    private EditText account;
    private EditText password;
    private TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main_activity);
        bindView();

        login.setOnClickListener(view -> {
            ARouter.getInstance().build(ARouterPath.Home_MainActivity).navigation();
        });
        register.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), Login_LoginActivity.class)));
    }

    private void bindView() {
        login = findViewById(R.id.login_main_login);
        account = findViewById(R.id.login_main_account);
        password = findViewById(R.id.login_main_password);
        register = findViewById(R.id.login_main_register);
    }
}