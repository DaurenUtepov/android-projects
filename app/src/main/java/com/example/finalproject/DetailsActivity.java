package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsActivity extends AppCompatActivity {
    // database and dao variable
    MyDatabase db = null;
    FavoriteDAO favoriteDAO= null;
    AttractionDAO attractionDAO = null;
    // text fields variables
    Attraction attraction;
    TextView phone;
    TextView website;
    TextView price;
    RatingBar ratingBar;
    //Intent variable
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Log.d("ABC", "user in detail screen  " + User.shareName );
        db = MyDatabase.getDatabase(getApplicationContext());

        i = getIntent();
        // get object with attraction from attraction list
        attraction = (Attraction) i.getSerializableExtra("data");

        TextView name = findViewById(R.id.d_tv_name);
        ImageView ivImage = findViewById(R.id.d_imageView);
        phone= findViewById(R.id.d_tv_phone);
        website = findViewById(R.id.d_tv_website);
        price = findViewById(R.id.d_tv_price);
        TextView des = findViewById(R.id.d_tv_des);
        ratingBar = (RatingBar) findViewById(R.id.d_ratingBar);

        name.setText(attraction.attractionName);
        int imageID = getResources().getIdentifier(attraction.imageURL, "drawable", getPackageName());
        ivImage.setImageDrawable(getResources().getDrawable(imageID));
        phone.setText("Phone: "+attraction.phone);
        website.setText("Website URL: "+attraction.website);
        price.setText("Price : "+attraction.price + " $");
        des.setText(attraction.description);
        ratingBar.setRating(attraction.rating);

        attractionDAO = db.attractionDAO();
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                float ratingNumber = ratingBar.getRating();
                attractionDAO.updateRating(attraction.attractionName, ratingNumber);
                Log.d("ABC", "star barr #" + ratingNumber);
            }
        });

    }
    // phone call method to make call
    public void phonePressed(View view) {
        Uri phoneNumber = Uri.parse("tel:" + attraction.phone);
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(phoneNumber);
        if (callIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(callIntent);
        }
    }
    // open website method to open in browser
    public void websitePressed(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(attraction.website));

        if (browserIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(browserIntent);
        }

    }
    // button to add attraction in favorite table db
    public void favoritePressed(View view) {
        favoriteDAO = db.favoriteDAO();
        favoriteDAO.insert(new Favorite(User.shareName, attraction.attractionName));
        Toast t = Toast.makeText(this, "Attraction is added to the favorite", Toast.LENGTH_SHORT);
        t.show();
    }
    //logout button to navigate main activity and turn off auto login
    public void logOut (View view){
        i = new Intent(this, MainActivity.class);
        i.putExtra("myBoolean",true);
        startActivity(i);
    }
    //button to back attractions list
    public void back (View view){
        i = new Intent(this, AttractionsActivity.class);
        startActivity(i);
    }

}