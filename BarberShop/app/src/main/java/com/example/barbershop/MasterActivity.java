package com.example.barbershop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MasterActivity extends AppCompatActivity {

    private Button button800, button1000, button1200, button1400, button1600, button1800, signUpButton;
    private String selectedTime = "";
    private int selectedColor, defaultColor;
    private TextView selectedTextView = null;
    private String selectedServices = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);

        button800 = findViewById(R.id.button_800);
        button1000 = findViewById(R.id.button_1000);
        button1200 = findViewById(R.id.button_1200);
        button1400 = findViewById(R.id.button_1400);
        button1600 = findViewById(R.id.button_1600);
        button1800 = findViewById(R.id.button_1800);
        signUpButton = findViewById(R.id.sign_up_button);

        defaultColor = ContextCompat.getColor(this, R.color.button_background_color);
        selectedColor = ContextCompat.getColor(this, android.R.color.white);

        Intent intent = getIntent();
        selectedServices = intent.getStringExtra("selectedServices");

        View.OnClickListener timeButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button clickedButton = (Button) v;
                if (selectedTextView != null) {
                    selectedTextView.setBackgroundColor(defaultColor);
                    selectedTextView.setTextColor(ContextCompat.getColor(MasterActivity.this, R.color.primary_beige));
                }
                clickedButton.setBackgroundColor(selectedColor);
                clickedButton.setTextColor(defaultColor);
                selectedTextView = clickedButton;
                selectedTime = clickedButton.getText().toString();
            }
        };

        button800.setOnClickListener(timeButtonClickListener);
        button1000.setOnClickListener(timeButtonClickListener);
        button1200.setOnClickListener(timeButtonClickListener);
        button1400.setOnClickListener(timeButtonClickListener);
        button1600.setOnClickListener(timeButtonClickListener);
        button1800.setOnClickListener(timeButtonClickListener);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!selectedTime.isEmpty()) {
                    Intent intent = new Intent(MasterActivity.this, LoadingActivity.class);
                    intent.putExtra("selectedTime", selectedTime);
                    intent.putExtra("selectedServices", selectedServices);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_up_in, R.anim.slide_up_out);
                }
            }
        });
    }

}
