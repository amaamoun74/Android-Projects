package com.example.healthapp.UI.fragment.data;

import static android.view.View.VISIBLE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.example.healthapp.R;
import com.example.healthapp.model.PersonalInformation;
import com.example.healthapp.model.User;
import com.example.healthapp.pojo.SessionManagement;
import com.example.healthapp.pojo.webServices.ApiClient;
import com.example.healthapp.pojo.webServices.ApiInterface;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonalDataFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private FirebaseUser firebaseUser;
    private DatabaseReference databaseReference;
    private String userID;
    private TextView name,phoneNum,emergency,email,dateOfBirth,gender,nationalId,mainName,mainEmail;
    private Context mContext;
    private SessionManagement sessionManagement;
    private List<User> users;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RelativeLayout errorLayout;
    private TextView errorTitle, errorMessage;
    private LottieAnimationView animationView;
    private LinearLayout personalLayout;
    private LottieAnimationView progressAnimation;

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
        mainEmail = view.findViewById(R.id.mainEmail);
        mainName = view.findViewById(R.id.mainName);
        personalLayout = view.findViewById(R.id.personalLayout);
        personalLayout.setVisibility(View.GONE);
        progressAnimation=view.findViewById(R.id.barProgress);
        progressAnimation.playAnimation();

        sessionManagement = new SessionManagement(container.getContext());
        swipeRefreshLayout=view.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.mainBlue);

        swipeRefreshLayout.setRefreshing(true);
        errorLayout = view.findViewById(R.id.errorLayout);
        errorTitle = view.findViewById(R.id.errorTitle);
        errorMessage = view.findViewById(R.id.errorMessage);
        animationView = view.findViewById(R.id.errorAnimationView);
        users = new ArrayList<>();

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
        String name = sessionManagement.getMainName();
        Log.d("name", name);
        mainName.setText(name);
        mainEmail.setText(sessionManagement.getMainEmail());
        getUserPersonalInformation();
        return view;
    }
    void getUserPersonalInformation(){

         //SharedPreferences prfs = mContext.getSharedPreferences("Token", Context.MODE_PRIVATE);
         //String token = prfs.getString("token", "");
         users.clear();
        swipeRefreshLayout.setRefreshing(true);
        personalLayout.setVisibility(View.GONE);
        progressAnimation.playAnimation();
        ApiInterface apiInterface = ApiClient.retrofitInstance().create(ApiInterface.class);
        Call<PersonalInformation> callData = apiInterface.viewUser("Bearer "+sessionManagement.getToken(),sessionManagement.getID());
        callData.enqueue(new Callback<PersonalInformation>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<PersonalInformation> call, Response<PersonalInformation> response) {
                Log.d("response", response.toString());
                //PersonalInformation user = response.body();
                if (response.isSuccessful() && response.body().getUser() != null) {

                    progressAnimation.pauseAnimation();
                    progressAnimation.setVisibility(View.GONE);
                    personalLayout.setVisibility(VISIBLE);
                    PersonalInformation personalInformation = response.body();
                    if (personalInformation != null) {
                        swipeRefreshLayout.setRefreshing(false);
                        users = personalInformation.getUser();
                        for (int i = 0; i < users.size(); i++) {
                            name.setText("Name : " + users.get(i).getName());
                            email.setText("Email : " + users.get(i).getEmail());
                            phoneNum.setText("Phone Number : " + users.get(i).getPhone());
                            emergency.setText("Emergency Number : " + users.get(i).getEmNum());
                            dateOfBirth.setText("age : " + users.get(i).getAge());
                            gender.setText("Gender : " + users.get(i).getGender());
                            nationalId.setText("Address : " + users.get(i).getAddress());
                        }
                    }
                }else {

                    progressAnimation.pauseAnimation();
                    progressAnimation.setVisibility(View.GONE);
                    swipeRefreshLayout.setRefreshing(false);
                    showMessage(
                            R.raw.noconnection,
                            "No Result",
                            "Please Try Again!\n" +
                                    "check your network connection");
                }
            }
            @Override
            public void onFailure(Call<PersonalInformation> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                progressAnimation.pauseAnimation();
                progressAnimation.setVisibility(View.GONE);
                showMessage(
                        R.raw.noconnection,
                        "No Result",
                        "Please Try Again!\n" +
                                "check your network connection");

            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onRefresh() {
        getUserPersonalInformation();
    }


    public void showMessage(int animation,String title, String message) {
        Log.v("Show1Error", message);
        if (errorLayout.getVisibility() != VISIBLE) {
            errorLayout.setVisibility(VISIBLE);
            errorTitle.setText(title);
            errorMessage.setText(message);
            animationView.setAnimation(animation);
            animationView.playAnimation();
        }
    }

}