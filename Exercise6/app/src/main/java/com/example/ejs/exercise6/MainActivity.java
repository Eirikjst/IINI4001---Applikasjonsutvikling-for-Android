package com.example.ejs.exercise6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String TAG = "mainactivity";

    Button sumOfNum;
    EditText first, second;
    TextView res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initiateLayout();

        Server server = new Server();
        server.start();
        Log.e(TAG, "Server started");
    }

    private void initiateLayout(){
        first = findViewById(R.id.first_number);
        second = findViewById(R.id.second_number);
        res = findViewById(R.id.result);
        sumOfNum = findViewById(R.id.calculate);
        sumOfNum.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.calculate:
                try {
                    int first_num = Integer.parseInt(first.getText().toString());
                    int second_num = Integer.parseInt(second.getText().toString());

                    Client client = new Client(this, first_num, second_num);
                    client.start();

                    break;
                } catch (NumberFormatException nfe) {
                    Toast.makeText(this, "Number is not integer, try again", Toast.LENGTH_SHORT).show();
                    break;
                }
        }
    }

    public void showResult(final String result){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                res.setText(result);
            }
        });
    }
}
