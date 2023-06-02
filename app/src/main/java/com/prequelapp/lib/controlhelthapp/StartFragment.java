package com.prequelapp.lib.controlhelthapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.prequelapp.lib.controlhelthapp.databinding.FragmentStartBinding;

import java.util.Objects;

public class StartFragment extends Fragment {
    private FragmentStartBinding binding;
    String sex ="";
    double act = 0;
    int c = 1200;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentStartBinding.inflate(inflater,container,false);
        SharedPreferences sp = requireActivity().getSharedPreferences("my_settings", Context.MODE_PRIVATE);
        SharedPreferences c1 = requireActivity().getSharedPreferences("c", Context.MODE_PRIVATE);

        binding.choiceSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.male: {
                        sex = "m";
                        break;
                    }
                    case R.id.female:{
                        sex = "f";
                        break;
                    }
                    default:
                        break;
                }
            }
        });
        binding.choiceActivity.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            //определение уровня активности
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.min: {
                        act = 1.2;
                        break;
                    }
                    case R.id.easy:{
                        act = 1.35;
                        break;
                    }
                    case R.id.mean:{
                        act = 1.55;
                        break;
                    }
                    case R.id.high:{
                        act = 1.75;
                        break;
                    }
                    case R.id.max:{
                        act = 1.95;
                        break;
                    }
                    default:
                        break;
                }
            }
        });
        binding.button.setOnClickListener(v ->{
            if (check_answer(String.valueOf(binding.name.getText()),
                    String.valueOf(binding.age.getText()),
                    String.valueOf(binding.height.getText()),
                    String.valueOf(binding.weight.getText())) && !Objects.equals(sex, "") && act != 0){
                c = count_c(String.valueOf(binding.age.getText()),
                        String.valueOf(binding.height.getText()),
                        String.valueOf(binding.weight.getText()), sex, act);
                SharedPreferences.Editor c_send = c1.edit();
                //преобразование данных и их последующая запись в SharedPreference
                float belki = 1.2f * Integer.parseInt(String.valueOf(binding.weight.getText()));
                float gir = 0.7f * Integer.parseInt(String.valueOf(binding.weight.getText()));
                float ygl = 1.8f * Integer.parseInt(String.valueOf(binding.weight.getText()));
                c_send.putInt("calories_form", c);
                c_send.putFloat("proteins_all",belki);
                c_send.putFloat("fats_all",gir);
                c_send.putFloat("carbon_all",ygl);
                c_send.putString("name",binding.name.getText().toString());
                c_send.putInt("weight", Integer.parseInt(String.valueOf(binding.weight.getText())));
                c_send.putInt("height", Integer.parseInt(String.valueOf(binding.height.getText())));
                c_send.apply();
                SharedPreferences.Editor e = sp.edit();
                e.putBoolean("hasVisited", true);
                e.apply();
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_startFragment_to_navigation_home2);
            }
        });


        return binding.getRoot();
    }
    //проверка корректности данных
    boolean check_answer(String name, String age, String height, String weight){
        if (name != null && age != null && height != null && weight != null) {
            try {
                int age1 = Integer.parseInt(age);
                int height1 = Integer.parseInt(height);
                double weight1 = Double.parseDouble(weight);
                if (age1 > 6 && age1 < 100 && 100 < height1  && height1 < 250 && weight1 > 30){
                    return true;
                }

            }
            catch (Exception e) {
                binding.correct.setVisibility(View.VISIBLE);
                return false;
            }
        }
        binding.correct.setVisibility(View.VISIBLE);
        return false;
    }
    //подсчет суточной нормы калорий
    int count_c(String age, String height, String weight, String s, Double kf){
        double count = Integer.parseInt(height) * 9.99 + 6.25 * Double.parseDouble(weight) - 4.92 * Integer.parseInt(age);
        if (s.equals("f"))
            count -=161;
        else
            count +=5;
        return (int) (count * kf);
    }
}