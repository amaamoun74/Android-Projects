package com.example.healthapp.UI.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.healthapp.R;
import com.example.healthapp.pojo.SessionManagement;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class QrDisplayingFragment extends Fragment {
    ImageView qrImage;
    SessionManagement sessionManagement;
    int text;
    Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_qr_displaying, container, false);
        qrImage = view.findViewById(R.id.qrImage1);
        sessionManagement = new SessionManagement(container.getContext());
        text = sessionManagement.getID();
        generate(text);
        return view;
    }

    void generate(int text) {
        String data = String.valueOf(text);
        QRGEncoder encoder = new QRGEncoder(data, null, QRGContents.Type.TEXT, 1200);
        if (sessionManagement.getCovid().equals("NAN")) {
            encoder.setColorBlack(Color.YELLOW);
            encoder.setColorWhite(Color.GRAY);
        } else if (sessionManagement.getCovid().equals("Negative")){
            encoder.setColorBlack(Color.GREEN);
            encoder.setColorWhite(Color.GRAY);
        }
        else if(sessionManagement.getCovid().equals("Positive")){
            encoder.setColorBlack(Color.RED);
            encoder.setColorWhite(Color.WHITE);
        }
        else {
            encoder.setColorBlack(Color.BLACK);
            encoder.setColorWhite(Color.WHITE);
        }
        qrImage.setImageBitmap(encoder.getBitmap());
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext= context;

    }
}