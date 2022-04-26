package com.example.healthapp.UI.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.healthapp.R;

public class StartingApp extends AppCompatActivity {

    Button logIn;
    Button signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_app);

        logIn = findViewById(R.id.logInButton);
        signUp = findViewById(R.id.signUpButton);
try {

    logIn.setOnClickListener(view -> {
        Intent intent = new Intent(StartingApp.this,LogIn.class);
        startActivity(intent);
        finish();
    });

    signUp.setOnClickListener(view -> {
        Intent intent = new Intent(StartingApp.this,SignUp.class);
        startActivity(intent);
        finish();
    });

}
catch (Exception e){
    e.printStackTrace();
    Log.d("error",e.toString());
}

    }
}