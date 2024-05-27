package com.example.barbershop;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private EditText edLogin, edPassword;
    private Button loginButton, signupButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edLogin = findViewById(R.id.email_edit_text);
        edPassword = findViewById(R.id.password_edit_text);
        loginButton = findViewById(R.id.login_button);
        signupButton = findViewById(R.id.signup_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edLogin.getText().toString().isEmpty() || edPassword.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Incorrect password or email", Toast.LENGTH_SHORT).show();
                }else {
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(edLogin.getText().toString(), edPassword.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    } else {
                                        Toast.makeText(getApplicationContext(),"Incorrect password or email", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                }
            }
        });


//        loginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                loginUser();
//            }
//        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }


//    private void loginUser() {
//        String email = edLogin.getText().toString().trim();
//        String password = edPassword.getText().toString().trim();
//
//        if (TextUtils.isEmpty(email)) {
//            edLogin.setError("Email is required.");
//            return;
//        }
//
//        if (TextUtils.isEmpty(password)) {
//            edPassword.setError("Password is required.");
//            return;
//        }
//
//        if (password.length() < 6) {
//            edPassword.setError("Password must be at least 6 characters.");
//            return;
//        }

//        // Placeholder for login logic
//        Toast.makeText(LoginActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//        startActivity(intent);
//        overridePendingTransition(R.anim.slide_down_in, R.anim.slide_down_out);
//        finish();
//    }
}
