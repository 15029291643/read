package com.example.search.writer;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.common.session.Session;
import com.example.common.activity.BaseFragment;
import com.example.common.object.Book;
import com.example.common.session.State;
import com.example.common.util.WriterUtil;
import com.example.search.R;
import com.example.search.writer.info.title.Search_TitleActivity;


public class Search_WriterFragment extends BaseFragment {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private static Search_BookAdapter adapter;
    private TextView add;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.search_writer_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bindView();
        initData();
        setActivityBar2(toolbar);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        add.setOnClickListener(view -> {
            Session.bookPosition = Session.ADD;
            startActivity(new Intent(getActivity(), Search_TitleActivity.class));
        });
        update();
    }

    private void initData() {
        adapter = new Search_BookAdapter(getContext());
    }

    private void update() {
        WriterUtil.findAll(list -> adapter.update(list));
    }

    public static void add(Book book) {
        adapter.add(book);
    }

    public static void set(int position, Book book) {
        adapter.set(position, book);
    }

    private void bindView() {
        View view = getView();
        if (view != null) {
            toolbar = view.findViewById(R.id.search_writer_tool_bar);
            recyclerView = view.findViewById(R.id.search_writer_recycler_view);
            add = view.findViewById(R.id.search_writer_add);
        }
    }
}