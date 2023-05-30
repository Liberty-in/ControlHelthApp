package com.prequelapp.lib.controlhelthapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.prequelapp.lib.controlhelthapp.databinding.FragmentHistoryBinding;
import com.prequelapp.lib.controlhelthapp.databinding.FragmentHomeBinding;

public class HistoryFragment extends Fragment {
    private FragmentHistoryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentHistoryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
