package com.prequelapp.lib.controlhelthapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.prequelapp.lib.controlhelthapp.databinding.FragmentSmileBinding;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class SmileFragment extends Fragment {

    FragmentSmileBinding binding;
    String message;
    Disposable sm1 = new Disposable() {
        @Override
        public void dispose() {

        }

        @Override
        public boolean isDisposed() {
            return false;
        }
    };
    int number = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSmileBinding.inflate(inflater,container,false);
        ReactionDB reactionDB = ReactionDB.getInstance(requireContext());
        ReactionDao reactionDao = reactionDB.reactionDao();
        binding.face4.setOnClickListener(v->
        {message = "У тебя были трудные моменты, но они остались позади. Спустя какое-то время все эти неприятности останутся позади в прошлом размытым пятном.";
            setMessage(message);
            number = 4;
            addSmile(reactionDao);
        });
        binding.face5.setOnClickListener(v->
        {message = "Выше нос. Твоя грустная рожица не способствуют светлому будущему. У тебя все обязательно образуется, а я помогу тебе в этом.";
            setMessage(message);
            number = 5;
            addSmile(reactionDao);
        });
        binding.face3.setOnClickListener(v->
        {message = "Улыбнись!Если дела идут не так, как вам хочется — это не ваши дела, пусть проходят мимо.";
            setMessage(message);
            number = 3;
            addSmile(reactionDao);
        });
        binding.face2.setOnClickListener(v->
        {message = "Чтобы жить и радоваться, нужно всего две вещи: во-первых ― жить, во-вторых ― радоваться…";
            setMessage(message);
            number = 2;
            addSmile(reactionDao);
        });
        binding.face1.setOnClickListener(v->
        {message = "Добрые улыбки и теплые искренние слова — самое лучшее лекарство для здоровья и хорошего настроения, которое можно принимать без предписания врача и без побочных эффектов.";
            setMessage(message);
            number = 1;
            addSmile(reactionDao);
        });

        sm1 = reactionDao.getAllReaction()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onLoaded);



        return binding.getRoot();
    }

    private void setMessage(String x){
        binding.makesm.setText(x);
        binding.inf.setImageResource(R.drawable.cat1);
        binding.pods.setVisibility(View.VISIBLE);
    }
    //добавление настроения в базу данных и обновления списка
    void addSmile(ReactionDao reactionDao){
        Date x = new Date();
        String d = String.format("%1$te %1$tB %1$tR", x);
        reactionDao.addReaction(
                        new Reaction(
                                number, d))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
        sm1 = reactionDao.getAllReaction()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onLoaded);

    }
    //установка адаптера
    private void onLoaded(List<Reaction> reactions) {
        ReactionAdapter adapter = new ReactionAdapter((ArrayList<Reaction>) reactions);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerview.setAdapter(adapter);
    }
    public void onDestroyView() {
        super.onDestroyView();

        sm1.dispose();
        binding = null;
    }



}


