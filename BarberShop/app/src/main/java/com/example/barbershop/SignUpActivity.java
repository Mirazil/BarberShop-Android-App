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
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {
    private EditText edSignupEmail, edSignupPassword;
    private Button confirmButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edSignupEmail = findViewById(R.id.signup_email_edit_text);
        edSignupPassword = findViewById(R.id.signup_password_edit_text);
        confirmButton = findViewById(R.id.confirm_button);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edSignupEmail.getText().toString().isEmpty() || edSignupPassword.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Fill in the blanks", Toast.LENGTH_SHORT).show();
                }else{
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(edSignupEmail.getText().toString(),edSignupPassword.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){

                                        HashMap<String, String> userInfo = new HashMap<>();
                                        userInfo.put("Email",edSignupEmail.getText().toString());
                                        userInfo.put("Password", edSignupPassword.getText().toString());
                                        FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(userInfo);

                                        startActivity(new Intent(SignUpActivity.this,MainActivity.class));
                                    } else {
                                        Toast.makeText(getApplicationContext(),"Something wrong", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }

            }
        });


//        init();
//
//        confirmButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                signupUser();
//            }
//        });
    }

    private void init() {
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
