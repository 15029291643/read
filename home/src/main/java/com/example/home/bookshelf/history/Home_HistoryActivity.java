package com.example.home.bookshelf.history;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;

import com.example.common.activity.SecondaryActivity;
import com.example.common.util.HistoryUtil;
import com.example.home.Home_RecyclerViewAdapter;
import com.example.home.R;

import java.util.ArrayList;

public class Home_HistoryActivity extends SecondaryActivity {
    private RecyclerView recyclerview;
    private static Home_RecyclerViewAdapter adapter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_history_activity);
        bindView();
        setActionBar2(toolbar);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Home_RecyclerViewAdapter(this, new ArrayList<>());
        recyclerview.setAdapter(adapter);

        update();
    }

    private void update() {
        HistoryUtil.findAll(list -> runOnUiThread(() -> adapter.update(list)));
    }

    private void bindView() {
        recyclerview = findViewById(R.id.home_history_recycler_view);
        toolbar = findViewById(R.id.home_history_toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu_history, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.home_history_menu_clean) {
            new AlertDialog.Builder(this)
                    .setTitle("温馨提示")
                    .setMessage("确认要清空您的浏览记录？")
                    .setPositiveButton("确定", (dialogInterface, i) -> {
                        adapter.removeAll();
                        HistoryUtil.removeAll();
                    }).setNegativeButton("取消", (dialogInterface, i) -> {

            }).show();
        }
        return super.onOptionsItemSelected(item);
    }
}