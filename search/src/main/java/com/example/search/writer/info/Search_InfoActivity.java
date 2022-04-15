package com.example.search.writer.info;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.example.common.activity.BaseActivity;
import com.example.common.object.Book;
import com.example.common.session.Session;
import com.example.common.session.State;
import com.example.common.util.ImgUtil;
import com.example.search.R;
import com.example.search.writer.info.catalog.Search_CatalogActivity;
import com.example.search.writer.info.edit.Search_EditActivity;
import com.example.search.writer.info.title.Search_TitleActivity;

public class Search_InfoActivity extends BaseActivity {
    private ImageView img;
    private ImageView img2;
    private TextView title;
    private TextView title2;
    private TextView intro;
    private Button add;
    private Toolbar toolbar;
    private Book book;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_info_activity);
        bindView();
        setActionBar2(toolbar);

        add.setOnClickListener(view -> {
            Session.chapterPosition = Session.ADD;
            startActivity(new Intent(this, Search_EditActivity.class));
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        book = Session.book;
        title.setText(book.getTitle());
        title2.setText(book.getTitle());
        intro.setText(book.getIntro());
        Bitmap bitmap = ImgUtil.stringToBitmap(book.getImg());
        img.setImageBitmap(bitmap);
        // 高斯模糊
        Bitmap bitmap1 = ImgUtil.stringToBitmap(book.getImg());
        ImgUtil.gaussianBlur(bitmap1);
        // 添加到背景
        img2.setImageBitmap(bitmap1);
    }

    private void update(String url) {

    }

    private void bindView() {
        img = findViewById(R.id.search_info_img);
        img2 = findViewById(R.id.home_info_img2);
        title = findViewById(R.id.search_info_title);
        title2 = findViewById(R.id.search_info_title2);
        intro = findViewById(R.id.search_info_intro);
        add = findViewById(R.id.search_info_add);
        toolbar = findViewById(R.id.search_info_toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu_info, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.search_item_info_title) {
            Session.editState = State.EDIT;
            startActivity(new Intent(this, Search_TitleActivity.class));
        } else if (itemId == R.id.search_item_info_catalog) {
            startActivity(new Intent(this, Search_CatalogActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}