package com.example.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.common.activity.BaseActivity;
import com.example.common.constant.ARouterPath;
import com.example.search.discover.Search_DiscoverFragment;
import com.example.search.message.Search_MessageFragment;
import com.example.search.writer.Search_WriterFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/*
* 写作
* */
@Route(path = ARouterPath.Search_MainActivity)
public class Search_MainActivity extends BaseActivity {

    private BottomNavigationView bottomNavigationView;
    private Search_MessageFragment messageFragment;
    private Search_DiscoverFragment discoverFragment;
    private Search_WriterFragment writerFragment;
    private int currentItemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity_main);
        bindView();
        initBookshelfFragment();
        currentItemId = R.id.search_item_main_message;
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == currentItemId) {
                return true;
            }
            if (itemId == R.id.search_item_main_message) {
                initBookshelfFragment();
            } else if (itemId == R.id.search_item_main_discover) {
                initSelectionFragment();
            } else if (itemId == R.id.search_item_main_writer) {
                initDiscoverFragment();
            }
            currentItemId = itemId;
            return true;
        });
    }

    private void bindView() {
        bottomNavigationView = findViewById(R.id.search_main_bottom_navigation_view);
    }



    //显示书库Fragment
    private void initBookshelfFragment(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(messageFragment == null){
            messageFragment = new Search_MessageFragment();
            transaction.add(R.id.search_main_fragment,messageFragment);
        }
        hideFragment(transaction);
        transaction.show(messageFragment);
        transaction.commit();
    }

    //显示精选Fragment
    private void initSelectionFragment(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(discoverFragment == null){
            discoverFragment = new Search_DiscoverFragment();
            transaction.add(R.id.search_main_fragment,discoverFragment);
        }
        hideFragment(transaction);
        transaction.show(discoverFragment);
        transaction.commit();
    }

    //显示发现Fragment
    private void initDiscoverFragment(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(writerFragment == null){
            writerFragment = new Search_WriterFragment();
            transaction.add(R.id.search_main_fragment,writerFragment);
        }
        hideFragment(transaction);
        transaction.show(writerFragment);
        transaction.commit();
    }

    //隐藏Fragment
    private void hideFragment(FragmentTransaction transaction){
        if(messageFragment != null){
            transaction.hide(messageFragment);
        }
        if(discoverFragment != null){
            transaction.hide(discoverFragment);
        }
        if(writerFragment != null){
            transaction.hide(writerFragment);
        }
    }
}