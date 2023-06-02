package com.prequelapp.lib.controlhelthapp;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

@Dao
public interface FoodStorageDao {

    @Insert
    Completable addFoods(FoodStorage foodStorage);

    @Query("SELECT * FROM storage_daily WHERE id = :id")
    Observable<FoodStorage> getFoodsById(int id);

    @Query("SELECT * FROM storage_daily")
    Observable<List<FoodStorage>> getAllFoods();

    @Query("DELETE FROM storage_daily")
    Completable del();
}
