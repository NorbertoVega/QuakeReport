package com.example.android.quakereport;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

        final Earthquake currentEarthquake = getItem(position);

        TextView magnitudeText = listItemView.findViewById(R.id.magnitude);
        DecimalFormat formatter = new DecimalFormat("0.0");
        String output = formatter.format(currentEarthquake.getMagnitude());
        magnitudeText.setText(output);

        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeText.getBackground();
        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());
        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        TextView locationText = listItemView.findViewById(R.id.primary_location);
        TextView nearbyLocation = listItemView.findViewById(R.id.location_offset);

        String spitted = currentEarthquake.getLocation();
        String [] splittedLocation = splitString(spitted);

        locationText.setText(splittedLocation[1]);
        nearbyLocation.setText(splittedLocation[0]);

        Date dateObject = new Date(currentEarthquake.getTimeInMilliseconds());

        TextView dateText = listItemView.findViewById(R.id.date);
        String formattedDate = formatDate(dateObject);
        dateText.setText(formattedDate);

        TextView timeView = listItemView.findViewById(R.id.time);
        String formattedTime = formatTime(dateObject);
        timeView.setText(formattedTime);

        listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(currentEarthquake.getUrl()));
                if (browserIntent.resolveActivity(getContext().getPackageManager()) != null) {
                    getContext().startActivity(browserIntent);
                }
            }
        });

        return listItemView;
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private String[] splitString(String s){

        String parts [] = new String[2];

        if(s.contains(" of ")){
            parts = s.split("(?<= of )");

        }else{
            parts[0] = "Near the";
            parts[1] = s;
        }
        //Log.d("EarthquakeAdapter: ","String 1: "+parts[0]+"\nString 2: "+parts[1]);
        return parts;

    }

    private int getMagnitudeColor(double magnitude){
        int mag = (int) Math.floor(magnitude);
        switch (mag){
            case 0:
            case 1:
                return ContextCompat.getColor(getContext(),R.color.magnitude1);
            case 2:
                return ContextCompat.getColor(getContext(),R.color.magnitude2);
            case 3:
                return ContextCompat.getColor(getContext(),R.color.magnitude3);
            case 4:
                return ContextCompat.getColor(getContext(),R.color.magnitude4);
            case 5:
                return ContextCompat.getColor(getContext(),R.color.magnitude5);
            case 6:
                return ContextCompat.getColor(getContext(),R.color.magnitude6);
            case 7:
                return ContextCompat.getColor(getContext(),R.color.magnitude7);
            case 8:
                return ContextCompat.getColor(getContext(),R.color.magnitude8);
            case 9:
                return ContextCompat.getColor(getContext(),R.color.magnitude9);
            default:
                return ContextCompat.getColor(getContext(),R.color.magnitude10plus);
        }
    }

}
