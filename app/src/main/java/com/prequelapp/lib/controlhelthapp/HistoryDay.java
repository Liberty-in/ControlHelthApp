package com.prequelapp.lib.controlhelthapp;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "history")
public class HistoryDay {

    @PrimaryKey(autoGenerate = true)
    int id;

    private int calories;

    private int day;

    public HistoryDay(int calories, int day) {
        this.calories = calories;
        this.day = day;
    }

    public int getCalories() {
        return calories;
    }

    public int getDay() {
        return day;
    }

    @Override
    public String toString() {
        return "HistoryDay{" +
                "id=" + id +
                ", calories=" + calories +
                ", day=" + day +
                '}';
    }
}
