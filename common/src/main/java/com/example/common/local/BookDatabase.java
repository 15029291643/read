package com.example.common.local;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.common.object.Book;

@Database(version = 1, entities = {Book.class}, exportSchema = false)
public abstract class BookDatabase extends RoomDatabase {
    public abstract BookDao bookDao();

    private static BookDatabase instance;

    public static BookDatabase getDatabase(Context context) {
        if (instance == null) {
            synchronized (BookDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(), BookDatabase.class, "book_database")
                            .build();
                }
            }
        }
        return instance;
    }
}
