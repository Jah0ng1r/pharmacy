package com.example.apteka.activities;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.apteka.R;
import com.example.apteka.adapter.HistoryAdapter;
import com.example.apteka.model.aptekaModel.AptekaModel;
import com.example.apteka.model.doriModel.DorilarModel;
import com.example.apteka.model.historyModel.AptekaHistoryModel;
import com.example.apteka.model.historyModel.HistoryModel;
import com.example.apteka.model.umumiyqarz.QarzModel;
import com.example.apteka.model.umumiyqarz.QarzResponse;
import com.example.apteka.pref_data.PreferenceData;
import com.example.apteka.service.NetworkBroadcast;
import com.example.apteka.viewmodel.AptekaHistoryViewModel;
import com.example.apteka.viewmodel.QarzViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class ShowApteka extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {
    private ImageView backIcon;
    private TextView showtext_apteka, txtQarzShow, txtDoriShow;
    private TextInputLayout txtInput;
    private FloatingActionButton fbnBuy;
    private RecyclerView historyRec;
    private HistoryAdapter adapter;
    BroadcastReceiver broadcastReceiver;
    private AutoCompleteTextView autoCompleteTextView;
    private ProgressBar progres1, progres2;

    SwipeRefreshLayout refreshLayout;

    public AptekaModel aptekaModel;
    public List<DorilarModel> list;
    public List<HistoryModel> historyModelList;
    public PreferenceData data;
    public AptekaHistoryViewModel aptekaHistoryViewModel;
    public QarzViewModel qarzViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_apteka);
        initViews();
        broadcastReceiver = new NetworkBroadcast();
        registerReceiver(broadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        getdataIntent();

        setProgresing();
        autoCompleteTextView.setOnItemClickListener(this);
        data = new PreferenceData(this);
        getViewModel();
        refreshLayout.setOnRefreshListener(this);
        fbnBuy.setOnClickListener(this);
        backIcon.setOnClickListener(view -> ShowApteka.this.onBackPressed());

    }

    private void setProgresing() {
        progres2.setVisibility(View.VISIBLE);
        progres1.setVisibility(View.VISIBLE);

    }

    private void getViewModel() {
        refreshLayout.setRefreshing(true);
        aptekaHistoryViewModel = new ViewModelProvider(ViewModelStore::new).get(AptekaHistoryViewModel.class);
        aptekaHistoryViewModel.getHistory(new AptekaHistoryModel(data.getData("token"), aptekaModel.getId())).observe(this, historyModels -> {
            if (historyModels != null) {
                historyModelList = historyModels;
                txtQarzShow.setText(getFuncQarz(historyModels));

                Collections.sort(historyModelList, new Comparator<HistoryModel>() {
                    @Override
                    public int compare(HistoryModel historyModel, HistoryModel t1) {
                        return Integer.compare(historyModel.getId(), t1.getId());
                    }
                });
                Collections.reverse(historyModelList);
            }
            adapter = new HistoryAdapter(historyModels, ShowApteka.this, aptekaModel);
            historyRec.setAdapter(adapter);
        });
        qarzViewModel = new ViewModelProvider(ViewModelStore::new).get(QarzViewModel.class);
        qarzViewModel.getQarz(new QarzModel(data.getData("token"), aptekaModel.getId())).observe(this, qarzResponse -> {
            stopProgres();
            refreshLayout.setRefreshing(false);

            txtDoriShow.setText(String.valueOf(qarzResponse.getNechi_marta_sotgan()));
        });
    }

    private void stopProgres() {
        progres2.setVisibility(View.GONE);
        progres1.setVisibility(View.GONE);
    }

    private void initViews() {
        txtInput = findViewById(R.id.txtInput);
        backIcon = findViewById(R.id.backIcon);
        showtext_apteka = findViewById(R.id.showtext_apteka);
        fbnBuy = findViewById(R.id.fbnBuy);
        historyRec = findViewById(R.id.historyRec);
        historyRec.setHasFixedSize(true);
        txtDoriShow = findViewById(R.id.txtDoriShow);
        txtQarzShow = findViewById(R.id.txtQarzShow);
        progres1 = findViewById(R.id.progres1);
        progres2 = findViewById(R.id.progres2);
        refreshLayout = findViewById(R.id.swiperef);
        autoCompleteTextView = findViewById(R.id.autCom);
    }

    private void getdataIntent() {

//            Bundle extras = getIntent().getExtras();
        aptekaModel = getIntent().getParcelableExtra("detail");
//            list = extras.getParcelableArrayList("array");
        list = getList("Set");
        setSpinner(list);
        showtext_apteka.setText(aptekaModel.getName());


    }

    public void setSpinner(List<DorilarModel> list2) {

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.simple_spinner_item, arrangeArr(list2));
//
        ((AutoCompleteTextView) Objects.requireNonNull(txtInput.getEditText())).setAdapter(adapter);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(ShowApteka.this, SellActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("array", (ArrayList<? extends Parcelable>) list);
        intent.putExtras(bundle);
        intent.putExtra("detail", aptekaModel);
        startActivity(intent);
    }

    public String[] arrangeArr(List<DorilarModel> list1) {
        String[] doris = new String[list1.size() + 2];
        doris[0] = "Barchasi";
        doris[1] = "To'lanmaganlar";
        for (int i = 0; i < list1.size(); i++) {
            doris[i + 2] = list1.get(i).getName();
        }
        return doris;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        setSearch(getId(adapterView.getItemAtPosition(i).toString(), i));


    }

    private int getId(String toString, int a) {
        int dori_id = -1;
        if (a != 1) {
            for (DorilarModel model : list) {
                if (model.getName().equals(toString)) {
                    dori_id = model.getId();
                    break;
                }
            }
        } else {
            dori_id = -2;
        }

        return dori_id;
    }

    public void setSearch(int id) {

        if (id == -1) {
            adapter.searchAd(historyModelList);
            setTxt(historyModelList);
        } else if (id == -2) {
            List<HistoryModel> newList = new ArrayList<>();
            for (HistoryModel model : historyModelList) {
                if (!model.isCompleted()) {
                    newList.add(model);
                }
            }
            setTxt(newList);

            adapter.searchAd(newList);
        } else {
            List<HistoryModel> newList = new ArrayList<>();
            for (HistoryModel model : historyModelList) {
                if (model.getDorilar_id() == id) {
                    newList.add(model);
                }
            }
            setTxt(newList);
            adapter.searchAd(newList);
        }

    }

    @Override
    public void onRefresh() {
        getViewModel();
        setProgresing();
    }

    public List<DorilarModel> getList(String a) {
        Gson gson = new Gson();
        List<DorilarModel> arrPackageData = null;
        final SharedPreferences sharedPreferences = getSharedPreferences("USER", MODE_PRIVATE);

        String json = sharedPreferences.getString(a, "");
        if (json.isEmpty()) {
            Toast.makeText(ShowApteka.this, "There is something error", Toast.LENGTH_LONG).show();
        } else {
            Type type = new TypeToken<List<DorilarModel>>() {
            }.getType();
            arrPackageData = gson.fromJson(json, type);

        }

        return arrPackageData;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);

    }

    private String getFuncQarz(List<HistoryModel> list) {
        int qarz = 0;
        for (HistoryModel model : list) {
            qarz += model.getQoldi();
        }
        return currencyFormatter(String.valueOf(qarz));
    }

    public String currencyFormatter(String num) {
        double m = Double.parseDouble(num);
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(m) + " so'm";
    }
    private void setTxt(List<HistoryModel> list){
        txtDoriShow.setText(String.valueOf(getFuncCount(list)));
        txtQarzShow.setText(getFuncQarz(list));
    }

    private String getFuncCount(List<HistoryModel> list) {
        int soni = 0;
        for (HistoryModel model : list) {
            soni += model.getQuantity();
        }
        return String.valueOf(soni);
    }
}