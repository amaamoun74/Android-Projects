package com.example.healthapp.UI.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.healthapp.R;
import com.example.healthapp.pojo.BottomNavigation;

public class LogIn extends AppCompatActivity {

    EditText logInUsername,logInPassword;
    Button loginBtn;
    TextView forgetPass,signUp;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        loginBtn= findViewById(R.id.logInBtn);
        logInUsername= findViewById(R.id.username);
        logInPassword= findViewById(R.id.password);
        forgetPass= findViewById(R.id.forgetPass_Text);
        signUp= findViewById(R.id.signUp_Text);


        loginBtn.setOnClickListener(view -> {
            if (validate()) {
                progressDialog = new ProgressDialog(LogIn.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.loding_dialogue);
                progressDialog.getWindow().setBackgroundDrawableResource(

                        android.R.color.transparent
                );
                Thread time = new Thread()
                {
                    @Override
                    public void run() {
                        try {
                            sleep(3500);
                            Intent i = new Intent(LogIn.this, BottomNavigation.class);
                            startActivity(i);
                            progressDialog.dismiss();

                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                };
                time.start();

              /*  Intent intent = new Intent(LogIn.this, BottomNavigation.class);
                startActivity(intent);
                Toast.makeText(LogIn.this, "Welcome welcome welcome!", Toast.LENGTH_LONG).show();
        */
            }
            else
                Toast.makeText(getApplicationContext(), "fill all attributes", Toast.LENGTH_SHORT).show();
        });

        signUp.setOnClickListener(view -> {
            Intent intent = new Intent(LogIn.this,SignUp.class);
            startActivity(intent);
        });
    }

    boolean validate (){
        if (logInUsername.getText() == null
                && logInPassword.getText() == null
               ){
            return false;}
        else
            return true;
    }
}