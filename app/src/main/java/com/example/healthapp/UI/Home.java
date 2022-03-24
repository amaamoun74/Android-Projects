package com.example.healthapp.UI;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.healthapp.R;
import com.google.android.material.navigation.NavigationView;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    CardView qrCodeCV,profileCV,dataCV,contactUsCv;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        qrCodeCV = findViewById(R.id.qrCV);
        contactUsCv=findViewById(R.id.contactCV);
        profileCV=findViewById(R.id.profileCV);
        dataCV=findViewById(R.id.dataCV);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar11);
        setSupportActionBar(toolbar);


        // navigationView.setBackgroundColor(getResources().getColor(R.color.white));
        // navigationView.setItemTextColor(ColorStateList.valueOf(getResources().getColor(R.color.mainBlue)));
        navigationView.bringToFront();


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        qrCodeCV.setOnClickListener(this);
        profileCV.setOnClickListener(this);
        dataCV.setOnClickListener(this);
        contactUsCv.setOnClickListener(this);
    }


    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
           // super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.nav_logout) {
            Intent intent = new Intent(this, LogIn.class);
            startActivity(intent);
        }

        return true;
    }

    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.profileCV:
                intent = new Intent(this, userProfile.class);
                startActivity(intent);
                break;

            case R.id.dataCV:
                intent = new Intent(this, QRGenerator.class);
                startActivity(intent);
         /*       Toast.makeText(this, "Data Screen is not implemented yet ", Toast.LENGTH_SHORT).show();
                intent = new Intent(this,.class);
                startActivity(intent);
           */     break;

            case R.id.qrCV:
                intent = new Intent(this, qrScanning.class);
                startActivity(intent);
                break;

            case R.id.contactCV:
                intent = new Intent(this, ContactUs.class);
                startActivity(intent);
                break;


        }
    }
}
