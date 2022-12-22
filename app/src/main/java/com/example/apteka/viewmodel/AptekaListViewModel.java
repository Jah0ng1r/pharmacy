package com.example.apteka.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.apteka.api.Api;
import com.example.apteka.api.ResClient;
import com.example.apteka.model.aptekaModel.AptekaModel;
import com.example.apteka.model.aptekaModel.AptekaTokenModel;
import com.example.apteka.model.aptekaModel.BaseApteka;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AptekaListViewModel extends ViewModel {
    private final MutableLiveData<List<AptekaModel>> storeList;

    public AptekaListViewModel() {

        storeList = new MutableLiveData<>();
    }

    public MutableLiveData<List<AptekaModel>> getStoreList(AptekaTokenModel mode) {
        makeApiCall(mode);
        return storeList;
    }

    public void makeApiCall(AptekaTokenModel model) {
        Api api = ResClient.buildHTTPClient();
        Call<BaseApteka> call = api.getAptekalar(model);
        call.enqueue(new Callback<BaseApteka>() {
            @Override
            public void onResponse(@NonNull Call<BaseApteka> call, @NonNull Response<BaseApteka> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        if (response.body() != null) {
                            storeList.postValue(response.body().getAptekalar());
                        }else {

                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<BaseApteka> call, @NonNull Throwable t) {
                storeList.postValue(null);
            }
        });
    }
}
