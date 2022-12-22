package com.example.apteka.model.aptekaModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class AptekaModel implements Parcelable {
   @SerializedName("id")
    int id;
   @SerializedName("name")
    String name;
   @SerializedName("description")
    String description;

    public AptekaModel(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    protected AptekaModel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
    }

    public static final Creator<AptekaModel> CREATOR = new Creator<AptekaModel>() {
        @Override
        public AptekaModel createFromParcel(Parcel in) {
            return new AptekaModel(in);
        }

        @Override
        public AptekaModel[] newArray(int size) {
            return new AptekaModel[size];
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(description);
    }
}
