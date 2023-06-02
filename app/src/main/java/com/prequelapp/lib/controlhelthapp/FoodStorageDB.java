package com.prequelapp.lib.controlhelthapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {FoodStorage.class}, version = 1)
public abstract class FoodStorageDB extends RoomDatabase {

    private static FoodStorageDB instances;

    public abstract FoodStorageDao foodStorageDao();

    public static synchronized FoodStorageDB getInstance(Context context) {
        if (instances == null) {
            instances = Room.databaseBuilder(
                            context.getApplicationContext(),
                            FoodStorageDB.class, "foods_database")
                    .build();
        }
        return instances;
    }
}
