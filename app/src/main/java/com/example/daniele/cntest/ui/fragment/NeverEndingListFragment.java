package com.example.daniele.cntest.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.daniele.cntest.R;

/**
 * Created by daniele on 21/03/17.
 */

public class NeverEndingListFragment extends Fragment{

    public static final String LOG_TAG = NeverEndingListFragment.class.getSimpleName();
    public static final String FRAGMENT_TAG = "fragment_never_ending_list";

    private FragmentListener mListener;

    public static NeverEndingListFragment newInstance() {
        NeverEndingListFragment fragment = new NeverEndingListFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_never_ending_list, container, false);

        return rootView;
    }
}
