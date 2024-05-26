package com.example.barbershop;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {
    private EditText edSignupEmail, edSignupPassword;
    private Button confirmButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        init();

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupUser();
            }
        });
    }

    private void init() {
        edSignupEmail = findViewById(R.id.signup_email_edit_text);
        edSignupPassword = findViewById(R.id.signup_password_edit_text);
        confirmButton = findViewById(R.id.confirm_button);
    }

    private void signupUser() {
        String email = edSignupEmail.getText().toString().trim();
        String password = edSignupPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            edSignupEmail.setError("Email is required.");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            edSignupPassword.setError("Password is required.");
            return;
        }

        if (password.length() < 6) {
            edSignupPassword.setError("Password must be at least 6 characters.");
            return;
        }

        // Placeholder for signup logic
        Toast.makeText(SignUpActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
    }
}
