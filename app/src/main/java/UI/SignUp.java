package UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.healthapp.R;

public class SignUp extends AppCompatActivity {

    Button nextBtn;
    TextView loginText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        nextBtn= findViewById(R.id.next_btn);
        loginText= findViewById(R.id.login_Text);

        nextBtn.setOnClickListener(view -> {
            Intent intent = new Intent(SignUp.this,Verification.class);
            startActivity(intent);
            finish();
        });

        loginText.setOnClickListener(view -> {
            Intent intent = new Intent(SignUp.this,LogIn.class);
            startActivity(intent);
            finish();
        });




    }
}