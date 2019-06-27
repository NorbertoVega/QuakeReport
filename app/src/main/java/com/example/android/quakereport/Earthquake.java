package com.example.android.quakereport;

public class Earthquake {

    private String mMagnitude;
    private String mLocation;
    private String mDate;

    public Earthquake(String magnitude, String location, String date){
        mLocation = location;
        mMagnitude = magnitude;
        mDate = date;
    }

    public String getLocation(){

        return mLocation;
    }

    public String getMagnitude(){

        return mMagnitude;
    }

    public String getDate(){

        return mDate;
    }

    @Override
    public String toString() {
        return "Magnitude: "+mMagnitude+"Location: "+mLocation+"Date: "+mDate;
    }
}
