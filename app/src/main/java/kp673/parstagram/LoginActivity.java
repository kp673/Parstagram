package kp673.parstagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inspector.StaticInspectionCompanionProvider;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;

import javax.security.auth.login.LoginException;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG= "LoginActivity";
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        if (ParseUser.getCurrentUser() != null){
//            gotoMainActivity();
//        }

        etUsername=findViewById(R.id.etUsername);
        etPassword=findViewById(R.id.etPassword);
        btnLogin=findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"login button clicked");
                String userName= etUsername.getText().toString();
                String password= etPassword.getText().toString();
                loginUser(userName,password);
            }
        });
    }

    private void loginUser(String userName, String password) {
        Log.i(TAG,"Attempting to login user " +userName);

        ParseUser.logInInBackground(userName, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null){
                    //todo Better error handling
                    Toast.makeText(LoginActivity.this,"Fail to log in",Toast.LENGTH_LONG).show();
                    Log.e(TAG,"Issue with Login", e);
                    return;
                }
                gotoMainActivity();
                Toast.makeText(LoginActivity.this,"Login Success",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void gotoMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}