package com.prequelapp.lib.controlhelthapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.prequelapp.lib.controlhelthapp.databinding.FragmentAddFoodBinding;

import java.util.Arrays;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddFoodFragment extends Fragment {
    private FragmentAddFoodBinding binding;
    Disposable listFood;
    Disposable currentFood;
    String name = "";
    Integer current_c;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentAddFoodBinding.inflate(inflater, container, false);
        FoodDB foodDB = FoodDB.getInstance(requireContext());
        FoodDao foodDao = foodDB.foodDao();
        listFood =  foodDao.getFoodName().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::getList);

        /*binding.buttonok.setOnClickListener(v ->
                currentFood = foodDao.getInfo(name).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::setArg));*/

        binding.button.setOnClickListener(v -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_nav_addFoodFragment_to_navigation_home);
        });

        return binding.getRoot();
    }

    private void setArg(Integer c) {
        /*Float b = Float.valueOf(strings.get(0));
        Float g = Float.valueOf(strings.get(1));
        Float y = Float.valueOf(strings.get(2));
        Integer c = Integer.valueOf(strings.get(3));
        binding.b.setText(binding.b.getText() + strings.get(0));
        binding.g.setText(binding.g.getText() + strings.get(1));
        binding.y.setText(binding.y.getText() + strings.get(2));*/
        if (binding.countf.getText() != null){
            current_c = (int)Float.parseFloat(String.valueOf(binding.countf.getText()))/100 * c;
        }

    }

    private void getList(String[] strings) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, strings);
        binding.autocomplete.setAdapter(adapter);
        binding.autocomplete.setOnItemClickListener((parent, view, position, id) -> {
            name = parent.getItemAtPosition(position).toString();
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
