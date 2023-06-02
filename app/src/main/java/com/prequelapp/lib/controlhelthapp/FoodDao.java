package com.prequelapp.lib.controlhelthapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

@Dao
public interface FoodDao {
    @Insert
    Completable addFood(Food food);

    // Метод чтения необходимой задачи из таблицы по id
    @Query("SELECT * FROM food WHERE foodName = :id")
    Observable<Food> getFoodById(String id);

    // Метод чтения всех задач из таблицы
    @Query("SELECT * FROM food")
    Observable<List<Food>> getAllFood();

    @Query("SELECT foodName FROM food")
    Observable<String[]> getFoodName();

    @Query("SELECT countCalories FROM food WHERE foodName = :food_name")
    Observable<Integer> getInfo(String food_name);
}
