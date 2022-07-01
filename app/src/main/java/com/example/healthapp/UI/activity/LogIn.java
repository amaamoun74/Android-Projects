package com.example.healthapp.UI.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.healthapp.R;
import com.example.healthapp.model.UserLogin;
import com.example.healthapp.pojo.BottomNavigation;
import com.example.healthapp.pojo.SessionManagement;
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
    FirebaseAuth mAuth;
    String email, password;
    SessionManagement sessionManagement;
    ProgressDialog progressDialog;
    private LottieAnimationView progressAnimation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        loginBtn = findViewById(R.id.logInBtn);
        logInUsername = findViewById(R.id.username);
        logInPassword = findViewById(R.id.password);
        forgetPass = findViewById(R.id.forgetPass_Text);
        signUp = findViewById(R.id.signUp_Text);
        progressAnimation=findViewById(R.id.progress);

        mAuth = FirebaseAuth.getInstance();
        sessionManagement = new SessionManagement(LogIn.this);

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE |
                        WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE );

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
          //  firebaseUserLogin();
        }
    }

    void callLogin() {
        progressAnimation.playAnimation();
        progressAnimation.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = ApiClient.retrofitInstance().create(ApiInterface.class);
        Call<UserLogin> callData = apiInterface.login(logInUsername.getText().toString(), logInPassword.getText().toString());
        callData.enqueue(new Callback<UserLogin>() {
            @Override
            public void onResponse(Call<UserLogin> call, Response<UserLogin> response) {
                Log.d("tag",response.message());
                UserLogin user = response.body();
                if (user != null) {
                    setProgressDialog();

                    sessionManagement.saveID(user.getUser().getId());
                    sessionManagement.saveUserState(user.getUser().getState());
                    sessionManagement.saveToken(user.getAccess_token());
                    sessionManagement.saveMainEmail(user.getUser().getEmail());
                    sessionManagement.saveName(user.getUser().getName());


                    Log.d("id",sessionManagement.getID()+"");
                    Log.d("UserType",sessionManagement.getUserState());
                    Log.d("token",sessionManagement.getToken());

                //    Toast.makeText(LogIn.this, user.getAccess_token(), Toast.LENGTH_SHORT).show();

                //    SharedPreferences preferences = getSharedPreferences("Token", getApplicationContext().MODE_PRIVATE);
                //    SharedPreferences.Editor editor = preferences.edit();
                //    editor.putString("token",user.getAccess_token());
                //    editor.putInt("Patient_id",user.getUser().getId() );
                //    editor.commit();

                }
                else{
                    progressAnimation.setVisibility(View.GONE);
                    progressAnimation.pauseAnimation();
                    Toast.makeText(LogIn.this, "enter correct email and password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserLogin> call, Throwable t) {
                progressAnimation.pauseAnimation();
                progressAnimation.setVisibility(View.GONE);
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
        progressAnimation.pauseAnimation();
        progressAnimation.setVisibility(View.GONE);
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