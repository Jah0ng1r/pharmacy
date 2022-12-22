package com.example.apteka.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apteka.model.aptekaModel.AptekaModel;

import java.util.List;

public class QarzAdapter extends RecyclerView.Adapter<QarzAdapter.MyViewHolder> {
    Context context;
    List<AptekaModel> aptekaModelList;
    public static int[] color = {0xffe57373,
            0xfff06292,
            0xffba68c8,
            0xff9575cd,
            0xff7986cb,
            0xff64b5f6,
            0xff4fc3f7,
            0xff4dd0e1,
            0xff4db6ac,
            0xff81c784,
            0xffaed581,
            0xffff8a65,
            0xffd4e157,
            0xffffd54f,
            0xffffb74d,
            0xffa1887f,
            0xff90a4ae
    };

    public QarzAdapter(Context context, List<AptekaModel> aptekaModelList) {
        this.context = context;
        this.aptekaModelList = aptekaModelList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if (aptekaModelList != null) {
            return aptekaModelList.size();
        } else {
            return 0;
        }
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
