package com.example.login.login.forget.verify.newpassword.succeed;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.Button;


import com.example.common.activity.BaseActivity;
import com.example.common.activity.SecondaryActivity;
import com.example.login.R;

public class Login_SucceedActivity extends SecondaryActivity {
    private Button back;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_succeed_activity);
        bindView();
        setActionBar2(toolbar);
    }

    private void bindView() {
        back = findViewById(R.id.login_succeed_back);
        toolbar = findViewById(R.id.login_succeed_toolbar);
    }
}