package com.example.apteka.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.apteka.api.Api;
import com.example.apteka.api.ResClient;
import com.example.apteka.model.historyModel.AptekaHistoryModel;
import com.example.apteka.model.historyModel.AptekaHistoryResponseModel;
import com.example.apteka.model.historyModel.HistoryModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AptekaHistoryViewModel extends ViewModel {
    private final MutableLiveData<List<HistoryModel>> getHistories;

    public AptekaHistoryViewModel() {
        getHistories = new MutableLiveData<>();
    }

    public MutableLiveData<List<HistoryModel>> getHistory(AptekaHistoryModel historyModel) {
        makeApiCall(historyModel);
        return getHistories;
    }

    public void makeApiCall(AptekaHistoryModel historyModel) {
        Api api = ResClient.buildHTTPClient();
        api.getHistory(historyModel).enqueue(new Callback<AptekaHistoryResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<AptekaHistoryResponseModel> call, @NonNull Response<AptekaHistoryResponseModel> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        if (response.body() != null) {
                            getHistories.postValue(response.body().getHistory());
                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<AptekaHistoryResponseModel> call, @NonNull Throwable t) {

            }
        });
    }
}
