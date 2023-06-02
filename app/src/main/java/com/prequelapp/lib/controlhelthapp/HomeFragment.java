package com.prequelapp.lib.controlhelthapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.prequelapp.lib.controlhelthapp.databinding.ActivityMainBinding;
import com.prequelapp.lib.controlhelthapp.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.ToDoubleFunction;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;

    Disposable foodStorage;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        SharedPreferences sp = requireActivity().getSharedPreferences("c",
                Context.MODE_PRIVATE);
        //получение данных из SharedPreference
        int water_all = sp.getInt("water_all", 2000);
        int water_current = sp.getInt("water_c", 0);

        float proteins_all = sp.getFloat("proteins_all",60);
        float fats_all = sp.getFloat("fats_all",60);
        float carbon_all = sp.getFloat("carbon_all",70);

        float proteins_current = sp.getFloat("proteins_current",0);
        float fats_current = sp.getFloat("fats_current",0);
        float carbon_current = sp.getFloat("carbon_current",0);


        boolean new_day = sp.getBoolean("new",false);
        int count_day = sp.getInt("days", 1);
        boolean hasVisited = sp.getBoolean("hasVisited", false);
        int current_calories = sp.getInt("current_c", 10);

        int calories_all = sp.getInt("calories_form", 1200);

        if (!hasVisited) {
            SharedPreferences.Editor e = sp.edit();
            e.putInt("calories_form", calories_all);
            e.apply();
        }
        binding.addingfood.setVisibility(View.INVISIBLE);
        binding.addingwater.setVisibility(View.INVISIBLE);
        //получение данных о съеденных продуктов
        FoodStorageDB foodStorageDB = FoodStorageDB.getInstance(requireContext());
        FoodStorageDao foodStorageDao = foodStorageDB.foodStorageDao();
        foodStorage = foodStorageDao
                .getAllFoods()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onLoaded);


        //установление прогресса индикаторов
        binding.appleView.setProgress((current_calories * 100)/calories_all);
        binding.bottleView.setProgress((water_current * 100)/water_all);
        binding.bProgress.setProgress((int)(proteins_current * 100/proteins_all));
        binding.gProgress.setProgress((int)(fats_current * 100/fats_all));
        binding.yProgress.setProgress((int)(carbon_current * 100/carbon_all));
        if (calories_all - current_calories > 0)
            binding.counts.setText("Осталось калорий: " + Integer.toString(calories_all - current_calories));
        else
            binding.counts.setText("Осталось калорий: 0" );
        //переход на фрагменты добавления еды и воды
        binding.adding.setOnClickListener(v -> {
                    binding.addingfood.setVisibility(View.VISIBLE);
                    binding.addingfood.setOnClickListener(b ->
                            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_navigation_home_to_nav_addFoodFragment));
                    binding.addingwater.setVisibility(View.VISIBLE);
                    binding.addingwater.setOnClickListener(n ->
                            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_navigation_home_to_addWaterFragment));
                }
        );
        //обнуление ежедневных параметров
        Date d = new Date();
        if (Integer.parseInt(String.format("%te",d)) != count_day){
            foodStorageDao.del().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe();
            SharedPreferences.Editor today = sp.edit();
            today.putInt("today_c", current_calories);
            today.putInt("current_c", 0);
            today.putInt("water_c", 0);
            today.putFloat("proteins_current",0);
            today.putFloat("fats_current",0);
            today.putFloat("carbon_current",0);
            today.putInt("days", Integer.parseInt(String.format("%te",d)));
            today.putBoolean("new", true);
            today.apply();
            binding.appleView.setProgress(0);
            binding.bottleView.setProgress(0);
            binding.counts.setText("Осталось калорий: " + Integer.toString(calories_all));
        }

        return binding.getRoot();

    }
    //установка пустого списка продуктов

    //установка адаптера
    private void onLoaded(List<FoodStorage> foodStorages) {
        FoodAdapter adapter = new FoodAdapter((ArrayList<FoodStorage>) foodStorages);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerview.setAdapter(adapter);

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        foodStorage.dispose();

        binding = null;
    }




}
