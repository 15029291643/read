package com.example.home.discover;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.common.constant.ARouterPath;
import com.example.home.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class Home_DiscoverFragment2 extends Fragment {
    private FloatingActionButton add;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.home_discover_fragment2, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bindView();
        add.setOnClickListener(view -> ARouter.getInstance().build(ARouterPath.Search_MainActivity2).navigation());
    }

    private void bindView() {
        View view = getView();
        if (view != null) {
            add = view.findViewById(R.id.home_discover2_add);
        }
    }
}