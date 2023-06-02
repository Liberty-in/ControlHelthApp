package com.prequelapp.lib.controlhelthapp;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prequelapp.lib.controlhelthapp.databinding.FoodItemBinding;
import com.prequelapp.lib.controlhelthapp.databinding.GlucoseItemBinding;

import java.util.ArrayList;

public class GlucoseAdapter extends RecyclerView.Adapter<GlucoseAdapter.GlucoseViewHolder> {

    private final ArrayList<Glucose> data;
    public GlucoseAdapter(ArrayList<Glucose> data) {
        this.data = data;
    }
    @NonNull
    @Override
    public GlucoseAdapter.GlucoseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        GlucoseItemBinding binding = GlucoseItemBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new GlucoseViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GlucoseAdapter.GlucoseViewHolder holder, int position) {
        Glucose item = data.get(data.size() - position - 1);
        double c = item.getCount();
        holder.binding.count.setText(String.format("%.1f", c));
        holder.binding.dateTime.setText(item.getTime_of_measure());
        if (c < 3.5)
            holder.binding.colorRes.setBackgroundResource(R.drawable.round_blue);
        if (c > 8.3)
            holder.binding.colorRes.setBackgroundResource(R.drawable.round_red);


    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    class GlucoseViewHolder extends RecyclerView.ViewHolder {

        GlucoseItemBinding binding;

        public GlucoseViewHolder(@NonNull GlucoseItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
