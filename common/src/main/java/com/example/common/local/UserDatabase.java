package com.example.common.local;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.common.object.User;

@Database(version = 1, entities = {User.class}, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDao userDao();

    private static UserDatabase instance;

    public static UserDatabase getDatabase(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class, "user_database")
                    .build();
        }
        return instance;
    }
}
