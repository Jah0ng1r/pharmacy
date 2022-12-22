package com.example.apteka.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apteka.R;
import com.example.apteka.api.Api;
import com.example.apteka.api.ResClient;
import com.example.apteka.model.storeModel.StoreModel;
import com.example.apteka.model.storeModel.StoreResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


}