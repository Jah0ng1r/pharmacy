package com.example.apteka.model.stattisticModel;

import java.util.List;

public class StatisticListResponse {
    List<StatisticResponseModel> wanted_day;

    public StatisticListResponse(List<StatisticResponseModel> wanted_day) {
        this.wanted_day = wanted_day;
    }

    public List<StatisticResponseModel> getWanted_day() {
        return wanted_day;
    }

    public void setWanted_day(List<StatisticResponseModel> wanted_day) {
        this.wanted_day = wanted_day;
    }
}
