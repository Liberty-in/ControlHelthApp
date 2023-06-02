package com.prequelapp.lib.controlhelthapp;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

@Dao
public interface HistoryDayDao {

    @Insert
    Completable addDay(HistoryDay historyDay);

    @Query("SELECT * FROM history")
    Observable<List<HistoryDay>> getAllDays();
}
