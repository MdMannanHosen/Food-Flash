package com.google.foododerapps;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.foododerapps.adapters.AllRestaurantsAdapter;
import com.google.foododerapps.adapters.ExclusiveAdapter;
import com.google.foododerapps.adapters.OffersAdapter;
import com.google.foododerapps.models.AllRestaurantModels;
import com.google.foododerapps.models.ExclusiveModels;
import com.google.foododerapps.models.OffersModels;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RestaurantActivity extends AppCompatActivity {

    ImageView imageBack;

    RecyclerView recyclerViewOffers;
    List<OffersModels> offersModels;
    OffersAdapter offersAdapter;

    RecyclerView recyclerViewExclusive;
    List<ExclusiveModels> exclusiveModels;
    ExclusiveAdapter exclusiveAdapter;

    RecyclerView recyclerViewAllRestaurant;
    List<AllRestaurantModels> allRestaurantModels;
    AllRestaurantsAdapter allRestaurantsAdapter;
    private RequestQueue requestQueue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        imageBack = findViewById(R.id.imageBack);

        imageBack.setOnClickListener(v -> {
            Intent intent = new Intent(RestaurantActivity.this, MainActivity.class);
            startActivity(intent);

        });

        requestQueue = Volley.newRequestQueue(this);
        getOffers();
        getAllRestaurantdata();
        getAllExclusivedata();
    }

    private void getAllExclusivedata() {
        String url = "https://dummyjson.com/recipes";
        recyclerViewExclusive = findViewById(R.id.rec_exe);
        recyclerViewExclusive.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerViewExclusive.setHasFixedSize(true);

        exclusiveModels = new ArrayList<>();
        exclusiveAdapter = new ExclusiveAdapter(RestaurantActivity.this, exclusiveModels);
        recyclerViewExclusive.setAdapter(exclusiveAdapter);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONArray recipesArray = response.getJSONArray("recipes");
                        Log.d("RestaurantActivity", "Full response: " + response.toString());
                        for (int i = 0; i < recipesArray.length(); i++) {
                            JSONObject recipe = recipesArray.getJSONObject(i);

                            String name = recipe.getString("name");

                            // Ingredients
                            JSONArray ingredientsArray = recipe.getJSONArray("ingredients");
                            StringBuilder ingredientsBuilder = new StringBuilder();
                            for (int j = 0; j < ingredientsArray.length(); j++) {
                                ingredientsBuilder.append(ingredientsArray.getString(j));
                                if (j < ingredientsArray.length() - 1) {
                                    ingredientsBuilder.append(", ");
                                }
                            }
                            String ingredients = ingredientsBuilder.toString();

                            // Tags (optional)
                            String tags = "";
                            if (recipe.has("tags")) {
                                JSONArray tagsArray = recipe.getJSONArray("tags");
                                StringBuilder tagsBuilder = new StringBuilder();
                                for (int j = 0; j < tagsArray.length(); j++) {
                                    tagsBuilder.append(tagsArray.getString(j));
                                    if (j < tagsArray.length() - 1) {
                                        tagsBuilder.append(", ");
                                    }
                                }
                                tags = tagsBuilder.toString();
                            }

                            String cuisine = recipe.getString("cuisine");
                            String image = recipe.getString("image");
                            int cookTimeMinutes = recipe.getInt("cookTimeMinutes");
                            double rating = recipe.getDouble("rating");

                            // Use correct constructor
                            ExclusiveModels model = new ExclusiveModels(name, ingredients, tags, cuisine, image, cookTimeMinutes, rating);
                            exclusiveModels.add(model);
                        }
                        exclusiveAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        Log.e("RestaurantActivity", "JSON parsing error: " + e.getMessage());
                        e.printStackTrace();
                    }
                },
                error -> {
                    Log.e("RestaurantActivity", "Volley error: " + error.toString());
                    error.printStackTrace();
                });

        requestQueue.add(jsonObjectRequest);
    }


    private void getAllRestaurantdata() {

        recyclerViewAllRestaurant = findViewById(R.id.rec_all_res);
        recyclerViewAllRestaurant.setLayoutManager(new GridLayoutManager(this,2 ,RecyclerView.VERTICAL, false));
        recyclerViewAllRestaurant.setHasFixedSize(true);

        allRestaurantModels = new ArrayList<>();
        allRestaurantsAdapter = new AllRestaurantsAdapter(allRestaurantModels, RestaurantActivity.this);
        recyclerViewAllRestaurant.setAdapter(allRestaurantsAdapter);



        JsonObjectRequest request = new JsonObjectRequest
                (Request.Method.GET, "https://mdmannanhosen.github.io/mock-restaurant-api/restaurants.json", null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("restaurants");
                            Log.d("RestaurantName", "Full response: " + response.toString());
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject recipe = jsonArray.getJSONObject(i);
                                String image = recipe.getString("image");
                                String name = recipe.getString("name");
                                double rating = recipe.getDouble("rating");
                                int deliveryTime = recipe.getInt("deliveryTime");
                                String cuisine = recipe.getString("cuisine");

                                AllRestaurantModels models = new AllRestaurantModels(image, name, rating, deliveryTime, cuisine);
                                allRestaurantModels.add(models);
                            }
                            allRestaurantsAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            Log.e("RestaurantName", "JSON parsing error: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("RestaurantName", "Volley error: " + error.toString());
                        error.printStackTrace();
                    }
                });

        requestQueue.add(request);

    }

    private void getOffers() {
        recyclerViewOffers = findViewById(R.id.rec_offer);
        recyclerViewOffers.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerViewOffers.setHasFixedSize(true);

        offersModels = new ArrayList<>();
        offersModels.add(new OffersModels(R.drawable.img15));
        offersModels.add(new OffersModels(R.drawable.img16));
        offersModels.add(new OffersModels(R.drawable.img19));
        offersModels.add(new OffersModels(R.drawable.img18));

        offersAdapter = new OffersAdapter(offersModels);
        recyclerViewOffers.setAdapter(offersAdapter);
    }
}