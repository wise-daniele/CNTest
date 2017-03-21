package com.example.daniele.cntest.ui.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.example.daniele.cntest.R;

/**
 * Created by daniele on 21/03/17.
 */

public class RandomJokeDialog extends DialogFragment {

    public static final String DIALOG_TAG = "joke_dialog";
    public static final String TEXT_JOKE_ARG = "text_joke";

    public static RandomJokeDialog newInstance(String textJoke) {
        RandomJokeDialog myDialog = new RandomJokeDialog();

        Bundle args = new Bundle();
        args.putString(TEXT_JOKE_ARG, textJoke);
        myDialog.setArguments(args);
        return myDialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String textJoke = getArguments().getString(TEXT_JOKE_ARG);

        return new AlertDialog.Builder(getActivity())
                .setTitle("Joke")
                .setMessage(textJoke)
                .setPositiveButton(R.string.text_dismiss,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dismiss();
                            }
                    }
                )
                .create();
    }

}