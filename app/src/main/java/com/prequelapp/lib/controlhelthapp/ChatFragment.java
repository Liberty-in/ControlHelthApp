package com.prequelapp.lib.controlhelthapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.prequelapp.lib.controlhelthapp.databinding.FragmentChatBinding;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ChatFragment extends Fragment {
    private FragmentChatBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentChatBinding.inflate(inflater, container, false);
        //перемещение на определенный фрагмент по нажатию
        binding.settinds.setOnClickListener(v-> Navigation.findNavController(binding.getRoot()).navigate(R.id.action_navigation_chat_to_settingsFragment));
        binding.fact.setOnClickListener(v->Navigation.findNavController(binding.getRoot()).navigate(R.id.action_navigation_chat_to_factsFragment));
        binding.nast.setOnClickListener(v -> Navigation.findNavController(binding.getRoot()).navigate(R.id.action_navigation_chat_to_smileFragment));
        binding.motiv.setOnClickListener(v -> Navigation.findNavController(binding.getRoot()).navigate(R.id.action_navigation_chat_to_motivationFragment));
        return binding.getRoot();
    }
}
