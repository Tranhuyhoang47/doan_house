package com.example.doan_house;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_activity extends AppCompatActivity {
            EditText inputEmail,inputPassword;
            FirebaseAuth auth;
            Button btnSignUp,btnLogin;
            ProgressBar progressBar;
            TextView tv_forgotpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        if (auth.getCurrentUser() != null) {
//            startActivity(new Intent(Login_activity.this, MainActivity.class));
//        }
        setContentView(R.layout.activity_login);

        inputEmail = findViewById(R.id.edt_email_lg);
        inputPassword = findViewById(R.id.edt_password_lg);
        progressBar = findViewById(R.id.progressBar_lg);
        btnLogin = findViewById(R.id.btn_login_lg);
        btnSignUp = findViewById(R.id.btn_signup_lg);
        tv_forgotpass = findViewById(R.id.Tv_resetpass);

        // get firebase auth instance
        auth = FirebaseAuth.getInstance();

        tv_forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login_activity.this,reset_password.class);
                startActivity(i);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login_activity.this,Register_activity.class);
                startActivity(i);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                //authenticate user
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Login_activity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        inputPassword.setError(getString(R.string.minimum_password));
                                    } else {
                                        Toast.makeText(Login_activity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Intent intent = new Intent(Login_activity.this, homee.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
        });

    }

}