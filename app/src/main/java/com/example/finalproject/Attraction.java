package com.example.finalproject;

import android.graphics.drawable.Drawable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "attractions")
public class Attraction implements Serializable  {
    @PrimaryKey(autoGenerate = true)
    int id;
    String attractionName;
    String cityName;
    String address;
    String description;
    String imageURL;
    float rating;
    String phone;
    String website;
    int price;

    public Attraction(String attractionName, String cityName, String address, String description, String imageURL, float rating, String phone, String website, int price) {
        this.attractionName = attractionName;
        this.cityName = cityName;
        this.address = address;
        this.description = description;
        this.imageURL = imageURL;
        this.rating = rating;
        this.phone = phone;
        this.website = website;
        this.price = price;
    }


}
