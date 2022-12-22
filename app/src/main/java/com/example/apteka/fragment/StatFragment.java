package com.example.apteka.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.Group;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.apteka.R;
import com.example.apteka.adapter.StatAdapter;
import com.example.apteka.model.doriModel.DorilarModel;
import com.example.apteka.model.stattisticModel.StatisticModel;
import com.example.apteka.model.stattisticModel.StatisticResponseModel;
import com.example.apteka.pref_data.PreferenceData;
import com.example.apteka.viewmodel.StatisticViewModel;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class StatFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener {

    private TextView txtTolovlar, txtQarzlar, numberDori;
    private SwipeRefreshLayout swipeRefreshLayout;
    public StatisticViewModel statisticViewModel;
    public PreferenceData data;
    public int position;
    public String formattedDate;
    public int i;
    private TextInputLayout txtInput;
    int day, completePosition;
    List<StatisticResponseModel> staticList;
    StatAdapter adapter;
    public List<DorilarModel> list;
    Group showGr, invisibleGr;
    RecyclerView recyclerView;
    NestedScrollView nestedScrollView;

    public AutoCompleteTextView autoCompleteTextView;

    public StatFragment(int position, String formattedDate) {

        this.position = position;
        this.formattedDate = formattedDate;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stat, container, false);
        initViews(view);
        day = Integer.parseInt(new SimpleDateFormat("dd", Locale.getDefault()).format(new Date()));

        data = new PreferenceData(requireContext());
        list = getList("Set");
        setSpinner(list);
        checkPosition(position);
        autoCompleteTextView.setOnItemClickListener(this);


        return view;
    }

    private void initViews(View view) {
        txtTolovlar = view.findViewById(R.id.textView17);
        txtQarzlar = view.findViewById(R.id.textView18);
        swipeRefreshLayout = view.findViewById(R.id.swipeStat);
        showGr = view.findViewById(R.id.showgr);
        invisibleGr = view.findViewById(R.id.invisgr);
        recyclerView = view.findViewById(R.id.recStat);
        numberDori = view.findViewById(R.id.numberDori);
        nestedScrollView = view.findViewById(R.id.lin11);
        autoCompleteTextView = view.findViewById(R.id.autCom1);
        txtInput = view.findViewById(R.id.txtInput1);

    }

    private void setSpinner(List<DorilarModel> list2) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.simple_spinner_item, arrangeArr(list2));
        ((AutoCompleteTextView) Objects.requireNonNull(txtInput.getEditText())).setAdapter(adapter);
    }

    private void checkPosition(int position) {
        switch (position) {
            case 0:
                today(null, 1);
                setNested();
                swipeRefreshLayout.setOnRefreshListener(this);
                break;
            case 1:
                today(null, 3);
                setNested();
                swipeRefreshLayout.setOnRefreshListener(this);
                break;
            case 2:
                today(null, 7);
                setNested();
                swipeRefreshLayout.setOnRefreshListener(this);
                break;
            case 3:
                today(null, day);
                setNested();
                swipeRefreshLayout.setOnRefreshListener(this);
                break;

            case 4:
                today(null, 1000);
                setNested();
                swipeRefreshLayout.setOnRefreshListener(this);
                break;

            case 5:
                today(formattedDate, 1);
                setNested();
                swipeRefreshLayout.setOnRefreshListener(this);
                break;
        }
    }


    private void setToday(List<StatisticResponseModel> statisticResponseModels) {
        setAllAdapter(getBoughDay(statisticResponseModels), statisticResponseModels);
        setTxt(getQarz(statisticResponseModels), getTolov(statisticResponseModels), getDorilarSoni(statisticResponseModels));

    }

    private List<String> getBoughDay(List<StatisticResponseModel> statisticResponseModels) {
        List<String> days = new ArrayList<>();
        for (StatisticResponseModel model : statisticResponseModels) {
            if (!days.contains(model.getBought_day())) {
                days.add(model.getBought_day());
            }
        }
        return days;
    }

    private int getQarz(List<StatisticResponseModel> statisticResponseModels) {
        int qarz = 0;
        for (StatisticResponseModel model : statisticResponseModels) {
            qarz += model.getQoldi();
        }
        return qarz;
    }

    private int getTolov(List<StatisticResponseModel> statisticResponseModels) {
        int tolov = 0;
        for (StatisticResponseModel model : statisticResponseModels) {
            tolov += model.getUmumiy_summa();
        }
        return tolov;
    }

    private void setAllAdapter(List<String> days, List<StatisticResponseModel> statisticResponseModels) {
        adapter = new StatAdapter(getContext(), days, statisticResponseModels);
        recyclerView.setAdapter(adapter);
    }

    private String getDorilarSoni(List<StatisticResponseModel> statisticResponseModels) {
        int soni = 0;
        for (StatisticResponseModel model : statisticResponseModels) {
            soni += model.getQuantity();
        }

        return soni + " ta";
    }


    private void setTxt(int qarz, int tolov, String dorilar_soni) {
        txtQarzlar.setText(currencyFormatter(String.valueOf(qarz)));
        txtTolovlar.setText(currencyFormatter(String.valueOf(tolov)));
        numberDori.setText(dorilar_soni);
    }

    public String currencyFormatter(String num) {
        double m = Double.parseDouble(num);
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(m) + " so'm";
    }


    private void today(String a, int days) {
        String date;
        if (a != null) {
            date = a;
        } else {
            date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        }
        swipeRefreshLayout.setRefreshing(true);
        statisticViewModel = new ViewModelProvider(ViewModelStore::new).get(StatisticViewModel.class);
        statisticViewModel.getResponses(new StatisticModel(data.getData("token"), date, days)).observe(getViewLifecycleOwner(), statisticResponseModels -> {
            swipeRefreshLayout.setRefreshing(false);

            if (statisticResponseModels == null || statisticResponseModels.isEmpty()) {
                showGr.setVisibility(View.VISIBLE);
                invisibleGr.setVisibility(View.GONE);
            } else {
                showGr.setVisibility(View.GONE);
                invisibleGr.setVisibility(View.VISIBLE);
                Collections.reverse(statisticResponseModels);
                staticList = statisticResponseModels;
                setToday(statisticResponseModels);
            }
        });
    }


    @Override
    public void onRefresh() {
        switch (position) {
            case 0:
                today(null, 1);
                setAutoCompleteTextView();
                break;
            case 1:
                today(null, 3);
                setAutoCompleteTextView();

                break;
            case 2:
                today(null, 7);
                setAutoCompleteTextView();

                break;
            case 3:
                today(null, day);
                setAutoCompleteTextView();

                break;
            case 4:
                today(null, 365);
                setAutoCompleteTextView();

                break;
            case 5:
                today(formattedDate, 1);
                setAutoCompleteTextView();

                break;
        }

    }

    public void setNested() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            nestedScrollView.setOnScrollChangeListener((View.OnScrollChangeListener) (view, i, i1, i2, i3) ->
                    swipeRefreshLayout.setEnabled(i1 == 0));
        }
    }

    public List<DorilarModel> getList(String a) {
        Gson gson = new Gson();
        List<DorilarModel> arrPackageData = null;
        final SharedPreferences sharedPreferences = requireContext().getSharedPreferences("USER", Context.MODE_PRIVATE);
        String json = sharedPreferences.getString(a, "");
        if (json.isEmpty()) {
            Toast.makeText(getContext(), "There is something error", Toast.LENGTH_LONG).show();
        } else {
            Type type = new TypeToken<List<DorilarModel>>() {
            }.getType();
            arrPackageData = gson.fromJson(json, type);

        }

        return arrPackageData;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        completePosition = getId(adapterView.getItemAtPosition(i).toString(), i);
        setSearch(completePosition);
    }

    private List<StatisticResponseModel> getQarzdorlar(List<StatisticResponseModel> modelList) {
        List<StatisticResponseModel> qarzdorlar = new ArrayList<>();
        for (StatisticResponseModel model : modelList) {
            if (!model.isCompleted()) {
                qarzdorlar.add(model);
            }
        }
        return qarzdorlar;
    }

    private List<StatisticResponseModel> getDoriWithId(int id, List<StatisticResponseModel> modelList) {
        List<StatisticResponseModel> doriWithId = new ArrayList<>();
        for (StatisticResponseModel model : modelList) {
            if (model.getDorilar() == id) {
                doriWithId.add(model);
            }
        }
        return doriWithId;
    }

    public void setSearch(int id) {
        if (id == -1) {
            adapter.searchAd(getBoughDay(staticList), staticList);
            setTxt(getQarz(staticList), getTolov(staticList), getDorilarSoni(staticList));

        } else if (id == -2) {
            adapter.searchAd(getBoughDay(getQarzdorlar(staticList)), getQarzdorlar(staticList));

        } else {
            adapter.searchAd(getBoughDay(getDoriWithId(id, staticList)), getDoriWithId(id, staticList));
            setTxt(getQarz(getDoriWithId(id, staticList)), getTolov(getDoriWithId(id, staticList)), getDorilarSoni(getDoriWithId(id, staticList)));

        }

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

    private void setAutoCompleteTextView() {
        autoCompleteTextView.setText(autoCompleteTextView.getAdapter().getItem(0).toString());
        setSpinner(list);

    }
}