package com.example.healthapp.UI.fragment;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import com.example.healthapp.R;
import com.example.healthapp.UI.activity.ContactUs;
import com.example.healthapp.UI.activity.DataActivity;
import com.example.healthapp.UI.activity.Home;
import com.example.healthapp.UI.activity.QRGenerator;
import com.example.healthapp.UI.activity.QR_Code;
import com.example.healthapp.UI.activity.StartingApp;
import com.example.healthapp.UI.activity.qrScanning;
import com.example.healthapp.UI.activity.userProfile;
import com.google.android.material.navigation.NavigationView;

public class HomeFragment extends Fragment implements View.OnClickListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    CardView qrCodeCV,profileCV,dataCV,contactUsCv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        qrCodeCV = view.findViewById(R.id.qrCV);
        contactUsCv = view.findViewById(R.id.contactCV);
        profileCV = view.findViewById(R.id.profileCV);
        dataCV = view.findViewById(R.id.dataCV);
        navigationView = view.findViewById(R.id.navigation_view);
        qrCodeCV.setOnClickListener(this);
        profileCV.setOnClickListener(this);
        dataCV.setOnClickListener(this);
        contactUsCv.setOnClickListener(this);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.share) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plan");
                        String body = "Share from the T-care app now";
                        intent.putExtra(Intent.EXTRA_TEXT, body);
                        startActivity(Intent.createChooser(intent, "Share with :"));
                        return true;
                    } catch (Exception e) {
                        Toast.makeText(getContext(), "\"Hmm.. Sorry, \\nCannot be share\"", Toast.LENGTH_SHORT).show();
                    }
                }
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.share:
                        try {
                            intent = new Intent(Intent.ACTION_SEND);
                            intent.setType("text/plan");
                            String body = "Share the T-care app now";
                            intent.putExtra(Intent.EXTRA_TEXT, body);
                            startActivity(Intent.createChooser(intent, "Share with :"));

                        } catch (Exception e) {
                            Toast.makeText(getContext(), "\"Hmm.. Sorry, \\nCannot be share\"", Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case R.id.logOut:
                        startActivity(new Intent(getActivity(), StartingApp.class));
                        break;

                    case R.id.aboutUs:
                        Toast.makeText(getContext(), "about us is pressed", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        drawerLayout = getView().findViewById(R.id.drawer_layout);
        navigationView = view.findViewById(R.id.navigation_view);
        toolbar = getView().findViewById(R.id.toolbar11);
        toolbar.setNavigationIcon(R.drawable.menu_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);}
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (item.getItemId()==R.id.share){
                    try {
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plan");
                        String body = "Share from the T-care app now" ;
                        intent.putExtra(Intent.EXTRA_TEXT, body);
                        startActivity(Intent.createChooser(intent, "Share with :"));
                        return true;
                    } catch (Exception e) {
                        Toast.makeText(getContext(), "\"Hmm.. Sorry, \\nCannot be share\"", Toast.LENGTH_SHORT).show();
                    }
                }
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.share:
                        try {
                            intent = new Intent(Intent.ACTION_SEND);
                            intent.setType("text/plan");
                            String body = "Share from the T-care app now" ;
                            intent.putExtra(Intent.EXTRA_TEXT, body);
                            startActivity(Intent.createChooser(intent, "Share with :"));

                        } catch (Exception e) {
                            Toast.makeText(getContext(), "\"Hmm.. Sorry, \\nCannot be share\"", Toast.LENGTH_SHORT).show();
                        }
                        break;

                    case R.id.logOut:
                        startActivity(new Intent(getActivity(), StartingApp.class));
                        break;

                    case R.id.aboutUs:
                        Toast.makeText(getContext(), "about us is pressed", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.profileCV:
                intent = new Intent(getActivity(), userProfile.class);
                startActivity(intent);
                break;

            case R.id.dataCV:
                intent = new Intent(getActivity(),DataActivity.class);
                startActivity(intent);
         /*       Toast.makeText(this, "Data Screen is not implemented yet ", Toast.LENGTH_SHORT).show();
                intent = new Intent(this,.class);
                startActivity(intent);
           */     break;

            case R.id.qrCV:
                intent = new Intent(getActivity(), QR_Code.class);
                startActivity(intent);
                break;

            case R.id.contactCV:
                intent = new Intent(getActivity(), Home.class);
                startActivity(intent);
                break;
        }

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

}