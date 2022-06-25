package com.example.healthapp.UI.activity;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.healthapp.R;

public class StartingApp extends AppCompatActivity {

    Button logIn;
    Button signUp;
    NotificationManager notificationManager;
     int notification_ID= 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_app);

        logIn = findViewById(R.id.logInButton);
        signUp = findViewById(R.id.signUpButton);
        try {
            logIn.setOnClickListener(view -> {

                Intent intent = new Intent(StartingApp.this, LogIn.class);
                startActivity(intent);
            });

            signUp.setOnClickListener(view -> {
                Intent intent = new Intent(StartingApp.this, SignUp.class);
                startActivity(intent);
            });

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("error", e.toString());
        }

    }


}