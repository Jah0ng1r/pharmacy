package com.example.apteka.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.apteka.R;
import com.example.apteka.api.Api;
import com.example.apteka.api.ResClient;
import com.example.apteka.model.addAptekaModel.AddAptekaModel;
import com.example.apteka.model.addAptekaModel.AddAptekaResponse;
import com.example.apteka.pref_data.PreferenceData;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    public ImageView backIcon;
    public MaterialButton btnAdd;
    public EditText txtName, txtDescription;
    public Api api;
    Dialog dialog;
    public PreferenceData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initViews();
        data = new PreferenceData(this);
        backIcon.setOnClickListener(view -> AddActivity.this.onBackPressed());
        btnAdd.setOnClickListener(this);
    }

    private void initViews() {
        backIcon = findViewById(R.id.backIcon1);
        btnAdd = findViewById(R.id.xabarBtn);
        txtName = findViewById(R.id.name);
        txtDescription = findViewById(R.id.desc);

    }

    @Override
    public void onClick(View view) {
        if (txtName.getText().toString().isEmpty() || txtDescription.getText().toString().isEmpty()) {
            Toast.makeText(this, "Maydonlar bo'sh", Toast.LENGTH_SHORT).show();
        } else {
            showDialog();
            AddAptekaModel model = new AddAptekaModel(data.getData("token"), txtName.getText().toString(), txtDescription.getText().toString());
            createApteka(model);
        }
    }

    private void createApteka(AddAptekaModel model) {
        api = ResClient.buildHTTPClient();
        api.addApteka(model).enqueue(new Callback<AddAptekaResponse>() {
            @Override
            public void onResponse(@NonNull Call<AddAptekaResponse> call, @NonNull Response<AddAptekaResponse> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 201) {
                        if (response.body() != null) {
                            dialog.dismiss();
                            Toast.makeText(AddActivity.this, "Apteka yaratildi!", Toast.LENGTH_SHORT).show();
                            newAcTivity();
                        }
                    }
                } else {
                    Toast.makeText(AddActivity.this, "Nimadir xato..", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<AddAptekaResponse> call, @NonNull Throwable t) {
                Toast.makeText(AddActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void newAcTivity() {
        Intent intent = new Intent(AddActivity.this,HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
    public void showDialog() {
        dialog = new Dialog(AddActivity.this);
        dialog.setContentView(R.layout.dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}