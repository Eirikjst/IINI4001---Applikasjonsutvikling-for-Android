package com.example.ejs.exercise5_v2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private HttpWrapperThreaded network;
    final static String TAG = "MainActivity";
    final static String urlToServer = "http://tomcat.stud.iie.ntnu.no/studtomas/tallspill.jsp";

    final static String RESPONSE_WARNING_ONE = "Du har glemt å støtte cookies, eller du har ikke oppgit parameterene navn og kortnummer i første forespørsel!!!";
    final static String RESPONSE_WARNING_TWO = "Feil, kortnummer er ikke oppgitt!";

    private String name;
    private String cardnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.w(TAG,"Contacting server...");
        network = new HttpWrapperThreaded(this, urlToServer, 1);

        final Button send = findViewById(R.id.btnSend);
        final Button restart = findViewById(R.id.btnRestart);
        final EditText etName = findViewById(R.id.name);
        final EditText etCardnumber = findViewById(R.id.card);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = etName.getText().toString();
                cardnumber = etCardnumber.getText().toString();
                network.runHttpRequestInThread(HttpWrapperThreaded.HttpRequestType.HTTP_GET, createRequestValues(name, cardnumber));
            }
        });

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.super.recreate();
            }
        });
    }

    //Method for showing response from HTTP server
    public void showResponse(String response){
        Log.w(TAG,"Server responded with: " + response);
        if (response == RESPONSE_WARNING_ONE || response == RESPONSE_WARNING_TWO){
            Toast.makeText(getBaseContext(), response, Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(this, GuessingActivity.class);
            intent.putExtra("ServerResponse", response);
            startActivity(intent);
        }
    }

    private Map<String,String> createRequestValues(String name, String cardnumber){
        Map<String,String> valueList = new HashMap<String,String>();
        valueList.put("navn", name);
        valueList.put("kortnummer", cardnumber);
        return valueList;
    }

    public HttpWrapperThreaded firstNetwork(){
        return network;
    }
}
