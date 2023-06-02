package com.prequelapp.lib.controlhelthapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prequelapp.lib.controlhelthapp.databinding.FoodItemBinding;

import java.util.ArrayList;

import io.reactivex.schedulers.Schedulers;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private final ArrayList<FoodStorage> data;

    public FoodAdapter(ArrayList<FoodStorage> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FoodItemBinding binding = FoodItemBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new FoodViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.FoodViewHolder holder, int position) {
        FoodStorage item = data.get(position);

        holder.binding.name.setText(item.getFoodName());
        holder.binding.calories.setText(item.getCountCalories() + " калорий");
        holder.binding.count.setText(item.getCount() + " г");
        }


    @Override
    public int getItemCount() {
        return data.size();
    }

    class FoodViewHolder extends RecyclerView.ViewHolder {

        FoodItemBinding binding;

        public FoodViewHolder(@NonNull FoodItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
