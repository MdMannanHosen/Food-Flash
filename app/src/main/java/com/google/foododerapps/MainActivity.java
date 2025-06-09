package com.google.foododerapps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.makeramen.roundedimageview.RoundedImageView;

public class MainActivity extends AppCompatActivity {

    RoundedImageView roundedImageView, panda_mart;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        roundedImageView = findViewById(R.id.roundedImageView);
        panda_mart = findViewById(R.id.panda_mart);
        editText = findViewById(R.id.editText);

        roundedImageView.setOnClickListener(v -> {

            Intent intent = new Intent(MainActivity.this, RestaurantActivity.class);
            startActivity(intent);

        });

        panda_mart.setOnClickListener(v -> {

            Intent intent = new Intent(MainActivity.this, PandaMartActivity
                    .class);
            startActivity(intent);

        });

    }
}