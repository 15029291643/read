package com.example.home;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.common.activity.BaseActivity;
import com.example.common.activity.PrimaryActivity;
import com.example.common.constant.ARouterPath;
import com.example.home.bookshelf.Home_BookshelfFragment;
import com.example.home.discover.Home_DiscoverFragment;
import com.example.home.discover.Home_DiscoverFragment2;
import com.example.home.me.Home_MeFragment;
import com.example.home.selection.Home_SelectionFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

@Route(path = ARouterPath.Home_MainActivity)
public class Home_MainActivity extends PrimaryActivity {
    private BottomNavigationView bottomNavigationView;
    private Fragment bookshelfFragment;
    private Fragment selectionFragment;
    private Fragment discoverFragment;
    private Fragment meFragment;
    private int currentItemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_main_activity);
        bindView();
        initBookshelfFragment();
        currentItemId = R.id.home_BookshelfFragment;
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == currentItemId) {
                return true;
            }
            if (itemId == R.id.home_BookshelfFragment) {
                initBookshelfFragment();
            } else if (itemId == R.id.home_SelectionFragment) {
                initSelectionFragment();
            } else if (itemId == R.id.home_DiscoverFragment) {
                initDiscoverFragment();
            } else if (itemId == R.id.home_MeFragment) {
                initMeFragment();
            }
            currentItemId = itemId;
            return true;
        });
    }

    private void bindView() {
        bottomNavigationView = findViewById(R.id.home_bottomNavigationView);
    }



    //显示书库Fragment
    private void initBookshelfFragment(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(bookshelfFragment == null){
            bookshelfFragment = new Home_BookshelfFragment();
            transaction.add(R.id.home_fragment,bookshelfFragment);
        }
        hideFragment(transaction);
        transaction.show(bookshelfFragment);
        transaction.commit();
    }

    //显示精选Fragment
    private void initSelectionFragment(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(selectionFragment == null){
            selectionFragment = new Home_SelectionFragment();
            transaction.add(R.id.home_fragment,selectionFragment);
        }
        hideFragment(transaction);
        transaction.show(selectionFragment);
        transaction.commit();
    }

    //显示发现Fragment
    private void initDiscoverFragment(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(discoverFragment == null){
            discoverFragment = new Home_DiscoverFragment2();
            transaction.add(R.id.home_fragment,discoverFragment);
        }
        hideFragment(transaction);
        transaction.show(discoverFragment);
        transaction.commit();
    }

    //显示我的Fragment
    private void initMeFragment(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(meFragment == null){
            meFragment = new Home_MeFragment();
            transaction.add(R.id.home_fragment,meFragment);
        }
        hideFragment(transaction);
        transaction.show(meFragment);
        transaction.commit();
    }

    //隐藏Fragment
    private void hideFragment(FragmentTransaction transaction){
        if(bookshelfFragment != null){
            transaction.hide(bookshelfFragment);
        }
        if(selectionFragment != null){
            transaction.hide(selectionFragment);
        }
        if(discoverFragment != null){
            transaction.hide(discoverFragment);
        }
        if(meFragment != null){
            transaction.hide(meFragment);
        }
    }
}