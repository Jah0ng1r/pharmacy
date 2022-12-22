package com.example.apteka.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apteka.R;
import com.example.apteka.api.Api;
import com.example.apteka.api.ResClient;
import com.example.apteka.model.aptekaModel.AptekaModel;
import com.example.apteka.model.doriModel.DorilarModel;
import com.example.apteka.model.storeModel.StoreModel;
import com.example.apteka.pref_data.PreferenceData;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SellActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher, AdapterView.OnItemClickListener {
    private ImageView backedIcon;
    private TextView showtext_detail, txtPrice;
    private EditText edtMiqdor;
    private TextInputLayout txtDori;
    private MaterialButton btnBekor, btnDavom;
    public boolean isCheck = false;
    private AutoCompleteTextView autoCompleteTextView;
    public ArrayList<DorilarModel> list;
    public AptekaModel aptekaModel;
    public int dori_id;
    public PreferenceData data;
    Api api;
    int position, b;
    String dori_name;
    LinearLayout showPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);
        initViews();
        getdataIntent();
        btnDavom.setEnabled(false);
        api = ResClient.buildHTTPClient();
        data = new PreferenceData(this);
        btnDavom.setOnClickListener(this);
        btnBekor.setOnClickListener(this);
        autoCompleteTextView.setOnItemClickListener(this);

        edtMiqdor.addTextChangedListener(this);
        backedIcon.setOnClickListener(view -> SellActivity.this.onBackPressed());
    }

    private void initViews() {
        backedIcon = findViewById(R.id.backedIcon);
        showtext_detail = findViewById(R.id.showtext_detail);
        txtDori = findViewById(R.id.txtDori);
        btnBekor = findViewById(R.id.btnBekor);
        btnDavom = findViewById(R.id.btnDavom);
        edtMiqdor = findViewById(R.id.edtMiqdor);
        autoCompleteTextView = findViewById(R.id.autocom);
        txtPrice = findViewById(R.id.txtPrice);
        showPrice = findViewById(R.id.showPrice);
    }

    private void getdataIntent() {
        Bundle extras = getIntent().getExtras();
        list = extras.getParcelableArrayList("array");
        aptekaModel = getIntent().getParcelableExtra("detail");
        setSpinner(list);
        showtext_detail.setText(aptekaModel.getName());

    }

    private void setSpinner(ArrayList<DorilarModel> list2) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.simple_spinner_item, arrangeArr(list2));
//
        ((AutoCompleteTextView) Objects.requireNonNull(txtDori.getEditText())).setAdapter(adapter);

    }

    public String[] arrangeArr(List<DorilarModel> list1) {
        String[] doris = new String[list1.size()];
        for (int i = 0; i < list1.size(); i++) {
            doris[i] = list1.get(i).getName();
        }
        return doris;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBekor:
                SellActivity.this.onBackPressed();
                break;
            case R.id.btnDavom:
                StoreModel model = new StoreModel(data.getData("token"),
                        dori_id,
                        aptekaModel.getId(),
                        Integer.parseInt(edtMiqdor.getText().toString()), 0);
                postValue(model);
                break;
        }
    }

    private void postValue(StoreModel model1) {
        Intent intent = new Intent(SellActivity.this, ConSellActivity.class);
        intent.putExtra("storemodel", model1);
        intent.putExtra("detail", aptekaModel);
        intent.putExtra("narxi", txtPrice.getText().toString());
        intent.putExtra("dori_name", dori_name);
        startActivity(intent);

    }

    private void checkButton(boolean check) {
        if (check) {
            btnDavom.setClickable(true);
            btnDavom.setEnabled(true);
            btnDavom.setCheckable(true);
            btnDavom.setBackgroundColor(getResources().getColor(R.color.primary));
        } else {
            btnDavom.setClickable(false);
            btnDavom.setEnabled(false);
            btnDavom.setCheckable(false);
            btnDavom.setBackgroundColor(getResources().getColor(R.color.background));
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        if (charSequence.length() > 0 && isCheck) {
            boolean a = charSequence.length() > 0 && isCheck;
            b = Integer.parseInt(charSequence.toString());
            txtPrice.setText(String.valueOf(b * list.get(position).getPrice() + " so'm"));
            checkButton(a);
        } else {
            b = 0;
            boolean a = charSequence.length() > 0 && isCheck;
            checkButton(a);
            txtPrice.setText(String.valueOf(b * list.get(position).getPrice() + " so'm"));
        }

    }


    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView.getItemAtPosition(i).toString().length() > 0) {
            isCheck = true;
            showPrice.setVisibility(View.VISIBLE);
            position = i;
            edtMiqdor.setText("");
            dori_name = adapterView.getItemAtPosition(i).toString();
            getId(adapterView.getItemAtPosition(i).toString());
        }
    }

    private void getId(String a) {
        for (DorilarModel model : list) {
            if (model.getName().equals(a)) {
                dori_id = model.getId();

                break;
            }
        }


    }
}