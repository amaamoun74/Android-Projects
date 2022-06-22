package com.example.healthapp.UI.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.healthapp.R;
import com.example.healthapp.model.Patient;
import com.example.healthapp.pojo.BottomNavigation;
import com.example.healthapp.pojo.webServices.ApiClient;
import com.example.healthapp.pojo.webServices.ApiInterface;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class user_Info extends AppCompatActivity implements View.OnClickListener {

    Button next, back;
    RadioButton male, female, patient, doctor;
    String name, password, confirmPassword, email, phoneNum, nationalId, gender, state, mobile_mac_address;;
   // OkHttpClient okHttpClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info2);

        next = findViewById(R.id.next_button);
        back = findViewById(R.id.back_btn);
        male = findViewById(R.id.maleBtn);
        female = findViewById(R.id.femaleBtn);
        patient = findViewById(R.id.patientbtn);
        doctor = findViewById(R.id.doctorbtn);

        male.setOnClickListener(this);
        female.setOnClickListener(this);
        patient.setOnClickListener(this);
        doctor.setOnClickListener(this);

        mobile_mac_address = getMacAddress();
        next.setOnClickListener(view -> {
            if (genderValidate() && userTypeValidation()) {
                callRegister();
             }
        });

        back.setOnClickListener(view -> {
            Intent intent = new Intent(user_Info.this, SignUp.class);
            startActivity(intent);
        });
    }

    void callRegister() {
        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        if (bundle != null) {
            name = bundle.getString("userName");
            password = bundle.getString("password");
            confirmPassword = bundle.getString("confirmPassword");
            email = bundle.getString("email");
            phoneNum = bundle.getString("phoneNum");
            nationalId = bundle.getString("nationalId");
        }

            ApiInterface apiInterface = ApiClient.retrofitInstance().create(ApiInterface.class);
            Call<Patient> callData = apiInterface.register(name, email,
                    password, "22", gender, phoneNum, state);
            callData.enqueue(new Callback<Patient>() {
                @Override
                public void onResponse(Call<Patient> call, Response<Patient> response) {
                    Log.d("response", response.toString());
                    Patient patient = response.body();
                    if (patient != null) {
                        Intent i = new Intent(user_Info.this, BottomNavigation.class);
                        startActivity(i);
                        Log.d("name", response.body().getMessage().toString());

                    } else {
                        Toast.makeText(getApplicationContext(), "" + response.code(), Toast.LENGTH_SHORT).show();
                        Log.d("TAG", response.message().toString() + "");
                    }
                }

                @Override
                public void onFailure(Call<Patient> call, Throwable t) {
                    Toast.makeText(user_Info.this, "failed to register :  " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("TAG1", t.getMessage().toString());
                }
            });
        }

    Boolean genderValidate() {
        if (male.isChecked()) {
            return true;

        }
       else if (female.isChecked()) {
            return true;
        } else {
            female.setError("");
            male.setError("");
            return false;
        }


    }
    Boolean userTypeValidation() {
        if (patient.isChecked()) {
            return true;

        } else if (doctor.isChecked()) {
            return true;

        } else {
            patient.setError("");
            doctor.setError("");
            return false;
        }

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == male.getId()) {
            gender = "male";

        } else if (view.getId() == female.getId()) {
            gender = "female";

        } else if (view.getId() == patient.getId()) {
            state = "patient";

        } else if (view.getId() == doctor.getId()) {
            state = "doctor";

        }
    }

    public String getMacAddress() {
        try {

            List<NetworkInterface> networkInterfaceList = Collections.list(NetworkInterface.getNetworkInterfaces());

            String stringMac = "";

            for (NetworkInterface networkInterface : networkInterfaceList) {
                if (networkInterface.getName().equalsIgnoreCase("wlon0")) ;
                Log.d("networkInterface size",networkInterface.getHardwareAddress().toString());
                {
                    for (int i = 0; i < networkInterface.getHardwareAddress().length; i++) {
                        String stringMacByte = Integer.toHexString(networkInterface.getHardwareAddress()[i] & 0xFF);

                        if (stringMacByte.length() == 1) {
                            stringMacByte = "0" + stringMacByte;
                        }

                        stringMac = stringMac + stringMacByte.toUpperCase() + ":";
                    }
                    break;
                }

            }
            return stringMac;
        } catch (SocketException e) {
            e.printStackTrace();
            return "0";
        }
    }







          /*  Call<Json> call = ApiClient.retrofitInstance().create(ApiInterface.class).getSpecificData();
        call.enqueue(new Callback<Json>() {
            @Override
            public void onResponse(Call<Json> call, Response<Json> response) {
                Log.d("response", response.toString());
                Json json = response.body();
                if (json != null) {
                    Log.d("data", json.getTitle());
                   next.setText(json.getId().toString());
                }
            }
            @Override
            public void onFailure(Call<Json> call, Throwable t) {
                Toast.makeText(user_Info.this, "erorrroorrrrrolrororor", Toast.LENGTH_SHORT).show();
            }
        });*/
}