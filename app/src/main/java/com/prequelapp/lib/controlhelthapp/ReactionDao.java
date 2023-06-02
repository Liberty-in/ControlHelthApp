package com.prequelapp.lib.controlhelthapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

@Dao
public interface ReactionDao {
    @Insert
    Completable addReaction(Reaction reaction);

    @Query("SELECT * FROM reaction")
    Observable<List<Reaction>> getAllReaction();
}
