package com.example.android.quakereport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter(Context context, ArrayList<Earthquake> earthquakes){
        super(context,0,earthquakes);
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Earthquake currentEarthquake = getItem(position);

        TextView magnitudeText = listItemView.findViewById(R.id.magnitude_text_view);
        magnitudeText.setText(currentEarthquake.getMagnitude());

        TextView locationText = listItemView.findViewById(R.id.location_text_view);
        locationText.setText(currentEarthquake.getLocation());

        TextView dateText = listItemView.findViewById(R.id.date_text_view);
        dateText.setText(currentEarthquake.getDate());

        return listItemView;
    }
}
