package com.example.finalproject;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface FavoriteDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insert(Favorite e);

    // query the database and return ALL employees
    @Query("SELECT * FROM favorites")
    public List<Favorite> getAllFavorite();

    @Query("SELECT attractionName FROM favorites WHERE username = :name")
    public List<String> getFavorite(String name);

    @Query("DELETE FROM favorites WHERE username = :name")
    public void deleteFavorites(String name);
}
