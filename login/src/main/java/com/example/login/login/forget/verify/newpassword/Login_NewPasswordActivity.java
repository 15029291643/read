package com.example.login.login.forget.verify.newpassword;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.common.activity.SecondaryActivity;
import com.example.login.R;
import com.example.login.login.forget.verify.newpassword.succeed.Login_SucceedActivity;

public class Login_NewPasswordActivity extends SecondaryActivity {
    private EditText password3;
    private EditText password4;
    private Button button;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_new_password_activity);
        bindView();
        setActionBar2(toolbar);
        button.setOnClickListener(view -> {
            String password = password3.getText().toString();
            String password2 = password4.getText().toString();
            /*if (password.length() == 0 || password2.length() == 0) {
                return;
            }*/
            startActivity(new Intent(Login_NewPasswordActivity.this, Login_SucceedActivity.class));
        });
    }

    private void bindView() {
        password3= findViewById(R.id.login_new_password_password);
        password4= findViewById(R.id.login_new_password_password2);
        button= findViewById(R.id.login_new_password_verify);
        toolbar= findViewById(R.id.login_new_password_toolbar);
    }
}