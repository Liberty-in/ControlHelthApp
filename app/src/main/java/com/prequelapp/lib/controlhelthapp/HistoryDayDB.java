package com.prequelapp.lib.controlhelthapp;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = HistoryDay.class, version = 1)
public abstract class HistoryDayDB extends RoomDatabase {
    private static HistoryDayDB instances;

    public abstract HistoryDayDao historyDayDao();

    public static synchronized HistoryDayDB getInstance(Context context) {
        if (instances == null) {
            instances = Room.databaseBuilder(
                            context.getApplicationContext(),
                            HistoryDayDB.class, "history")
                    .build();
        }
        return instances;
    }
}
