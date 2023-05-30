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
    @Query("SELECT * FROM food_table WHERE id = :id")
    Observable<Food> getFoodById(int id);

    // Метод чтения всех задач из таблицы
    @Query("SELECT * FROM food_table")
    Observable<List<Food>> getAllFood();

    @Query("SELECT foodName FROM food_table")
    Observable<String[]> getFoodName();

    //@Query("SELECT countCalories FROM food_table WHERE foodname = :food_name")
    //Observable<Integer> getInfo(String food_name);
}
