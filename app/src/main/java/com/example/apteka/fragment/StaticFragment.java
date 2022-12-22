package com.example.apteka.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;

import com.example.apteka.R;
import com.example.apteka.pref_data.PreferenceData;
import com.example.apteka.viewmodel.StatisticViewModel;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;
import java.util.TimeZone;


public class StaticFragment extends Fragment implements View.OnClickListener {
    private TabLayout tabLayout;
    private FloatingActionButton fbnCalendar;
    public FrameLayout fra;
    public PreferenceData data;

    public StaticFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_static, container, false);
        initViews(view);
        data = new PreferenceData(requireContext());
        fbnCalendar.setOnClickListener(this);
        getChildFragmentManager().beginTransaction().replace(R.id.fra, new StatFragment(0, null)).commit();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 5) {
                    calendarShow();
                } else {
                    getChildFragmentManager().beginTransaction().replace(R.id.fra, new StatFragment(tab.getPosition(), null)).commit();
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        return view;
    }


    private void initViews(View view) {
        tabLayout = view.findViewById(R.id.tabLayout);
        fra = view.findViewById(R.id.fra);
        fbnCalendar = view.findViewById(R.id.fbnCalendar);
    }


    @Override
    public void onClick(View view) {
        calendarShow();
    }

    public void calendarShow() {
        MaterialDatePicker.Builder<Long> materialDateBuilder = MaterialDatePicker.Builder.datePicker();

        materialDateBuilder.setTitleText("Vaqtni tanlang");


        MaterialDatePicker<Long> materialDatePicker = materialDateBuilder.build();

        // handle select date button which opens the
        // material design date picker

        // getSupportFragmentManager() to
        // interact with the fragments
        // associated with the material design
        // date picker tag is to get any error
        // in logcat
        materialDatePicker.show(getChildFragmentManager(), "MATERIAL_DATE_PICKER");


        // now handle the positive button click from the
        // material design date picker

        materialDatePicker.addOnPositiveButtonClickListener(
                new MaterialPickerOnPositiveButtonClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onPositiveButtonClick(Object selection) {

                        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
                        calendar.setTimeInMillis((Long) selection);
                        @SuppressLint("SimpleDateFormat") SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        String formattedDate = format.format(calendar.getTime());
                        getChildFragmentManager().beginTransaction().replace(R.id.fra, new StatFragment(5, formattedDate)).commit();
                        tabLayout.setScrollPosition(5, 0f, true);
                    }
                });
    }
}