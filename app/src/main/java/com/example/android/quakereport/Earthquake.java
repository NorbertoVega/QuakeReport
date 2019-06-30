package com.example.android.quakereport;

public class Earthquake {

    private double mMagnitude;
    private String mLocation;
    private long mTimeInMilliseconds;
    private String mUrl;

    public Earthquake(double magnitude, String location, long timeInMilliseconds,String url){
        mLocation = location;
        mMagnitude = magnitude;
        mTimeInMilliseconds = timeInMilliseconds;
        mUrl = url;
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

    public String getUrl(){
        return mUrl;
    }

    @Override
    public String toString() {
        return "Magnitude: "+mMagnitude+"Location: "+mLocation+"Date: "+ mTimeInMilliseconds;
    }
}
