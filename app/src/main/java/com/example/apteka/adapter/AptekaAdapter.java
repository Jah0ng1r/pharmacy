package com.example.apteka.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apteka.R;
import com.example.apteka.model.aptekaModel.AptekaModel;

import java.util.ArrayList;
import java.util.List;

public class AptekaAdapter extends RecyclerView.Adapter<AptekaAdapter.MyViewHolder> {
    List<AptekaModel> list;
    AptekaAdapterInterface anInterface;
    public Context context;
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

    public AptekaAdapter(List<AptekaModel> list, Context context,AptekaAdapterInterface anInterface) {
        this.list = list;
        this.context = context;
        this.anInterface = anInterface;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.apteka_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setUp(list.get(position), position);
        holder.itemView.setOnClickListener(view -> {
            anInterface.click(list.get(position));

        });
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        } else {
            return 0;
        }
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView bgImage;
        public TextView edtname, edtDesc;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            bgImage = itemView.findViewById(R.id.bgImage);
            edtname = itemView.findViewById(R.id.edtname);
            edtDesc = itemView.findViewById(R.id.edtDesc);
        }

        public void setUp(AptekaModel model, int position) {
            bgImage.setBackgroundColor(color[position % 17]);
            edtname.setText(model.getName());
            edtDesc.setText(model.getDescription());
        }


    }

    @SuppressLint("NotifyDataSetChanged")
    public void setApteka(List<AptekaModel> modelList) {
        this.list = modelList;
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void SearchList(List<AptekaModel> models){
        list = new ArrayList<>();
        list.addAll(models);
        notifyDataSetChanged();
    }

}
