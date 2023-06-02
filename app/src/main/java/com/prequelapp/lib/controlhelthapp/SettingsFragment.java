package com.prequelapp.lib.controlhelthapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.prequelapp.lib.controlhelthapp.databinding.FragmentSettingsBinding;

public class SettingsFragment extends Fragment {
    FragmentSettingsBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(inflater,container,false);
        SharedPreferences setting = requireActivity().getSharedPreferences("c", Context.MODE_PRIVATE);
        //установление текущих значений в виде подсказок
        binding.newN.setHint(setting.getString("name","Username"));
        binding.newH.setHint(String.valueOf(setting.getInt("height",170)));
        binding.newW.setHint(String.valueOf(setting.getInt("weight",60)));
        binding.newCal.setHint(String.valueOf(setting.getInt("calories_form",1500)));
        binding.newB.setHint(String.format("%.1f",setting.getFloat("proteins_all",60)));
        binding.newY.setHint(String.format("%.1f",setting.getFloat("fats_all",60)));
        binding.newG.setHint(String.format("%.1f",setting.getFloat("carbon_all",120)));
        //обработка изменений
        binding.end.setOnClickListener(v->{
            SharedPreferences.Editor x = setting.edit();
            boolean correct = true;
            if (checkChange(binding.newCal))
                try{
                    x.putInt("calories_form", Integer.parseInt(String.valueOf(binding.newCal.getText())));}
            catch (Exception e){
                    correct = false;
                    binding.newCal.setError("Некорректное значение", Drawable.createFromPath("@drawable/baseline_error_24"));
            }
            if (checkChange(binding.newW))
                try{
                    x.putInt("weight", Integer.parseInt(String.valueOf(binding.newW.getText())));}
                catch (Exception e){
                    correct = false;
                    binding.newW.setError("Некорректное значение", Drawable.createFromPath("@drawable/baseline_error_24"));
                }
            if (checkChange(binding.newH))
                try{
                    x.putInt("height", Integer.parseInt(String.valueOf(binding.newH.getText())));}
                catch (Exception e){
                    correct = false;
                    binding.newH.setError("Некорректное значение", Drawable.createFromPath("@drawable/baseline_error_24"));
                }
            if (checkChange(binding.newN))
                try{
                    x.putString("name", String.valueOf(binding.newN.getText()));}
                catch (Exception e){
                    correct = false;
                    binding.newN.setError("Некорректное значение", Drawable.createFromPath("@drawable/baseline_error_24"));
                }
            if (checkChange(binding.newB))
                try{
                    x.putFloat("proteins_all", Float.parseFloat(String.valueOf(binding.newB.getText())));}
                catch (Exception e){
                    correct = false;
                    binding.newB.setError("Некорректное значение", Drawable.createFromPath("@drawable/baseline_error_24"));
                }
            if (checkChange(binding.newY))
                try{
                    x.putFloat("fats_all", Float.parseFloat(String.valueOf(binding.newY.getText())));}
                catch (Exception e){
                    correct = false;
                    binding.newY.setError("Некорректное значение", Drawable.createFromPath("@drawable/baseline_error_24"));
                }
            if (checkChange(binding.newG))
                try{
                    x.putInt("carbon_all", Integer.parseInt(String.valueOf(binding.newG.getText())));}
                catch (Exception e){
                    correct = false;
                    binding.newG.setError("Некорректное значение", Drawable.createFromPath("@drawable/baseline_error_24"));
                }
            x.apply();
            if (correct) Navigation.findNavController(binding.getRoot()).navigate(R.id.action_settingsFragment_to_navigation_chat);
        });
        return binding.getRoot();

    }
    //функция проверяет, изменились ли данные
    boolean checkChange(EditText editText){
        return !String.valueOf(editText.getText()).equals("");
    }
}
