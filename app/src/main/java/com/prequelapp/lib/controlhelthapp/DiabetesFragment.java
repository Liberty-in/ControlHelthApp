package com.prequelapp.lib.controlhelthapp;

import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jjoe64.graphview.series.BaseSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.Series;
import com.prequelapp.lib.controlhelthapp.databinding.FragmentDiabetesBinding;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DiabetesFragment extends Fragment {

    private FragmentDiabetesBinding binding;
    Disposable dateGlucose;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDiabetesBinding.inflate(inflater, container, false);

        GlucoseDB glucoseDB = GlucoseDB.getInstance(requireContext());
        GlucoseDao glucoseDao = glucoseDB.glucoseDao();
        //получение всех измерений из базы данных
        dateGlucose = glucoseDao
                .getAllMeasures()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onLoaded);

        binding.addMeas.setOnClickListener(v -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_diabetesFragment_to_addMeasureFragment);
        });
        return binding.getRoot();
    }
    //установка адаптера и построение графика
    private void onLoaded(List<Glucose> glucoses) {
        GlucoseAdapter adapter = new GlucoseAdapter((ArrayList<Glucose>) glucoses);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerview.setAdapter(adapter);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
        });
        for (int i = 0; i < glucoses.size(); i++){
            series.appendData(new DataPoint(i,glucoses.get(i).getCount()), true, 100);
        }
        binding.graph1.addSeries(series);




    }
    public void onDestroyView() {
        super.onDestroyView();
        dateGlucose.dispose();
        binding = null;
    }
}
