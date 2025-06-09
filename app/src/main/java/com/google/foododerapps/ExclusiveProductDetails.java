package com.google.foododerapps;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

public class ExclusiveProductDetails extends AppCompatActivity {

    String imageUrl, productTile, productIngredientstwo, productCategoryList, productBrandList ;
    RoundedImageView roundedImageCat1;
    TextView productTitle;
     TextView productIngredients, productCategory, productRating, deliveryTime, productBrand;
    double productRatingList;
    int deliveryTimeList;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exclusive_product_details);

        imageUrl = getIntent().getStringExtra("image");
        productTile = getIntent().getStringExtra("name");
        productIngredientstwo = getIntent().getStringExtra("ingredients");
        productCategoryList = getIntent().getStringExtra("tags");
        productRatingList = getIntent().getDoubleExtra("rating" , 0.000);
        deliveryTimeList = getIntent().getIntExtra("cookTimeMinutes", 0 );
        productBrandList = getIntent().getStringExtra("cuisine");





        // Step 2: findViewById for the RoundedImageView
        roundedImageCat1 = findViewById(R.id.roundedImageCat1);
        productTitle = findViewById(R.id.productTitle);
        productIngredients = findViewById(R.id.productIngredients);
        productCategory = findViewById(R.id.productCategory);
        productRating = findViewById(R.id.productRating);
        deliveryTime = findViewById(R.id.deliveryTime);
        productBrand = findViewById(R.id.productBrand);





        productTitle.setText(productTile);
        productIngredients.setText(productIngredientstwo);
        productCategory.setText(productCategoryList);
        productRating.setText(String.format("%.1f", productRatingList));
        deliveryTime.setText(String.format("%d minutes", deliveryTimeList));
        productBrand.setText(productBrandList);







        Glide.with(this)
                .load(imageUrl)
                .into(roundedImageCat1);


    }



    }
