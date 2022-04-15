package com.example.common.callback;


import com.example.common.object.Book;

import java.util.List;

public interface BookListCallback {
    void onResponse(List<Book> list);
}
