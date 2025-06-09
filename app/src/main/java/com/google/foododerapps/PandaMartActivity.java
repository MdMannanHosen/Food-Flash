package com.google.foododerapps;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.foododerapps.adapters.CategoryAdapter;
import com.google.foododerapps.adapters.ExclusiveAdapter;
import com.google.foododerapps.adapters.LaysAdapter;
import com.google.foododerapps.adapters.OffersAdapter;
import com.google.foododerapps.adapters.SliderAdapter;
import com.google.foododerapps.adapters.TeaCoffeeAdapter;
import com.google.foododerapps.models.CategoryModels;
import com.google.foododerapps.models.ExclusiveModels;
import com.google.foododerapps.models.OffersModels;
import com.google.foododerapps.models.SliderData;
import com.google.foododerapps.models.SnackModels;
import com.google.foododerapps.models.TeaCoffeeModels;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PandaMartActivity extends AppCompatActivity {

    ImageView imageBack;
    EditText editText;

    RecyclerView recyclerViewCategory;
    List<CategoryModels> categoryModels;
    CategoryAdapter categoryAdapter;

    RecyclerView recyclerViewTeaCoffee;
    List<TeaCoffeeModels> teaCoffeeModels;
    TeaCoffeeAdapter teaCoffeeAdapter;

    RecyclerView recyclerViewSnack;
    List<SnackModels> snackModels;
    LaysAdapter laysAdapter;

    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panda_mart);
        requestQueue = Volley.newRequestQueue(this);
        imageBack = findViewById(R.id.imageBack);
        editText = findViewById(R.id. editText);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                filterCategory(getReferrer().getQuery());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        imageBack.setOnClickListener(v -> {
            Intent intent = new Intent(PandaMartActivity.this, MainActivity.class);
            startActivity(intent);

        });



        getAllSlider();
        getCategory();
        getTeaCoffee();
        getSnack();




    }

    private void getSnack() {

        recyclerViewSnack = findViewById(R.id.rec_snack);
        recyclerViewSnack.setLayoutManager(new LinearLayoutManager(this,
                RecyclerView.HORIZONTAL, false));
        recyclerViewSnack.setHasFixedSize(true);

        snackModels = new ArrayList<>();
        laysAdapter = new LaysAdapter(snackModels, PandaMartActivity.this);
        recyclerViewSnack.setAdapter(laysAdapter);



        String url = "https://mdmannanhosen.github.io/sanck/snack.json";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    try {
                        // Extract the "products" JSON array
                        JSONArray jsonArray = response.getJSONArray("snacks");
                        Log.d("PandaMartSnack", "Data received. Items: " + jsonArray.length());

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject item = jsonArray.getJSONObject(i);
                            snackModels.add(new SnackModels(
                                    item.getString("name"),
                                    item.getString("brand"),
                                    item.getString("image"),
                                    item.getInt("price")
                            ));

                        }
                        laysAdapter.notifyDataSetChanged(); // Update RecyclerView

                    } catch (JSONException e) {
                        Log.e("PandaMart", "JSON Error: " + e.getMessage());
                    }
                },
                error -> Log.e("PandaMart", "Volley Error: " + error.getMessage())
        );

        requestQueue.add(jsonObjectRequest);







    }

    private void getTeaCoffee() {
        recyclerViewTeaCoffee = findViewById(R.id.rec_tea);
        StaggeredGridLayoutManager layoutManager =
                new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL);

        recyclerViewTeaCoffee.setLayoutManager(layoutManager);
        recyclerViewTeaCoffee.setHasFixedSize(true);


        teaCoffeeModels = new ArrayList<>();

        teaCoffeeAdapter = new TeaCoffeeAdapter(teaCoffeeModels, PandaMartActivity.this);
        recyclerViewTeaCoffee.setAdapter(teaCoffeeAdapter);

        String url = "https://mdmannanhosen.github.io/tea_coffee/tea_coffee.json";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                response -> {
                    Log.d("PandaActivity", "Full response: " + response.toString());
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject item = response.getJSONObject(i);
                            String type = item.getString("type");
                            String name = item.getString("name");
                            String image = item.getString("image");

                            TeaCoffeeModels models = new TeaCoffeeModels(type, name, image);
                            teaCoffeeModels.add(models);

                        } catch (JSONException e) {

                            Log.d("PandaActivity", "Full response: " + response.toString());
                            e.printStackTrace();
                        }
                    }

                    teaCoffeeAdapter.notifyDataSetChanged();
                },
                error ->

                        error.printStackTrace()
        );

        requestQueue.add(jsonArrayRequest);


    }

    private void getCategory() {
        recyclerViewCategory = findViewById(R.id.rec_cat);
        recyclerViewCategory.setLayoutManager(new GridLayoutManager(
                this,
                2,
                RecyclerView.HORIZONTAL,
                false
        ));
        recyclerViewCategory.setHasFixedSize(true);

        categoryModels = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(categoryModels, PandaMartActivity.this);
        recyclerViewCategory.setAdapter(categoryAdapter);

        String url = "https://mdmannanhosen.github.io/meat_fruits_vegetable_api/fruits_vegetable.json";




        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    try {
                        JSONArray jsonArray = response.getJSONArray("products");


                        Log.d("PandaMart", "Data received. Items: " + jsonArray.length());

                        for (int i = 0; i < jsonArray.length(); i++) {
                            try {
                                JSONObject item = jsonArray.getJSONObject(i);

                                String image = item.optString("image", "");
                                String name = item.optString("name", "");
                                String brand = item.optString("brand", "");
                                String type = item.optString("type", "");
                                String category = item.optString("category", "");
                                String description = item.optString("description", "");
                                String origin = item.optString("origin", "");
                                int calories = 0;
                                int protein = 0;
                                double fat = 0.0;
                                if (item.has("nutrition") && !item.isNull("nutrition")) {
                                    JSONObject nutrition = item.getJSONObject("nutrition");
                                    calories = nutrition.optInt("calories", 0);
                                    protein = nutrition.optInt("protein", 0);
                                    fat = nutrition.optDouble("fat", 0.0);
                                }

                                String expiry = item.optString("expiry", "N/A"); // default "N/A" or ""

                                // Optional: tags array
                                List<String> tags = new ArrayList<>();
                                if (item.has("tags") && !item.isNull("tags")) {
                                    JSONArray tagsArray = item.getJSONArray("tags");
                                    for (int j = 0; j < tagsArray.length(); j++) {
                                        tags.add(tagsArray.getString(j));
                                    }
                                }

                                int price = item.optInt("price", 0);

                                CategoryModels models = new CategoryModels(image, name, brand, type, category,
                                        description, origin, calories, protein, fat, tags, expiry, price);

                                categoryModels.add(models);

                            } catch (JSONException e) {
                                Log.e("PandaMart", "Error parsing item at index " + i + ": " + e.getMessage());
                            }
                        }


                        categoryAdapter.notifyDataSetChanged(); // Update RecyclerView



                    } catch (JSONException e) {
                        Log.e("PandaMart", "JSON Error: " + e.getMessage());
                    }
                },
                error -> Log.e("PandaMart", "Volley Error: " + error.getMessage())
        );

        requestQueue.add(jsonObjectRequest);
    }


    private void getAllSlider() {

        ArrayList<SliderData> sliderDataArrayList = new ArrayList<>();
        SliderView sliderView = findViewById(R.id.slider);


        sliderDataArrayList.add(new SliderData(R.drawable.food1));
        sliderDataArrayList.add(new SliderData(R.drawable.food4));
        sliderDataArrayList.add(new SliderData(R.drawable.food2));
        sliderDataArrayList.add(new SliderData(R.drawable.food3));
        sliderDataArrayList.add(new SliderData(R.drawable.food1));
        sliderDataArrayList.add(new SliderData(R.drawable.food4));
        sliderDataArrayList.add(new SliderData(R.drawable.food2));
        sliderDataArrayList.add(new SliderData(R.drawable.food3));


        SliderAdapter adapter = new SliderAdapter(this, sliderDataArrayList);
        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
        sliderView.setSliderAdapter(adapter);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();

    }

    private void filterCategory( String query) {
        List<CategoryModels> filteredList = new ArrayList<>();
        for (CategoryModels item : categoryModels) {
            if (item.getName().toLowerCase().contains(query) ||
                    item.getBrand().toLowerCase().contains(query) ||
                    item.getCategory().toLowerCase().contains(query)) {
                filteredList.add(item);
            }
        }



    }
}









