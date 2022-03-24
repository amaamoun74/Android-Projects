package com.example.healthapp.UI;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.healthapp.R;

public class Verification extends AppCompatActivity {

    Button signup_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        signup_btn= findViewById(R.id.signup_btn);

        signup_btn.setOnClickListener(view -> {

            Toast.makeText(this, "welcoooome", Toast.LENGTH_SHORT).show();
        });

    }
}