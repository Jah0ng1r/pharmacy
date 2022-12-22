package com.example.apteka.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apteka.Animations;
import com.example.apteka.R;
import com.example.apteka.activities.ConSellActivity;
import com.example.apteka.model.aptekaModel.AptekaModel;
import com.example.apteka.model.historyModel.HistoryModel;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    List<HistoryModel> list;
    Context context;
    boolean a = false;
    AptekaModel aptekaModel;

    public HistoryAdapter(List<HistoryModel> list, Context context,AptekaModel aptekaModel) {
        this.list =list;
        this.context = context;
        this.aptekaModel = aptekaModel;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.history_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.setUp(list.get(position), context);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ConSellActivity.class);
                intent.putExtra("check",1);
                intent.putExtra("detail", aptekaModel);
                intent.putExtra("model",list.get(holder.getAdapterPosition()));
                context.startActivity(intent);
            }
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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView  checkIcon;
        ConstraintLayout expandCons;
        TextView completed, txtName;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkIcon = itemView.findViewById(R.id.checkIcon);
            expandCons = itemView.findViewById(R.id.expandCons);
            txtName = itemView.findViewById(R.id.textView3);
            completed = itemView.findViewById(R.id.textView4);
        }

        public void setUp(HistoryModel model, Context context1) {
            if (model.isCompleted()) {
                completed.setTextColor(context1.getResources().getColor(R.color.tolandi));
                completed.setText(R.string.tolandi);
                checkIcon.setImageResource(R.drawable.ic_outline_check_circle_24);
                checkIcon.setColorFilter(ContextCompat.getColor(context1, R.color.tolandi));


            } else {
                completed.setTextColor(context1.getResources().getColor(R.color.tolanmadi));
                completed.setText(R.string.tolanmadi);
                checkIcon.setImageResource(R.drawable.ic_outline_hourglass_empty_24);
                checkIcon.setColorFilter(ContextCompat.getColor(context1, R.color.tolanmadi));


            }
            txtName.setText(model.getDori_name());

            //** apteka nomi, dori nomi jonatish kere


        }

    }


    private boolean toggleLayout(boolean isExpanded, View v, ConstraintLayout layoutExpand) {
        Animations.toggleArrow(v, isExpanded);
        if (isExpanded) {
            Animations.expand(layoutExpand);
        } else {
            Animations.collapse(layoutExpand);
        }
        return isExpanded;

    }

    @SuppressLint("NotifyDataSetChanged")
    public void searchAd(List<HistoryModel> models) {
        list = new ArrayList<>();
        list.addAll(models);
        notifyDataSetChanged();
    }
}

