package com.example.barbershop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SuccessActivity extends AppCompatActivity {
    private TextView orderTextView, timeTextView;
    private Button homeButton, myOrdersButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        orderTextView = findViewById(R.id.order_text_view);
        timeTextView = findViewById(R.id.time_text_view);
        homeButton = findViewById(R.id.home_screen_button);
        myOrdersButton = findViewById(R.id.my_orders_button);

        // Получаем переданные данные
        String selectedServices = getIntent().getStringExtra("selectedServices");
        String selectedTime = getIntent().getStringExtra("selectedTime");

        // Отображаем данные
        orderTextView.setText(selectedServices);
        timeTextView.setText(selectedTime);

        // Сохраняем данные в SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("OrdersData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String newOrder = "ORDER: " + selectedServices + " TIME: " + selectedTime;
        String existingOrders = sharedPreferences.getString("orders", "");
        String updatedOrders = newOrder + "\n" + existingOrders;
        editor.putString("orders", updatedOrders.trim());
        editor.apply();

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SuccessActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_up_in, R.anim.slide_up_out);
            }
        });

        myOrdersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SuccessActivity.this, OrdersActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_up_in, R.anim.slide_up_out);
            }
        });
    }
}
