package com.example.apteka.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.apteka.api.Api;
import com.example.apteka.api.ResClient;
import com.example.apteka.model.umumiyqarz.QarzModel;
import com.example.apteka.model.umumiyqarz.QarzResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QarzViewModel extends ViewModel {
     MutableLiveData<QarzResponse> getQarzList;

     public QarzViewModel (){
         getQarzList = new MutableLiveData<>();
     }
     public MutableLiveData<QarzResponse> getQarz(QarzModel qarzModel){
         makeApiCall(qarzModel);
         return getQarzList;
     }

    private void makeApiCall(QarzModel model) {
        Api api = ResClient.buildHTTPClient();
        api.getQarz(model).enqueue(new Callback<QarzResponse>() {
            @Override
            public void onResponse(@NonNull Call<QarzResponse> call, @NonNull Response<QarzResponse> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        if (response.body() != null) {
                            getQarzList.postValue(response.body());
                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<QarzResponse> call, @NonNull Throwable t) {

            }
        });
    }
}
