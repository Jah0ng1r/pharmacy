package com.example.apteka.model.loginModel;

import com.google.gson.annotations.SerializedName;

public class LoginModel {
    @SerializedName("username")
    String name;
    @SerializedName("password")
    String password;

    public LoginModel(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
