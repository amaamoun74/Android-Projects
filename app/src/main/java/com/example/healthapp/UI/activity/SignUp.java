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

    TextInputEditText nameET,mailET, passwordET,confirmPasswordET,phoneNumberET,nationalID;
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

        nextBtn= findViewById(R.id.next_btn);
        loginText= findViewById(R.id.login_Text);

        nextBtn.setOnClickListener(view -> {
            if (validate()){
                if ( passwordET.getText() == confirmPasswordET.getText())
                {
                    Intent intent = new Intent(SignUp.this,user_Info.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(this, "Password is not matched", Toast.LENGTH_SHORT).show();

            }
            else
            Toast.makeText(getApplicationContext(), "fill all attributes", Toast.LENGTH_SHORT).show();
        });

        loginText.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "sth went wrong", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SignUp.this,LogIn.class);
            startActivity(intent);
        });
    }

    boolean validate (){
        if (nameET.getText() == null
                || mailET.getText() == null
                || passwordET.getText() == null
                ||confirmPasswordET.getText() == null
                ||phoneNumberET.getText() == null
                ||nationalID.getText() == null){
            Toast.makeText(getApplicationContext(), "fill all attributes", Toast.LENGTH_SHORT).show();
            return false;}
        else
            return true;
    }
}