package com.example.apteka.activities;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.apteka.R;
import com.example.apteka.fragment.HomeFragment;
import com.example.apteka.fragment.ProfileFragment;
import com.example.apteka.fragment.QarzFragment;
import com.example.apteka.fragment.StaticFragment;
import com.example.apteka.service.NetworkBroadcast;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    FrameLayout frameLayout;
    BroadcastReceiver broadcastReceiver;


    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initViews();

        broadcastReceiver = new NetworkBroadcast();
        registerReceiver(broadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));


        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedfragment;
            switch (item.getItemId()) {
                case R.id.icHom:
                    selectedfragment = new HomeFragment();
                    break;
                case R.id.icQarz:
                    selectedfragment = new QarzFragment();
                    break;
                case R.id.icStat:
                    selectedfragment = new StaticFragment();
                    break;
                case R.id.icProf:
                    selectedfragment = new ProfileFragment();
                    break;

                default:
                    throw new IllegalStateException("Unexpected value: " + item.getItemId());
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.frame, selectedfragment).commit();


            return true;
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new HomeFragment()).commit();

    }


    private void initViews() {
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        frameLayout = findViewById(R.id.frame);
    }


    @Override
    protected void onStop() {
        unregisterReceiver(broadcastReceiver);
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(broadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

    }
}