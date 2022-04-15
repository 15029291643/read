package com.example.home.bookshelf;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.appcompat.widget.Toolbar;

import com.example.common.activity.BaseFragment;
import com.example.common.object.Book;
import com.example.common.util.BookshelfUtil;
import com.example.home.Home_RecyclerViewAdapter;
import com.example.home.R;
import com.example.home.bookshelf.history.Home_HistoryActivity;
import com.example.home.search.Home_SearchActivity;

import java.util.ArrayList;

public class Home_BookshelfFragment extends BaseFragment {
    private RecyclerView recyclerview;
    private static Home_RecyclerViewAdapter adapter;
    private Button search;
    private Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_bookshelf_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bindView();
        setActivityBar2(toolbar);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Home_RecyclerViewAdapter(getContext(), new ArrayList<>());
        recyclerview.setAdapter(adapter);
        search.setOnClickListener(view -> startActivity(new Intent(getContext(), Home_SearchActivity.class)));
        update();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.home_menu_bookshelf, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId ==R.id.home_bookshelf_menu_history) {
            startActivity(new Intent(getContext(), Home_HistoryActivity.class));
        }
        return true;
    }

    private void update() {
        BookshelfUtil.findAll(list -> getActivity().runOnUiThread(() -> adapter.update(list)));
    }

    public static void add(Book book) {
        adapter.add(book);
    }

    private void bindView() {
        View view = getView();
        if (view != null) {
            recyclerview = view.findViewById(R.id.home_bookshelf_recycler_view);
            search = view.findViewById(R.id.home_bookshelf_search);
            toolbar = view.findViewById(R.id.home_bookshelf_toolbar);
        }
    }

}