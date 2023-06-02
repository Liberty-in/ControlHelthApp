package com.prequelapp.lib.controlhelthapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Glucose.class}, version = 1)
public abstract class GlucoseDB extends RoomDatabase {

    private static GlucoseDB instances;

    public abstract GlucoseDao glucoseDao();

    public static synchronized GlucoseDB getInstance(Context context) {
        if (instances == null) {
            instances = Room.databaseBuilder(
                            context.getApplicationContext(),
                            GlucoseDB.class, "glucose")
                    .build();
        }
        return instances;
    }
}
