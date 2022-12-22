package com.example.apteka.model.stattisticModel;

import com.google.gson.annotations.SerializedName;

public class StatisticModel {
    @SerializedName("token")
    String token;
    @SerializedName("day")
    String day;
    @SerializedName("days")
    int days;

    public StatisticModel(String token, String day, int days) {
        this.token = token;
        this.day = day;
        this.days = days;
    }
}
