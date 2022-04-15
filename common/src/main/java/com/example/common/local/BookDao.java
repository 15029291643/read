package com.example.common.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.common.object.Book;


import java.util.List;

@Dao
public interface BookDao {
    @Insert
    long add(Book book);

    @Query("select * from Book where title = :title")
    Book findByTitle(String title);

    @Query("select * from Book")
    List<Book> findAllBook();

    @Query("delete from Book")
    void removeAllBook();

    @Update
    void update(Book book);
}
