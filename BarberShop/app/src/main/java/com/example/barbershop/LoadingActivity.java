package com.example.barbershop;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();
        String selectedTime = intent.getStringExtra("selectedTime");
        String selectedServices = intent.getStringExtra("selectedServices");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fake_loading);

        // Генерация случайной длительности загрузки от 1.5 до 3.5 секунд
        Random random = new Random();
        int minLoadingTime = 3500; // 3.5 секунды
        int maxLoadingTime = 6500; // 6.5 секунды
        int loadingTime = minLoadingTime + random.nextInt(maxLoadingTime - minLoadingTime);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LoadingActivity.this, SuccessActivity.class);

                intent.putExtra("selectedTime", selectedTime);
                intent.putExtra("selectedServices", selectedServices);

                startActivity(intent);
                overridePendingTransition(R.anim.slide_up_in, R.anim.slide_up_out);
                finish();
            }
        }, loadingTime);
    }
}
