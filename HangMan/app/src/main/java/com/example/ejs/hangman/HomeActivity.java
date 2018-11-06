package com.example.ejs.hangman;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    private String exitText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        exitText = getResources().getStringArray(R.array.exitActionBar)[0];;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuItem menuItem = menu.add(0,0,0, exitText);
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        exit();
        return super.onOptionsItemSelected(menuItem);
    }

    public void startGame(String language){
        Intent intent = new Intent(this, GameScreen.class);
        intent.putExtra("lang", language);
        startActivityForResult(intent, 5);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == 5 && resultCode == RESULT_CANCELED){
            exit();
        }
    }

    public void exit(){
        this.finish();
        System.exit(0);
    }
}
