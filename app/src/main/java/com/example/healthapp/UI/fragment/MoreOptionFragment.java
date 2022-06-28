package com.example.healthapp.UI.fragment;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.healthapp.R;
import com.example.healthapp.UI.activity.ContactUs;
import com.example.healthapp.UI.activity.QRGenerator;
import com.example.healthapp.UI.activity.StartingApp;
import com.example.healthapp.model.User;
import com.example.healthapp.pojo.SessionManagement;
import com.example.healthapp.pojo.webServices.ApiClient;
import com.example.healthapp.pojo.webServices.ApiInterface;
import com.google.firebase.auth.FirebaseAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoreOptionFragment extends Fragment {
    private Dialog logoutDialog,deleteDialog;
    Button logoutBtn;
    CardView deleteAccount, qrGenerator,contactUs,aboutUs,share;
    private Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.option_layout, container, false);
        logoutDialogue();
        deleteAccountDialogue();

        deleteAccount = view.findViewById(R.id.deleteAccount);
        deleteAccount.setOnClickListener(view1 -> deleteDialog.show());

        logoutBtn = view.findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(view12 -> logoutDialog.show());

        contactUs = view.findViewById(R.id.contactCV);
        contactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ContactUs.class);
                startActivity(intent);
            }
        });

        qrGenerator= view.findViewById(R.id.qrGenerator);
        qrGenerator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), QRGenerator.class);
                startActivity(intent);
            }
        });

        share=view.findViewById(R.id.share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plan");
                String body = "Share the T-care app now";
                intent.putExtra(Intent.EXTRA_TEXT, body);
                startActivity(Intent.createChooser(intent, "Share with :"));
            }
        });
        return view;
    }

    void logoutDialogue(){
        logoutDialog = new Dialog(getActivity());
        logoutDialog.setContentView(R.layout.dialogue_logout);
        logoutDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        logoutDialog.setCancelable(true); //Optional
        logoutDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //Setting the animations to dialog

        Button Yes = logoutDialog.findViewById(R.id.btn_okay);
        Button No = logoutDialog.findViewById(R.id.btn_cancel);

        Yes.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent =  new Intent(getActivity(), StartingApp.class);
            startActivity(intent);
            Toast.makeText(getActivity(), "Yes", Toast.LENGTH_SHORT).show();
            logoutDialog.dismiss();
        });

        No.setOnClickListener(v -> {

            Toast.makeText(getActivity(), "No", Toast.LENGTH_SHORT).show();
            logoutDialog.dismiss();
        });
    }

    void deleteAccountDialogue(){
        deleteDialog = new Dialog(getActivity());
        deleteDialog.setContentView(R.layout.delete_dialogue);
        deleteDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        deleteDialog.setCancelable(true); //Optional
        deleteDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //Setting the animations to dialog

        Button Yes = deleteDialog.findViewById(R.id.btn_okay);
        Button No = deleteDialog.findViewById(R.id.btn_cancel);

        Yes.setOnClickListener(v -> {
            destroyUser();
            Toast.makeText(getActivity(), "Yes", Toast.LENGTH_SHORT).show();
            deleteDialog.dismiss();
        });

        No.setOnClickListener(v -> {

            Toast.makeText(getActivity(), "No", Toast.LENGTH_SHORT).show();
            deleteDialog.dismiss();
        });
    }

    void destroyUser() {
        SessionManagement sessionManagement = new SessionManagement(mContext);

        ApiInterface apiInterface = ApiClient.retrofitInstance().create(ApiInterface.class);
        Call<User> callData = apiInterface.destroyUser(sessionManagement.getUserIDFromQR());
        callData.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                //Toast.makeText(LogIn.this, "failed to login :  " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext= context;
    }
}