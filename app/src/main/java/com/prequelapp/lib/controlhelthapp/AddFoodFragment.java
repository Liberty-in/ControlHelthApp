package com.prequelapp.lib.controlhelthapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.prequelapp.lib.controlhelthapp.databinding.ActivityMainBinding;
import com.prequelapp.lib.controlhelthapp.databinding.FragmentAddFoodBinding;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddFoodFragment extends Fragment {
    private FragmentAddFoodBinding binding;
    Disposable listFood;

    Disposable currentc = new Disposable() {
        @Override
        public void dispose() {

        }

        @Override
        public boolean isDisposed() {
            return false;
        }
    };
    Integer current_c = 10;
    float bk = 0, gr = 0, yg = 0;
    Float count_product = 100.0f;
    Food current_food = new Food(0,0,"0",0,0);

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        SharedPreferences s = requireActivity().getSharedPreferences("c", Context.MODE_PRIVATE);
        binding = FragmentAddFoodBinding.inflate(inflater, container, false);
        FoodDB foodDB = FoodDB.getInstance(requireContext());
        FoodDao foodDao = foodDB.foodDao();
        //получение данных
        current_c = s.getInt("cc",0);
        bk = s.getFloat("proteins_current",0);
        gr = s.getFloat("fats_current",0);
        yg = s.getFloat("carbon_current",0);

        FoodStorageDB foodStorageDB = FoodStorageDB.getInstance(requireContext());
        FoodStorageDao foodStorageDao = foodStorageDB.foodStorageDao();
        // получение данных из базы данных
        listFood =  foodDao.getFoodName().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::getList);
        //перерасчет количества в граммах
        binding.ok.setOnClickListener(n->{
            count_product = Float.parseFloat(String.valueOf(binding.countf.getText()));
            setArg(current_food);
        });
        binding.autocomplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentc = foodDao.getFoodById(String.valueOf(binding.autocomplete.getText())).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread()).subscribe(this::setArg1);
            }
            private void setArg1(Food food) {
                setArg(food);
            }
        });

        //изменение локальных данных о текущем потреблении
        binding.button.setOnClickListener(v ->{
            SharedPreferences n = requireActivity().getSharedPreferences("c", Context.MODE_PRIVATE);
            SharedPreferences.Editor b = n.edit();
            b.putInt("cc",current_c);
            b.putInt("current_c", current_c + n.getInt("current_c", 0));
            b.putFloat("proteins_current", bk + n.getFloat("proteins_current",0));
            b.putFloat("fats_current", gr + n.getFloat("fats_current",0));
            b.putFloat("carbon_current", yg + n.getFloat("carbon_current",0));
            b.apply();
            //добавление в ежедневную базу данных
            foodStorageDao.addFoods(
                            new FoodStorage(
                                    String.valueOf(binding.autocomplete.getText()),
                                    s.getInt("cc",100),
                                    Integer.parseInt(String.valueOf(binding.countf.getText()))
                            )
                    )
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe();

            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_nav_addFoodFragment_to_navigation_home);

        });
        return binding.getRoot();
    }
    //приведение данных в нужный формат и установка значений на ProgressBar
    private void setArg(Food food) {
        //if (binding.countf.getText() != null){
            //
            current_food = food;
            current_c = (int)update_c(food.getCountCalories(), count_product);
            double summary = food.getCountProteins() + food.getCountFat() + food.getCountCarbohydrates();
            binding.percentB.setText((int)(food.getCountProteins() * 100 / summary) + "%");
            binding.percentG.setText((int)(food.getCountFat() * 100 / summary) + "%");
            binding.percentY.setText((int)(food.getCountCarbohydrates() * 100 / summary) + "%");
            binding.progressB.setProgress((int)(food.getCountProteins() * 100 / summary));
            binding.progressG.setProgress((int)(food.getCountFat() * 100 / summary));
            binding.progressY.setProgress((int)(food.getCountCarbohydrates() * 100 / summary));
            binding.test.setText(current_c + " калорий");
            binding.b.setText("Белки: " + update_cl(food.getCountProteins(), count_product));
            binding.g.setText("Жиры: "  + update_cl(food.getCountFat(), count_product));
            binding.y.setText("Углеводы: " + update_cl(food.getCountCarbohydrates(), count_product));
            bk = (float)(food.getCountProteins()/100 * count_product);
            gr = (float)(food.getCountFat()/100 * count_product);
            yg = (float)(food.getCountCarbohydrates()/100 * count_product);


    }

    private float update_c(Integer x, Float count){
        return count/100 * x;
    }
    private String update_cl(Double x, Float count){
        String r = String.format("%.2f", count/100 * x);
        return r;
    }
    //установка адаптера
    private void getList(String[] strings) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, strings);
        binding.autocomplete.setAdapter(adapter);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        currentc.dispose();
        listFood.dispose();


        binding = null;
    }
}
