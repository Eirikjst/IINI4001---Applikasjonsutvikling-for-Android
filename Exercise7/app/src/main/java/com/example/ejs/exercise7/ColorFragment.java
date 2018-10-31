package com.example.ejs.exercise7;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class ColorFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
