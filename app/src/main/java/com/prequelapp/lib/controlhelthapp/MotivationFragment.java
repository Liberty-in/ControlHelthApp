package com.prequelapp.lib.controlhelthapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.prequelapp.lib.controlhelthapp.databinding.FragmentMotivationBinding;

import java.util.ArrayList;

public class MotivationFragment extends Fragment {

    FragmentMotivationBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMotivationBinding.inflate(inflater,container,false);

        binding.next.setOnClickListener(v->updatePhrases());
        return binding.getRoot();
    }

    void updatePhrases(){
        String[] x = {
                "Ничем в мире нельзя заменить упорство. Его не может заменить талант — никого не встретишь так часто, как талантливого неудачника. Его не может заменить гениальность — непризнанные гении почти вошли в пословицу. Одного образования тоже недостаточно — мир полон образованных изгоев. Только упорство и решимость всесильны. Фраза „работай дальше“ была и остается решением всех проблем человеческой расы",
                "Сила происходит не от побед. Силу порождает борьба. Когда вы проходите через трудности и решаете не сдаваться — это и есть сила",
                "Чтобы дойти до цели, человеку нужно только одно. Идти",
                "Столкнувшись с трудностями, нельзя сдаваться, бежать. Вы должны оценивать ситуацию, искать решения и верить в то, что все делается к лучшему. Терпение – вот ключ к победе"
        };
        binding.motivText.setText(x[(int)(Math.random() * 100)%x.length]);
    }

}
