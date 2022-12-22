package com.example.apteka.model.stattisticModel;

import com.google.gson.annotations.SerializedName;

public class StatisticResponseModel {
    @SerializedName("id")
    String id;
    @SerializedName("dori_name")
    String dori_name;
    @SerializedName("apteka_name")
    String apteka_name;
    @SerializedName("bought_day")
    String bought_day;
    @SerializedName("quantity")
    int quantity;
    @SerializedName("umumiy_summa")
    int umumiy_summa;
    @SerializedName("qoldi")
    int qoldi;
    @SerializedName("completed")
    boolean completed;
    @SerializedName("checked")
    boolean checked;
    @SerializedName("dorilar")
    int dorilar;
    @SerializedName("orderQarz")
    int orderQarz;
    @SerializedName("apteka")
    int apteka;

    public StatisticResponseModel(String id, String dori_name, String apteka_name, String bought_day, int quantity, int umumiy_summa, int qoldi, boolean completed, boolean checked, int dorilar, int orderQarz, int apteka) {
        this.id = id;
        this.dori_name = dori_name;
        this.apteka_name = apteka_name;
        this.bought_day = bought_day;
        this.quantity = quantity;
        this.umumiy_summa = umumiy_summa;
        this.qoldi = qoldi;
        this.completed = completed;
        this.checked = checked;
        this.dorilar = dorilar;
        this.orderQarz = orderQarz;
        this.apteka = apteka;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDori_name() {
        return dori_name;
    }

    public void setDori_name(String dori_name) {
        this.dori_name = dori_name;
    }

    public String getApteka_name() {
        return apteka_name;
    }

    public void setApteka_name(String apteka_name) {
        this.apteka_name = apteka_name;
    }

    public String getBought_day() {
        return bought_day;
    }

    public void setBought_day(String bought_day) {
        this.bought_day = bought_day;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUmumiy_summa() {
        return umumiy_summa;
    }

    public void setUmumiy_summa(int umumiy_summa) {
        this.umumiy_summa = umumiy_summa;
    }

    public int getQoldi() {
        return qoldi;
    }

    public void setQoldi(int qoldi) {
        this.qoldi = qoldi;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getDorilar() {
        return dorilar;
    }

    public void setDorilar(int dorilar) {
        this.dorilar = dorilar;
    }

    public int getOrderQarz() {
        return orderQarz;
    }

    public void setOrderQarz(int orderQarz) {
        this.orderQarz = orderQarz;
    }

    public int getApteka() {
        return apteka;
    }

    public void setApteka(int apteka) {
        this.apteka = apteka;
    }
}
