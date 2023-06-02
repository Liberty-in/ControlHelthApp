package com.prequelapp.lib.controlhelthapp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "food")
public class Food {
    private double countFat;
    private double countCarbohydrates;
    @NonNull
    @PrimaryKey
    private String foodName;


    private int countCalories;
    private double countProteins;


    public Food(double countFat, double countCarbohydrates, @NonNull String foodName, int countCalories, double countProteins) {
        this.countFat = countFat;
        this.countCarbohydrates = countCarbohydrates;
        this.foodName = foodName;
        this.countCalories = countCalories;
        this.countProteins = countProteins;
    }

    public String getFoodName() {
        return foodName;
    }

    public int getCountCalories() {
        return countCalories;
    }

    public double getCountCarbohydrates() {
        return countCarbohydrates;
    }

    public double getCountFat() {
        return countFat;
    }

    public double getCountProteins() {
        return countProteins;
    }

    @Override
    public String toString() {
        return "Food{" +
                ", foodName='" + foodName + '\'' +
                ", countCalories=" + countCalories +
                ", countCarbohydrates=" + countCarbohydrates +
                ", countFat=" + countFat +
                ", countProteins=" + countProteins +
                '}';
    }
}
