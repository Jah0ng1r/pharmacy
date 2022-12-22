package com.example.apteka.model.tolovModel;

import com.google.gson.annotations.SerializedName;

public class TolovModel {
    @SerializedName("token")
    String token;
    @SerializedName("qarz_id")
    int qarz_id;
    @SerializedName("summa")
    int summa;

    public TolovModel(String token, int qarz_id, int summa) {
        this.token = token;
        this.qarz_id = qarz_id;
        this.summa = summa;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getQarz_id() {
        return qarz_id;
    }

    public void setQarz_id(int qarz_id) {
        this.qarz_id = qarz_id;
    }

    public int getSumma() {
        return summa;
    }

    public void setSumma(int summa) {
        this.summa = summa;
    }
}
