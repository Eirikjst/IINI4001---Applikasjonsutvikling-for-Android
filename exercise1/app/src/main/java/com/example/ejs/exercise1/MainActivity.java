package com.example.ejs.exercise1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean onCreateOptionsMenu(Menu meny){
        super.onCreateOptionsMenu(meny);//kaller metoden som vi arver, er dog ikke nødvendig
        meny.add("Eirik"); //legger til meny-valg med teksten «Valg 1»
        meny.add("Stensen");
        Log.i("onCreateOptionsMenu()","meny laget"); //skriver ut til logg, vises i LogCat
        return true; //true her gjør at menyen vil vises
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getTitle().equals("Valg 1")){
            Log.i("onOptionsItemSelected()","Valg 1 er trykket av brukeren");
        }
        return true;
    }
}
