package com.example.finalproject;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
@Entity(tableName = "users")
public class User {
    @PrimaryKey(autoGenerate = true)
    int id;

    String username;
    String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    @Ignore
    public User() {
    }
    static String  shareName;
    public static String getShareName() {
        return shareName;
    }
    public static void setShareName(String shareName) {
        User.shareName = shareName;
    }




}
