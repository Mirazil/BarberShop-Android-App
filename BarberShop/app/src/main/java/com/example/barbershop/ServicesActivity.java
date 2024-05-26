package com.example.barbershop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import java.util.HashSet;
import java.util.Set;

public class ServicesActivity extends AppCompatActivity {

    private int selectedServicesCount = 0;
    private TextView priceHaircuts, priceShaving, priceHairColoring, priceHairStyling;
    private RelativeLayout haircutsLayout, shavingLayout, hairColoringLayout, hairStylingLayout, cartLayout;
    private Set<String> selectedServices = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        haircutsLayout = findViewById(R.id.haircuts_layout);
        shavingLayout = findViewById(R.id.shaving_layout);
        hairColoringLayout = findViewById(R.id.hair_coloring_layout);
        hairStylingLayout = findViewById(R.id.hair_styling_layout);
        cartLayout = findViewById(R.id.cart_layout);

        priceHaircuts = findViewById(R.id.price_haircuts);
        priceShaving = findViewById(R.id.price_shaving);
        priceHairColoring = findViewById(R.id.price_hair_coloring);
        priceHairStyling = findViewById(R.id.price_hair_styling);

        haircutsLayout.setOnClickListener(serviceClickListener);
        shavingLayout.setOnClickListener(serviceClickListener);
        hairColoringLayout.setOnClickListener(serviceClickListener);
        hairStylingLayout.setOnClickListener(serviceClickListener);
        cartLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServicesActivity.this, MasterActivity.class);
                intent.putExtra("selectedServices", String.join(", ", selectedServices));
                startActivity(intent);
                overridePendingTransition(R.anim.slide_up_in, R.anim.slide_up_out);
            }
        });
    }

    private final View.OnClickListener serviceClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            toggleServiceSelection((RelativeLayout) v);
        }
    };

    private void toggleServiceSelection(RelativeLayout serviceLayout) {
        boolean isSelected = (serviceLayout.getTag() != null && (boolean) serviceLayout.getTag());
        TextView priceText = null;
        String service = "";

        if (serviceLayout.getId() == R.id.haircuts_layout) {
            priceText = priceHaircuts;
            service = "Haircuts";
        } else if (serviceLayout.getId() == R.id.shaving_layout) {
            priceText = priceShaving;
            service = "Shaving";
        } else if (serviceLayout.getId() == R.id.hair_coloring_layout) {
            priceText = priceHairColoring;
            service = "Hair Coloring";
        } else if (serviceLayout.getId() == R.id.hair_styling_layout) {
            priceText = priceHairStyling;
            service = "Hair Styling";
        }

        if (isSelected) {
            serviceLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.service_circle));
            if (priceText != null) {
                priceText.setTextColor(ContextCompat.getColor(this, R.color.secondary_beige));
            }
            selectedServices.remove(service);
            selectedServicesCount--;
        } else {
            serviceLayout.setBackground(ContextCompat.getDrawable(this, R.drawable.service_circle_selected));
            if (priceText != null) {
                priceText.setTextColor(ContextCompat.getColor(this, android.R.color.white));
            }
            selectedServices.add(service);
            selectedServicesCount++;
        }
        serviceLayout.setTag(!isSelected);
        updateCartCount();
    }

    private void updateCartCount() {
        TextView cartCount = findViewById(R.id.cart_count);
        cartCount.setText(String.valueOf(selectedServicesCount));
    }

}
