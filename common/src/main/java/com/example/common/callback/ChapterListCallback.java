package com.example.common.callback;


import com.example.common.object.Chapter;

import java.util.List;

public interface ChapterListCallback {
    void onResponse(List<Chapter> list);
}
