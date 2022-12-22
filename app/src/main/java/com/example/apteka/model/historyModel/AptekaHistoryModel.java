package com.example.apteka.model.historyModel;

import com.google.gson.annotations.SerializedName;

public class AptekaHistoryModel {
    @SerializedName("token")
    String token;
    @SerializedName("apteka_id")
    int apteka_id;

    public AptekaHistoryModel(String token, int apteka_id) {
        this.token = token;
        this.apteka_id = apteka_id;
    }
}
