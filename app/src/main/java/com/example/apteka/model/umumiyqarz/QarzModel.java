package com.example.apteka.model.umumiyqarz;

import com.google.gson.annotations.SerializedName;

public class QarzModel {
    @SerializedName("token")
    String token;
    @SerializedName("apteka_id")
    int apteka_id;

    public QarzModel(String token, int apteka_id) {
        this.token = token;
        this.apteka_id = apteka_id;
    }

}
