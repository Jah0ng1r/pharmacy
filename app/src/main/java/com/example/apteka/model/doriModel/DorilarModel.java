package com.example.apteka.model.doriModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class DorilarModel implements Parcelable {
    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;
    @SerializedName("price")
    int price;
    @SerializedName("available")
    int available;

    public DorilarModel(int id, String name, int price, int available) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.available = available;
    }

    protected DorilarModel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        price = in.readInt();
        available = in.readInt();
    }

    public static final Creator<DorilarModel> CREATOR = new Creator<DorilarModel>() {
        @Override
        public DorilarModel createFromParcel(Parcel in) {
            return new DorilarModel(in);
        }

        @Override
        public DorilarModel[] newArray(int size) {
            return new DorilarModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeInt(price);
        parcel.writeInt(available);
    }

}
