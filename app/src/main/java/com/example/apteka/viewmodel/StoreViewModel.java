package com.example.apteka.viewmodel;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.apteka.api.Api;
import com.example.apteka.api.ResClient;
import com.example.apteka.model.storeModel.StoreModel;
import com.example.apteka.model.storeModel.StoreResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoreViewModel extends ViewModel {
    private final MutableLiveData<StoreResponse> responses;

    public StoreViewModel() {
        responses = new MutableLiveData<>();

    }

    public MutableLiveData<StoreResponse> getResponses(StoreModel model) {
        makeApiCall(model);
        return responses;
    }

    public void makeApiCall(StoreModel model1) {
        Api api = ResClient.buildHTTPClient();
        api.senData(model1).enqueue(new Callback<StoreResponse>() {
            @Override
            public void onResponse(@NonNull Call<StoreResponse> call, @NonNull Response<StoreResponse> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        if (response.body() != null) {
                            responses.setValue(response.body());
                        }
                    }
                }else {
                    Log.e("xato",response.errorBody()+"");
                    Log.e("xato",response.code()+"");
                }
            }

            @Override
            public void onFailure(@NonNull Call<StoreResponse> call, @NonNull Throwable t) {

            }
        });
    }
}
