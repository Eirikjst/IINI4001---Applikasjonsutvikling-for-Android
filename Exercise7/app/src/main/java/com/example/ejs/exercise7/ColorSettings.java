package com.example.ejs.exercise7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ColorSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void goBackButton(View v){
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();

    }
}
