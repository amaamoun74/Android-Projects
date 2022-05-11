package com.example.healthapp.UI.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.healthapp.R;
import com.example.healthapp.pojo.BottomNavigation;

public class Verification extends AppCompatActivity {

    Button signup_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        signup_btn= findViewById(R.id.signup_btn);

        signup_btn.setOnClickListener(view ->{
            Intent intent = new Intent(Verification.this, BottomNavigation.class);
            startActivity(intent);
        });

    }
}