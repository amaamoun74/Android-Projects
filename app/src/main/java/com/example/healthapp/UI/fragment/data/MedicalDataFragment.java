package com.example.healthapp.UI.fragment.data;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.example.healthapp.R;
import com.example.healthapp.model.Diseases;
import com.example.healthapp.model.DiseasesData;
import com.example.healthapp.pojo.SessionManagement;
import com.example.healthapp.pojo.adapter.DataAdapter;
import com.example.healthapp.pojo.webServices.ApiClient;
import com.example.healthapp.pojo.webServices.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MedicalDataFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    RecyclerView recyclerView;
    List<Diseases> userData = new ArrayList<>();
    SessionManagement sessionManagement;
    DataAdapter dataAdapter;
    Context mContext;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RelativeLayout errorLayout;
    private TextView errorTitle, errorMessage;
    private LottieAnimationView animationView,progressAnimation;



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
        sessionManagement = new SessionManagement(container.getContext());
        swipeRefreshLayout=view.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.mainBlue);
        swipeRefreshLayout.setRefreshing(true);
        errorLayout = view.findViewById(R.id.errorLayout);
        errorTitle = view.findViewById(R.id.errorTitle);
        errorMessage = view.findViewById(R.id.errorMessage);
        animationView = view.findViewById(R.id.errorAnimationView);
        progressAnimation=view.findViewById(R.id.progress);
        progressAnimation.setVisibility(VISIBLE);



        retrieveData();
        return view;

    }


    void userMedicalDataByPatient() {
        progressAnimation.playAnimation();
        errorLayout.setVisibility(GONE);
        progressAnimation.setVisibility(VISIBLE);
        swipeRefreshLayout.setRefreshing(true);
        //SharedPreferences prfs = mContext.getSharedPreferences("Token", Context.MODE_PRIVATE);
        //String token = prfs.getString("token", "");
        userData.clear();
        ApiInterface apiInterface = ApiClient.retrofitInstance().create(ApiInterface.class);
        Call<DiseasesData> callData = apiInterface.viewMedicalDataByPatient("Bearer "+sessionManagement.getToken(),sessionManagement.getID());
        callData.enqueue(new Callback<DiseasesData>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<DiseasesData> call, Response<DiseasesData> response){
                dataResponse(response);
            }

            @Override
            public void onFailure(Call<DiseasesData> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                progressAnimation.setVisibility(GONE);
                showMessage(
                        R.raw.noconnection,
                        "No Result",
                        "Please Try Again!\n" +
                                "check your network connection");

            }
        });
    }

    void userMedicalDataByDoctor() {
        progressAnimation.playAnimation();
        errorLayout.setVisibility(GONE);
        progressAnimation.setVisibility(VISIBLE);
        ApiInterface apiInterface = ApiClient.retrofitInstance().create(ApiInterface.class);
        Call<DiseasesData> callData = apiInterface.viewMedicalDataByDoctor("Bearer " + sessionManagement.getToken(), sessionManagement.getUserIDFromQR());
        callData.enqueue(new Callback<DiseasesData>() {
            @Override
            public void onResponse(@NonNull Call<DiseasesData> call, @NonNull Response<DiseasesData> response) {
                dataResponse(response);
            }

            @Override
            public void onFailure(@NonNull Call<DiseasesData> call, @NonNull Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                progressAnimation.setVisibility(GONE);
                showMessage(
                        R.raw.noconnection,
                        "No Result",
                        "Please Try Again!\n" +
                                "check your network connection");

            }
        });
    }

// momkn ynf3 bdl l function de bs msh mot2kd -> getActivity().getApplicationContext()
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onRefresh() {
        retrieveData();
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

    void retrieveData(){
        if (sessionManagement.getUserState().equals("doctor")) {
            userMedicalDataByDoctor();
        } else  {
            userMedicalDataByPatient();
        }
    }

    void
    dataResponse(Response<DiseasesData> response){
        if (response.isSuccessful() && response.body().getData() != null) {
            swipeRefreshLayout.setRefreshing(false);
            progressAnimation.pauseAnimation();
            progressAnimation.setVisibility(GONE);
            Toast.makeText(mContext, ""+response.body(), Toast.LENGTH_SHORT).show();
            userData.addAll(response.body().getData());
            dataAdapter.notifyDataSetChanged();
        }

        else if (response.isSuccessful() && response.body().getData() == null){
            swipeRefreshLayout.setRefreshing(false);
            progressAnimation.setVisibility(GONE);
            showMessage(R.raw.examine,"Empty Medical data!","to get medical information \nplease go to the nearest hospital for examination ");
        }

        else {
            progressAnimation.setVisibility(GONE);
            progressAnimation.pauseAnimation();
            swipeRefreshLayout.setRefreshing(false);

            String errorCode;
            switch (response.code()) {
                case 404:
                    errorCode = "404 not found";
                    break;
                case 500:
                    errorCode = "500 server broken";
                    break;
                default:
                    errorCode = "unknown error";
                    break;
            }
            showMessage(
                    R.raw.noconnection,
                    "No Result",
                    "Please Try Again!\n" +
                            errorCode);
        }
    }

}