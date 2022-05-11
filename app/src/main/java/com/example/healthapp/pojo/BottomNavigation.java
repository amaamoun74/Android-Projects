package com.example.healthapp.pojo;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.healthapp.R;
import com.example.healthapp.UI.fragment.ExposureFragment;
import com.example.healthapp.UI.fragment.HomeFragment2;
import com.example.healthapp.UI.fragment.MoreOptionFragment;
import com.example.healthapp.UI.fragment.QRScanningFragment;
import com.example.healthapp.UI.fragment.data.profileFragment;
import com.example.healthapp.modelQues;

public class BottomNavigation extends AppCompatActivity {

    MeowBottomNavigation bottomNav;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        layout = findViewById(R.id.linearLayout);
        layout.setVisibility(View.VISIBLE);
        bottomNav = findViewById(R.id.bottomNav);
        //add menu items to bottomNav
        bottomNav.add(new MeowBottomNavigation.Model(1, R.drawable.ic_baseline_person_24));
        bottomNav.add(new MeowBottomNavigation.Model(2, R.drawable.ic_baseline_coronavirus_24));
        bottomNav.add(new MeowBottomNavigation.Model(3, R.drawable.ic_home_black_24dp));
        bottomNav.add(new MeowBottomNavigation.Model(4, R.drawable.ic_baseline_more_vert_24));
        bottomNav.add(new MeowBottomNavigation.Model(5, R.drawable.qr));
        bottomNav.add(new MeowBottomNavigation.Model(6, R.drawable.ic_baseline_login_24));

        //set bottomNav on show listener
        bottomNav.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                //initialize fragment
                Fragment fragment = null;
                //QRGenerator qrGenerator = null;
                // LogIn logIn=null;
                if (item.getId() == 1) {
                    layout.setVisibility(View.INVISIBLE);
                    fragment = new profileFragment();
                } else if (item.getId() == 2) {
                    fragment = new ExposureFragment();
                } else if (item.getId() == 3) {
                    fragment = new HomeFragment2();
                } else if (item.getId() == 4) {
                    fragment = new modelQues();
                    layout.setVisibility(View.INVISIBLE);
                } else if (item.getId() == 5) {
                    fragment = new QRScanningFragment();
                    layout.setVisibility(View.INVISIBLE);
                } else if (item.getId() == 6) {
                    fragment = new MoreOptionFragment();
                    layout.setVisibility(View.INVISIBLE);
                }
                loadFragment(fragment);
            }
        });
        //set initially selected fragment
        bottomNav.show(3, true);
        //set show item on click listener
        bottomNav.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                Toast.makeText(getApplicationContext(), "You clicked "+item.getId(), Toast.LENGTH_SHORT).show();
            }
        });

        bottomNav.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                Toast.makeText(getApplicationContext(), "You reselected "+item.getId(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void loadFragment (Fragment fragment){
        //replace the fragment
        getSupportFragmentManager().beginTransaction().
                replace(R.id.nav_host_fragment_container,fragment,null)
                .commit();
    }
}