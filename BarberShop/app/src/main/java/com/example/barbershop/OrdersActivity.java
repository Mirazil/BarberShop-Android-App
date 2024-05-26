package com.example.barbershop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrdersActivity extends AppCompatActivity {

    private LinearLayout ordersContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        ordersContainer = findViewById(R.id.orders_container);
        Button homeButton = findViewById(R.id.home_button);

        // Получаем данные из SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("OrdersData", MODE_PRIVATE);
        String orders = sharedPreferences.getString("orders", "");

        // Преобразуем строку заказов в список
        List<String> ordersList = new ArrayList<>(Arrays.asList(orders.split("\n")));

        // Удаляем пустые строки
        ordersList.removeAll(Arrays.asList("", null));

        // Отображаем последние 5 заказов
        displayOrders(ordersList);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrdersActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_down_in, R.anim.slide_down_out);
            }
        });
    }

    private void displayOrders(List<String> ordersList) {
        int count = ordersList.size();
        int start = Math.max(count - 5, 0); // Начинаем с последнего или с 0, если заказов меньше 5

        // Обратный порядок для последних 5 заказов
        for (int i = count - 1; i >= start; i--) {
            String order = ordersList.get(i);
            TextView orderTextView = new TextView(this);

            if (i == count - 1) {
                // Последний заказ - зелёный бокс
                orderTextView.setBackgroundResource(R.drawable.green_box);
            } else {
                // Остальные заказы - серые боксы
                orderTextView.setBackgroundResource(R.drawable.gray_box);
            }

            orderTextView.setText(order);
            orderTextView.setTextSize(18);
            orderTextView.setTextColor(getResources().getColor(android.R.color.white));
            orderTextView.setTypeface(getResources().getFont(R.font.intro)); // Устанавливаем шрифт

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, 0, 16);
            ordersContainer.addView(orderTextView, 0, params); // Добавляем в начало, чтобы сохранить обратный порядок
        }
    }
}
