package com.example.apteka.model.addAptekaModel;

import com.google.gson.annotations.SerializedName;

public class AddAptekaModel {
    @SerializedName("token")
    String token;
    @SerializedName("name")
    String name;
    @SerializedName("description")
    String description;

    public AddAptekaModel(String token, String name, String description) {
        this.token = token;
        this.name = name;
        this.description = description;
    }
}
