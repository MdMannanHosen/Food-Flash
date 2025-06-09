package com.google.foododerapps.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.foododerapps.ExclusiveProductDetails;
import com.google.foododerapps.R;
import com.google.foododerapps.models.ExclusiveModels;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;


public class ExclusiveAdapter extends RecyclerView.Adapter<ExclusiveAdapter.ViewHolder> {

    List<ExclusiveModels> list;
    Context context;

    public ExclusiveAdapter(Context context, List<ExclusiveModels> list) {
        this.context = context;
        this.list = list;

}

    @NonNull
    @Override
    public ExclusiveAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.exclusive_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ExclusiveAdapter.ViewHolder holder, int position) {

        ExclusiveModels model = list.get(position);


        Glide.with(context).load(model.getImage()).into(holder.roundedImageCat);

        String name = model.getName().trim();
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

        holder.textViewName.setText(shortenedName);

       holder.textViewName.setText(model.getName());
        holder.textViewRating.setText(String.valueOf(model.getRating()));
        holder.textViewTime.setText(model.getCookTimeMinutes() + " min");
        holder.textViewFeature.setText(model.getCuisine());



        holder.roundedImageCat.setOnClickListener(v -> {

            Intent myIntent = new Intent(v.getContext(), ExclusiveProductDetails.class);
            myIntent.putExtra("image", model.getImage());
            myIntent.putExtra("name", model.getName());
            myIntent.putExtra("ingredients", model.getIngredients());
            myIntent.putExtra("rating", model.getRating());
            myIntent.putExtra("cuisine", model.getCuisine());
            myIntent.putExtra("tags", model.getTags());
            myIntent.putExtra("ingredients", model.getIngredients());
            myIntent.putExtra("cookTimeMinutes", model.getCookTimeMinutes());
            myIntent.putExtra("rating", model.getRating());
            v.getContext().startActivity(myIntent);



        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RoundedImageView roundedImageCat;
        TextView textViewName, textViewRating, textViewTime, textViewPrice, textViewFeature;
        ImageView imageViewBike;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            roundedImageCat = itemView.findViewById(R.id.roundedImageCat);
            textViewName = itemView.findViewById(R.id.textView3);
            textViewRating = itemView.findViewById(R.id.textView4);
            textViewTime = itemView.findViewById(R.id.textView5);
            // Add these IDs to your XML or use existing ones
            textViewPrice = itemView.findViewById(R.id.textView6); // For calories
            textViewFeature = itemView.findViewById(R.id.textView7); // For cuisine
            imageViewBike = itemView.findViewById(R.id.imageView2);
        }
    }

}