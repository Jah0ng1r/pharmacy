package com.example.apteka.model.tolovModel;

import com.google.gson.annotations.SerializedName;

import retrofit2.http.POST;

public class TolovModelResponse {

    @SerializedName("satus")
    String status;

    public TolovModelResponse(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
