package com.example.apteka.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apteka.R;
import com.example.apteka.model.stattisticModel.StatisticResponseModel;

import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.List;

public class ShowStatAdapter extends RecyclerView.Adapter<ShowStatAdapter.ViewMyHolder> {
    Context context;
    List<StatisticResponseModel> list;

    public ShowStatAdapter(Context context, List<StatisticResponseModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewMyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_showstat, parent, false);
        return new ViewMyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewMyHolder holder, int position) {
        if (position % 2 == 0) {
            holder.constraintLayout.setBackgroundColor(context.getResources().getColor(R.color.soft));
        } else {
            holder.constraintLayout.setBackgroundColor(context.getResources().getColor(R.color.white));
        }

        holder.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        } else {
            return 0;
        }
    }

    public class ViewMyHolder extends RecyclerView.ViewHolder {
        ConstraintLayout constraintLayout;
        TextView txtSumma, txtNumber, txtName;

        public ViewMyHolder(@NonNull View itemView) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.consColor);
            txtName = itemView.findViewById(R.id.textView21);
            txtNumber = itemView.findViewById(R.id.txtNumber);
            txtSumma = itemView.findViewById(R.id.txtSumma);
        }

        public void setText(StatisticResponseModel model) {
            if(!model.isCompleted()){
                txtSumma.setTextColor(context.getResources().getColor(R.color.tolanmadi));
            }
            txtSumma.setText(currencyFormatter(String.valueOf(model.getUmumiy_summa())));
            txtNumber.setText(MessageFormat.format("{0} ta", model.getQuantity()));
            txtName.setText(String.valueOf(model.getDori_name()));
        }
    }

    public String currencyFormatter(String num) {
        double m = Double.parseDouble(num);
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(m) + " so'm";
    }
}
