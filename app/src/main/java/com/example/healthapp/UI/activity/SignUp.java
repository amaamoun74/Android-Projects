package com.example.healthapp.UI.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.healthapp.R;
import com.example.healthapp.model.BaseUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    TextInputEditText nameET, mailET, passwordET, confirmPasswordET, phoneNumberET,
            nationalID, ageET,addressET,emergencyNumET;
    Button nextBtn;
    TextView loginText;
    private FirebaseAuth mAuth;
    String name, password, confirmPassword, email, phoneNum,
            nationalId, age,address,emergency;

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
        ageET = findViewById(R.id.age);
        addressET = findViewById(R.id.address);
        emergencyNumET = findViewById(R.id.emergencyNum);

        nextBtn = findViewById(R.id.next_btn);
        loginText = findViewById(R.id.login_Text);

        nextBtn.setOnClickListener(view -> {
            validate();
        });

        loginText.setOnClickListener(view -> {
            Intent intent = new Intent(SignUp.this, LogIn.class);
            startActivity(intent);
        });
    }

    private void firebaseCreateUser() {
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
                                        //   Toast.makeText(SignUp.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                                        //   startActivity(new Intent(SignUp.this, BottomNavigation.class));

                                 /*   } else {
                                  //      Toast.makeText(SignUp.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }*/
                                    }
                                }
                            });

                        } /*else {
                          //  Toast.makeText(SignUp.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }*/
                    }
                });
    }

    void validate() {
        if (nameET.getText().toString().isEmpty()) {
            nameET.setError("fill in name field");
            nameET.requestFocus();

        } else if (mailET.getText().toString().isEmpty()) {
            mailET.setError("fill in mail field");
            mailET.requestFocus();

        } /*else if (Patterns.EMAIL_ADDRESS.matcher(mailET.getText().toString()).matches()) {
            mailET.setError("should be in ****@example.com format");
            mailET.requestFocus();

        }*/  else if (passwordET.getText().toString().isEmpty()) {
            passwordET.setError("fill in Password field");
            passwordET.requestFocus();

        } else if (confirmPasswordET.getText().toString().isEmpty()) {
            confirmPasswordET.setError("fill in Confirm Password field");
            confirmPasswordET.requestFocus();

        }
        else if (addressET.getText().toString().isEmpty()) {
            addressET.setError("fill in Address field");
            addressET.requestFocus();

        }
        else if (phoneNumberET.getText().toString().isEmpty()) {
            phoneNumberET.setError("fill in Phone Number field");
            phoneNumberET.requestFocus();

        }
        else if (emergencyNumET.getText().toString().isEmpty()) {
            emergencyNumET.setError("fill in Emergency Number field");
            emergencyNumET.requestFocus();

        }
        else if (ageET.getText().toString().isEmpty()) {
            ageET.setError("fill in age field");
            ageET.requestFocus();

        } else if (nationalID.getText().toString().isEmpty()) {
            nationalID.setError("fill in National ID field");
            nationalID.requestFocus();

        } else if (passwordET.getText().toString().length() < 6) {
            passwordET.setError("Min Password length Is 6 char ");
            passwordET.requestFocus();

        } else if (!(passwordET.getText().toString().equals(confirmPasswordET.getText().toString()))) {
            Toast.makeText(this, "Password is not matched", Toast.LENGTH_SHORT).show();

        } else {
            data();
            //  firebaseCreateUser();
        }
    }
    void data(){

        name = nameET.getText().toString();
        password = passwordET.getText().toString();
        confirmPassword = confirmPasswordET.getText().toString();
        email = mailET.getText().toString();
        phoneNum = phoneNumberET.getText().toString();
        nationalId = nationalID.getText().toString();
        age = ageET.getText().toString();
        address = addressET.getText().toString();
        emergency = emergencyNumET.getText().toString();

        Intent intent = new Intent(SignUp.this, user_Info.class);
        intent.putExtra("userName", name);
        intent.putExtra("password", password);
        intent.putExtra("confirmPassword", confirmPassword);
        intent.putExtra("email", email);
        intent.putExtra("phoneNum", phoneNum);
        intent.putExtra("nationalId", nationalId);
        intent.putExtra("age", age);
        intent.putExtra("address", address);
        intent.putExtra("emergency", emergency);
        startActivity(intent);
    }
}