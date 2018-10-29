package com.example.ejs.exercise2_task1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.Random;

public class Test_bcd extends Activity {

    private int upper_bound = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_bcd);

        Random r = new Random();
        Intent intent = getIntent();
        //String message =  intent.getStringExtra("upper_bound");

        //upper_bound = Integer.parseInt(message);

        int random_integer1 = r.nextInt(upper_bound);

        Intent data = new Intent();
        data.putExtra("random_int1", Integer.toString(random_integer1));
        setResult(RESULT_OK, data);
        finish();
    }
}
