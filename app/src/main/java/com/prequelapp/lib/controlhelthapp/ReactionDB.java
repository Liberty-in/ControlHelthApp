package com.prequelapp.lib.controlhelthapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Reaction.class}, version = 1)
public abstract class ReactionDB extends RoomDatabase {

    private static ReactionDB instances;

    public abstract ReactionDao reactionDao();

    public static synchronized ReactionDB getInstance(Context context) {
        if (instances == null) {
            instances = Room.databaseBuilder(
                            context.getApplicationContext(),
                            ReactionDB.class, "reaction")
                    .build();
        }
        return instances;
    }
}

