package com.example.healthapp.UI.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.healthapp.R;
import com.example.healthapp.pojo.SessionManagement;

public class HomeFragment2 extends Fragment {

SessionManagement sessionManagement;
TextView id,covidResult,name,type;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.home, container, false);
        sessionManagement = new SessionManagement(container.getContext());
        id = view.findViewById(R.id.userID);
        covidResult = view.findViewById(R.id.userCovidResultTV);
        type = view.findViewById(R.id.typeTV);
        name = view.findViewById(R.id.welcome);

        name.setText("Hi "+sessionManagement.getMainName());
        type.setText(sessionManagement.getUserState());
        id.setText("Your ID \n" + sessionManagement.getID());
        covidResult.setText("Covid-19 \n" + sessionManagement.getCovid());

        return view;
    }
}