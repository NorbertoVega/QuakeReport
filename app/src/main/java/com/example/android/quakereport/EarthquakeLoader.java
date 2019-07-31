package com.example.android.quakereport;


import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    private String mUrl;

    public EarthquakeLoader(Context context, String url){
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
        //Log.i("EarthquakeLoader","Loader forzando inicio");
    }

    @Override
    public List<Earthquake> loadInBackground() {
        if(mUrl == null)
            return null;

        List<Earthquake> earthquakes = QueryUtils.fetchEarthquakeData(mUrl);
        //Log.i("EarthquakeLoader","Loader datos fetched");
        return earthquakes;
    }
}
