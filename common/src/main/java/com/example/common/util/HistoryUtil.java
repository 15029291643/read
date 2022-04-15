package com.example.common.util;

import com.example.common.activity.BaseApplication;
import com.example.common.callback.BookListCallback;
import com.example.common.local.BookDao;
import com.example.common.local.HistoryDatabase;
import com.example.common.object.Book;

import java.util.List;

public class HistoryUtil {
    private static BookDao bookDao;

    private static BookDao getBookDao() {
        if (bookDao == null) {
            synchronized (BookDao.class) {
                if (bookDao == null) {
                    bookDao = HistoryDatabase.getDatabase(BaseApplication.getContext()).bookDao();
                }
            }
        }
        return bookDao;
    }

    public static void add(Book book) {
        new Thread(() -> {
            Book book1 = getBookDao().findByTitle(book.getTitle());
            if (book1 == null) {
                getBookDao().add(book);
            }
        }).start();
    }

    public static void findAll(BookListCallback callback) {
        new Thread(() -> {
            List<Book> list = getBookDao().findAllBook();
            callback.onResponse(list);
        }).start();
    }

    public static void removeAll() {
        new Thread(() -> getBookDao().removeAllBook()).start();
    }
}
