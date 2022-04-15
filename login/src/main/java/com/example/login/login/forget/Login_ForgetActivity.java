package com.example.login.login.forget;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import com.example.common.activity.SecondaryActivity;
import com.example.common.activity.Show;
import com.example.login.R;
import com.example.login.login.forget.verify.Login_VerifyActivity;

public class Login_ForgetActivity extends SecondaryActivity {
    private EditText account;
    private Button affirm;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_forget_activity);
        bindView();
        setActionBar2(toolbar);
        affirm.setOnClickListener(view -> {
            String account1 = account.getText().toString();
            /*if (account1.length() == 0) {
                return;
            }*/
            startActivity(new Intent(this, Login_VerifyActivity.class));
        });
    }

    private void bindView() {
        account = findViewById(R.id.login_forget_account);
        affirm = findViewById(R.id.login_forget_affirm);
        toolbar = findViewById(R.id.login_forget_toolbar);
    }
}