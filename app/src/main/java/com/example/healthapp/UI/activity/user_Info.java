package com.example.healthapp.UI.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.healthapp.R;
import com.example.healthapp.model.UserSignup;
import com.example.healthapp.pojo.BottomNavigation;
import com.example.healthapp.pojo.SessionManagement;
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
    String name, password, confirmPassword, email,
            phoneNum, nationalId, age, gender, state,
            mobile_mac_address, address, emergency;
    SessionManagement sessionManagement;
    ProgressDialog progressbarDialog;
    private LottieAnimationView progressAnimation;


    // OkHttpClient okHttpClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info2);

        sessionManagement = new SessionManagement(user_Info.this);
        next = findViewById(R.id.next_button);
        back = findViewById(R.id.back_btn);
        male = findViewById(R.id.maleBtn);
        female = findViewById(R.id.femaleBtn);
        patient = findViewById(R.id.patientbtn);
        doctor = findViewById(R.id.doctorbtn);
        progressAnimation = findViewById(R.id.progressing);

        male.setOnClickListener(this);
        female.setOnClickListener(this);
        patient.setOnClickListener(this);
        doctor.setOnClickListener(this);
        //mobile_mac_address = getMacAddress();
        // Log.d("mobile_mac_address", mobile_mac_address);

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
            age = bundle.getString("age");
            address = bundle.getString("address");
            emergency = bundle.getString("emergency");
        }
        progressAnimation.playAnimation();
        progressAnimation.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = ApiClient.retrofitInstance().create(ApiInterface.class);
        Call<UserSignup> callData = apiInterface.register(name,email,password,age,gender,address,phoneNum,state,"mobile_mac_address",emergency);
        callData.enqueue(new Callback<UserSignup>() {
            @Override
            public void onResponse(@NonNull Call<UserSignup> call, @NonNull Response<UserSignup> response) {
                Log.d("response", response.toString());
                if (response.body() != null) {
                    progressAnimation.pauseAnimation();
                    sessionManagement.saveUserState(state);
                    setProgressDialog();
                } else {
                    progressAnimation.pauseAnimation();
                    progressAnimation.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "" + response.code(), Toast.LENGTH_SHORT).show();
                    Log.d("TAG", response.message() + "");
                }
            }

            @Override
            public void onFailure(Call<UserSignup> call, Throwable t) {
                progressAnimation.pauseAnimation();
                progressAnimation.setVisibility(View.GONE);
                Toast.makeText(user_Info.this, "failed to register :  " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("TAG1", t.getMessage());
            }
        });
    }

    Boolean genderValidate() {
        if (male.isChecked()) {
            return true;
        } else if (female.isChecked()) {
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
                Log.d("networkInterface size", networkInterface.getHardwareAddress().toString());
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

    void setProgressDialog() {
        progressAnimation.pauseAnimation();
        progressAnimation.setVisibility(View.GONE);
        progressbarDialog = new ProgressDialog(user_Info.this);
        progressbarDialog.show();
        progressbarDialog.setContentView(R.layout.loding_dialogue);
        progressbarDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent
        );
        Thread time = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3500);
                    Intent i = new Intent(user_Info.this, BottomNavigation.class);
                    startActivity(i);
                    progressbarDialog.dismiss();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        time.start();
    }


}