package com.example.finalproject;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorites")
public class Favorite {
    @PrimaryKey(autoGenerate = true)
    int id;
    String username;
    String attractionName;

    public Favorite(String username, String attractionName) {
        this.username = username;
        this.attractionName = attractionName;
    }
}
