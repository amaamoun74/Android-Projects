package com.example.healthapp.UI.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.healthapp.R;
import com.example.healthapp.pojo.SessionManagement;

public class modelQues extends Fragment {
    private Context mContext;
    SessionManagement sessionManagement;
    RadioButton yes1,yes2,yes3,yes4,yes5,yes6,yes7,yes8,yes9,yes10,yes11,yes12,yes13,yes14,yes15,yes16,
            no1,no2,no3,no4,no5,no6,no7,no8,no9,no10,no11,no12,no13,no14,no15,no16;

    String gender, intubed, pneumonia ,  age, pregnancy , diabetes, copd, asthma  , inmsupr ,hypertension,
            other_disease, cardiovascular, obesity,renal_chronic,tobacco,icu;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_model_ques, container, false);
        yes1 = view.findViewById(R.id.male);
        no1 = view.findViewById(R.id.female);

        yes2 = view.findViewById(R.id.yesIntubed);
        no2 = view.findViewById(R.id.noIntubed);

        yes3 = view.findViewById(R.id.yesRadioBtn);
        no3 = view.findViewById(R.id.noRadioBtn);

        yes4 = view.findViewById(R.id.yesAge);
        no4 = view.findViewById(R.id.noAge);

        yes5 = view.findViewById(R.id.yesPregnanant);
        no5 = view.findViewById(R.id.noPregnanant);

        yes6 = view.findViewById(R.id.yesDiabetes);
        no6 = view.findViewById(R.id.noDiabetes);



        yes7 = view.findViewById(R.id.yesCopd);
        no7 = view.findViewById(R.id.noCopd);

        yes8 = view.findViewById(R.id.yesAsthma);
        no8 = view.findViewById(R.id.noAsthma);

        yes9 = view.findViewById(R.id.yesInmsupr);
        no9 = view.findViewById(R.id.noInmsupr);

        yes10 = view.findViewById(R.id.yesHypertension);
        no10 = view.findViewById(R.id.noHypertension);

        yes11 = view.findViewById(R.id.yesOther);
        no11 = view.findViewById(R.id.noOther);

        yes12 = view.findViewById(R.id.yescardiovascular);
        no12 = view.findViewById(R.id.noCardiovascularn);

        yes13 = view.findViewById(R.id.yesObesity);
        no13 = view.findViewById(R.id.noObesity);

        yes14 = view.findViewById(R.id.yesChronic);
        no14 = view.findViewById(R.id.noChronic);

        yes15 = view.findViewById(R.id.yesSmoke);
        no15 = view.findViewById(R.id.noSmoke);

        yes16 = view.findViewById(R.id.yesICU);
        no16 = view.findViewById(R.id.noICU);

        sessionManagement = new SessionManagement(container.getContext());

        if (yes1.isChecked()){
            gender= "Negative";
        sessionManagement.saveCovid(gender);
        }


        Toast.makeText(mContext, ""+ gender, Toast.LENGTH_SHORT).show();
        return view;
    }

    void validate() {

        if (yes1.isChecked()) {
            gender="1";
        } else if (no1.isChecked()) {
            gender ="0";
        } else {
            yes1.setError("");
            no1.setError("");
        }

        if (yes2.isChecked()) {
            intubed="1";
        } else if (no2.isChecked()) {
            intubed="0";
        } else {
            yes2.setError("");
            no2.setError("");
        }

        if (yes3.isChecked()) {
            pneumonia = "1";
        } else if (no3.isChecked()) {
            pneumonia = "0";
        } else {
            yes3.setError("");
            no3.setError("");
        }

        if (yes4.isChecked()) {
            age = "1";
        } else if (no4.isChecked()) {
            age = "0";
        } else {
            yes4.setError("");
            no4.setError("");
        }

        if (yes5.isChecked()) {
            pregnancy="1";
        } else if (no5.isChecked()) {
            pregnancy="0";
        } else {
            yes5.setError("");
            no5.setError("");
        }
        if (yes6.isChecked()) {
            diabetes="1";
        } else if (no6.isChecked()) {
            diabetes="0";
        } else {
            yes6.setError("");
            no6.setError("");
        }

        if (yes7.isChecked()) {
            copd="1";
        } else if (no7.isChecked()) {
            copd="0";
        } else {
            yes7.setError("");
            no7.setError("");
        }

        if (yes8.isChecked()) {
            asthma="1";
        } else if (no8.isChecked()) {
            asthma="0";
        } else {
            yes8.setError("");
            no8.setError("");
        }

        if (yes9.isChecked()) {
            inmsupr="1";
        } else if (no9.isChecked()) {
            inmsupr="0";
        } else {
            yes10.setError("");
            no10.setError("");
        }

        if (yes11.isChecked()) {

        } else if (no11.isChecked()) {

        } else {
            yes11.setError("");
            no11.setError("");
        }


        if (yes12.isChecked()) {

        } else if (no12.isChecked()) {

        } else {
            yes13.setError("");
            no13.setError("");
        }


        if (yes14.isChecked()) {

        } else if (no14.isChecked()) {

        } else {
            yes15.setError("");
            no15.setError("");
        }


        if (yes16.isChecked()) {

        } else if (no16.isChecked()) {

        } else {
            yes16.setError("");
            no16.setError("");
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext= context;

    }

}