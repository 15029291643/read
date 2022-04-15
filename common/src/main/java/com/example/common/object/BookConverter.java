package com.example.common.object;

import androidx.room.TypeConverter;

import com.example.common.object.Book;
import com.example.common.util.GsonInstance;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class BookConverter {
    @TypeConverter
    public String objectToString(List<Book> list) {
        return GsonInstance.getInstance().getGson().toJson(list);
    }

    @TypeConverter
    public List<Book> stringToObject(String json) {
        Type listType = new TypeToken<List<Book>>(){}.getType();
        return GsonInstance.getInstance().getGson().fromJson(json, listType);
    }
}
