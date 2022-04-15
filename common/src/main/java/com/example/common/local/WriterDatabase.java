package com.example.common.local;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.common.object.Book;

@Database(version = 1, entities = {Book.class}, exportSchema = false)
public abstract class WriterDatabase extends RoomDatabase {
    public abstract BookDao bookDao();
    private static final String NAME = "writer_database";

    private static WriterDatabase instance;

    public static WriterDatabase getDatabase(Context context) {
        if (instance == null) {
            synchronized (WriterDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(), WriterDatabase.class, NAME)
                            .build();
                }
            }
        }
        return instance;
    }
}
