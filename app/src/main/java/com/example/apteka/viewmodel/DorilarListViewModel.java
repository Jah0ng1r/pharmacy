package com.example.apteka.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.apteka.api.Api;
import com.example.apteka.api.ResClient;
import com.example.apteka.model.doriModel.DorilarModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DorilarListViewModel extends ViewModel {

    private final MutableLiveData<List<DorilarModel>> dorilarList;

    public DorilarListViewModel() {
        dorilarList = new MutableLiveData<>();
    }

    public MutableLiveData<List<DorilarModel>> getStoreList(){
        makeApiCall();
        return dorilarList;
    }

    public void makeApiCall() {
        Api api = ResClient.buildHTTPClient();
        Call<List<DorilarModel>> call = api.getDorilar();
        call.enqueue(new Callback<List<DorilarModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<DorilarModel>> call, @NonNull Response<List<DorilarModel>> response) {
                if(response.isSuccessful()){
                    if(response.code() == 200){
                        if (response.body() != null) {
                            dorilarList.postValue(response.body());
                        }

                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<DorilarModel>> call, @NonNull Throwable t) {
                dorilarList.postValue(null);
            }
        });

    }
}
