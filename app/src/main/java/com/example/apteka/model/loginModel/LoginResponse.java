package com.example.apteka.model.loginModel;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("token")
    String token;
    @SerializedName("username")
    String name;
    @SerializedName("phone")
    String phone;

    //user_name
    //asd@gmail.com At20010211#


    public LoginResponse(String token, String name, String phone) {
        this.token = token;
        this.name = name;
        this.phone = phone;

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

  }
