package com.rwin336.runtracker;

/**
 * Created by rwin on 1/2/17.
 */

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.LocationManager;
import android.util.Log;

public class RunManager {
    private static final String TAG = "RunManager";

    public static final String ACTION_LOCATION =
            "com.rwin336.runtracker.ACTION_LOCATION";

    private static RunManager sRunManager;
    private Context mAppContext;
    private LocationManager mLocationManager;

    // The private constructor forces users to use RunManager.get(context)
    private RunManager(Context appContext) {
        mAppContext = appContext;
        mLocationManager =
                (LocationManager)mAppContext.
                        getSystemService(Context.LOCATION_SERVICE);

    }

    public static RunManager get(Context c) {
        if (sRunManager == null) {
            // Use the application context to avoid leaking activitives
            sRunManager = new RunManager(c.getApplicationContext());
        }
        return sRunManager;
    }

    private PendingIntent getLocationPendingIntent(boolean shouldCreate) {
        Log.d(TAG, "getLocationPendingIntent");
        Intent broadcast = new Intent(ACTION_LOCATION);
        int flags = shouldCreate ? 0 : PendingIntent.FLAG_NO_CREATE;
        return PendingIntent.getBroadcast(mAppContext, 0, broadcast, flags);
    }

    public void startLocationUpdates() {
        Log.d(TAG, "Starting location Updates");
        String provider = LocationManager.GPS_PROVIDER;

        // Start updates from the location manager
        PendingIntent pi = getLocationPendingIntent(true);
        try {
            mLocationManager.requestLocationUpdates(provider, 0, 0, pi);
        } catch (SecurityException se) {
            // TODO: Log the exception
        }
    }

    public void stopLocationUpdates() {
        Log.d(TAG, "Stoping location Updates");
        PendingIntent pi = getLocationPendingIntent(false);
        if (pi != null) {
            mLocationManager.removeUpdates(pi);
            pi.cancel();
        }
    }

    public boolean isTrackingRun() {
        PendingIntent pi = getLocationPendingIntent(false);
        return pi != null;
    }

}
