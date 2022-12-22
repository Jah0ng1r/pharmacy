package com.example.apteka.model.aptekaModel;

import com.example.apteka.model.aptekaModel.AptekaModel;

import java.util.List;

public class BaseApteka{
    List<AptekaModel> aptekalar;

    public BaseApteka(List<AptekaModel> aptekalar) {
        this.aptekalar = aptekalar;
    }

    public List<AptekaModel> getAptekalar() {
        return aptekalar;
    }

    public void setAptekalar(List<AptekaModel> aptekalar) {
        this.aptekalar = aptekalar;
    }
}
