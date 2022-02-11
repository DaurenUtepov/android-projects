package com.example.finalproject;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;

public class AttractionAdapter extends ArrayAdapter<Attraction> {

    public AttractionAdapter(Context context, ArrayList<Attraction> attractions)
    {
        super(context, 0, attractions);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Attraction attraction = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_layout, parent, false);
        }

        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvAdress = convertView.findViewById(R.id.tvAdress);
        TextView tvCity = convertView.findViewById(R.id.tvCity);
        ImageView ivImage = convertView.findViewById(R.id.ivImage);

        tvName.setText(attraction.attractionName);
        tvAdress.setText(attraction.address);
        tvCity.setText(attraction.cityName);

        int imageID = convertView.getResources().getIdentifier(attraction.imageURL, "drawable", convertView.getContext().getPackageName());

        // update the image view with your image
        ivImage.setImageDrawable(convertView.getResources().getDrawable(imageID));



        return convertView;
    }

}
