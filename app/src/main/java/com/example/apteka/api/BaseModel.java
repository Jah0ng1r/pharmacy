package com.example.apteka.api;

import java.util.List;

public class BaseModel<T> {

    String eror;
    String status;
    T data;

    public BaseModel(String eror, String status, T data) {
        this.eror = eror;
        this.status = status;
        this.data = data;
    }

    public String getEror() {
        return eror;
    }

    public void setEror(String eror) {
        this.eror = eror;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
