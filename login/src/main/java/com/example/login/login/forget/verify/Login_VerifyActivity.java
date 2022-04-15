package com.example.login.login.forget.verify;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.common.activity.SecondaryActivity;
import com.example.login.R;
import com.example.login.login.forget.verify.newpassword.Login_NewPasswordActivity;

public class Login_VerifyActivity extends SecondaryActivity {
    private EditText account;
    private Button button;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_verify_activity);
        bindView();
        setActionBar2(toolbar);
        button.setOnClickListener(view -> {
            startActivity(new Intent(this, Login_NewPasswordActivity.class));
        });
    }

    private void bindView() {
        account = findViewById(R.id.login_verify_account);
        button = findViewById(R.id.login_verify_button);
        toolbar = findViewById(R.id.login_verify_toolbar);
    }
}