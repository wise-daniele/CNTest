package com.example.daniele.cntest.ui.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.daniele.cntest.R;
import com.example.daniele.cntest.rest.RestClient;
import com.example.daniele.cntest.rest.model.RandomJoke;
import com.example.daniele.cntest.utils.TextUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by daniele on 21/03/17.
 */

public class TextInputFragment extends Fragment {

    public static final String LOG_TAG = TextInputFragment.class.getSimpleName();
    public static final String FRAGMENT_TAG = "fragment_text_input";

    private TextInputLayout mLayoutInputName;
    private EditText mEditTextName;
    private Button mButtonSubmit;
    private FragmentListener mListener;

    public static TextInputFragment newInstance() {
        TextInputFragment fragment = new TextInputFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_text_input, container, false);

        mLayoutInputName = (TextInputLayout) rootView.findViewById(R.id.layout_input_name);
        mEditTextName = (EditText) rootView.findViewById(R.id.edit_text_name);
        mButtonSubmit = (Button) rootView.findViewById(R.id.button_submit);

        mButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mEditTextName.getText().toString().trim();
                if(!TextUtils.isNameValid(name)){
                    showErrorDialog();
                }
                else{
                    String[] nameSplitted = TextUtils.getNameSplitted(name);
                    getJokeByName(nameSplitted[0], nameSplitted[1]);
                }
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
                String textJoke = response.body().getValue().getJoke().replace("&quot;", "\"");
                mListener.onJokeReceived(textJoke);
            }

            @Override
            public void onFailure(Call<RandomJoke> call, Throwable t) {
                Log.d(LOG_TAG, "Error");
            }

        });
    }

    public void showErrorDialog(){
        new AlertDialog.Builder(getActivity())
                .setTitle("Error")
                .setMessage("Input text must be in the form \"<name> <lastname>\"")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
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
