package com.example.ejs.hangman;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class GameScreen extends AppCompatActivity implements View.OnClickListener, HangmanImages.HangmanImagesListener {

    private boolean ACTIVE_GAME = true;

    private Button btnNextWord;

    private int gamesLostCount = 0;
    private int gamesWonCount = 0;
    private int languageSelected;
    private int wordsPlayedCount = 0;
    private int wordsTotalCount = 0;

    private List<Integer> letters_guessed = new ArrayList<>();

    private String alphabetString;
    private String exitText;
    private String gamesLost;
    private String gamesWon;
    private String helpText;
    private String nextWord;
    private String totalWords;
    private String wordsPlayed;

    private String[] words_to_guess;

    private TableLayout tableLayout;
    private TextView gamesWonTextView, gamesLostTextView, totalWordsTextView, wordsPlayedTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        tableLayout = findViewById(R.id.alphabet);
        tableLayout.setStretchAllColumns(true);

        Intent intent = getIntent();
        languageSelected = Integer.parseInt(intent.getStringExtra("lang"));

        setVariables();
        setTextViews();

        initializeAlphabetTable();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuItem menuItem = menu.add(0,0,0, exitText);
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        menuItem=menu.add(0,1,0,helpText);
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        String temp = menuItem.getTitle().toString();

        //if else structure instead of case because vars is not final
        if (temp == exitText) {
            finish();
        } else if (temp == "") {
            System.out.println("test");
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @SuppressLint("ResourceType")
    private void initializeAlphabetTable() {

        for (int i = 0; i < alphabetString.length(); i=i+5) {

            final TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT
            ));

            if (i < alphabetString.length()) {
                final TextView column1 = new TextView(this);
                column1.setId(i);
                column1.setText(Character.toString(alphabetString.charAt(i)));
                column1.setTextSize(20);
                column1.setPadding(5,3,5,3);
                column1.setOnClickListener(this);
                tableRow.addView(column1);
            }

            if (i+1 < alphabetString.length()){
                final TextView column2 = new TextView(this);
                column2.setId(i+1);
                column2.setText(Character.toString(alphabetString.charAt(i+1)));
                column2.setTextSize(20);
                column2.setPadding(5,3,5,3);
                column2.setOnClickListener(this);
                tableRow.addView(column2);
            }

            if (i+2 < alphabetString.length()) {
                final TextView column3 = new TextView(this);
                column3.setId(i+2);
                column3.setText(Character.toString(alphabetString.charAt(i+2)));
                column3.setTextSize(20);
                column3.setPadding(5,3,5,3);
                column3.setOnClickListener(this);
                tableRow.addView(column3);
            }

            if (i+3 < alphabetString.length()) {
                final TextView column4 = new TextView(this);
                column4.setId(i+3);
                column4.setText(Character.toString(alphabetString.charAt(i+3)));
                column4.setTextSize(20);
                column4.setPadding(5,3,5,3);
                column4.setOnClickListener(this);
                tableRow.addView(column4);
            }

            if (i+4 < alphabetString.length()) {
                final TextView column5 = new TextView(this);
                column5.setId(i+4);
                column5.setText(Character.toString(alphabetString.charAt(i+4)));
                column5.setTextSize(20);
                column5.setPadding(5,3,5,3);
                column5.setOnClickListener(this);
                tableRow.addView(column5);
            }

            tableLayout.addView(tableRow, new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.WRAP_CONTENT,
                    TableLayout.LayoutParams.WRAP_CONTENT
            ));
        }
    }

    private void setVariables(){
        alphabetString = getResources().getStringArray(R.array.alphabet)[languageSelected];
        exitText = getResources().getStringArray(R.array.exitActionBar)[languageSelected];
        gamesLost = getResources().getStringArray(R.array.gamesLost)[languageSelected];
        gamesWon = getResources().getStringArray(R.array.gamesWon)[languageSelected];
        helpText = getResources().getStringArray(R.array.helpActionBar)[languageSelected];
        nextWord = getResources().getStringArray(R.array.nextWord)[languageSelected];
        totalWords = getResources().getStringArray(R.array.wordsTotal)[languageSelected];
        wordsPlayed = getResources().getStringArray(R.array.wordsPlayed)[languageSelected];

        /*
        Get words for hangman depending on language selected
        0 - English
        1 - Norwegian

        */
        if (languageSelected == 0) {
            words_to_guess = getResources().getStringArray(R.array.hangmanWords_english);
        } else if (languageSelected == 1) {
            words_to_guess = getResources().getStringArray(R.array.hangmanWords_norwegian);
        }
        wordsTotalCount = words_to_guess.length;
    }

    private void setTextViews(){
        btnNextWord = findViewById(R.id.btnNextWord);
        gamesLostTextView = findViewById(R.id.gamesLost);
        gamesWonTextView = findViewById(R.id.gamesWon);
        totalWordsTextView = findViewById(R.id.totalWords);
        wordsPlayedTextView = findViewById(R.id.wordsPlayed);

        btnNextWord.setText(nextWord);
        gamesLostTextView.setText(gamesLost+gamesLostCount);
        gamesWonTextView.setText(gamesWon+gamesWonCount);
        totalWordsTextView.setText(totalWords+ wordsTotalCount);
        wordsPlayedTextView.setText(wordsPlayed+wordsPlayedCount);
    }

    @Override
    public void onClick(View v) {
        if (ACTIVE_GAME) {
            v.setVisibility(View.INVISIBLE);
            TextView temp = tableLayout.findViewById(v.getId());
            letters_guessed.add(v.getId());
            HangmanImages hangmanImages = (HangmanImages) getSupportFragmentManager().findFragmentById(R.id.fragment1);
            hangmanImages.nextImage(temp.getText().toString());
        }
    }

    public void onClickButton(View v) {
        HangmanImages hangmanImages = (HangmanImages) getSupportFragmentManager().findFragmentById(R.id.fragment1);
        hangmanImages.newWord();
        for (int i = 0; i < letters_guessed.size(); i++){
            tableLayout.findViewById(letters_guessed.get(i)).setVisibility(View.VISIBLE);
        }
        btnNextWord.setVisibility(View.INVISIBLE);
        ACTIVE_GAME = true;
    }

    @Override
    public void updateVarsAfterGame() {
        ACTIVE_GAME = false;
        wordsPlayedCount++;
        wordsPlayedTextView.setText(wordsPlayed+wordsPlayedCount);
        btnNextWord.setVisibility(View.VISIBLE);
    }
}
