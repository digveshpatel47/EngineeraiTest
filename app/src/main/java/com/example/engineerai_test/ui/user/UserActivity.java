package com.example.engineerai_test.ui.user;

import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.engineerai_test.R;

public class UserActivity extends AppCompatActivity implements UserFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, UserFragment.newInstance());
        transaction.commit();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
