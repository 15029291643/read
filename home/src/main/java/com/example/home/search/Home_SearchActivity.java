package com.example.home.search;


import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.common.activity.BaseActivity;
import com.example.common.callback.BookListCallback;
import com.example.common.network.OkHttpUtil;
import com.example.common.object.Book;
import com.example.home.Home_RecyclerViewAdapter;
import com.example.home.R;

import java.util.List;

import okhttp3.OkHttpClient;

public class Home_SearchActivity extends BaseActivity {
    private EditText edit;
    private TextView send;
    private RecyclerView recyclerview;
    private Home_RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_search_activity);
        bindView();
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Home_RecyclerViewAdapter(this, null);
        recyclerview.setAdapter(adapter);
        send.setOnClickListener(view -> {
            String title = this.edit.getText().toString();
            if (title.length() == 0) {
                return;
            }
            update(title);
        });
    }

    private void update(String title) {
        OkHttpUtil.search(title, list -> adapter.update(list));
    }

    private void bindView() {
        edit = findViewById(R.id.home_search_edit);
        send = findViewById(R.id.home_search_send);
        recyclerview = findViewById(R.id.home_search_recycler_view);
    }
}