package com.example.ejs.hangman;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ViewFlipper;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HangmanImages extends Fragment {

    HangmanImagesListener activityCommander;

    private List<String> wordsList = new ArrayList<>();

    private String currentWord;

    private String[] words;

    private ViewFlipper viewFlipper;

    private TextView letters_guessed;
    private TextView word_to_guess;

    public interface HangmanImagesListener{
        void wordGuessedLost();
        void wordGuessedWon();
        void gameFinished();
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
        word_to_guess = view.getRootView().findViewById(R.id.word_to_guess);
        return view;
    }

    public void nextImage(String letter){
        String temp = letters_guessed.getText().toString();
        if (temp == ""){
            letters_guessed.setText(letter);
        }
        letters_guessed.setText(temp+" "+letter);
        checkForCharacterInWord(letter);
    }

    public void newWord() {
        if (!wordsList.isEmpty()) {
            viewFlipper.setDisplayedChild(0);
            Random rand = new Random();
            int random = rand.nextInt(wordsList.size());
            setUnderscores(wordsList.get(random));
            System.out.println(currentWord);
            letters_guessed.setText("");
            wordsList.remove(random);
        }
    }

    public void setWords(int language){
        if (language == 0) words = getResources().getStringArray(R.array.hangmanWords_english);
        if (language == 1) words = getResources().getStringArray(R.array.hangmanWords_norwegian);

        for (int i = 0; i < words.length; i++){
            wordsList.add(words[i]);
        }
        Random rand = new Random();
        int random = rand.nextInt(words.length);
        setUnderscores(words[random]);
        wordsList.remove(random);
    }

    private void setUnderscores(String randomWord) {
        String underscores = "";
        currentWord = "";
        for (int i = 0; i < randomWord.length(); i++){
            if (randomWord.substring(i, i+1).equals("-")){
                underscores += "- ";
                currentWord += String.valueOf(randomWord.charAt(i))+" ";
            } else if (randomWord.substring(i, i+1).equals(" ")) {
                underscores += "  ";
                currentWord += String.valueOf(randomWord.charAt(i))+" ";
            } else {
                underscores += "_ ";
                currentWord += String.valueOf(randomWord.charAt(i))+" ";
            }
        }
        word_to_guess.setText(underscores);
    }

    private void checkForCharacterInWord(String letter) {
        String temp = word_to_guess.getText().toString();
        System.out.println("Current word: "+currentWord);
        for (int i = 0; i < currentWord.length(); i++) {
            if (currentWord.substring(i, i + 1).equalsIgnoreCase(letter)) {
                char[] temp2 = temp.toCharArray();
                temp2[i] = letter.charAt(0);
                temp = String.valueOf(temp2);
            }
        }
        if (temp.equals(word_to_guess.getText().toString())) {
            viewFlipper.showNext();
            if (viewFlipper.getDisplayedChild() == 10) {
                //String temp3 = letters_guessed.getText().toString();
                fillInMissingCharacters();
                activityCommander.wordGuessedLost();
            }
        } else {
            word_to_guess.setText(temp);
            if (word_to_guess.getText().toString().equalsIgnoreCase(currentWord)) activityCommander.wordGuessedWon();
        }
    }

    /*
    Fills in missing characters if players loses

    source: https://stackoverflow.com/questions/6094315/single-textview-with-multiple-colored-text
     */
    private void fillInMissingCharacters(){
        String temp = word_to_guess.getText().toString();
        String finalWord = "";
        for (int i = 0; i < currentWord.length(); i++) {
            if (temp.substring(i, i+1).equalsIgnoreCase(String.valueOf(currentWord.charAt(i)))) {
                finalWord += "<font color=#ffffff>"+String.valueOf(temp.charAt(i))+"</font>";
            } else {
                finalWord += "<font color=#808080>"+String.valueOf(currentWord.charAt(i))+"</font>";
            }
        }
        word_to_guess.setText(Html.fromHtml(finalWord));
    }
}
