package com.example.common.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

/*
* 实现了setActivityBar重写
* */
public class BaseFragment extends Fragment {
    public void setActivityBar2(Toolbar toolbar) {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(toolbar);
            ActionBar actionBar = activity.getSupportActionBar();
            if (actionBar != null) {
                actionBar.setTitle("");
            }
        }
        setHasOptionsMenu(true);
    }
}
