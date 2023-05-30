package com.prequelapp.lib.controlhelthapp;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.prequelapp.lib.controlhelthapp.databinding.SmileItemBinding;

import java.util.ArrayList;

public class ReactionAdapter extends RecyclerView.Adapter<ReactionAdapter.ReactionViewHolder> {

    private final ArrayList<Reaction> data;
    public ReactionAdapter(ArrayList<Reaction> data) {
        this.data = data;
    }
    @NonNull
    @Override
    public ReactionAdapter.ReactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SmileItemBinding binding = SmileItemBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new ReactionViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ReactionAdapter.ReactionViewHolder holder, int position) {
        Reaction item = data.get(position);
       int number = item.getCount();
       switch (number){
           case 1:{
               holder.binding.smile.setImageResource(R.drawable.sentiment_very_satisfied_fill0_wght400_grad0_opsz48);
               break;
           }
           case 2:{
               holder.binding.smile.setImageResource(R.drawable.mood_fill0_wght400_grad0_opsz48);
               break;
           }
           case 3:{
               holder.binding.smile.setImageResource(R.drawable.sentiment_neutral_fill0_wght400_grad0_opsz48);
               break;
           }
           case 4:{
               holder.binding.smile.setImageResource(R.drawable.sentiment_dissatisfied_fill0_wght400_grad0_opsz48);
               break;
           }
           case 5:{
               holder.binding.smile.setImageResource(R.drawable.sentiment_very_dissatisfied_fill0_wght400_grad0_opsz48);
               break;
           }
       }
       holder.binding.dateTime.setText(item.getTime());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    class ReactionViewHolder extends RecyclerView.ViewHolder {

        SmileItemBinding binding;

        public ReactionViewHolder(@NonNull SmileItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

