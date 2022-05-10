package com.example.healthapp.UI.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.healthapp.R;

public class user_Info extends AppCompatActivity {

    Button next,back;
    RadioButton male,female,patient,doctor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info2);

        next=findViewById(R.id.next_button);
        back=findViewById(R.id.back_btn);
        male=findViewById(R.id.maleBtn);
        female=findViewById(R.id.femaleBtn);
        patient=findViewById(R.id.patientbtn);
        doctor=findViewById(R.id.doctorbtn);


        next.setOnClickListener(view -> {
            Intent intent = new Intent(user_Info.this,Verification.class);
            startActivity(intent);
        });

        back.setOnClickListener(view -> {
            Intent intent = new Intent(user_Info.this,SignUp.class);
            startActivity(intent);
        });
    }
}