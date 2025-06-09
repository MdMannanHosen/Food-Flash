package com.google.foododerapps.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.foododerapps.R;
import com.google.foododerapps.models.AllRestaurantModels;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class AllRestaurantsAdapter extends RecyclerView.Adapter<AllRestaurantsAdapter.ViewHolder> {

    private List<AllRestaurantModels> list;
    private Context context;

    public AllRestaurantsAdapter(List<AllRestaurantModels> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.all_res_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AllRestaurantModels model = list.get(position);

        // ইমেজ লোড
        Glide.with(context)
                .load(model.getImage())
                .placeholder(R.drawable.cat1)
                .into(holder.roundedImage);

        // নাম প্রসেসিং
        String name = model.getName().trim();
        String[] words = name.split("\\s+");
        String displayName;

        if (words.length >= 3) {
            displayName = words[0] + " " + words[1] + " " + words[2] + "...";
        } else {
            displayName = name;
        }

        if (displayName.length() > 20) {
            displayName = displayName.substring(0, 17) + "...";
        }

        holder.tvRestaurantName.setText(displayName);
        holder.tvRatting.setText(String.format("%.1f", model.getRating()));
        holder.tvDeliveryTime.setText(model.getDeliveryTime() + " min");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView roundedImage;
        TextView tvRestaurantName, tvRatting, tvDeliveryTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            roundedImage = itemView.findViewById(R.id.roundedImage);
            tvRestaurantName = itemView.findViewById(R.id.tvRestaurantName);
            tvRatting = itemView.findViewById(R.id.tvRatting);
            tvDeliveryTime = itemView.findViewById(R.id.tvDeliveryTime);
        }
    }
}