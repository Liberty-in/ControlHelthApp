package com.prequelapp.lib.controlhelthapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

@Dao
public interface GlucoseDao {
    @Insert
    Completable addMeasure(Glucose glucose);

    @Query("SELECT * FROM glucose")
    Observable<List<Glucose>> getAllMeasures();
}
