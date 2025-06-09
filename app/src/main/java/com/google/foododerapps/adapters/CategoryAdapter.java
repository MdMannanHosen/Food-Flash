package com.google.foododerapps.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.foododerapps.CategoryProductDetails;
import com.google.foododerapps.R;
import com.google.foododerapps.models.CategoryModels;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class

CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {


    List<CategoryModels> list;
    Context context;

    public CategoryAdapter(List<CategoryModels> list, Context context) {
        this.list = list;
        this.context = context;
    }



    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {

      CategoryModels models = list.get(position);


        String imageUrl = models.getImage();

        Log.d("IMAGE_DEBUG", "Image URL: " + imageUrl);  // Logcat এ URL দেখার জন্য

        if (imageUrl != null && imageUrl.startsWith("http")) {
            Glide.with(context)
                    .load(imageUrl)
                    .placeholder(R.drawable.cat1)
                    .error(R.drawable.lays1)
                    .into(holder.roundedImageCat);
        } else {
            holder.roundedImageCat.setImageResource(R.drawable.lays1);
        }


        String name = models.getName().trim();
        String[] words = name.split("\\s+");

        String shortenedName;
        if (words.length >= 2) {
            shortenedName = words[0] + " " + words[1] + "...";
        } else {
            shortenedName = name;
        }
        if (shortenedName.length() > 20) {
            shortenedName = words[0].substring(0, Math.min(15, words[0].length())) + "...";
        }

        holder.textCatName.setText(shortenedName);
        holder.textCatBrand.setText(models.getBrand());
        holder.textCatPrice.setText(String.valueOf(models.getPrice()));

        holder.roundedImageCat.setOnClickListener(view -> {

            Intent intent = new Intent(view.getContext(), CategoryProductDetails.class);
            intent.putExtra("image", models.getImage());
            intent.putExtra("name", models.getName());
            intent.putExtra("brand", models.getBrand());
            intent.putExtra("type", models.getType());
            intent.putExtra("category", models.getCategory());
            intent.putExtra("description", models.getDescription());
            intent.putExtra("origin", models.getOrigin());
            intent.putExtra("calories", models.getCalories());
            intent.putExtra("protein", models.getProtein());
            intent.putExtra("fat", models.getFat());
            intent.putExtra("expiry", models.getExpiry());
           // intent.putExtra("tags", models.getTags());
            intent.putExtra("price", models.getPrice());



            view.getContext().startActivity(intent);

        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RoundedImageView roundedImageCat;
        TextView  textCatBrand,textCatPrice, textCatName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            roundedImageCat= itemView.findViewById(R.id.roundedImageCat);

            textCatBrand = itemView.findViewById(R.id.textCatBrand);
            textCatPrice = itemView.findViewById(R.id.textCatPrice);
            textCatName = itemView.findViewById(R.id.textCatName);
        }
    }
}
