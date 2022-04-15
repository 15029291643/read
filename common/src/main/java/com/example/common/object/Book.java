package com.example.common.object;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.io.Serializable;
import java.util.List;

@Entity
public class Book {
    @PrimaryKey(autoGenerate = true)
    private long id;                        // id
    private String title;                   // 书名
    private String author;                  // 作者
    private String type;                    // 类型
    private String type2;
    private String state;                   // 状态
    private String intro;                   // 简介
    private String img;                     // 图片, 有可能放的bitmap转换成的string
    private String href;                    // 链接
    @TypeConverters(ChapterConverter.class)
    private List<Chapter> chapterList;      // 章节

    public Book() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getHref() {
        return href;
    }


    public void setHref(String href) {
        this.href = href;
    }

    public List<Chapter> getChapterList() {
        return chapterList;
    }

    public void setChapterList(List<Chapter> chapterList) {
        this.chapterList = chapterList;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", type='" + type + '\'' +
                ", type2='" + type2 + '\'' +
                ", state='" + state + '\'' +
                ", intro='" + intro + '\'' +
                ", img='" + img + '\'' +
                ", href='" + href + '\'' +
                ", chapterList=" + chapterList +
                '}';
    }
}