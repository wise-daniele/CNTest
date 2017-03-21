package com.example.daniele.cntest.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.daniele.cntest.R;
import com.example.daniele.cntest.rest.RestClient;
import com.example.daniele.cntest.rest.model.Joke;
import com.example.daniele.cntest.rest.model.RandomJokes;
import com.example.daniele.cntest.ui.adapter.JokesAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by daniele on 21/03/17.
 */

public class NeverEndingListFragment extends Fragment{

    public static final String LOG_TAG = NeverEndingListFragment.class.getSimpleName();
    public static final String FRAGMENT_TAG = "fragment_never_ending_list";

    public static NeverEndingListFragment newInstance() {
        NeverEndingListFragment fragment = new NeverEndingListFragment();
        return fragment;
    }

    private RecyclerView mJokesView;
    private List<Joke> mJokesList;
    private JokesAdapter mJokesAdapter;
    private LinearLayoutManager mLayoutManager;

    private int mVisibleItemCount = 0;
    private int mTotalItemCount = 0;
    private int mPastVisiblesItems = 0;
    private boolean mIsLoading = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_never_ending_list, container, false);

        mJokesView = (RecyclerView) rootView.findViewById(R.id.jokes_list);
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mJokesView.setLayoutManager(mLayoutManager);
        mJokesList = new ArrayList<>();
        mJokesAdapter = new JokesAdapter(getActivity(), mJokesList);
        mJokesView.setAdapter(mJokesAdapter);

        mJokesView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                if(dy > 0){
                    mVisibleItemCount = mLayoutManager.getChildCount();
                    mTotalItemCount = mLayoutManager.getItemCount();
                    mPastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                    if (mIsLoading){
                        if((mVisibleItemCount + mPastVisiblesItems) >= mTotalItemCount){
                            mIsLoading = false;
                            getJokesList();
                        }
                    }
                }
            }
        });

        getJokesList();

        return rootView;
    }

    private void getJokesList(){
        RestClient.ChuckNorrisInterface service = RestClient.getInstance().getClient().create(RestClient.ChuckNorrisInterface.class);
        Call<RandomJokes> call = service.getJokesList(20);

        call.enqueue(new Callback<RandomJokes>() {
            @Override
            public void onResponse(Call<RandomJokes> call, Response<RandomJokes> response) {
                mIsLoading = true;
                RandomJokes jokes = response.body();
                if(mJokesList.size() == 0){
                    mJokesList.addAll(jokes.getValue());
                    mJokesList.add(null);
                }
                else{
                    mJokesList.addAll(mJokesList.size() - 2, jokes.getValue());
                }
                mJokesAdapter.setJokesList(mJokesList);
                mJokesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<RandomJokes> call, Throwable t) {
            }

        });
    }
}
