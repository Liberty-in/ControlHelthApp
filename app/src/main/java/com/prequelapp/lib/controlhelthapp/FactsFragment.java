package com.prequelapp.lib.controlhelthapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.prequelapp.lib.controlhelthapp.databinding.FragmentFactsBinding;

public class FactsFragment extends Fragment {
    FragmentFactsBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentFactsBinding.inflate(inflater,container,false);
        binding.next.setOnClickListener(v->updatePhrases());
        return binding.getRoot();
    }
    void updatePhrases(){
        String[] x = {
        "Сыр признан самой желанной едой для воров во всем мире. Его крадут из магазинов чаще всего.",
                "ДНК человека и банана совпадают на 60%.",
                "Вода не может просрочиться. Тогда почему у бутилированной воды есть срок годности? Это срок годности пластиковой бутылки, но не воды."
        };
        binding.factText.setText(x[(int)(Math.random() * 100)%x.length]);
    }
}
