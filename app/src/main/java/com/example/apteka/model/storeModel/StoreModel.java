package com.example.apteka.model.storeModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StoreModel implements Parcelable {
    @SerializedName("token")
    String token;
    @SerializedName("dori_id")
    int dori_id;
    @SerializedName("apteka_id")
    int apteka_id;
    @SerializedName("quantity")
    int quantity;
    @SerializedName("status")
    int status;

    public StoreModel(String token, int dori_id, int apteka_id, int quantity, int status) {
        this.token = token;
        this.dori_id = dori_id;
        this.apteka_id = apteka_id;
        this.quantity = quantity;
        this.status = status;
    }

    protected StoreModel(Parcel in) {
        token = in.readString();
        dori_id = in.readInt();
        apteka_id = in.readInt();
        quantity = in.readInt();
        status = in.readInt();
    }

    public static final Creator<StoreModel> CREATOR = new Creator<StoreModel>() {
        @Override
        public StoreModel createFromParcel(Parcel in) {
            return new StoreModel(in);
        }

        @Override
        public StoreModel[] newArray(int size) {
            return new StoreModel[size];
        }
    };

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getDori_id() {
        return dori_id;
    }

    public void setDori_id(int dori_id) {
        this.dori_id = dori_id;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(token);
        parcel.writeInt(dori_id);
        parcel.writeInt(apteka_id);
        parcel.writeInt(quantity);
        parcel.writeInt(status);
    }
}
