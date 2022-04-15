package com.example.search.writer.info.catalog;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.common.object.Chapter;
import com.example.common.session.Session;
import com.example.common.activity.BaseActivity;
import com.example.search.R;


public class Search_CatalogActivity extends BaseActivity {
    private RecyclerView recyclerview;
    private static Search_CatalogAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_catalog_activity);
        bindView();
        initData();
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(adapter);
        update();
    }

    private void update() {
        adapter.update(Session.getChapterList());
    }

    public static void set(int position, Chapter chapter) {
        adapter.set(position, chapter);
    }

    public static void add(Chapter chapter) {
        adapter.add(chapter);
    }

    private void initData() {
        adapter = new Search_CatalogAdapter(this);
    }

    private void bindView() {
        recyclerview = findViewById(R.id.search_catalog_recycler_view);
    }
}