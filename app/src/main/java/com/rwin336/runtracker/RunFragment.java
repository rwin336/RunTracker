package com.rwin336.runtracker;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;

/**
 * A placeholder fragment containing a simple view.
 */
public class RunFragment extends Fragment {
    private static final String TAG = "RunFragment";

    private RunManager mRunManager;

    private Button mStartButton, mStopButton;
    private TextView mStartedTextView, mLatitudeTextView;
    private TextView mLongitudeTextView, mAltitudeTextView, mDurationTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mRunManager = RunManager.get(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment, container, false);
        Log.d(TAG, "RunFragment:      ************************************** onCreateView");
        mStartedTextView = (TextView)view.findViewById(R.id.run_startedTextView);
        mLatitudeTextView = (TextView)view.findViewById(R.id.run_latitudeTextView);
        mLongitudeTextView = (TextView)view.findViewById(R.id.run_longitudeTextView);
        mAltitudeTextView = (TextView)view.findViewById(R.id.run_altitudeTextView);
        mDurationTextView = (TextView)view.findViewById(R.id.run_durationTextView);

        mStartButton = (Button)view.findViewById(R.id.run_startButton);
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "RunFragment:         ******************* Start Button clicked");
                mRunManager.startLocationUpdates();
                updateUI();
            }
        });

        mStopButton = (Button)view.findViewById(R.id.run_stopButton);
        mStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "RunFragment:      ******************************* Stop Button clicked");
                mRunManager.stopLocationUpdates();
                updateUI();
            }
        });

        updateUI();
        return view;
    }

    private void updateUI() {
        Log.d(TAG, "RunFragemnt:      ************************************************** updateUI");
        boolean started = mRunManager.isTrackingRun();
        mStartButton.setEnabled(!started);
        mStopButton.setEnabled(started);
    }
}
