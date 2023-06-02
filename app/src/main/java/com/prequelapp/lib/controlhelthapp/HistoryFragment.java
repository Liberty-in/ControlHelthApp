package com.prequelapp.lib.controlhelthapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.prequelapp.lib.controlhelthapp.databinding.FragmentHistoryBinding;
import com.prequelapp.lib.controlhelthapp.databinding.FragmentHomeBinding;

import java.util.Date;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HistoryFragment extends Fragment {
    private FragmentHistoryBinding binding;


    Disposable get_day;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        SharedPreferences history = getActivity().getSharedPreferences("c", Context.MODE_PRIVATE);
        HistoryDayDB historyDayDB = HistoryDayDB.getInstance(requireContext());
        HistoryDayDao historyDayDao = historyDayDB.historyDayDao();
        int current_day = history.getInt("days", 1);
        boolean new_d = history.getBoolean("new", false);
        //ежедневное обновление
        if (new_d){
            historyDayDao.addDay(new HistoryDay(history.getInt("today_c", 0),current_day))
                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();
            SharedPreferences.Editor day_ch = history.edit();
            day_ch.putBoolean("new", false);
            day_ch.putInt("days", current_day + 1);
            day_ch.apply();
        }

        get_day = historyDayDao.getAllDays().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onLoaded);
        binding = FragmentHistoryBinding.inflate(inflater, container, false);
        binding.ford.setOnClickListener(v->{
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_navigation_history_to_diabetesFragment);
        });
        int current_calories = history.getInt("current_c", 10);
        int calories_all = history.getInt("calories_form", 1200);
        if (calories_all > current_calories)
            binding.today.setText("Сегодня было съедено " + String.valueOf(current_calories) +" калорий,\n что на " +  String.valueOf((int)(100 - current_calories * 100 /calories_all)) + "% ниже нормы");
        else{
            binding.today.setText("Сегодня было съедено " + String.valueOf(current_calories) +" калорий,\n что на " +  String.valueOf((int)(current_calories * 100 /calories_all - 100)) + "% выше нормы");
        }


        return binding.getRoot();
    }
    //построение графика
    private void onLoaded(List<HistoryDay> historyDays) {
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                //данные добавлены для наглядности
                new DataPoint(1,1500),
                new DataPoint(2, 2000),
                new DataPoint(3, 1876),
                new DataPoint(4, 1754),
                new DataPoint(5, 2235),
                new DataPoint(6, 2465)
        });


        binding.graph.addSeries(series);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        get_day.dispose();
    }
}
