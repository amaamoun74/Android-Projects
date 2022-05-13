package com.example.healthapp.UI.fragment.data;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.healthapp.R;
import com.example.healthapp.model.BaseUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PersonalDataFragment extends Fragment {
    private FirebaseUser firebaseUser;
    private DatabaseReference databaseReference;
    private String userID;
    private TextView name,phoneNum,emergency,email,dateOfBirth,gender,nationalId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_personal_data, container, false);
        name = view.findViewById(R.id.patientNameTV);
        phoneNum = view.findViewById(R.id.phoneNumberTV);
        emergency = view.findViewById(R.id.emergencyNoTV);
        email = view.findViewById(R.id.emailTV);
        dateOfBirth = view.findViewById(R.id.dateOfBirthTV);
        gender = view.findViewById(R.id.genderTV);
        nationalId = view.findViewById(R.id.nationalIDTV);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        userID = firebaseUser.getUid();
        databaseReference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                BaseUser baseUser = snapshot.getValue(BaseUser.class);
                if (baseUser!=  null){
                    name.setText("Name : " + baseUser.name);
                    phoneNum.setText("Phone Number : " +baseUser.phoneNumber);
                    nationalId.setText("National ID : " +baseUser.nationalID);
                    email.setText("E-mail : " +baseUser.email);
                }
                else Toast.makeText(getActivity(), "NO User Data", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "ERROR", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}