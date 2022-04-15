package com.example.common.object;

import androidx.room.TypeConverter;

import com.example.common.util.GsonInstance;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ChapterConverter {
    @TypeConverter
    public String objectToString(List<Chapter> list) {
        return GsonInstance.getInstance().getGson().toJson(list);
    }

    @TypeConverter
    public List<Chapter> stringToObject(String json) {
        Type listType = new TypeToken<List<Chapter>>(){}.getType();
        return GsonInstance.getInstance().getGson().fromJson(json, listType);
    }
}
