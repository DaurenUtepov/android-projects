package com.example.finalproject;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
@Dao
public interface AttractionDAO {

        //  define functions for an insert and a get all operation
        @Insert(onConflict = OnConflictStrategy.IGNORE)
        public void insert(Attraction e);

        // query the database and return ALL employees
        @Query("SELECT * FROM attractions")
        public List<Attraction> getAllAttraction();
        // get quantity of attractions
        @Query("SELECT COUNT(id) FROM attractions")
        public int getQuantity();
        // Update quantity with particular id
        @Query("UPDATE attractions SET rating = :ratingNum WHERE attractionName = :name")
        public void updateRating(String name, float ratingNum);
}
