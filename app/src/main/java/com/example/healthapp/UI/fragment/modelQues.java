package com.example.healthapp.UI.fragment;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.healthapp.R;
import com.example.healthapp.pojo.SessionManagement;

public class modelQues extends Fragment implements View.OnClickListener {
    private Context mContext;
    SessionManagement sessionManagement;
    RadioButton yes1, yes2, yes3, yes5, yes6, yes7, yes8, yes9, yes10, yes11, yes12, yes13, yes14, yes15, yes16,
            no1, no2, no3, no5, no6, no7, no8, no9, no10, no11, no12, no13, no14, no15, no16;

    private Dialog logoutDialog, deleteDialog;

    Button submit;
    String gender, intubed, pneumonia, pregnancy, diabetes, copd, asthma, inmsupr, hypertension,
            other_disease, cardiovascular, obesity, renal_chronic, tobacco, icu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_model_ques, container, false);

        sessionManagement = new SessionManagement(container.getContext());

        submit = view.findViewById(R.id.submit_button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( genderValidate() && tobaccoValidate() && hypertensionValidate() &&
                        obesityValidate() && diabetesValidate() && asthmaValidate() &&
                        gpneumoniaValidate() && copdValidate() && inmsuprValidate() &&
                        cardiovascularValidate() && pregnancyValidate() && renal_chronicValidate() &&
                        other_diseaseValidate() && icuValidate() && intubedValidate()) {
                    if (tobacco.equals("1") && diabetes.equals("1") &&
                            copd.equals("1") && other_disease.equals("1") &&
                            icu.equals("1")) {
                        logoutDialog.show();
                        sessionManagement.saveCovid("Positive");
                    } else {
                        deleteDialog.show();
                        sessionManagement.saveCovid("Negative");


                    }
                }
            }
        });
        positiveResultDialogue();
        nagtiveResultDialogue();


        yes1 = view.findViewById(R.id.male);
        no1 = view.findViewById(R.id.female);

        yes1.setOnClickListener(this);
        no1.setOnClickListener(this);

        yes2 = view.findViewById(R.id.yesIntubed);
        no2 = view.findViewById(R.id.noIntubed);
        yes2.setOnClickListener(this);
        no2.setOnClickListener(this);

        yes3 = view.findViewById(R.id.yesRadioBtn);
        no3 = view.findViewById(R.id.noRadioBtn);
        yes3.setOnClickListener(this);
        no3.setOnClickListener(this);

        yes5 = view.findViewById(R.id.yesPregnanant);
        no5 = view.findViewById(R.id.noPregnanant);
        yes5.setOnClickListener(this);
        no5.setOnClickListener(this);

        yes6 = view.findViewById(R.id.yesDiabetes);
        no6 = view.findViewById(R.id.noDiabetes);
        yes6.setOnClickListener(this);
        no6.setOnClickListener(this);

        yes7 = view.findViewById(R.id.yesCopd);
        no7 = view.findViewById(R.id.noCopd);
        yes7.setOnClickListener(this);
        no7.setOnClickListener(this);

        yes8 = view.findViewById(R.id.yesAsthma);
        no8 = view.findViewById(R.id.noAsthma);
        yes8.setOnClickListener(this);
        no8.setOnClickListener(this);

        yes9 = view.findViewById(R.id.yesInmsupr);
        no9 = view.findViewById(R.id.noInmsupr);
        yes9.setOnClickListener(this);
        no9.setOnClickListener(this);

        yes10 = view.findViewById(R.id.yesHypertension);
        no10 = view.findViewById(R.id.noHypertension);
        yes10.setOnClickListener(this);
        no10.setOnClickListener(this);

        yes11 = view.findViewById(R.id.yesOther);
        no11 = view.findViewById(R.id.noOther);
        yes11.setOnClickListener(this);
        no11.setOnClickListener(this);

        yes12 = view.findViewById(R.id.yescardiovascular);
        no12 = view.findViewById(R.id.noCardiovascularn);
        yes12.setOnClickListener(this);
        no12.setOnClickListener(this);

        yes13 = view.findViewById(R.id.yesObesity);
        no13 = view.findViewById(R.id.noObesity);
        yes13.setOnClickListener(this);
        no13.setOnClickListener(this);

        yes14 = view.findViewById(R.id.yesChronic);
        no14 = view.findViewById(R.id.noChronic);
        yes14.setOnClickListener(this);
        no14.setOnClickListener(this);

        yes15 = view.findViewById(R.id.yesSmoke);
        no15 = view.findViewById(R.id.noSmoke);
        yes15.setOnClickListener(this);
        no15.setOnClickListener(this);

        yes16 = view.findViewById(R.id.yesICU);
        no16 = view.findViewById(R.id.noICU);
        yes16.setOnClickListener(this);
        no16.setOnClickListener(this);

        return view;
    }




    Boolean genderValidate() {
        if (yes1.isChecked()) {
            return true;
        } else if (no1.isChecked()) {
            return true;
        } else {
            no1.setError("");
            yes1.setError("");
            return false;
        }
    }

    Boolean intubedValidate() {
        if (yes2.isChecked()) {
            return true;
        } else if (no2.isChecked()) {
            return true;
        } else {
            no2.setError("");
            yes2.setError("");
            return false;
        }
    }

    Boolean gpneumoniaValidate() {
        if (yes3.isChecked()) {
            return true;
        } else if (no3.isChecked()) {
            return true;
        } else {
            no3.setError("");
            yes3.setError("");
            return false;
        }
    }

    Boolean pregnancyValidate() {
        if (yes5.isChecked()) {
            return true;
        } else if (no5.isChecked()) {
            return true;
        } else {
            no5.setError("");
            yes5.setError("");
            return false;
        }
    }

    Boolean diabetesValidate() {
        if (yes6.isChecked()) {
            return true;
        } else if (no6.isChecked()) {
            return true;
        } else {
            no6.setError("");
            yes6.setError("");
            return false;
        }
    }

    Boolean copdValidate() {
        if (yes7.isChecked()) {
            return true;
        } else if (no7.isChecked()) {
            return true;
        } else {
            no7.setError("");
            yes7.setError("");
            return false;
        }
    }

    Boolean asthmaValidate() {
        if (yes8.isChecked()) {
            return true;
        } else if (no8.isChecked()) {
            return true;
        } else {
            no8.setError("");
            yes8.setError("");
            return false;
        }
    }

    Boolean inmsuprValidate() {
        if (yes9.isChecked()) {
            return true;
        } else if (no9.isChecked()) {
            return true;
        } else {
            no9.setError("");
            yes9.setError("");
            return false;
        }
    }

    Boolean hypertensionValidate() {
        if (yes10.isChecked()) {
            return true;
        } else if (no10.isChecked()) {
            return true;
        } else {
            no10.setError("");
            yes10.setError("");
            return false;
        }
    }

    Boolean other_diseaseValidate() {
        if (yes11.isChecked()) {
            return true;
        } else if (no11.isChecked()) {
            return true;
        } else {
            no11.setError("");
            yes11.setError("");
            return false;
        }
    }

    Boolean cardiovascularValidate() {
        if (yes12.isChecked()) {
            return true;
        } else if (no12.isChecked()) {
            return true;
        } else {
            no12.setError("");
            yes12.setError("");
            return false;
        }
    }

    Boolean obesityValidate() {
        if (yes13.isChecked()) {
            return true;
        } else if (no13.isChecked()) {
            return true;
        } else {
            no13.setError("");
            yes13.setError("");
            return false;
        }
    }

    Boolean renal_chronicValidate() {
        if (yes14.isChecked()) {
            return true;
        } else if (no14.isChecked()) {
            return true;
        } else {
            no14.setError("");
            yes14.setError("");
            return false;
        }
    }

    Boolean tobaccoValidate() {
        if (yes15.isChecked()) {
            return true;
        } else if (no15.isChecked()) {
            return true;
        } else {
            no15.setError("");
            yes15.setError("");
            return false;
        }
    }

    Boolean icuValidate() {
        if (yes16.isChecked()) {
            return true;
        } else if (no16.isChecked()) {
            return true;
        } else {
            no16.setError("");
            yes16.setError("");
            return false;
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == yes1.getId()) {
            gender = "1";
        } else if (view.getId() == no1.getId()) {
            gender = "0";
        } else if (view.getId() == yes2.getId()) {
            intubed = "1";
        } else if (view.getId() == no2.getId()) {
            intubed = "0";
        } else if (view.getId() == yes3.getId()) {
            pneumonia = "1";
        } else if (view.getId() == no3.getId()) {
            pneumonia = "0";
        } else if (view.getId() == yes5.getId()) {
            pregnancy = "1";
        } else if (view.getId() == no5.getId()) {
            pregnancy = "0";
        } else if (view.getId() == yes6.getId()) {
            diabetes = "1";
        } else if (view.getId() == no6.getId()) {
            diabetes = "0";
        } else if (view.getId() == yes7.getId()) {
            copd = "1";
        } else if (view.getId() == no7.getId()) {
            copd = "0";
        } else if (view.getId() == yes8.getId()) {
            asthma = "1";
        } else if (view.getId() == no8.getId()) {
            asthma = "0";
        } else if (view.getId() == yes9.getId()) {
            inmsupr = "1";
        } else if (view.getId() == no9.getId()) {
            inmsupr = "0";
        } else if (view.getId() == yes10.getId()) {
            hypertension = "1";
        } else if (view.getId() == no10.getId()) {
            hypertension = "0";
        } else if (view.getId() == yes11.getId()) {
            other_disease = "1";
        } else if (view.getId() == no11.getId()) {
            other_disease = "0";
        } else if (view.getId() == yes12.getId()) {
            cardiovascular = "1";
        } else if (view.getId() == no12.getId()) {
            cardiovascular = "0";
        } else if (view.getId() == yes13.getId()) {
            obesity = "1";
        } else if (view.getId() == no13.getId()) {
            obesity = "0";
        } else if (view.getId() == yes14.getId()) {
            renal_chronic = "1";
        } else if (view.getId() == no14.getId()) {
            renal_chronic = "0";
        } else if (view.getId() == yes15.getId()) {
            tobacco = "1";
        } else if (view.getId() == no15.getId()) {
            tobacco = "0";
        } else if (view.getId() == yes16.getId()) {
            icu = "1";
        } else if (view.getId() == no16.getId()) {
            icu = "0";
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;

    }

    void positiveResultDialogue() {
        logoutDialog = new Dialog(getActivity());
        logoutDialog.setContentView(R.layout.postive_model_result);
        logoutDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        logoutDialog.setCancelable(true); //Optional
        logoutDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //Setting the animations to dialog
    }

    void nagtiveResultDialogue() {
        deleteDialog = new Dialog(getActivity());
        deleteDialog.setContentView(R.layout.model_result);
        deleteDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        deleteDialog.setCancelable(true); //Optional
        deleteDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //Setting the animations to dialog


    }
}