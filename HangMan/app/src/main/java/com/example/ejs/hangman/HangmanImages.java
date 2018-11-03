package com.example.ejs.hangman;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ViewFlipper;

import org.w3c.dom.Text;

public class HangmanImages extends Fragment {

    private ViewFlipper viewFlipper;
    private TextView letters_guessed;

    HangmanImagesListener activityCommander;

    public interface HangmanImagesListener{
        public void updateVarsAfterGame();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            activityCommander = (HangmanImagesListener) activity;
        } catch (ClassCastException cce) {
            throw new ClassCastException(activity.toString());
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hangman_images, container, false);
        viewFlipper = view.getRootView().findViewById(R.id.hangmanViewFlipper);
        letters_guessed = view.getRootView().findViewById(R.id.letters_guessed);
        return view;
    }

    public void nextImage(String letter){
        viewFlipper.showNext();
        if (viewFlipper.getDisplayedChild() == 10) {
            String temp = letters_guessed.getText().toString();
            letters_guessed.setText(temp+" "+letter);
            activityCommander.updateVarsAfterGame();
        } else {
            String temp = letters_guessed.getText().toString();
            if (temp == ""){
                letters_guessed.setText(letter);
            }
            letters_guessed.setText(temp+" "+letter);
        }
    }

    public void newWord() {
        letters_guessed.setText("");
        viewFlipper.showNext();
    }
}
