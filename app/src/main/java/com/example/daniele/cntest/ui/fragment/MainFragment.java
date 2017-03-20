package com.example.daniele.cntest.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.daniele.cntest.R;

/**
 * Created by daniele on 20/03/17.
 */

public class MainFragment extends Fragment {

    public static final String LOG_TAG = MainFragment.class.getSimpleName();
    public static final String FRAGMENT_TAG = "fragment_main";

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        return rootView;
    }
}
