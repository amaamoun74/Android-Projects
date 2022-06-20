package com.example.healthapp.UI.fragment.data;
import android.annotation.SuppressLint;
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
import com.example.healthapp.model.Data;
import com.example.healthapp.model.Patient;
import com.example.healthapp.pojo.SessionManagement;
import com.example.healthapp.pojo.webServices.ApiClient;
import com.example.healthapp.pojo.webServices.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MedicalDataFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Data> userData;

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
        userData = new ArrayList<>();
        recyclerView.setAdapter(new DataAdapter(getContext(),userData));
        return view;

        /**String title_items[] = getResources().getStringArray(R.array.title);
        String descriptionforAll= getResources().getString(R.string.description);
        for(int i=0; i<title.length();i++){
            list.add(title);

        }*/
        //adapter = new DataAdapter(this,list);
    }


    void getUserDiseases(){
        SessionManagement sessionManagement = new SessionManagement();

        ApiInterface apiInterface = ApiClient.retrofitInstance().create(ApiInterface.class);
        Call<Patient> callData = apiInterface.viewDiseases(sessionManagement.getId());
        callData.enqueue(new Callback<Patient>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<Patient> call, Response<Patient> response) {
                Log.d("response", response.toString());
                Patient patient = response.body();
                if (patient != null) {

                } else {
                    Toast.makeText(getActivity(), "" + response.code(), Toast.LENGTH_SHORT).show();
                    Log.d("TAG1", response.message());
                    // tl3 not allowed :)
                }
            }

            @Override
            public void onFailure(Call<Patient> call, Throwable t) {
                Toast.makeText(getActivity(), "failed to register :  " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("TAG1", t.getMessage().toString());
            }
        });
    }

    void userMedicalData(){
        ApiInterface apiInterface = ApiClient.retrofitInstance().create(ApiInterface.class);
        Call<Patient> callData = apiInterface.destroyUser();
        callData.enqueue(new Callback<Patient>() {
            @Override
            public void onResponse(@NonNull Call<Patient> call, @NonNull Response<Patient> response) {
                //  Intent i = new Intent(LogIn.this, BottomNavigation.class);
                //  startActivity(i);
            }

            @Override
            public void onFailure(@NonNull Call<Patient> call, @NonNull Throwable t) {
                Toast.makeText(getActivity(), "error, please try agian or check the internet connection", Toast.LENGTH_LONG).show();
            }
        });
    }
}