package com.example.home.selection.girl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.common.network.OkHttpUtil;
import com.example.home.Home_RecyclerViewAdapter;
import com.example.home.R;

import java.util.ArrayList;

public class Home_GirlFragment extends Fragment {
    private RecyclerView recyclerview;
    private Home_RecyclerViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_girl_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bindView();
        adapter = new Home_RecyclerViewAdapter(getContext(), new ArrayList<>());
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.setAdapter(adapter);
        update();
    }

    private void update() {
        OkHttpUtil.girlList(list -> adapter.update(list));
    }

    private void bindView() {
        View view = getView();
        recyclerview = view.findViewById(R.id.home_selection_boy_recycler_view);
    }
}