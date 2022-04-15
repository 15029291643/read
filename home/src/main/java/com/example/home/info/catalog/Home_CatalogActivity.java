package com.example.home.info.catalog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.common.activity.SecondaryActivity;
import com.example.home.Home_ViewPager2Adapter;
import com.example.home.R;
import com.example.home.info.catalog.catalog.Home_CatalogFragment;
import com.example.home.info.catalog.topical.Home_TopicalFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Arrays;

public class Home_CatalogActivity extends SecondaryActivity {
    private Toolbar toolbar;
    private ViewPager2 viewPager2;
    private TabLayout tabLayout;
    private ArrayList<Fragment> fragmentList;
    private ArrayList<String> tabList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_catalog_activity);
        bindView();
        initData();
        setActionBar2(toolbar);
        viewPager2.setAdapter(new Home_ViewPager2Adapter(this, fragmentList));
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> tab.setText(tabList.get(position))).attach();
    }

    private void initData() {
        fragmentList= new ArrayList<>();
        fragmentList.add(new Home_CatalogFragment());
        fragmentList.add(new Home_TopicalFragment());
        fragmentList.add(new Home_TopicalFragment());
        fragmentList.add(new Home_TopicalFragment());
        tabList = new ArrayList<>(Arrays.asList("目录", "热门", "足迹", "书签"));
    }

    private void bindView() {
        toolbar = findViewById(R.id.home_catalog_toolbar);
        viewPager2 = findViewById(R.id.home_catalog_view_pager2);
        tabLayout = findViewById(R.id.home_catalog_tab_layout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu_catalog, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}