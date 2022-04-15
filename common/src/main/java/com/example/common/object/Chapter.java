package com.example.common.object;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.io.Serializable;

public class Chapter {
    private String title;       // 章节名
    private String time;        // 时间
    private String href;        // 链接
    private String content;     // 内容

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Chapter(String title, String time, String href, String content) {
        this.title = title;
        this.time = time;
        this.href = href;
        this.content = content;
    }

    public Chapter() {
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "title='" + title + '\'' +
                ", time='" + time + '\'' +
                ", href='" + href + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
