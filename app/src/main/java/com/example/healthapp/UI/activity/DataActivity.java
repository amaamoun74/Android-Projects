package com.example.healthapp.UI.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.healthapp.R;
import com.example.healthapp.UI.fragment.MedicalDataFragment;
import com.example.healthapp.UI.fragment.PersonalDataFragment;
import com.example.healthapp.adapter.FragmentAdapter;
import com.google.android.material.tabs.TabLayout;

public class DataActivity extends AppCompatActivity {

    ViewPager2 viewPager2;
    TabLayout tabLayout;
    FragmentManager fragmentManager;
    FragmentAdapter fragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        viewPager2 = findViewById(R.id.schedulepager);
        tabLayout = findViewById(R.id.tablayout);
        fragmentManager = getSupportFragmentManager();
        //    fragmentAdapter = new FragmentAdapter(fragmentManager,getLifecycle());
        setUpViewPager(viewPager2, fragmentManager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setUpViewPager(ViewPager2 viewPager, FragmentManager fragmentManager) {
        FragmentAdapter fragmentAdapter = new FragmentAdapter(fragmentManager, getLifecycle());
        fragmentAdapter.addFragment(new MedicalDataFragment(), "Medical");
        fragmentAdapter.addFragment(new PersonalDataFragment(), "Personal");

        viewPager.setAdapter(fragmentAdapter);
    }
}