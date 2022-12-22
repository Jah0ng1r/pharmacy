package com.example.apteka.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.apteka.model.aptekaModel.AptekaModel;

public class ItemViewModel extends ViewModel {

    private final MutableLiveData<AptekaModel> aptekaModelMutableLiveData = new MutableLiveData<>();

    public void setData(AptekaModel model){
        aptekaModelMutableLiveData.postValue(model);

    }
}
