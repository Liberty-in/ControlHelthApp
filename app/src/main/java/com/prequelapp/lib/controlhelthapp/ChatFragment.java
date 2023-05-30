package com.prequelapp.lib.controlhelthapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.prequelapp.lib.controlhelthapp.databinding.FragmentChatBinding;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ChatFragment extends Fragment {
    private FragmentChatBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentChatBinding.inflate(inflater, container, false);
        AtomicInteger choice = new AtomicInteger();
        binding.advices.setOnClickListener(v -> {
            addText("Хочу получить совет");
            choice.set(1);
        });
        binding.facts.setOnClickListener(v -> {
            addText("Хочу узнать факт");
            choice.set(2);
        });
        binding.support.setOnClickListener(v -> {
            addText("Хочу поддержку");
            choice.set(3);
        });
        binding.buttonSend.setOnClickListener(v ->{
            setChat(choice);
        });
        return binding.getRoot();
    }

    public void addText(String str){
        binding.textSend.setText(str);
        binding.textSend.setTextColor(Color.parseColor("#000000"));
    }
    public void setBotText(String str){
        binding.botChat.setText(str);
        binding.botChat.setVisibility(View.VISIBLE);
    }
    public void setChat(AtomicInteger ch){
        binding.userChat.setText(binding.textSend.getText());
        binding.userChat.setVisibility(View.VISIBLE);
        String s = RandomText(ch.intValue());
        setBotText(s);
        addText("Введите сообщение...");
        binding.textSend.setTextColor(Color.parseColor("#AAAAAA"));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public String RandomText(int i){
        String s = "";
        String advice[] = {"Не забывай пить воду", "Ешь больше фруктов", "Не забывай контролировать количество мучного в своем рационе"};
        String fact[] = {"В сельдерре содержится 0 калорий", "Желудок может увеличиваться и уменьшаться", "Мозг питается глюкозой"};
        String sup[] = {"Никогда не сдавайся", "На пути всегда будкт преграды, главное - преодолеть их", "Результат стоит всех усилий"};
        switch (i){
            case 1:{
                s = advice[(int)(Math.random() *1000) % 3];
                break;
            }
            case 2:{
                s = fact[(int)(Math.random() *1000) % 3];
                break;
            }
            case 3:{
                s = sup[(int)(Math.random() *1000) % 3];
                break;
            }
        }
        return s;
    }
}
