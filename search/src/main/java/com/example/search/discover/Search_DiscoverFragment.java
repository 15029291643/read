package com.example.search.discover;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.search.R;
import com.example.search.Search_ViewPager2Adapter;
import com.example.search.discover.attention.Search_AttentionFragment;
import com.example.search.discover.recommend.Search_RecommendFragment;
import com.example.search.discover.writing.Search_WritingFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Arrays;
import androidx.fragment.app.Fragment;

public class Search_DiscoverFragment extends Fragment {
    private ViewPager2 viewPager2;
    private TabLayout tabLayout;
    private ArrayList<Fragment> fragmentList;
    private ArrayList<String> tabList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.search_discover_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bindView();
        initData();
        viewPager2.setAdapter(new Search_ViewPager2Adapter((FragmentActivity) getActivity(), fragmentList));
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> tab.setText(tabList.get(position))).attach();
    }

    private void initData() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new Search_AttentionFragment());
        fragmentList.add(new Search_RecommendFragment());
        fragmentList.add(new Search_WritingFragment());
        tabList = new ArrayList<>(Arrays.asList("关注", "推荐", "写作"));
    }

    private void bindView() {
        View view = getView();
        if (view != null) {
            viewPager2 = view.findViewById(R.id.search_discover_view_pager2);
            tabLayout = view.findViewById(R.id.search_discover_tab_layout);
        }
    }
}