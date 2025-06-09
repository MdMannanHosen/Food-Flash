package com.google.foododerapps;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.google.android.material.chip.Chip;
import com.makeramen.roundedimageview.RoundedImageView;

public class CategoryProductDetails extends AppCompatActivity {

   String productImageViewDts, tvProductNameDts, tvCategoryDts, tvOriginDts, tvBrandNameDts, tvProductDescriptionDts;
    RoundedImageView productImageView;
    TextView tvProductName, tvProductPrice,  tvCategory, tvOrigin, tvBrandName, tvProductDescription, tvCalories, tvFat, tvCarbs, tvProtein;
    RatingBar ratingBar;
    Chip chipAvailability;
    int tvProductPriceDts, tvCaloriesDts, tvProteinDts ;
    double tvFatDts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_product_details);


        productImageViewDts = getIntent().getStringExtra("image");
        tvProductNameDts = getIntent().getStringExtra("name");
        tvCategoryDts = getIntent().getStringExtra("category");
        tvOriginDts = getIntent().getStringExtra("origin");
        tvBrandNameDts = getIntent().getStringExtra("brand");
        tvProductDescriptionDts = getIntent().getStringExtra("description");
        tvProductPriceDts = getIntent().getIntExtra("price", 0);//
        tvCaloriesDts = getIntent().getIntExtra("calories", 0);
        tvFatDts = getIntent().getDoubleExtra("fat", 0);
        tvProteinDts = getIntent().getIntExtra("protein", 0);







        productImageView = findViewById(R.id.productImageView);
        tvProductName = findViewById(R.id.tvProductName);
        tvProductPrice = findViewById(R.id.tvProductPrice);
        tvCategory = findViewById(R.id.tvCategory);
        tvOrigin = findViewById(R.id.tvOrigin);
        tvBrandName = findViewById(R.id.tvBrandName);
        tvProductDescription = findViewById(R.id.tvProductDescription);
        tvCalories = findViewById(R.id.tvCalories);
        tvFat = findViewById(R.id.tvFat);
        tvCarbs = findViewById(R.id.tvCarbs);
        tvProtein = findViewById(R.id.tvProtein);
        ratingBar = findViewById(R.id.ratingBar);
        chipAvailability = findViewById(R.id.chipAvailability);



        Glide.with(this)
                .load(productImageViewDts)
                .into(productImageView);

        tvProductName.setText(tvProductNameDts);
        tvCategory.setText("Category: " + tvCategoryDts);
        tvOrigin.setText("Origin : " + tvOriginDts);
        tvBrandName.setText( tvBrandNameDts);
        tvProductDescription.setText(tvProductDescriptionDts);
        tvProductPrice.setText( tvProductPriceDts + " ৳");
        tvCalories.setText("Calories : "+ tvCaloriesDts  );
        tvFat.setText("Fat : " + tvFatDts);
        tvProtein.setText("Protein : " + tvProteinDts);



        Drawable stars = ratingBar.getProgressDrawable();
        stars.setColorFilter(ContextCompat.getColor(this, R.color.purple_200), PorterDuff.Mode.SRC_ATOP);
        ratingBar.setRating(3.5f);


    }
}