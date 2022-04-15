package com.example.home.selection;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.common.activity.Show;
import com.example.home.Home_ViewPager2Adapter;
import com.example.home.R;
import com.example.home.search.Home_SearchActivity;
import com.example.home.selection.boy.Home_BoyFragment;
import com.example.home.selection.girl.Home_GirlFragment;
import com.example.home.selection.self.Home_SelfFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Arrays;

public class Home_SelectionFragment extends Fragment {
    private ViewPager2 viewpager2;
    private TabLayout tabLayout;
    private Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_selection_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bindView();
        setActionBar();
        ArrayList<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new Home_BoyFragment());
        fragmentList.add(new Home_GirlFragment());
        fragmentList.add(new Home_SelfFragment());
        Home_ViewPager2Adapter adapter = new Home_ViewPager2Adapter(getActivity(), fragmentList);
        viewpager2.setAdapter(adapter);
        ArrayList<String> tabList = new ArrayList<>(Arrays.asList("男生", "女生", "精选"));
        new TabLayoutMediator(tabLayout, viewpager2, (tab, position) -> tab.setText(tabList.get(position))).attach();
        update();
    }

    private void update() {

    }

    private void setActionBar() {
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
    }

    private void bindView() {
        View view = getView();
        if (view != null) {
            viewpager2 = view.findViewById(R.id.home_selection_viewPager2);
            tabLayout = view.findViewById(R.id.home_selection_tabLayout);
            toolbar = view.findViewById(R.id.home_selection_toolbar);
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.home_menu_selection, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.home_selection_menu_search) {
            startActivity(new Intent(getContext(), Home_SearchActivity.class));
        }
        return true;
    }
}