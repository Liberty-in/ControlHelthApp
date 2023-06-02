package com.prequelapp.lib.controlhelthapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.material.slider.Slider;
import com.prequelapp.lib.controlhelthapp.databinding.FragmentAddMeasureBinding;

import java.util.Date;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddMeasureFragment extends Fragment {
    FragmentAddMeasureBinding binding;
    Disposable measures = new Disposable() {
        @Override
        public void dispose() {

        }

        @Override
        public boolean isDisposed() {
            return false;
        }
    };
    double count = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAddMeasureBinding.inflate(inflater,container,false);

        Date x = new Date();
        GlucoseDB glucoseDB= GlucoseDB.getInstance(requireContext());
        GlucoseDao glucoseDao = glucoseDB.glucoseDao();
        binding.date.setText(String.format("Текущая дата и время:\n    %tR",x));
        //изменения состояния текста при измерении уровня глюкозы
        binding.countGl.setOnTouchListener((v, event) -> {
            count = binding.countGl.getValue();
            upgradeStatus(count);
            return false;
        });
        //добавление измерения в базу данных
        binding.button.setOnClickListener(n->{
           measures = glucoseDao.addMeasure(
                            new Glucose(
                                    count, String.format("%tR",x)))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe();
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_addMeasureFragment_to_diabetesFragment);

        });



        return binding.getRoot();
    }

    void upgradeStatus(double x){
        String status = "Норма";
        String desc = "У вас нормальный уровень сахара";
        if (x < 3.5){
            status = "Гипогликемия";
            desc = "У вас пониженный уровень сахара";
            binding.status.setTextColor(Color.parseColor("#2DA9DA"));
        }
        else if (x > 8.3){
            status = "Гипергликемия";
            desc = "У вас повышенный уровень сахара";
            binding.status.setTextColor(Color.parseColor("#F44336"));
        }
        else
            binding.status.setTextColor(Color.parseColor("#79DC7A"));
        binding.status.setText(status);
        binding.statusDesc.setText(desc);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        measures.dispose();
        binding = null;
    }
}
