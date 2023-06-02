package com.prequelapp.lib.controlhelthapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.prequelapp.lib.controlhelthapp.databinding.FragmentAddWaterBinding;

public class AddWaterFragment extends Fragment {
    int water = 0;
    FragmentAddWaterBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAddWaterBinding.inflate(inflater,container,false);
        binding.water1.setOnClickListener(v ->{
            water = 50;
            f(water);
        });
        binding.water2.setOnClickListener(v ->{
            water = 100;
            f(water);
        });
        binding.water3.setOnClickListener(v ->{
            water = 250;
            f(water);
        });
        binding.water4.setOnClickListener(v ->{
            water = 500;
            f(water);
        });




        return binding.getRoot();
    }
    void f(int w){
        SharedPreferences wat = requireActivity().getSharedPreferences("c", Context.MODE_PRIVATE);
        SharedPreferences.Editor waterRed = wat.edit();
        waterRed.putInt("water_c", w + wat.getInt("water_c", 0));
        waterRed.apply();
        Navigation.findNavController(binding
                .getRoot()).navigate(R.id.action_addWaterFragment_to_navigation_home);
    }
}
