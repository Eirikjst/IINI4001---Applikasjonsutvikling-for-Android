package com.example.ejs.exercise7;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "mainactivity";
    private DatabaseManager db;
    private ArrayList<String[]> dbfile;
    private Button btnAuthors, btnBooks, btnShowAll;;

    private List_Fragment listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbfile = readBooksAndAuthors();

        btnAuthors = findViewById(R.id.btnauthors);
        btnAuthors.setOnClickListener(this);
        btnBooks = findViewById(R.id.btnbooks);
        btnBooks.setOnClickListener(this);
        btnShowAll = findViewById(R.id.btnshowall);
        btnShowAll.setOnClickListener(this);

        listFragment = (List_Fragment) getSupportFragmentManager().findFragmentById(R.id.frag1);

        try {
            db = new DatabaseManager(this);
            db.clean();
            for (int i = 0; i < dbfile.size(); i++){
                db.insert(dbfile.get(i)[0], dbfile.get(i)[1]);
            }
            showAuthorsBooks(db.getAllBooksAndAuthors());
        }
        catch (Exception e) {
            String tekst = e.getMessage();
            TextView t = (TextView)findViewById(R.id.tw1);
            t.setText(tekst);
        }
    }

    public void showAuthorsBooks(ArrayList<String> list) {
        listFragment.showAuthorAndBooksToList(list);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.color) {
            Intent intent = new Intent(this, ColorSettings.class);
            startActivityForResult(intent, 1);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private ArrayList<String[]> readBooksAndAuthors() {
        Log.e(TAG, "Reading datafile");
        ArrayList<String[]> temp = new ArrayList<>();

        InputStream inputStream = getResources().openRawResource(R.raw.books);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            String line = bufferedReader.readLine();

            while (line != null) {
                String author = line.split(",")[0];
                String book = line.split(",")[1];
                String[] authorbook = new String[]{author, book};
                temp.add(authorbook);
                line = bufferedReader.readLine();
            }

            bufferedReader.close();

        } catch (IOException ioe) {
            Log.e(TAG, ioe.getMessage());
        }

        return temp;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnauthors:
                listFragment.showAuthors(db.getAllAuthors());
                break;
            case R.id.btnbooks:
                listFragment.showBooks(db.getAllBooks());
                break;
            case R.id.btnshowall:
                listFragment.showAuthorAndBooksToList(db.getAllBooksAndAuthors());
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == 1 && resultCode == RESULT_OK){
            String color = getDefaultSharedPreferences(this).getString("backgroundColorPref", "#ffffff");
            listFragment.changeBackgroundColor(color);
        }
    }
}