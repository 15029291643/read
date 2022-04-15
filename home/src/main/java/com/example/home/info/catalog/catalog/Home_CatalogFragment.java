package com.example.home.info.catalog.catalog;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.common.object.Chapter;
import com.example.common.session.Session;
import com.example.home.R;

import java.util.List;

public class Home_CatalogFragment extends Fragment {
    private RecyclerView recyclerview;
    private Home_CatalogAdapter adapter;
    private List<Chapter> chapterList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_catalog_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bindView();
        initData();
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Home_CatalogAdapter(getContext(), chapterList);
        recyclerview.setAdapter(adapter);
    }

    private void initData() {
        chapterList = Session.book.getChapterList();
    }

    private void bindView() {
        View view = getView();
        recyclerview = view.findViewById(R.id.home_catalog_recycler_view);
    }
}