package com.google.foododerapps.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.foododerapps.R;
import com.google.foododerapps.models.OffersModels;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.ViewHolder> {

    List <OffersModels> list;

    public OffersAdapter(List<OffersModels> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public OffersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.offer_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OffersAdapter.ViewHolder holder, int position) {

        holder.roundedImageView.setImageResource(list.get(position).getImg());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RoundedImageView  roundedImageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            roundedImageView= itemView.findViewById(R.id.img);
        }
    }
}
