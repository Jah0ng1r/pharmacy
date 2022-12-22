package com.example.apteka.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.apteka.R;
import com.example.apteka.activities.AddActivity;
import com.example.apteka.activities.ShowApteka;
import com.example.apteka.adapter.AptekaAdapter;
import com.example.apteka.adapter.AptekaAdapterInterface;
import com.example.apteka.model.aptekaModel.AptekaModel;
import com.example.apteka.model.aptekaModel.AptekaTokenModel;
import com.example.apteka.model.doriModel.DorilarModel;
import com.example.apteka.pref_data.PreferenceData;
import com.example.apteka.viewmodel.AptekaListViewModel;
import com.example.apteka.viewmodel.DorilarListViewModel;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements AptekaAdapterInterface, TextWatcher {
    RecyclerView recyclerView;
    AptekaAdapter adapter;
    EditText searchedit;
    FloatingActionButton fbnAdd;
    SwipeRefreshLayout swipeRefreshLayout;
    private ShimmerFrameLayout mShimmerViewContainer;
    public List<AptekaModel> aptekaModelList = new ArrayList<>();
    public List<DorilarModel> dorilarModelList = new ArrayList<>();
    AptekaListViewModel viewModel;
    DorilarListViewModel dorilarListViewModel;
    PreferenceData data;
    Group noDataGroup;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initViews(view);
        data = new PreferenceData(requireContext());
        mShimmerViewContainer.startShimmer();
        setAdapter();
        getViewModel();
        fbnAdd.setOnClickListener(this::addApteka);
        swipeRefreshLayout.setOnRefreshListener(this::getViewModel);
        searchedit.addTextChangedListener(this);

        return view;
    }

    private void addApteka(View view) {
        Intent intent = new Intent(getContext(), AddActivity.class);
        startActivity(intent);
    }

    private void setAdapter() {
        adapter = new AptekaAdapter(aptekaModelList, getContext(), this);
        recyclerView.setAdapter(adapter);
    }


    private void getViewModel() {
        swipeRefreshLayout.setRefreshing(true);
        viewModel = new ViewModelProvider(ViewModelStore::new).get(AptekaListViewModel.class);
        dorilarListViewModel = new ViewModelProvider(ViewModelStore::new).get(DorilarListViewModel.class);
        dorilarListViewModel.getStoreList().observe(getViewLifecycleOwner(), dorilarModels -> dorilarModelList = dorilarModels);
        viewModel.getStoreList(new AptekaTokenModel(data.getData("token"))).observe(getViewLifecycleOwner(), aptekaModels -> {
            if (aptekaModels == null || aptekaModels.isEmpty()) {
                swipeRefreshLayout.setRefreshing(false);
                noDataGroup.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            } else {
                noDataGroup.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                aptekaModelList = aptekaModels;
                swipeRefreshLayout.setRefreshing(false);
                mShimmerViewContainer.stopShimmer();
                mShimmerViewContainer.setVisibility(View.GONE);
                adapter.setApteka(aptekaModels);

            }

        });
    }

    private void initViews(View view) {
        recyclerView = view.findViewById(R.id.rec);
        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container);
        searchedit = view.findViewById(R.id.searchedit);
        noDataGroup = view.findViewById(R.id.noDataGroup);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        fbnAdd = view.findViewById(R.id.fbnAdd);

    }

    @Override
    public void onResume() {
        super.onResume();
        mShimmerViewContainer.startShimmer();
    }

    @Override
    public void onPause() {
        mShimmerViewContainer.stopShimmer();
        mShimmerViewContainer.setVisibility(View.GONE);
        super.onPause();
    }

    @Override
    public void click(AptekaModel model) {

        Intent intent = new Intent(getContext(), ShowApteka.class);
        addTask(dorilarModelList);
//        bundle.putParcelableArrayList("array", (ArrayList<? extends Parcelable>) dorilarModelList);
//        intent.putExtras(bundle);
        intent.putExtra("detail", model);
        startActivity(intent);
    }

    private void filter(String txt) {
        List<AptekaModel> searchList = new ArrayList<>();
        for (AptekaModel item : aptekaModelList) {
            if (item.getName().toLowerCase().contains(txt.toLowerCase())) {
                searchList.add(item);
            }
        }

        adapter.SearchList(searchList);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (mShimmerViewContainer.isShimmerVisible() || mShimmerViewContainer.isShimmerStarted()) {
            mShimmerViewContainer.stopShimmer();
            mShimmerViewContainer.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (charSequence.length() > 0) {
            mShimmerViewContainer.stopShimmer();
            mShimmerViewContainer.setVisibility(View.GONE);
            filter(charSequence.toString());
        } else {
            setAdapter();
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (mShimmerViewContainer.isShimmerVisible() || mShimmerViewContainer.isShimmerStarted()) {
            mShimmerViewContainer.stopShimmer();
            mShimmerViewContainer.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mShimmerViewContainer.stopShimmer();
        mShimmerViewContainer.setVisibility(View.GONE);
    }

    public void addTask(List<DorilarModel> t) {
        final SharedPreferences sharedPreferences = requireContext().getSharedPreferences("USER", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = gson.toJson(t);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Set", json);
        editor.apply();
    }
}