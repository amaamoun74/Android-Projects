package com.example.healthapp.UI.fragment.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthapp.R;
import com.example.healthapp.adapter.DataAdapter;
import com.example.healthapp.model.Diseases;
import com.example.healthapp.model.DiseasesData;
import com.example.healthapp.model.User;
import com.example.healthapp.pojo.SessionManagement;
import com.example.healthapp.pojo.webServices.ApiClient;
import com.example.healthapp.pojo.webServices.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MedicalDataFragment extends Fragment {

    RecyclerView recyclerView;
    List<Diseases> userData = new ArrayList<>();
    SessionManagement sessionManagement;
    DataAdapter dataAdapter;
    Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_medical_data, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        dataAdapter = new DataAdapter(mContext,userData);
         recyclerView.setAdapter(dataAdapter);

        userMedicalDataByPatient();
        sessionManagement = new SessionManagement(mContext.getApplicationContext());
      /*  if (sessionManagement.getUserState() == "doctor") {
            userMedicalDataByDoctor();
        } else  {
            userMedicalDataByPatient();
        }*/
        return view;

    }


    void userMedicalDataByPatient() {
        SharedPreferences prfs = mContext.getSharedPreferences("Token", Context.MODE_PRIVATE);
        String token = prfs.getString("token", "");
        ApiInterface apiInterface = ApiClient.retrofitInstance().create(ApiInterface.class);
        Call<DiseasesData> callData = apiInterface.viewDiseases("Bearer "+sessionManagement.getToken() ,sessionManagement.getID());
        callData.enqueue(new Callback<DiseasesData>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<DiseasesData> call, Response<DiseasesData> response){
                if (response.isSuccessful() && response.body().getData() != null) {
                    Toast.makeText(getActivity(), ""+response.message(), Toast.LENGTH_SHORT).show();

                    userData.addAll(response.body().getData());
                    dataAdapter.notifyDataSetChanged();

                }
                else {
                    String message="error";
                    Toast.makeText(mContext.getApplicationContext(),message,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DiseasesData> call, Throwable t) {
                Toast.makeText(getActivity(), "failed to register :  " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("TAG2", t.getMessage().toString());
            }
        });
    }

    void userMedicalDataByDoctor() {
        ApiInterface apiInterface = ApiClient.retrofitInstance().create(ApiInterface.class);
        Call<User> callData = apiInterface.showUserData(sessionManagement.getUserIDFromQR());
        callData.enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {

            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                Toast.makeText(getActivity(), "error, please try agian or check the internet connection", Toast.LENGTH_LONG).show();
            }
        });
    }

// momkn ynf3 bdl l function de bs msh mot2kd -> getActivity().getApplicationContext()
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }
}