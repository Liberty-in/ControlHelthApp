package com.prequelapp.lib.controlhelthapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Food.class}, version = 1)
public abstract class FoodDB extends RoomDatabase {

    // Хранимый синглтоном единственный экземпляр
    private static FoodDB instance;

    // Ссылка на DAO
    public abstract FoodDao foodDao();

    // Метод создания и получения экземляра синглтона
    public static synchronized FoodDB getInstance(Context context) {
        if (instance == null) {
            // Инициализация базы данных
            instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            FoodDB.class, "food_database").createFromAsset("food.db")
                    .build();
        }
        return instance;
    }

}
