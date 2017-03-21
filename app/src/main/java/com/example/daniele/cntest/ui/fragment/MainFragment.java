package com.example.daniele.cntest.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.daniele.cntest.R;
import com.example.daniele.cntest.rest.RestClient;
import com.example.daniele.cntest.rest.model.RandomJoke;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by daniele on 20/03/17.
 */

public class MainFragment extends Fragment implements View.OnClickListener {

    public static final String LOG_TAG = MainFragment.class.getSimpleName();
    public static final String FRAGMENT_TAG = "fragment_main";


    private Button buttonRandomJoke;
    private Button buttonTextInput;
    private Button buttonNeverList;
    private FragmentListener mListener;

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        buttonRandomJoke = (Button) rootView.findViewById(R.id.button_random_joke);
        buttonTextInput = (Button) rootView.findViewById(R.id.button_text_input);
        buttonNeverList = (Button) rootView.findViewById(R.id.button_never_list);

        buttonRandomJoke.setOnClickListener(this);
        buttonTextInput.setOnClickListener(this);
        buttonNeverList.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_random_joke:
                getRandomJoke();
                break;
            case R.id.button_text_input:
                mListener.onItemSelected(TextInputFragment.FRAGMENT_TAG);
                break;
            case R.id.button_never_list:
                mListener.onItemSelected(NeverEndingListFragment.FRAGMENT_TAG);
                break;
        }
    }

    public void getRandomJoke(){
        RestClient.ChuckNorrisInterface service = RestClient.getInstance().getClient().create(RestClient.ChuckNorrisInterface.class);
        Call<RandomJoke> call = service.getRandomJoke();

        call.enqueue(new Callback<RandomJoke>() {
            @Override
            public void onResponse(Call<RandomJoke> call, Response<RandomJoke> response) {
                String textJoke = response.body().getValue().getJoke().replace("&quot;", "\"");
                mListener.onJokeReceived(textJoke);
            }

            @Override
            public void onFailure(Call<RandomJoke> call, Throwable t) {
                Log.d(LOG_TAG, "Error");
            }

        });
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        try {
            mListener = (FragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement FragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
