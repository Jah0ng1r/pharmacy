package com.example.apteka.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.apteka.R;

public class QarzFragment extends Fragment {
    RecyclerView qarzRec;

    public QarzFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_qarz, container, false);
        initViews(v);

        return v;
    }

    private void initViews(View v) {
        qarzRec = v.findViewById(R.id.qarzRec);
    }
}