package com.example.common.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.common.object.User;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    long add(User user);

    @Update
    void update(User user);

    @Query("select * from User")
    List<User> findAll();
}
