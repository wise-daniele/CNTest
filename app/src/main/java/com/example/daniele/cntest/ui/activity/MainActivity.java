package com.example.daniele.cntest.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.daniele.cntest.R;
import com.example.daniele.cntest.ui.fragment.MainFragment;
import com.example.daniele.cntest.ui.fragment.TextInputFragment;

public class MainActivity extends AppCompatActivity implements MainFragment.FragmentListener {

    private MainFragment mMainFragment;

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
        /*
        Bundle args = new Bundle();
        args.putString(PropertyDetailFragment.PROPERTY_ID, property.getId());
        detailFragment.setArguments(args);
        */
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_fragment_container, myFragment, fragmentTag);
        ft.addToBackStack(null);
        ft.commit();
    }
}
