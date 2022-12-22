package com.example.apteka.model.historyModel;

import java.util.List;

public class AptekaHistoryResponseModel {
        List<HistoryModel> history;

        public AptekaHistoryResponseModel(List<HistoryModel> history) {
                this.history = history;
        }

        public List<HistoryModel> getHistory() {
                return history;
        }

        public void setHistory(List<HistoryModel> history) {
                this.history = history;
        }
}
