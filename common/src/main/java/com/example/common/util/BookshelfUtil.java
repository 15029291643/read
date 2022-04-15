package com.example.common.util;

import com.example.common.activity.BaseActivity;
import com.example.common.activity.BaseApplication;
import com.example.common.callback.BookListCallback;
import com.example.common.callback.BoolCallBack;
import com.example.common.local.BookDao;
import com.example.common.local.BookShelfDatabase;
import com.example.common.object.Book;

import java.util.List;

public class BookshelfUtil {
    private static BookDao bookDao;

    private static BookDao getBookDao() {
        if (bookDao == null) {
            synchronized (BookDao.class) {
                if (bookDao == null) {
                    bookDao = BookShelfDatabase.getDatabase(BaseApplication.getContext()).bookDao();
                }
            }
        }
        return bookDao;
    }

    public static void insert(Book book, BoolCallBack callback) {
        new Thread(() -> {
            Book book1 = getBookDao().findByTitle(book.getTitle());
            if (book1 != null) {
                BaseActivity.getActivity().runOnUiThread(() -> callback.bool(false));

            } else {
                getBookDao().add(book);
                BaseActivity.getActivity().runOnUiThread(() -> callback.bool(true));
            }
        }).start();
    }

    public static void findAll(BookListCallback callback) {
        new Thread(() -> {
            List<Book> list = getBookDao().findAllBook();
            callback.onResponse(list);
        }).start();
    }
}
