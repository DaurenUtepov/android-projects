package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class AttractionsActivity extends AppCompatActivity {
    // database and dao variable
    MyDatabase db = null;
    AttractionDAO atrDAO = null;
    // Intent variable
    Intent a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attractions);

         a = new Intent(this, DetailsActivity.class);
        db = MyDatabase.getDatabase(getApplicationContext());
        atrDAO = db.attractionDAO();
        //insert user into db if table of attractions empty
        int atrQuantity = atrDAO.getQuantity();
        if(atrQuantity == 0){
            atrDAO.insert(new Attraction("The Eiffel Tower","Paris","Champ de Mars, 75007",
                    "The Eiffel Tower (la Tour Eiffel) ranks high on the list of places to visit in France and is the most-visited tourist attraction in the world",
                    "eiffel_tower",2, "+9 450 4563636","https://www.toureiffel.paris/en", 20));
            atrDAO.insert(new Attraction("The Louvre","Paris"," Rue de Rivoli, 75001",
                    "A sumptuous palace that was once the home of France's kings, the Louvre is the most important of Paris' top museums.",
                    "louvre",2, "+9 450 4563636","https://www.louvre.fr/en", 20));
            atrDAO.insert(new Attraction("The Cathédrale Notre-Dame de Paris","Paris","6 Parvis Notre-Dame - Place Jean-Paul II, 75004",
                    "Notre-Dame stands in the heart of Paris on the Île-de-la-Cité next to the Latin Quarter. An island in the Seine River, the Île-de-la-Cité is the historical and geographical center of Paris",
                    "notre_dame",2, "+9 450 4563636","https://www.notredamedeparis.fr", 20));
            atrDAO.insert(new Attraction("The Arc de Triomphe","Paris","Place Charles de Gaulle, 75008",
                    "The Arc de Triomphe is dedicated to the soldiers who fought in the French armies of the Revolution and the First Empire (Napoleonic Wars).",
                    "arc_triomphe",2, "+9 450 4563636","http://www.paris-arc-de-triomphe.fr", 20));
        }
        // set adapter
        ArrayList<Attraction> attractionsList = (ArrayList<Attraction>)atrDAO.getAllAttraction();
        AttractionAdapter adapter = new AttractionAdapter(this, attractionsList);
        ListView lv = findViewById(R.id.lv);
        lv.setAdapter(adapter);
        //attraction click listener and pass attraction object into detail screen
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapter.notifyDataSetChanged();
                Log.d("ABC", "row #" + i);
                Attraction currItem = attractionsList.get(i);
                a.putExtra("data", currItem);
                startActivity(a);
            }
        });

    }
    //logout button to navigate main activity and turn off auto login
    public void logOut (View view){
        a = new Intent(this, MainActivity.class);
        a.putExtra("myBoolean",true);
        startActivity(a);
    }
    //button to back home screen
    public void back (View view){
        a = new Intent(this, HomeActivity.class);
        startActivity(a);
    }
}