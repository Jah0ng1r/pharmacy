package com.example.apteka.activities;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apteka.R;
import com.example.apteka.pref_data.PreferenceData;
import com.example.apteka.service.NetworkBroadcast;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {
    TextView nameSos;
    Animation animation;
    PreferenceData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);


        data = new PreferenceData(this);
        nameSos = findViewById(R.id.namsos);
        animation = AnimationUtils.loadAnimation(this, R.anim.bottom_anim);
        nameSos.setAnimation(animation);
        new Handler().postDelayed(() -> {
           if(data.getData("token").isEmpty()){
               startActivity(new Intent(SplashActivity.this, LoginActivity.class));
           }else {
               startActivity(new Intent(SplashActivity.this, HomeActivity.class));
           }
            finish();
        }, 2200);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}