package com.example.healthapp.UI.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.healthapp.R;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {

    Animation topAnim, bottomAnim;
    ImageView logo;
    TextView appName;
    private FirebaseAuth mAuth;
     private static final int splashDuration= 4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FIRST_SYSTEM_WINDOW);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.down_animation);

        logo= findViewById(R.id.logoImageView);
        //appName= findViewById(R.id.appName_TextView);


        logo.setAnimation(topAnim);
        //appName.setAnimation(bottomAnim);
        //slogan.setAnimation(bottomAnim);


        new Handler().postDelayed(() -> {
            Intent intent = new Intent(MainActivity.this, StartingApp.class);
            startActivity(intent);
            finish();
        },splashDuration);
    }

/*
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            startActivity(new Intent(MainActivity.this, BottomNavigation.class));
        }
    }

 */
}