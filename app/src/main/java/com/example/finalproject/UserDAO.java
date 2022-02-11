package com.example.finalproject;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDAO {
    //  define functions for an insert and a get all operation
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insert(User e);

    // query the database and return ALL employees
    @Query("SELECT * FROM attractions")
    public List<User> getAllUser();
    //get user
    @Query("SELECT * FROM users WHERE username = :name")
    public User getUser(String name);

    // get quantity of users
    @Query("SELECT COUNT(id) FROM users")
    public int getQuantity();

    // to clear database
    @Query("DELETE FROM users")
    public int deleteAllUsers();

}
