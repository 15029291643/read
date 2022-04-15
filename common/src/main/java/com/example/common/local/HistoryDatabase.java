package com.example.common.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.common.object.Book;


@Database(version = 1, entities = {Book.class}, exportSchema = false)
public abstract class HistoryDatabase extends RoomDatabase {
    public abstract BookDao bookDao();

    private static final String NAME = "history_database";

    private static HistoryDatabase instance;

    public static HistoryDatabase getDatabase(Context context) {
        if (instance == null) {
            synchronized (HistoryDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(), HistoryDatabase.class, NAME)
                            .build();
                }
            }
        }
        return instance;
    }
}
