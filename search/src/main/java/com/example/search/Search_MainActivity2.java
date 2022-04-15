package com.example.search;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.common.activity.BaseActivity;
import com.example.common.constant.ARouterPath;

@Route(path = ARouterPath.Search_MainActivity2)
public class Search_MainActivity2 extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_main_activity_2);
    }
}