package com.prequelapp.lib.controlhelthapp;

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
    Bundle bundle = new Bundle();
    String sex ="";
    double act = 0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentStartBinding.inflate(inflater,container,false);
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
                /*bundle.putString("name", String.valueOf(binding.name.getText()));
                bundle.putInt("height", Integer.parseInt(String.valueOf(binding.height.getText())));
                bundle.putInt("age", Integer.parseInt(String.valueOf(binding.age.getText())));
                bundle.putDouble("weight", Double.parseDouble(String.valueOf(binding.weight.getText())));
                bundle.putString("sex", sex);
                bundle.putDouble("k", act);
                setArguments(bundle);*/
                int c = count_c(String.valueOf(binding.age.getText()),
                        String.valueOf(binding.height.getText()),
                        String.valueOf(binding.weight.getText()), sex, act);
                bundle.putString("count", String.valueOf(c));
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_startFragment_to_navigation_home2, bundle);
            }
        });


        return binding.getRoot();
    }
    boolean check_answer(String name, String age, String height, String weight){
        if (name != null && age != null && height != null && weight != null) {
            try {
                int age1 = Integer.parseInt(age);
                int height1 = Integer.parseInt(height);
                double weight1 = Double.parseDouble(weight);
                if (age1 < 100 && 100 < height1  && height1 < 250 && weight1 > 30){
                    return true;
                }

            }
            catch (Exception e) {
                return false;
            }
        }
        return false;
    }
    int count_c(String age, String height, String weight, String s, Double kf){
        double count = Integer.parseInt(height) * 9.99 + 6.25 * Double.parseDouble(weight) - 4.92 * Integer.parseInt(age);
        if (s.equals("f"))
            count -=161;
        else
            count +=5;
        return (int) (count * kf);
    }
}
