package com.example.apteka.service;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.Button;

import androidx.fragment.app.FragmentManager;

import com.example.apteka.R;
import com.example.apteka.activities.HomeActivity;

public class NetworkBroadcast extends BroadcastReceiver {

    public NetworkBroadcast() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {

        Dialog dialog = new Dialog(context, R.style.generalnotitle);
        dialog.setContentView(R.layout.dialog_ak);
        dialog.setCancelable(false);
        Button btn = dialog.findViewById(R.id.btnRel);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              dialog.dismiss();
              dialog.cancel();
              onReceive(context,intent);
            }
        });

        if (!isNetworkConnected(context)) {
            dialog.show();
        } else {
            dialog.dismiss();
        }
    }

    private boolean isNetworkConnected(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
