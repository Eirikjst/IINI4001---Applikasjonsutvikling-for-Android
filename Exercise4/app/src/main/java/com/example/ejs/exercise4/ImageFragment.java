package com.example.ejs.exercise4;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class ImageFragment extends Fragment {

    int index = 0;
    private String[] description;

    private ViewFlipper viewFlipper;
    private TextView desc_textview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.image_fragment, container, false);

        Resources res = getResources();
        viewFlipper = view.getRootView().findViewById(R.id.imageFlipper);
        desc_textview = view.getRootView().findViewById(R.id.imageDescription);
        description = res.getStringArray(R.array.description);

        desc_textview.setText(description[index]);

        return view;
    }

    public void updateImage(boolean action){
        if (action){
            index += 1;
            if (index > 3) index = 0;
            desc_textview.setText(description[index]);
            viewFlipper.showNext();
        } else {
            index -= 1;
            if (index < 0) index = 3;
            desc_textview.setText(description[index]);
            viewFlipper.showPrevious();
        }
    }
}
