package com.example.apteka.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.apteka.R;
import com.example.apteka.api.Api;
import com.example.apteka.api.ResClient;
import com.example.apteka.model.loginModel.LoginModel;
import com.example.apteka.model.loginModel.LoginResponse;
import com.example.apteka.pref_data.PreferenceData;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    TextInputLayout txtName, txtPass;
    MaterialButton btnKirish;
    Api api;
    PreferenceData data;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        api = ResClient.buildHTTPClient();
        initViews();
        setDialog();

        data = new PreferenceData(this);
        btnKirish.setOnClickListener(this);
    }

    private void setDialog() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    }

    private void initViews() {
        txtName = findViewById(R.id.textInputLayout);
        txtPass = findViewById(R.id.txtPas);
        btnKirish = findViewById(R.id.btnKirish);
    }

    @Override
    public void onClick(View view) {
        if (TextUtils.isEmpty(Objects.requireNonNull(txtName.getEditText()).getText().toString())) {
            txtName.setError("Foydalanuvchi nomini kiriting");
            return;
        }
        if (TextUtils.isEmpty(Objects.requireNonNull(txtPass.getEditText()).getText().toString())) {
            txtPass.setError("Parolni kiriting");
            return;
        }
        dialog.show();
        api.getLogin(new LoginModel(txtName.getEditText().getText().toString().trim(), txtPass.getEditText().getText().toString().trim()))
                .enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                data.saveData("phone", response.body().getPhone());
                                data.saveData("username", response.body().getName());
                                data.saveData("token", response.body().getToken());
                                Toast.makeText(LoginActivity.this, "Ma'lumotlar tasdiqlandi!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                                finish();
                                dialog.dismiss();
                            }
                        } else {
                            dialog.dismiss();
                            Toast.makeText(LoginActivity.this, "Ma'lumotlar xato!", Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                        t.printStackTrace();
                        dialog.dismiss();
                        Toast.makeText(LoginActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

}