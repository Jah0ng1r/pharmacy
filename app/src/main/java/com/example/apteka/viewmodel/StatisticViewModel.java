package com.example.apteka.viewmodel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.apteka.api.Api;
import com.example.apteka.api.ResClient;
import com.example.apteka.model.doriModel.DorilarModel;
import com.example.apteka.model.stattisticModel.StatisticListResponse;
import com.example.apteka.model.stattisticModel.StatisticModel;
import com.example.apteka.model.stattisticModel.StatisticResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatisticViewModel extends ViewModel {
    private final MutableLiveData<List<StatisticResponseModel>> responses;

    public StatisticViewModel() {
        responses = new MutableLiveData<>();

    }

    public MutableLiveData<List<StatisticResponseModel>> getResponses(StatisticModel model) {
        makeApiCall(model);
        return responses;
    }

    private void makeApiCall(StatisticModel model1) {
        Api api = ResClient.buildHTTPClient();
        api.getStatisticHistory(model1).enqueue(new Callback<StatisticListResponse>() {
            @Override
            public void onResponse(@NonNull Call<StatisticListResponse> call, @NonNull Response<StatisticListResponse> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        if (response.body() != null) {
                            responses.setValue(response.body().getWanted_day());
                        }else {

                            responses.setValue(null);
                        }
                    }
                    else {
                        responses.setValue(null);

                    }
                } else {
                    Log.e("xato", response.errorBody() + "");
                    Log.e("xato", response.code() + "");
                }
            }

            @Override
            public void onFailure(@NonNull Call<StatisticListResponse> call, @NonNull Throwable t) {
                responses.setValue(null);
            }
        });
    }

}
