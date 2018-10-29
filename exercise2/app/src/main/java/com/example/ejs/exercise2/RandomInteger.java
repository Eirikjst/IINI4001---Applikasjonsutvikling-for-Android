package com.example.ejs.exercise2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Random;


public class RandomInteger extends Activity {

    private int upper_bound = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise1_task_a);

        Intent intent = getIntent();
        String message =  intent.getStringExtra("upper_bound");

        upper_bound = Integer.parseInt(message);

        Random r = new Random();
        int random_integer1 = r.nextInt(upper_bound-0) + 0;
        int random_integer2 = r.nextInt(upper_bound-0) + 0;

        //String random_int = Integer.toString(random_integer);
        Intent data = new Intent();
        data.putExtra("random_int1", Integer.toString(random_integer1));
        data.putExtra("random_int2", Integer.toString(random_integer2));
        setResult(RESULT_OK, data);
        finish();
    }

}