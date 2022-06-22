package com.example.healthapp.UI.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.healthapp.R;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class QRGenerator extends AppCompatActivity {
    EditText text;
    Button generate;
    ImageView qrImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrgenerator);
        qrImage = findViewById(R.id.qrImage);
        generate = findViewById(R.id.generate);
        text = findViewById(R.id.text);
        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = text.getText().toString();
                QRGEncoder encoder = new QRGEncoder(data, null , QRGContents.Type.TEXT,1200);
                encoder.setColorBlack(Color.RED);
                encoder.setColorWhite(Color.WHITE);
                qrImage.setImageBitmap(encoder.getBitmap());


            }
        });


    }


}
