package com.example.ejs.exercise5_v2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class GuessingActivity extends AppCompatActivity {

    private HttpWrapperThreaded network;
    final static String TAG = "GuessingActivity";
    final static String urlToServer = "http://tomcat.stud.iie.ntnu.no/studtomas/tallspill.jsp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guessing);

        Intent intent = getIntent();
        String response = intent.getStringExtra("ServerResponse");

        TextView temp = findViewById(R.id.responseText);
        temp.setText(response);

        Log.w(TAG,"Contacting server...");
        network = new HttpWrapperThreaded(this, urlToServer, 2);

        final Button send = findViewById(R.id.btnSend);
        final Button restart = findViewById(R.id.btnRestart);
        final EditText etTall = findViewById(R.id.tall);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tall = etTall.getText().toString();
                network.runHttpRequestInThread(HttpWrapperThreaded.HttpRequestType.HTTP_GET, createRequestValues(tall));
            }
        });

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private Map<String,String> createRequestValues(String tall){
        Map<String,String> valueList = new HashMap<String,String>();
        valueList.put("tall", tall);
        return valueList;
    }

    public void showResponse(String response){
        Log.w(TAG,"Server responded with: " + response);
        TextView temp = findViewById(R.id.responseText);
        temp.setText(response);
    }
}
