package com.example.ejs.exercise4;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        MenuItem mi=menu.add(0,0,0, "Previous");
        mi.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        mi=menu.add(0,1,0, "Next");
        mi.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getTitle().toString()) {
            case "Next":
                next_or_prev_image(true);
                break;
            case "Previous":
                next_or_prev_image(false);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void next_or_prev_image(boolean action){
        ImageFragment imgfrag = (ImageFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        imgfrag.updateImage(action);
    }
}
