package com.example.ejs.exercise2_task1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int upper_bound = 100;
    //change to true for task e, else demonstrating task a
    private boolean TASK_E = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //exercise1, task a
        final Button button = findViewById(R.id.button);
        //Listener for addition button
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (TASK_E){
                    Intent intent = new Intent(v.getContext(),Test_bcd.class);
                    startActivityForResult(intent, 1);
                } else {
                    Random r = new Random();
                    random_integer_toast(r);
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode==1 && resultCode==RESULT_OK){
            String random_int = data.getStringExtra("random_int1");
            Toast.makeText(getApplicationContext(),"Random int: "+random_int,Toast.LENGTH_SHORT).show();
        }
    }

    //Task 1 a
    public void random_integer_toast(Random r) {
        Toast.makeText(getApplicationContext(),"Random int: "+r.nextInt(upper_bound),Toast.LENGTH_SHORT).show();
    }
}
