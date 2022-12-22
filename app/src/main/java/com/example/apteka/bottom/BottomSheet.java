package com.example.apteka.bottom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.apteka.R;
import com.example.apteka.model.aptekaModel.AptekaModel;
import com.example.apteka.model.doriModel.DorilarModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

public class BottomSheet extends BottomSheetDialogFragment {
    AptekaModel aptekaModel;
    List<DorilarModel> dorilarModelList;

    public BottomSheet(AptekaModel model, List<DorilarModel> dorilarModelList) {
        this.aptekaModel = model;
        this.dorilarModelList = dorilarModelList;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottomsheet, container, false);
        Toast.makeText(getContext(), "" + dorilarModelList.size(), Toast.LENGTH_SHORT).show();
        return v;
    }

    @Override
    public int getTheme() {
        return R.style.AppBottomSheetDialogTheme;

    }
}
