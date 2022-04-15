package com.example.search.writer.info.edit;



import static com.example.common.session.Session.chapterPosition;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.common.activity.Show;
import com.example.common.session.Session;
import com.example.common.activity.SecondaryActivity;
import com.example.common.object.Chapter;
import com.example.common.util.WriterUtil;
import com.example.search.R;
import com.example.search.writer.info.catalog.Search_CatalogActivity;

public class Search_EditActivity extends SecondaryActivity {
    private Toolbar toolbar;
    private EditText title;
    private EditText content;
    private TextView release;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_edit_activity);
        bindView();
        setActionBar2(toolbar);
        initData();
        release.setOnClickListener(view -> {
            String title2 = title.getText().toString();
            String content2 = content.getText().toString();
            if (title2.length() == 0 || content2.length() == 0) {
                return;
            }
            Chapter chapter = new Chapter();
            chapter.setTitle(title2);
            chapter.setContent(content2);
            // 修改的
            if (chapterPosition != Session.ADD) {
                Session.getChapterList().set(chapterPosition,chapter);
                Search_CatalogActivity.set(chapterPosition, chapter);
                Show.toast("修改成功");
                // 新添的
            } else {
                Session.getChapterList().add(chapter);
                Search_CatalogActivity.add(chapter);
                Show.toast("发布成功");
            }
            WriterUtil.update(Session.book);
            finish();
        });
    }

    private void initData() {
        if (chapterPosition != Session.ADD) {
            Chapter chapter = Session.getChapterList().get(chapterPosition);
            title.setText(chapter.getTitle());
            content.setText(chapter.getContent());
        }
    }

    private void bindView() {
        toolbar= findViewById(R.id.search_edit_toolbar);
        title= findViewById(R.id.search_edit_title);
        content= findViewById(R.id.search_edit_content);
        release= findViewById(R.id.search_edit_release);
    }
}