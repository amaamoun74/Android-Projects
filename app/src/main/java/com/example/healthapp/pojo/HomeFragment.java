package com.example.healthapp.pojo;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import com.example.healthapp.R;
import com.example.healthapp.UI.ContactUs;
import com.example.healthapp.UI.QRGenerator;
import com.example.healthapp.UI.qrScanning;
import com.example.healthapp.UI.userProfile;
import com.google.android.material.navigation.NavigationView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    CardView qrCodeCV,profileCV,dataCV,contactUsCv;

    public HomeFragment() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        qrCodeCV = view.findViewById(R.id.qrCV);
        contactUsCv=view.findViewById(R.id.contactCV);
        profileCV=view.findViewById(R.id.profileCV);
        dataCV=view.findViewById(R.id.dataCV);
        qrCodeCV.setOnClickListener(this);
        profileCV.setOnClickListener(this);
        dataCV.setOnClickListener(this);
        contactUsCv.setOnClickListener(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        drawerLayout = getView().findViewById(R.id.drawer_layout);
        navigationView = getView().findViewById(R.id.navigation_view);
        toolbar = getView().findViewById(R.id.toolbar11);
      //  toolbar.inflateMenu(R.menu.menu);
        toolbar.setNavigationIcon(R.drawable.menu_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);}
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
                intent = new Intent(getActivity(), QRGenerator.class);
                startActivity(intent);
         /*       Toast.makeText(this, "Data Screen is not implemented yet ", Toast.LENGTH_SHORT).show();
                intent = new Intent(this,.class);
                startActivity(intent);
           */     break;

            case R.id.qrCV:
                intent = new Intent(getActivity(), qrScanning.class);
                startActivity(intent);
                break;

            case R.id.contactCV:
                intent = new Intent(getActivity(), ContactUs.class);
                startActivity(intent);
                break;
        }

    }
}