package com.example.ejs.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void onClick(View view){
        switch (view.getId()) {
            case R.id.lang_english:
                startGame("0");
                break;
            case R.id.lang_norwegian:
                startGame("1");
                break;
        }
    }

    public void startGame(String language){
        Intent intent = new Intent(this, GameScreen.class);
        intent.putExtra("lang", language);
        startActivity(intent);
    }
}
