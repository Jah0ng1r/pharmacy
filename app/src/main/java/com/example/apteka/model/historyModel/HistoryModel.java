package com.example.apteka.model.historyModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class HistoryModel implements Parcelable {
    @SerializedName("id")
    int id;
    @SerializedName("dorilar_id")
    int dorilar_id;
    @SerializedName("orderQarz_id")
    int orderQarz_id;
    @SerializedName("apteka_id")
    int apteka_id;
    @SerializedName("quantity")
    int quantity;
    @SerializedName("bought_day")
    String bought_day;
    @SerializedName("completed")
    boolean completed;
    @SerializedName("checked")
    boolean checked;
    @SerializedName("dori_name")
    String dori_name;
    @SerializedName("apteka_name")
    String apteka_name;
    @SerializedName("dori_price")
    int dori_price;
    @SerializedName("umumiy_summa")
    int umumiy_summa;
    @SerializedName("qoldi")
    int qoldi;

    public HistoryModel(int id, int dorilar_id, int orderQarz_id, int apteka_id, int quantity, String bought_day, boolean completed, boolean checked, String dori_name, String apteka_name, int dori_price, int umumiy_summa, int qoldi) {
        this.id = id;
        this.dorilar_id = dorilar_id;
        this.orderQarz_id = orderQarz_id;
        this.apteka_id = apteka_id;
        this.quantity = quantity;
        this.bought_day = bought_day;
        this.completed = completed;
        this.checked = checked;
        this.dori_name = dori_name;
        this.apteka_name = apteka_name;
        this.dori_price = dori_price;
        this.umumiy_summa = umumiy_summa;
        this.qoldi = qoldi;
    }

    protected HistoryModel(Parcel in) {
        id = in.readInt();
        dorilar_id = in.readInt();
        orderQarz_id = in.readInt();
        apteka_id = in.readInt();
        quantity = in.readInt();
        bought_day = in.readString();
        completed = in.readByte() != 0;
        checked = in.readByte() != 0;
        dori_name = in.readString();
        apteka_name = in.readString();
        dori_price = in.readInt();
        umumiy_summa = in.readInt();
        qoldi = in.readInt();
    }

    public static final Creator<HistoryModel> CREATOR = new Creator<HistoryModel>() {
        @Override
        public HistoryModel createFromParcel(Parcel in) {
            return new HistoryModel(in);
        }

        @Override
        public HistoryModel[] newArray(int size) {
            return new HistoryModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDorilar_id() {
        return dorilar_id;
    }

    public void setDorilar_id(int dorilar_id) {
        this.dorilar_id = dorilar_id;
    }

    public int getOrderQarz_id() {
        return orderQarz_id;
    }

    public void setOrderQarz_id(int orderQarz_id) {
        this.orderQarz_id = orderQarz_id;
    }

    public int getApteka_id() {
        return apteka_id;
    }

    public void setApteka_id(int apteka_id) {
        this.apteka_id = apteka_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBought_day() {
        return bought_day;
    }

    public void setBought_day(String bought_day) {
        this.bought_day = bought_day;
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

    public int getDori_price() {
        return dori_price;
    }

    public void setDori_price(int dori_price) {
        this.dori_price = dori_price;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(dorilar_id);
        parcel.writeInt(orderQarz_id);
        parcel.writeInt(apteka_id);
        parcel.writeInt(quantity);
        parcel.writeString(bought_day);
        parcel.writeByte((byte) (completed ? 1 : 0));
        parcel.writeByte((byte) (checked ? 1 : 0));
        parcel.writeString(dori_name);
        parcel.writeString(apteka_name);
        parcel.writeInt(dori_price);
        parcel.writeInt(umumiy_summa);
        parcel.writeInt(qoldi);
    }
}
