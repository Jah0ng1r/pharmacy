package com.example.apteka.model.addAptekaModel;

import com.google.gson.annotations.SerializedName;

public class AddAptekaResponse {
    @SerializedName("token")
    String token;
    @SerializedName("user")
    int user;
    @SerializedName("description")
    String description;

    public AddAptekaResponse(String token, int user, String description) {
        this.token = token;
        this.user = user;
        this.description = description;
    }
}
