package com.example.healthapp.UI.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
                QRGEncoder encoder = new QRGEncoder(data, null , QRGContents.Type.TEXT,800);
                encoder.setColorBlack(Color.RED);
                encoder.setColorWhite(Color.WHITE);
                qrImage.setImageBitmap(encoder.getBitmap());


            }
            public ColorDrawable[] vibrantLightColorList =
                    {
                            new ColorDrawable(Color.parseColor("#ffeead")),
                            new ColorDrawable(Color.parseColor("#93cfb3")),
                            new ColorDrawable(Color.parseColor("#fd7a7a")),
                            new ColorDrawable(Color.parseColor("#faca5f")),
                            new ColorDrawable(Color.parseColor("#1ba798")),
                            new ColorDrawable(Color.parseColor("#6aa9ae")),
                            new ColorDrawable(Color.parseColor("#ffbf27")),
                            new ColorDrawable(Color.parseColor("#d93947"))
                    };

        });


    }


}
