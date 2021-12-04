package UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthapp.R;

public class LogIn extends AppCompatActivity {

    EditText logInUsername,logInPassword;
    Button loginBtn;
    TextView forgetPass,signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        loginBtn= findViewById(R.id.logInBtn);
        logInUsername= findViewById(R.id.username);
        logInPassword= findViewById(R.id.password);
        forgetPass= findViewById(R.id.forgetPass_Text);
        signUp= findViewById(R.id.signUp_Text);

        loginBtn.setOnClickListener(view -> {
            Toast.makeText(LogIn.this, "Welcome welcome welcome!", Toast.LENGTH_LONG).show();
        });

        signUp.setOnClickListener(view -> {
            Intent intent = new Intent(LogIn.this,SignUp.class);
            startActivity(intent);
            finish();
        });
    }
}