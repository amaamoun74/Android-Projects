package com.example.healthapp.pojo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.healthapp.R;
import com.example.healthapp.UI.ContactUs;
import com.example.healthapp.UI.QRGenerator;
import com.example.healthapp.UI.qrScanning;
import com.example.healthapp.UI.userProfile;

public class BottomNavActivity extends AppCompatActivity {

    MeowBottomNavigation meowBottomNavigation ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_nav);
        //replaceFragment(new HomeFragment());


        meowBottomNavigation= findViewById(R.id.bottom_nav_bar);

        //adding menu items
        meowBottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.qr));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_baseline_send_24));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_person_32));


        meowBottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {

                switch (item.getId()) {
                    case 1:

                        replaceFragment(new HomeFragment());
                        break;


                    case 2:
                        replaceFragment(new DashboardFragment());
                        break;

                    case 3:
                        replaceFragment(new profileFragment());
                        break;
                }
            }
        });

       // meowBottomNavigation.setCount(1,"10");
        meowBottomNavigation.show(1,true);

        meowBottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                switch (item.getId()) {
                    case 1:

                        replaceFragment(new HomeFragment());
                        break;


                    case 2:
                        replaceFragment(new DashboardFragment());
                        break;

                    case 3:
                        replaceFragment(new profileFragment());
                        break;
                }
                Toast.makeText(getApplicationContext(), "u clicked" + item.getId(), Toast.LENGTH_SHORT).show();
            }
        });

        meowBottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                Toast.makeText(getApplicationContext(), "u reselected" + item.getId(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frame_layout,fragment);
        transaction.commit();
        //getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,fragment);
    }


}