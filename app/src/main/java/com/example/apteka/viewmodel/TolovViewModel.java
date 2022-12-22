package com.example.apteka.viewmodel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.apteka.api.Api;
import com.example.apteka.api.ResClient;
import com.example.apteka.model.tolovModel.TolovModel;
import com.example.apteka.model.tolovModel.TolovModelResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TolovViewModel extends ViewModel {
    private final MutableLiveData<TolovModelResponse> tolovResponse;

    public TolovViewModel() {
        tolovResponse = new MutableLiveData<>();

    }
    public MutableLiveData<TolovModelResponse> getResponses(TolovModel model) {
        makeApiCall(model);
        return tolovResponse;
    }

    private void makeApiCall(TolovModel model) {
        Api api = ResClient.buildHTTPClient();
        api.payQarz(model).enqueue(new Callback<TolovModelResponse>() {
            @Override
            public void onResponse(@NonNull Call<TolovModelResponse> call, @NonNull Response<TolovModelResponse> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        if (response.body() != null) {
                            tolovResponse.setValue(response.body());
                        }
                    }
                }else {
                    Log.e("xato",response.errorBody()+"");
                    Log.e("xato",response.code()+"");
                }
            }

            @Override
            public void onFailure(@NonNull Call<TolovModelResponse> call, @NonNull Throwable t) {

            }
        });
    }
}
