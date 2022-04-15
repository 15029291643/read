package com.example.common.object;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.List;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    private long id;                    // id
    private String name;                // 用户名
    private String password;            // 密码
    private String account;             // 账号
    @TypeConverters(BookConverter.class)
    private List<Book> collectList;     // 收藏
    @TypeConverters(BookConverter.class)
    private List<Book> historyList;     // 历史记录

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public List<Book> getCollectList() {
        return collectList;
    }

    public void setCollectList(List<Book> collectList) {
        this.collectList = collectList;
    }

    public List<Book> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(List<Book> historyList) {
        this.historyList = historyList;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", account='" + account + '\'' +
                ", collectList=" + collectList +
                ", historyList=" + historyList +
                '}';
    }
}
