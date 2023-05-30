package com.prequelapp.lib.controlhelthapp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "food_table")
public class Food {
    @PrimaryKey(autoGenerate = true)
    int id;
    private String foodName;
    private float countProteins;
    private float countFat;
    private float countCarbohydrates;
    private int countCalories;


    public Food(String foodName, float countProteins, float countFat, float countCarbohydrates, int countCalories) {
        this.foodName = foodName;
        this.countProteins = countProteins;
        this.countFat = countFat;
        this.countCarbohydrates = countCarbohydrates;
        this.countCalories = countCalories;
    }

    public String getFoodName() {
        return foodName;
    }

    public int getCountCalories() {
        return countCalories;
    }

    public float getCountCarbohydrates() {
        return countCarbohydrates;
    }

    public float getCountFat() {
        return countFat;
    }

    public float getCountProteins() {
        return countProteins;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", foodName='" + foodName + '\'' +
                ", countCalories=" + countCalories +
                ", countCarbohydrates=" + countCarbohydrates +
                ", countFat=" + countFat +
                ", countProteins=" + countProteins +
                '}';
    }
}
