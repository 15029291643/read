package com.example.home.info.read;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.example.common.activity.SecondaryActivity;
import com.example.common.network.OkHttpUtil;
import com.example.common.session.Session;
import com.example.home.R;
import com.example.home.info.catalog.Home_CatalogActivity2;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home_ReadActivity extends SecondaryActivity {
    private ScrollView scrollView;
    private BottomNavigationView bottomNavigationView;
    private Toolbar toolbar;
    private TextView content;
    private TextView title;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_read_activity);
        bindView();
        setActionBar2(toolbar);
        content.setOnClickListener(view -> {
            if (toolbar.getVisibility() == View.GONE) {
                toolbar.setVisibility(View.VISIBLE);
                bottomNavigationView.setVisibility(View.VISIBLE);
            } else {
                toolbar.setVisibility(View.GONE);
                bottomNavigationView.setVisibility(View.GONE);
            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            // 目录
            if (itemId == R.id.home_menu_item_catalog) {
                startActivity(new Intent(Home_ReadActivity.this, Home_CatalogActivity2.class));
                finish();
            }
            return true;
        });
        update(Session.getChapter().getHref());
    }

    private void update(String href) {
        OkHttpUtil.chapter(href, chapter -> {
            title.setText(chapter.getTitle());
            content.setText(chapter.getContent());
        });
    }

    private void bindView() {
        scrollView = findViewById(R.id.home_read_scroll_view);
        toolbar = findViewById(R.id.home_read_toolbar);
        bottomNavigationView = findViewById(R.id.home_read_bottom_navigation_view);
        content = findViewById(R.id.home_read_content);
        title = findViewById(R.id.home_read_title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu_read_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
}