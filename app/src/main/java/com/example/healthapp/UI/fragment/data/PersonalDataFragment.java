package com.example.healthapp.UI.fragment.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.healthapp.R;
import com.example.healthapp.model.PersonalInformation;
import com.example.healthapp.pojo.SessionManagement;
import com.example.healthapp.pojo.webServices.ApiClient;
import com.example.healthapp.pojo.webServices.ApiInterface;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonalDataFragment extends Fragment {
    private FirebaseUser firebaseUser;
    private DatabaseReference databaseReference;
    private String userID;
    private TextView name,phoneNum,emergency,email,dateOfBirth,gender,nationalId;
    private Context mContext;
    private SessionManagement sessionManagement;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getUserPersonalInformation();
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
        sessionManagement = new SessionManagement(mContext);
     /*   firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
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
*/
        getUserPersonalInformation();
        return view;
    }
    void getUserPersonalInformation(){

         SharedPreferences prfs = mContext.getSharedPreferences("Token", Context.MODE_PRIVATE);
         String token = prfs.getString("token", "");
        ApiInterface apiInterface = ApiClient.retrofitInstance().create(ApiInterface.class);
        Call<PersonalInformation> callData = apiInterface.viewUser("Bearer "+token,1);
        callData.enqueue(new Callback<PersonalInformation>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<PersonalInformation> call, Response<PersonalInformation> response) {
                Log.d("response", response.toString());
                //PersonalInformation user = response.body();
                if (response.isSuccessful() && response.body().getUser() != null) {

                    name.setText("Name : " + response.body().getUser());
                    phoneNum.setText("utvbuhnjmkl");
                    //nationalId.setText(response.body().getUser().getAddress());
                    //email.setText(response.body().getUser().getEmail());


                } else {
                    Toast.makeText(getActivity(), "" + response.code(), Toast.LENGTH_SHORT).show();
                    Log.d("TAG1", response.message());
                    // tl3 not allowed :)
                }
            }
            @Override
            public void onFailure(Call<PersonalInformation> call, Throwable t) {
                Toast.makeText(mContext, "failed to register :  " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("TAG1", t.getMessage().toString());
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }
}