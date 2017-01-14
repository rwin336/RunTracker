package com.rwin336.runtracker;

import android.support.v4.app.Fragment;

public class RunActivity extends SingleFragmentActivity {

    private static final String TAG = "RunActivity";

    @Override
    protected Fragment createFragment() {
        return new RunFragment();
    }

}
