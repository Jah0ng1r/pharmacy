package com.example.apteka.api;

import android.util.Log;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;

import java.util.concurrent.TimeUnit;

public class ResClient {
    public static String BASE_URL = "https://sos-pharm.uz/";

    public static Api buildHTTPClient() {

        //TODO Replace with your URL
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(Api.class);
    }

    private static OkHttpClient getClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(provideHttpLoggingInterceptor())
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    private static HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor =
                new HttpLoggingInterceptor(message -> Log.d("HTTP", message));
        httpLoggingInterceptor.setLevel(BODY);
        return httpLoggingInterceptor;
    }
}
