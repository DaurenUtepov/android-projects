package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.state.State;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    // database and dao variable
    MyDatabase db = null;
    FavoriteDAO favoriteDAO= null;
    // Intent variable
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.d("ABC", "user in home screen " + User.shareName);
        db = MyDatabase.getDatabase(getApplicationContext());
        favoriteDAO = db.favoriteDAO();

        //get user favorite list
        ArrayList<String> favoriteList =(ArrayList<String>) favoriteDAO.getFavorite(User.shareName);
        Log.d("ABC", "favoriteList " + favoriteList);

       ConstraintLayout favoriteSection =  findViewById(R.id.tv_favorite);
       //if user don't have favorite list, favorite section by default hidden
       if(favoriteList.size() == 0){
           favoriteSection.setVisibility(View.GONE);
       }else {
           favoriteSection.setVisibility(View.VISIBLE);
           ArrayAdapter<String> adapter =  new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, favoriteList);
           ListView lv = findViewById(R.id.lv_favorite);
           lv.setAdapter(adapter);
       }
    }
    // button to navigate attractions list
    public void to_attractions(View view) {
        i = new Intent(this, AttractionsActivity.class);
        startActivity(i);
    }
    //button to clear all user favorites
    public void clearFavorite (View view){
        favoriteDAO.deleteFavorites(User.shareName);
        recreate();
    }
    //logout button to navigate main activity and turn off auto login
    public void logOut (View view){
        i = new Intent(this, MainActivity.class);
        i.putExtra("myBoolean",true);
        startActivity(i);
    }
}