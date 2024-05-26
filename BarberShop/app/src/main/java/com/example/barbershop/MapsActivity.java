package com.example.barbershop;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

public class MapsActivity extends FragmentActivity {
    private static final String TAG = "MapsActivity";
    private Button homeButton;
    private int defaultColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Initialize the defaultColor
        defaultColor = ContextCompat.getColor(this, R.color.button_background_color);

        RelativeLayout shopBox1 = findViewById(R.id.shop_box1);
        RelativeLayout shopBox2 = findViewById(R.id.shop_box2);
        RelativeLayout shopBox3 = findViewById(R.id.shop_box3);
        RelativeLayout shopBox4 = findViewById(R.id.shop_box4);
        RelativeLayout shopBox5 = findViewById(R.id.shop_box5);

        // Set background color using defaultColor for all boxes
        setBoxColor(shopBox1);
        setBoxColor(shopBox2);
        setBoxColor(shopBox3);
        setBoxColor(shopBox4);
        setBoxColor(shopBox5);

        // Initialize and set color for homeButton
        homeButton = findViewById(R.id.home_screen_button);
        setButtonColor(homeButton);

        shopBox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Shop box 1 clicked");
                openMapLocation();
            }
        });

        shopBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Shop box 2 clicked");
                openMapLocation();
            }
        });

        shopBox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Shop box 3 clicked");
                openMapLocation();
            }
        });

        shopBox4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Shop box 4 clicked");
                openMapLocation();
            }
        });

        shopBox5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Shop box 5 clicked");
                openMapLocation();
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapsActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_down_in, R.anim.slide_down_out);
            }
        });
    }

    private void setBoxColor(RelativeLayout box) {
        GradientDrawable drawable = (GradientDrawable) box.getBackground();
        drawable.setColor(defaultColor);
    }

    private void setButtonColor(Button button) {
        button.setBackgroundColor(defaultColor);
    }

    private void openMapLocation() {
        // Использование другого URI для открытия Google Maps
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=вулиця Василя Липківського, 18, Київ, 03035");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            Log.d(TAG, "Starting map activity");
            startActivity(mapIntent);
        } else {
            Log.e(TAG, "No application can handle this request. Please install a web browser or maps application.");
        }
    }
}
