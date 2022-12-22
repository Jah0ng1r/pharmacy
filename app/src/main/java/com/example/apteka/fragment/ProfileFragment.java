package com.example.apteka.fragment;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.apteka.R;
import com.example.apteka.activities.LoginActivity;
import com.example.apteka.pref_data.PreferenceData;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.Objects;

public class ProfileFragment extends Fragment implements View.OnClickListener {
    RelativeLayout btnExit,btnNotification,btnBuyurtmalar;
    PreferenceData data;
    TextView txtName;


    public ProfileFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);;
        initViews(view);
        btnExit.setOnClickListener(this);
        data = new PreferenceData(requireContext());
        txtName.setText(data.getData("username"));
        return view;
    }

    private void initViews(View view) {
        btnExit = view.findViewById(R.id.btnExit);
        txtName = view.findViewById(R.id.txtName);
        btnBuyurtmalar = view.findViewById(R.id.btnBuyurtmalar);
        btnNotification = view.findViewById(R.id.btnNotification);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnExit:
                exit();
                break;
            case R.id.btnBuyurtmalar:

                break;
            case R.id.btnNotification:

                break;
        }
    }

    private void exit() {
        new MaterialAlertDialogBuilder(requireContext())
                .setTitle("Chiqish 🛑")
                .setMessage("Chindan ham hisobdan chiqishni xohlaysizmi?")
                .setPositiveButton("Ha", (dialogInterface, i) -> {
                    data.clearData();
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                    requireActivity().finish();
                })
                .setNegativeButton("Yo'q", (dialogInterface, i) -> dialogInterface.cancel())
                .show();
    }
}