package com.example.healthapp.UI.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;

import com.example.healthapp.R;
import com.example.healthapp.pojo.SessionManagement;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class QrDisplayingFragment extends Fragment {

    ImageView qrImage;
    ProgressBar progressBar;
    SessionManagement sessionManagement;
    int text;

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
        progressBar = view.findViewById(R.id.progress_load);
        progressBar.setVisibility(View.VISIBLE);

        return view;
    }

    void generate(int text) {
        String data = String.valueOf(text);
        QRGEncoder encoder = new QRGEncoder(data, null, QRGContents.Type.TEXT, 1200);
        encoder.setColorBlack(Color.RED);
        encoder.setColorWhite(Color.WHITE);
        qrImage.setImageBitmap(encoder.getBitmap());
    }
}