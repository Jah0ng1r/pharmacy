package com.example.apteka.activities;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;

import com.example.apteka.R;
import com.example.apteka.model.aptekaModel.AptekaModel;
import com.example.apteka.model.historyModel.HistoryModel;
import com.example.apteka.model.storeModel.StoreModel;
import com.example.apteka.model.tolovModel.TolovModel;
import com.example.apteka.pref_data.PreferenceData;
import com.example.apteka.viewmodel.StoreViewModel;
import com.example.apteka.viewmodel.TolovViewModel;
import com.google.android.material.button.MaterialButton;

import java.text.DecimalFormat;
import java.text.NumberFormat;


public class ConSellActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    private StoreModel storeModel;
    private HistoryModel historyModel;
    private TextView dori_nomi, tolov_summa, dori_miqdori, txtSumma, txtSana, txtQoldiq, txtinfo;
    private MaterialButton btnTolash, btnKeyinroq, btnTol;
    private ImageView btnBack;
    private Dialog dialog;
    private AptekaModel aptekaModel;
    private PreferenceData data;
    private EditText edtSumma;
    private LinearLayout linech;
    private Group group, group1;
    public int check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_con_sell);
        initViews();
        getIntentData();
        data = new PreferenceData(this);
        btnBack.setOnClickListener(this);
        btnTolash.setOnClickListener(this);
        btnKeyinroq.setOnClickListener(this);
        btnTol.setOnClickListener(this);
        edtSumma.addTextChangedListener(this);


    }

    private void initViews() {
        tolov_summa = findViewById(R.id.textView7);
        dori_nomi = findViewById(R.id.textView10);
        dori_miqdori = findViewById(R.id.dori_miqdori);
        btnTolash = findViewById(R.id.btnTolash);
        txtinfo = findViewById(R.id.textView11);
        btnKeyinroq = findViewById(R.id.btnKeyinro);
        btnBack = findViewById(R.id.backIcon1);
        txtSumma = findViewById(R.id.textView6);
        txtSana = findViewById(R.id.txtSana);
        linech = findViewById(R.id.linech);
        group = findViewById(R.id.grp);
        group1 = findViewById(R.id.grp1);
        edtSumma = findViewById(R.id.edtSumma);
        btnTol = findViewById(R.id.btnTol);
        txtQoldiq = findViewById(R.id.txtQoldiq);

    }

    @SuppressLint("DefaultLocale")
    private void getIntentData() {
        check = getIntent().getIntExtra("check", 0);
        aptekaModel = getIntent().getParcelableExtra("detail");
        if (check == 0) {
            showLine();
            txtSana.setVisibility(View.INVISIBLE);
            txtinfo.setVisibility(View.INVISIBLE);
            storeModel = getIntent().getParcelableExtra("storemodel");
            tolov_summa.setText(getIntent().getStringExtra("narxi"));
            dori_nomi.setText(getIntent().getStringExtra("dori_name"));
            dori_miqdori.setText(String.format("%d dona", storeModel.getQuantity()));
        } else {

            txtSana.setVisibility(View.VISIBLE);
            txtinfo.setVisibility(View.VISIBLE);
            historyModel = getIntent().getParcelableExtra("model");
            if (historyModel.isCompleted()) {

                showCom();
                txtSumma.setText(R.string.tolangan_summa);

            } else {
                showGrp();
                txtSumma.setText(R.string.qarz_miqdori);

            }
            tolov_summa.setText(currencyFormatter(String.valueOf(historyModel.getUmumiy_summa())));
            dori_nomi.setText(historyModel.getDori_name());
            edtSumma.setText(String.valueOf(historyModel.getQoldi()));
            dori_miqdori.setText(String.format("%d dona", historyModel.getQuantity()));
            txtSana.setText(setTime(historyModel.getBought_day()));
            txtQoldiq.setText(currencyFormatter(String.valueOf(historyModel.getQoldi())));
        }

    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backIcon1:
                ConSellActivity.this.onBackPressed();
                break;
            case R.id.btnTolash:
                storeModel.setStatus(1);
                showDialog();
                postValue(storeModel);
                break;
            case R.id.btnKeyinro:
                storeModel.setStatus(0);
                showDialog();
                postValue(storeModel);
                break;
            case R.id.btnTol:
                showDialog();
                TolovModel tolovModel = new TolovModel(data.getData("token"), historyModel.getId(), Integer.parseInt(edtSumma.getText().toString()));
                pay(tolovModel);
                break;
        }
    }

    private void pay(TolovModel tolovModel) {
        TolovViewModel tolovViewModel = new ViewModelProvider(ViewModelStore::new).get(TolovViewModel.class);
        tolovViewModel.getResponses(tolovModel).observe(this, tolovModelResponse -> {
            dialog.dismiss();
            Toast.makeText(ConSellActivity.this, "" + tolovModelResponse.getStatus(), Toast.LENGTH_SHORT).show();
            newActivity();
        });
    }

    private void postValue(StoreModel model1) {
        StoreViewModel storeViewModel = new ViewModelProvider(ViewModelStore::new).get(StoreViewModel.class);
        storeViewModel.getResponses(model1).observe(this, storeResponse -> {
            dialog.dismiss();
            Toast.makeText(ConSellActivity.this, "" + storeResponse.getStatus(), Toast.LENGTH_SHORT).show();
            newActivity();
        });
    }

    public void showDialog() {
        dialog = new Dialog(ConSellActivity.this);
        dialog.setContentView(R.layout.dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence s, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

//        if (!editable.toString().endsWith("so'm")) {
//            String t = edtSumma.getText().toString() + " so'm";
//            edtSumma.setText(t);
//            int lastPositon = edtSumma.getText().toString().length() -5;
//            edtSumma.setSelection(lastPositon);
//        }


    }


    private void newActivity() {
        Intent intent = new Intent(ConSellActivity.this, ShowApteka.class);

        intent.putExtra("detail", aptekaModel);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent);
        finish();
    }

    private void showLine() {
        linech.setVisibility(View.VISIBLE);
        group.setVisibility(View.GONE);
        group1.setVisibility(View.GONE);
    }

    private void showGrp() {
        linech.setVisibility(View.GONE);
        group.setVisibility(View.VISIBLE);
        group1.setVisibility(View.GONE);
    }

    private void showCom() {
        linech.setVisibility(View.GONE);
        group.setVisibility(View.GONE);
        group1.setVisibility(View.VISIBLE);
    }

    private String setTime(String bought_day) {
        String[] tim = bought_day.split("T");
        return tim[0];
    }

    public String currencyFormatter(String num) {
        double m = Double.parseDouble(num);
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(m) + " so'm";
    }

    private String currencyFormatternoSom(String valueOf) {
        double m = Double.parseDouble(valueOf);
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(m);
    }
    private int noFormatCurreny(String num){

        return Integer.parseInt(num.replaceAll(" ",""));
    }
}