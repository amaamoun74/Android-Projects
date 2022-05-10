package com.example.healthapp.UI.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.healthapp.R;
import com.google.android.material.textfield.TextInputEditText;

public class SignUp extends AppCompatActivity {

    TextInputEditText nameET, mailET, passwordET, confirmPasswordET, phoneNumberET, nationalID;
    Button nextBtn;
    TextView loginText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        nameET = findViewById(R.id.name);
        mailET = findViewById(R.id.email);
        passwordET = findViewById(R.id.password);
        confirmPasswordET = findViewById(R.id.confirm_pass);
        phoneNumberET = findViewById(R.id.phone);
        nationalID = findViewById(R.id.nationalId);

        nextBtn = findViewById(R.id.next_btn);
        loginText = findViewById(R.id.login_Text);

        nextBtn.setOnClickListener(view -> {
            if (validate()) {
                if (passwordET.getText().toString().equals(confirmPasswordET.getText().toString())) {
                    Intent intent = new Intent(SignUp.this, user_Info.class);
                    startActivity(intent);
                } else
                    Toast.makeText(this, "Password is not matched", Toast.LENGTH_SHORT).show();

            }
        });

        loginText.setOnClickListener(view -> {
            Intent intent = new Intent(SignUp.this, LogIn.class);
            startActivity(intent);
        });
    }

    boolean validate() {
        if (nameET.getText().toString().isEmpty()) {
            nameET.setError("fill in name field");
            nameET.requestFocus();
            return false;
        } else if (mailET.getText().toString().isEmpty()) {
            mailET.setError("fill in mail field");
            mailET.requestFocus();
            return false;
        } else if (passwordET.getText().toString().isEmpty()) {
            passwordET.setError("fill in password field");
            passwordET.requestFocus();
            return false;
        } else if (confirmPasswordET.getText().toString().isEmpty()) {
            confirmPasswordET.setError("fill in confirm Password field");
            confirmPasswordET.requestFocus();
            return false;
        } else if (phoneNumberET.getText().toString().isEmpty()) {
            phoneNumberET.setError("fill in phone Number field");
            phoneNumberET.requestFocus();
            return false;
        } else if (nationalID.getText().toString().isEmpty()) {
            nationalID.setError("fill in national ID field");
            nationalID.requestFocus();
            return false;
        } else {
            return true;
        }
    }
}