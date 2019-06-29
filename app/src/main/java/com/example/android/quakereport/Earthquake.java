package com.example.android.quakereport;

public class Earthquake {

    private double mMagnitude;
    private String mLocation;
    private long mTimeInMilliseconds;

    public Earthquake(double magnitude, String location, long timeInMilliseconds){
        mLocation = location;
        mMagnitude = magnitude;
        mTimeInMilliseconds = timeInMilliseconds;
    }

    public String getLocation(){

        return mLocation;
    }

    public double getMagnitude(){

        return mMagnitude;
    }

    public long getTimeInMilliseconds(){

        return mTimeInMilliseconds;
    }

    @Override
    public String toString() {
        return "Magnitude: "+mMagnitude+"Location: "+mLocation+"Date: "+ mTimeInMilliseconds;
    }
}
