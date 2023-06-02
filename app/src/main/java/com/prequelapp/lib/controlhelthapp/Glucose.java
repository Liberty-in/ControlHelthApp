package com.prequelapp.lib.controlhelthapp;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "glucose")
public class Glucose {

    @PrimaryKey(autoGenerate = true)
    int id;
    private double count;
    private String time_of_measure;

    public Glucose(double count, String time_of_measure) {
        this.count = count;
        this.time_of_measure = time_of_measure;
    }

    public double getCount() {
        return count;
    }

    public String getTime_of_measure() {
        return time_of_measure;
    }

    @Override
    public String toString() {
        return "Glucose{" +
                "id=" + id +
                ", count=" + count +
                ", time_of_measure='" + time_of_measure + '\'' +
                '}';
    }
}
