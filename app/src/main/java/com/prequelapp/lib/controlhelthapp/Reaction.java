package com.prequelapp.lib.controlhelthapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "reaction")
public class Reaction {

    @PrimaryKey(autoGenerate = true)
    int id;
    private int count;
    private String time;

    public Reaction(int count, String time) {
        this.count = count;
        this.time = time;
    }

    public int getCount() {
        return count;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Reaction{" +
                "id=" + id +
                ", count=" + count +
                ", time='" + time + '\'' +
                '}';
    }
}
