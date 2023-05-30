package com.prequelapp.lib.controlhelthapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import android.content.SharedPreferences;
import com.prequelapp.lib.controlhelthapp.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.function.ToDoubleFunction;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        SharedPreferences sp = requireActivity().getSharedPreferences("c",
                Context.MODE_PRIVATE);
        boolean hasVisited = sp.getBoolean("hasVisited", false);
        String calories_all = sp.getString("c", getArguments().getString("count"));
        if (!hasVisited) {
            SharedPreferences.Editor e = sp.edit();
            e.putString("c", calories_all);
            e.apply();
        }

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        binding.test.setText(calories_all);
        FoodDB foodDB = FoodDB.getInstance(requireContext());
        FoodDao foodDao = foodDB.foodDao();
        binding.imageApple.setImageResource(R.drawable.apple0);
        binding.imageBottle.setImageResource(R.drawable.bottle0);
        binding.imageApple.setOnClickListener(v ->
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_navigation_home_to_choiceFragment)
        );

        //foodDao.addFood(new Food());



        return binding.getRoot();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
