package com.example.daniele.cntest.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.daniele.cntest.R;
import com.example.daniele.cntest.ui.dialog.RandomJokeDialog;
import com.example.daniele.cntest.ui.fragment.FragmentListener;
import com.example.daniele.cntest.ui.fragment.MainFragment;
import com.example.daniele.cntest.ui.fragment.TextInputFragment;

public class MainActivity extends AppCompatActivity implements FragmentListener {

    private MainFragment mMainFragment;
    private RandomJokeDialog mJokeDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (findViewById(R.id.main_fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            mMainFragment = MainFragment.newInstance();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.main_fragment_container, mMainFragment, MainFragment.FRAGMENT_TAG);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onItemSelected(String fragmentTag) {
        Fragment myFragment = null;
        if(fragmentTag.equals(TextInputFragment.FRAGMENT_TAG)){
            myFragment = TextInputFragment.newInstance();
        }
        else{

        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_fragment_container, myFragment, fragmentTag);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onJokeReceived(String textJoke) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag(RandomJokeDialog.DIALOG_TAG);
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        mJokeDialog = RandomJokeDialog.newInstance(textJoke);
        mJokeDialog.setCancelable(false);
        mJokeDialog.show(ft, RandomJokeDialog.DIALOG_TAG);
    }
}
