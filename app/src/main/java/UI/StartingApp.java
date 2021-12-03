package UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.healthapp.R;

public class StartingApp extends AppCompatActivity {

    Button logIn;
    Button signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_app);

        logIn = findViewById(R.id.logInButton);
        signUp = findViewById(R.id.signUpButton);

        logIn.setOnClickListener(view -> {
            Intent intent = new Intent(StartingApp.this,LogIn.class);
            startActivity(intent);
            finish();
        });
    }
}