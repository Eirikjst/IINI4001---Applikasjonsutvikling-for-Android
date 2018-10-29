package com.example.ejs.exercise2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class TestExerciseOne_bcd extends Activity {


    public static int random_integer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_exercise1_bcd);

        final Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivityForResult(new Intent(v.getContext(), RandomInteger.class), 1);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode==1 && resultCode==RESULT_OK){
            String number = (String)data.getStringExtra("random_int");
            TextView t = (TextView)findViewById(R.id.textView);
            t.setText(number);
            //Toast.makeText(getApplicationContext(),"Random int: "+number,Toast.LENGTH_SHORT).show();
        }
    }
}
