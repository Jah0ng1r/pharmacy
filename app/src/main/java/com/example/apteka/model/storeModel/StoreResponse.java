package com.example.apteka.model.storeModel;

import com.google.gson.annotations.SerializedName;

public class StoreResponse {
    @SerializedName("status")
    String status;

    public StoreResponse(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
