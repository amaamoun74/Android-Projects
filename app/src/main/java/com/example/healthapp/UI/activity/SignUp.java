package com.example.healthapp.UI.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.healthapp.R;
import com.example.healthapp.model.BaseUser;
import com.example.healthapp.pojo.BottomNavigation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    TextInputEditText nameET, mailET, passwordET, confirmPasswordET, phoneNumberET, nationalID;
    Button nextBtn;
    TextView loginText;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        nameET = findViewById(R.id.name);
        mailET = findViewById(R.id.email);
        passwordET = findViewById(R.id.password);
        confirmPasswordET = findViewById(R.id.confirm_pass);
        phoneNumberET = findViewById(R.id.phone);
        nationalID = findViewById(R.id.nationalId);

        nextBtn = findViewById(R.id.next_btn);
        loginText = findViewById(R.id.login_Text);

        nextBtn.setOnClickListener(view -> {
            createUser();
         /*   if (validate()) {
                if (passwordET.getText().toString().equals(confirmPasswordET.getText().toString())) {
                    Intent intent = new Intent(SignUp.this, user_Info.class);
                    startActivity(intent);
                } else
                    Toast.makeText(this, "Password is not matched", Toast.LENGTH_SHORT).show();

            }*/
        });

        loginText.setOnClickListener(view -> {
            Intent intent = new Intent(SignUp.this, LogIn.class);
            startActivity(intent);
        });
    }

    private void createUser() {
        String name = nameET.getText().toString();
        String password = passwordET.getText().toString();
        String confirmPassword = confirmPasswordET.getText().toString();
        String email = mailET.getText().toString();
        String phoneNum = phoneNumberET.getText().toString();
        String nationalId = nationalID.getText().toString();
        if (name.isEmpty()) {
            nameET.setError("fill in name field");
            nameET.requestFocus();

        } else if (email.isEmpty()) {
            if (TextUtils.isEmpty(email)) {
                mailET.setError("should be in ****@gmail.com format");

            } else {
                mailET.setError("fill in mail field");

            }
            mailET.requestFocus();
        } else if (password.isEmpty()) {
            passwordET.setError("fill in Password field");
            passwordET.requestFocus();

        } else if (confirmPassword.isEmpty()) {
            confirmPasswordET.setError("fill in confirm Password field");
            confirmPasswordET.requestFocus();


        } else if (phoneNum.isEmpty()) {
            phoneNumberET.setError("fill in phone Number field");
            phoneNumberET.requestFocus();


        } else if (nationalId.isEmpty()) {
            nationalID.setError("fill in national ID field");
            nationalID.requestFocus();


        } else if (password.length() < 6) {
            passwordET.setError("Min Password length Is 6 char ");
            passwordET.requestFocus();


        } else if (!(passwordET.getText().toString().equals(confirmPasswordET.getText().toString()))) {
            Toast.makeText(this, "Password is not matched", Toast.LENGTH_SHORT).show();
        }
        else {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                BaseUser user = new BaseUser(name, email, phoneNum, nationalId);
                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                                            firebaseUser.sendEmailVerification();
                                            Toast.makeText(SignUp.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(SignUp.this, BottomNavigation.class));

                                        } else {
                                            Toast.makeText(SignUp.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                            } else {
                                Toast.makeText(SignUp.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

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