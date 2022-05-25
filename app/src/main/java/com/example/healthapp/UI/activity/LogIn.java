package com.example.healthapp.UI.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.healthapp.R;
import com.example.healthapp.model.Patient;
import com.example.healthapp.pojo.BottomNavigation;
import com.example.healthapp.pojo.webServices.ApiClient;
import com.example.healthapp.pojo.webServices.ApiInterface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogIn extends AppCompatActivity {

    EditText logInUsername, logInPassword;
    Button loginBtn;
    TextView forgetPass, signUp;
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        loginBtn = findViewById(R.id.logInBtn);
        logInUsername = findViewById(R.id.username);
        logInPassword = findViewById(R.id.password);
        forgetPass = findViewById(R.id.forgetPass_Text);
        signUp = findViewById(R.id.signUp_Text);
        mAuth = FirebaseAuth.getInstance();

        forgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Patterns.EMAIL_ADDRESS.matcher(logInUsername.getText().toString()).matches()) {
                    firebaseForgetPassword();
                }
            }
        });
        loginBtn.setOnClickListener(view -> {
           validate();

            // validate();
              /*  Intent intent = new Intent(LogIn.this, BottomNavigation.class);
                startActivity(intent);
                Toast.makeText(LogIn.this, "Welcome welcome welcome!", Toast.LENGTH_LONG).show();
        */

        });

        signUp.setOnClickListener(view -> {
            Intent intent = new Intent(LogIn.this, SignUp.class);
            startActivity(intent);
        });
    }

    void validate() {
        email = logInUsername.getText().toString();
        password = logInPassword.getText().toString();
        if (email.isEmpty()) {
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                logInUsername.setError("should be in ****@gmail.com format");

            } else {
                logInUsername.setError("fill in mail field");

            }
            logInUsername.requestFocus();
        } else if (password.isEmpty()) {
            logInPassword.setError("fill in Password field");
            logInPassword.requestFocus();


        } else {
            callLogin();
            firebaseUserLogin();
        }
    }

    void callLogin() {
        ApiInterface apiInterface = ApiClient.retrofitInstance().create(ApiInterface.class);
        Call<Patient> callData = apiInterface.login(email, password);
        callData.enqueue(new Callback<Patient>() {
            @Override
            public void onResponse(Call<Patient> call, Response<Patient> response) {
                Log.d("tag",response.message());
                Patient patient = response.body();
                if (patient != null) {
                    setProgressDialog();
                }
                else{
                    Toast.makeText(LogIn.this, "enter correct email and password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Patient> call, Throwable t) {
                Toast.makeText(LogIn.this, "failed to login :  " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    void firebaseUserLogin() {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                    }
                });
    }

    void firebaseForgetPassword() {
        if (!Patterns.EMAIL_ADDRESS.matcher(logInUsername.getText().toString()).matches()) {
            mAuth.sendPasswordResetEmail(logInUsername.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(LogIn.this, "check your E-mail to reset your password", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(LogIn.this, "Try Again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    void setProgressDialog() {
        progressDialog = new ProgressDialog(LogIn.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.loding_dialogue);
        progressDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent
        );
        Thread time = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3500);
                    Intent i = new Intent(LogIn.this, BottomNavigation.class);
                    startActivity(i);
                    progressDialog.dismiss();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        time.start();
    }
}