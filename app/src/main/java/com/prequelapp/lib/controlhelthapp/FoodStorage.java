package com.prequelapp.lib.controlhelthapp;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "storage_daily")
public class FoodStorage {

    @PrimaryKey(autoGenerate = true)
    int id;
    private String foodName;
    private int countCalories;
    private int count;

    public FoodStorage(String foodName, int countCalories, int count) {
        this.foodName = foodName;
        this.countCalories = countCalories;
        this.count = count;
    }

    public String getFoodName() {
        return foodName;
    }

    public int getCountCalories() {
        return countCalories;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "FoodStorage{" +
                "id=" + id +
                ", foodName='" + foodName + '\'' +
                ", countCalories=" + countCalories +
                ", count=" + count +
                '}';
    }
}
