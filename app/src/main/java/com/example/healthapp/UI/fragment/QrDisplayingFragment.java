package com.example.healthapp.UI.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.healthapp.R;
import com.example.healthapp.model.User;
import com.example.healthapp.pojo.webServices.ApiClient;
import com.example.healthapp.pojo.webServices.ApiInterface;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QrDisplayingFragment extends Fragment {

    ImageView qrImage;
    ProgressBar progressBar;
    private Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_qr_displaying, container, false);
        qrImage= view.findViewById(R.id.qrImage1);
        progressBar = view.findViewById(R.id.progress_load);
        progressBar.setVisibility(View.VISIBLE);
        getQRImage();
        return view;
    }
    void getQRImage() {
        progressBar.setVisibility(View.GONE);
        ApiInterface apiInterface = ApiClient.retrofitInstance().create(ApiInterface.class);
        //hn8ier l function bta3t l show covid
        Call<User> callData = apiInterface.showrCovid();
        callData.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d("tag",response.message());
                User user = response.body();
                if (user != null) {
                    Picasso.get().load(user.getImg()).into(qrImage);
                }
                else{
                    Toast.makeText(requireActivity().getApplicationContext(), "you may have no qr code image", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getActivity(), "failed to login :  " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext= context;
    }
}