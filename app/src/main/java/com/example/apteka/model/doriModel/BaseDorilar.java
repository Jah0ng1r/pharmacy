package com.example.apteka.model.doriModel;

import java.util.List;

public class BaseDorilar {
    List<DorilarModel> dorilarModels;

    public BaseDorilar(List<DorilarModel> dorilarModels) {
        this.dorilarModels = dorilarModels;
    }

    public List<DorilarModel> getDorilarModels() {
        return dorilarModels;
    }

    public void setDorilarModels(List<DorilarModel> dorilarModels) {
        this.dorilarModels = dorilarModels;
    }
}
