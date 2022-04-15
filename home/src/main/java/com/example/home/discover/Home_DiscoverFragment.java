package com.example.home.discover;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.common.activity.BaseFragment;
import com.example.common.constant.ARouterPath;
import com.example.home.Home_ViewPager2Adapter;
import com.example.home.R;
import com.example.home.info.catalog.topical.Home_TopicalFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Arrays;

public class Home_DiscoverFragment extends BaseFragment {

    private TabLayout tabLayout;
    private FloatingActionButton writer;
    private ViewPager2 viewPager2;
    private ArrayList<Fragment> fragmentList;
    private ArrayList<String> tabList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_discover_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bindView();
        initData();
        viewPager2.setAdapter(new Home_ViewPager2Adapter(getActivity(), fragmentList));
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> tab.setText(tabList.get(position))).attach();
        writer.setOnClickListener(view -> ARouter.getInstance().build(ARouterPath.Search_MainActivity2).navigation());
    }

    private void initData() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new Home_TopicalFragment());
        fragmentList.add(new Home_TopicalFragment());
        fragmentList.add(new Home_TopicalFragment());
        tabList = new ArrayList<>(Arrays.asList("关注", "推荐", "写作"));
    }

    private void bindView() {
        View view = getView();
        if (view != null) {
            tabLayout = view.findViewById(R.id.home_discover_tabLayout);
            viewPager2 = view.findViewById(R.id.home_discover_view_pager2);
            writer = view.findViewById(R.id.home_discover_writer);
        }
    }
}