package com.example.common.session;

import com.example.common.object.Book;
import com.example.common.object.Chapter;

import java.util.ArrayList;
import java.util.List;

public class Session {
    public static final int ADD = -1;
    public static Book book;  // 正在阅读
    public static Chapter chapter;  // 正在编写
    public static State editState = State.ADD;
    public static int bookPosition = ADD;
    public static int chapterPosition = ADD;

    public static Chapter getChapter() {
        return book.getChapterList().get(chapterPosition);
    }

    public static List<Chapter> getChapterList() {
        if (book.getChapterList() == null) {
            book.setChapterList(new ArrayList<>());
        }
        return book.getChapterList();
    }

}
