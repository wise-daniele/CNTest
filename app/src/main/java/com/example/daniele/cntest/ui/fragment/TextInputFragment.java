package com.example.daniele.cntest.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.daniele.cntest.R;
import com.example.daniele.cntest.rest.RestClient;
import com.example.daniele.cntest.rest.model.RandomJoke;
import com.example.daniele.cntest.ui.dialog.RandomJokeDialog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by daniele on 21/03/17.
 */

public class TextInputFragment extends Fragment {

    public static final String LOG_TAG = TextInputFragment.class.getSimpleName();
    public static final String FRAGMENT_TAG = "fragment_text_input";

    private EditText mEditTextName;
    private Button mButtonSubmit;
    private RandomJokeDialog mJokeDialog;

    public static TextInputFragment newInstance() {
        TextInputFragment fragment = new TextInputFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_text_input, container, false);

        mEditTextName = (EditText) rootView.findViewById(R.id.edit_text_name);
        mButtonSubmit = (Button) rootView.findViewById(R.id.button_submit);

        mButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mEditTextName.getText().toString();
                String[] nameSplitted = name.split(" ");
                getJokeByName(nameSplitted[0], nameSplitted[1]);
            }
        });

        return rootView;
    }

    public void getJokeByName(String firstName, String lastName){
        RestClient.ChuckNorrisInterface service = RestClient.getInstance().getClient().create(RestClient.ChuckNorrisInterface.class);
        Call<RandomJoke> call = service.getJokeByName(firstName, lastName);

        call.enqueue(new Callback<RandomJoke>() {
            @Override
            public void onResponse(Call<RandomJoke> call, Response<RandomJoke> response) {
                String textJoke = response.body().getValue().getJoke();
                onJokeReceived(textJoke);
            }

            @Override
            public void onFailure(Call<RandomJoke> call, Throwable t) {
                Log.d(LOG_TAG, "Error");
            }

        });
    }

    public void onJokeReceived(String textJoke){
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        Fragment prev = getActivity().getSupportFragmentManager().findFragmentByTag(RandomJokeDialog.DIALOG_TAG);
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        mJokeDialog = RandomJokeDialog.newInstance(textJoke);
        mJokeDialog.setCancelable(false);
        mJokeDialog.show(ft, RandomJokeDialog.DIALOG_TAG);
    }

}
