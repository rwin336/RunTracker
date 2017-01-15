package com.rwin336.runtracker;

import android.content.Context;
import android.location.Location;
import android.util.Log;


/**
 * Created by riwinters on 1/15/17.
 */

public class TrackingLocationReceiver extends LocationReceiver {

    private static final String TAG = "TackingLocationReceiver";

    @Override
    protected void onLocationReceived(Context context, Location loc) {
        Log.d(TAG, "Got location from " + loc.getProvider() + ": "
                + loc.getLatitude() + ", " + loc.getLongitude());
        RunManager.get(context).insertLocation(loc);
    }
}
