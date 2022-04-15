package com.example.myapplication;


import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.example.common.activity.BaseActivity;
import com.example.common.activity.Show;
import com.example.common.callback.IntCallback;
import com.example.common.callback.StringCallBack;
import com.example.common.callback.UserCallback;
import com.example.common.network.user.UserService;
import com.example.common.network.JsonRootBean;
import com.example.common.network.RetrofitUtils;
import com.example.common.network.user.User;
import com.example.common.network.user.UserUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {
    private Button login;
    private ImageView img;
    private static final float BLUR_RADIUS = 25f;
    Button button;
    private static final String token = "37e83777-648f-4db8-af81-d05715869252";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindView();
        // ARouter.getInstance().build(ARouterPath.Home_MainActivity).navigation();
        login.setOnClickListener(view -> UserUtil.login("hq", "hq", html -> Show.log(html)));
        button.setOnClickListener(view -> UserUtil.getByUid(token, user -> Show.log(user.toString())));
        // UserUtil.changePassword("hq", "11", code -> Show.log(code + ""));
        User user = new User();
        user.setAddress("地址");
        user.setChildName("孩子");
        user.setClassName("班级");
        UserUtil.changeInfo(token, user, code -> Show.log(code + ""));
    }


    private void bindView() {
        login = findViewById(R.id.app_login);
        img = findViewById(R.id.app_img);
        button = findViewById(R.id.app_button);
    }
}