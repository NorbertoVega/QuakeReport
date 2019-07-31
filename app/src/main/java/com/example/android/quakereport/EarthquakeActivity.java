/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Earthquake>> {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    public static final String USGS_REQUEST_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=3&limit=25";
    private static final int EARTHQUAKE_LOADER_ID = 1;
    private EarthquakeAdapter mEarthquakeAdapter;
    private TextView emptyStateTextView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);
        ListView earthquakeListView = findViewById(R.id.list);
        mEarthquakeAdapter = new EarthquakeAdapter(this, new ArrayList<Earthquake>());
        earthquakeListView.setAdapter(mEarthquakeAdapter);

        emptyStateTextView = findViewById(R.id.empty_view);
        earthquakeListView.setEmptyView(emptyStateTextView);

        ConnectivityManager cm =
                (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if(isConnected) {
            getLoaderManager().initLoader(EARTHQUAKE_LOADER_ID, null, this);
            //Log.i(LOG_TAG,"Loader inicializado");
        }else{
            mProgressBar = findViewById(R.id.progress_bar);
            mProgressBar.setVisibility(View.GONE);
            emptyStateTextView.setText(R.string.no_internet);
        }

    }

    @Override
    public Loader<List<Earthquake>> onCreateLoader(int id, Bundle args) {
        //Log.i(LOG_TAG,"Loader creado");
        return new EarthquakeLoader(this, USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Earthquake>> loader, List<Earthquake> data) {
        mProgressBar = findViewById(R.id.progress_bar);
        mProgressBar.setVisibility(View.GONE);

        emptyStateTextView.setText(R.string.no_earthquakes);

        mEarthquakeAdapter.clear();
        if(data != null && !data.isEmpty()){
            mEarthquakeAdapter.addAll(data);
        }
        //Log.i(LOG_TAG,"Loader cargado");


    }

    @Override
    public void onLoaderReset(Loader<List<Earthquake>> loader) {
        mEarthquakeAdapter.clear();
        //Log.i(LOG_TAG,"Loader reseteado");
    }

}
