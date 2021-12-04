package UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.healthapp.R;

public class user_Info extends AppCompatActivity {

    Button next,back;
    RadioButton male,female,patient,doctor,policeman;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info2);

        next=findViewById(R.id.next_button);
        back=findViewById(R.id.back_btn);
        male=findViewById(R.id.maleBtn);
        female=findViewById(R.id.femaleBtn);
        patient=findViewById(R.id.patientbtn);
        doctor=findViewById(R.id.doctorbtn);
        policeman=findViewById(R.id.policemanbtn);

        next.setOnClickListener(view -> {
            Intent intent = new Intent(user_Info.this,Verification.class);
            startActivity(intent);
            finish();
        });

        back.setOnClickListener(view -> {
            Intent intent = new Intent(user_Info.this,SignUp.class);
            startActivity(intent);
            finish();
        });
    }
}