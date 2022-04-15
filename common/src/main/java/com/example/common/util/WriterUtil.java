package com.example.common.util;

import com.example.common.activity.BaseActivity;
import com.example.common.activity.BaseApplication;
import com.example.common.callback.BookCallback;
import com.example.common.callback.BookListCallback;
import com.example.common.local.BookDao;
import com.example.common.local.HistoryDatabase;
import com.example.common.local.WriterDatabase;
import com.example.common.object.Book;

import java.util.List;

public class WriterUtil {
    private static BookDao bookDao;

    private static BookDao getBookDao() {
        if (bookDao == null) {
            synchronized (BookDao.class) {
                if (bookDao == null) {
                    bookDao = WriterDatabase.getDatabase(BaseApplication.getContext()).bookDao();
                }
            }
        }
        return bookDao;
    }

    // 添加一本书
    public static void add(Book book) {
        new Thread(() -> {
            Book book1 = getBookDao().findByTitle(book.getTitle());
            if (book1 == null) {
                getBookDao().add(book);
            }
        }).start();
    }

    // 根据书名找到一本书
    public static void findByTitle(String title, BookCallback callback) {
        new Thread(() -> {
            Book book = getBookDao().findByTitle(title);
            BaseActivity.getActivity().runOnUiThread(() -> callback.onResponse(book));
        }).start();
    }

    // 找到所有书
    public static void findAll(BookListCallback callback) {
        new Thread(() -> {
            List<Book> list = getBookDao().findAllBook();
            BaseActivity.getActivity().runOnUiThread(() -> callback.onResponse(list));
        }).start();
    }

    // 删除所有书
    public static void removeAll() {
        new Thread(() -> getBookDao().removeAllBook()).start();
    }


    // 更新一本书
    public static void update(Book book) {
        new Thread(() -> getBookDao().update(book)).start();
    }
}
