package com.example.apteka.model.aptekaModel;

import com.google.gson.annotations.SerializedName;

public class AptekaTokenModel {

    @SerializedName("token")
    String token;

    public AptekaTokenModel(String token) {
        this.token = token;
    }
}
