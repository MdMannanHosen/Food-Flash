package com.google.foododerapps.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.foododerapps.R;
import com.google.foododerapps.models.TeaCoffeeModels;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class TeaCoffeeAdapter extends RecyclerView.Adapter<TeaCoffeeAdapter.ViewHolder> {

    List<TeaCoffeeModels> list;
    Context context;

    public TeaCoffeeAdapter(List<TeaCoffeeModels> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public TeaCoffeeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.tea_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TeaCoffeeAdapter.ViewHolder holder, int position) {

       TeaCoffeeModels models = list.get(position);

        Glide.with(context)
                .load(models.getImage())
                .placeholder(R.drawable.cat1)
                .into(holder.roundedImgTea);

        holder.tvCoffee.setText(models.getName());
        holder.tvBrandName.setText(models.getType());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RoundedImageView  roundedImgTea;
        TextView tvCoffee, tvBrandName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            roundedImgTea = itemView.findViewById(R.id.roundedImgTea);
            tvCoffee = itemView.findViewById(R.id.tvCoffee);
            tvBrandName = itemView.findViewById(R.id.tvBrandName);

        }
    }


}
