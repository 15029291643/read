package com.example.home.info;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.example.common.session.Session;
import com.example.common.activity.SecondaryActivity;
import com.example.common.activity.Show;
import com.example.common.callback.BoolCallBack;
import com.example.common.network.OkHttpUtil;
import com.example.common.object.Book;
import com.example.common.util.BookshelfUtil;
import com.example.common.util.HistoryUtil;
import com.example.common.util.ImgUtil;
import com.example.home.R;
import com.example.home.bookshelf.Home_BookshelfFragment;
import com.example.home.info.catalog.Home_CatalogActivity;
import com.example.home.info.read.Home_ReadActivity;

public class Home_InfoActivity extends SecondaryActivity {
    private ImageView img;
    private ImageView img2;
    private TextView title;
    private TextView title2;
    private TextView author;
    private TextView type;
    private TextView type2;
    private TextView intro;
    private Button read;
    private TextView add;
    private Toolbar toolbar;
    private ConstraintLayout constraintLayout;
    private Book book;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_info_activity);
        bindView();
        setActionBar2(toolbar);
        initData();
        HistoryUtil.add(book);
        update(book.getHref());
        read.setOnClickListener(view -> {
            if (Session.book.getChapterList() == null) {
                Toast.makeText(this, "请再次点击", Toast.LENGTH_SHORT).show();
                return;
            }
            Session.chapterPosition = 0;
            startActivity(new Intent(Home_InfoActivity.this, Home_ReadActivity.class));
        });
        add.setOnClickListener(view -> {
            BookshelfUtil.insert(book, new BoolCallBack() {
                @Override
                public void bool(boolean bool) {
                    if (bool) {
                        Show.toast("添加成功");
                        Home_BookshelfFragment.add(book);
                    } else {
                        Show.toast("重复添加");
                    }
                }
            });
        });
        constraintLayout.setOnClickListener(view -> {
            if (Session.book.getChapterList() == null) {
                Toast.makeText(this, "请再次点击", Toast.LENGTH_SHORT).show();
                return;
            }
            startActivity(new Intent(this, Home_CatalogActivity.class));
        });
    }

    private void initData() {
        book = Session.book;
        title.setText(book.getTitle());
        title2.setText(book.getTitle());
        author.setText(book.getAuthor());
        type.setText(book.getType());
        type2.setText(book.getType2());
        intro.setText(book.getIntro());
        Glide.with(this).load(book.getImg()).into(img);
        ImgUtil.urlToBitmap(book.getImg(), bitmap -> img2.setImageBitmap(bitmap));
    }

    private void update(String url) {
        OkHttpUtil.catalog(url, list -> Session.book.setChapterList(list));
    }

    private void bindView() {
        img = findViewById(R.id.home_info_img);
        img2 = findViewById(R.id.home_info_img2);
        title = findViewById(R.id.home_info_title);
        title2 = findViewById(R.id.home_info_title2);
        author = findViewById(R.id.home_info_author);
        type = findViewById(R.id.home_info_type);
        type2 = findViewById(R.id.home_info_type2);
        intro = findViewById(R.id.home_info_intro);
        read = findViewById(R.id.home_info_read);
        add = findViewById(R.id.home_info_add);
        toolbar = findViewById(R.id.home_info_toolbar);
        constraintLayout= findViewById(R.id.home_info_catalog);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu_info, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}