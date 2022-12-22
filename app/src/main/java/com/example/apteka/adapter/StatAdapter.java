package com.example.apteka.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apteka.R;
import com.example.apteka.model.historyModel.HistoryModel;
import com.example.apteka.model.stattisticModel.StatisticResponseModel;

import java.util.ArrayList;
import java.util.List;

public class StatAdapter extends RecyclerView.Adapter<StatAdapter.ViewHolder> {
    Context context;
    List<String> days;
    public List<StatisticResponseModel> statisticResponseModels;

    public StatAdapter(Context context, List<String> days, List<StatisticResponseModel> statisticResponseModels) {
        this.context = context;
        this.days = days;
        this.statisticResponseModels = statisticResponseModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_stat,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.statTime.setText(days.get(position));
        holder.setAdapter(days.get(position));
    }


    @Override
    public int getItemCount() {
        if (days != null) {
            return days.size();
        } else {
            return 0;
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView statRec;
        TextView statTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            statRec = itemView.findViewById(R.id.statRec);
            statTime = itemView.findViewById(R.id.textView20);
        }
        private void setAdapter(String s) {
            List<StatisticResponseModel> list = new ArrayList<>();
            for(StatisticResponseModel model : statisticResponseModels){
                if(model.getBought_day().equals(s)){
                    list.add(model);
                }
            }
            ShowStatAdapter adapter = new ShowStatAdapter(context,list);
            statRec.setAdapter(adapter);
        }


    }
    @SuppressLint("NotifyDataSetChanged")
    public void searchAd(List<String> days1, List<StatisticResponseModel> statisticResponseModels1) {
        days = new ArrayList<>();
        statisticResponseModels = new ArrayList<>();
        days.addAll(days1);
        statisticResponseModels.addAll(statisticResponseModels1);
        notifyDataSetChanged();
    }
}
