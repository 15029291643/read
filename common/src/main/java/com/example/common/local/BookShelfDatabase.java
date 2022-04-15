package com.example.common.local;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.common.object.Book;

@Database(version = 1, entities = {Book.class}, exportSchema = false)
public abstract class BookShelfDatabase extends RoomDatabase {
    public abstract BookDao bookDao();
    private static final String NAME = "bookshelf_database";

    private static BookShelfDatabase instance;

    public static BookShelfDatabase getDatabase(Context context) {
        if (instance == null) {
            synchronized (BookShelfDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(), BookShelfDatabase.class, NAME)
                            .build();
                }
            }
        }
        return instance;
    }
}
