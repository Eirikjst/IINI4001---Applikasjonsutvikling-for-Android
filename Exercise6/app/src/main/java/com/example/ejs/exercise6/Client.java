package com.example.ejs.exercise6;

import android.app.Activity;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Thread {

    private final static String TAG = "client";
    private final static String IP_ADDRESS = "127.0.0.1";
    private final static int PORT_NUMBER = 8082;

    private Socket socket = null;
    private InputStreamReader inputStreamReader = null;
    private BufferedReader bufferedReader = null;
    private PrintWriter printWriter = null;


    private int number_one, number_two;
    private MainActivity activity;

    public Client(MainActivity activity, int number_one, int number_two) {
        this.number_one = number_one;
        this.number_two = number_two;
        this.activity = activity;
    }

    @Override
    public void run() {
        try {
            socket = new Socket(IP_ADDRESS, PORT_NUMBER);
            inputStreamReader = new InputStreamReader(socket.getInputStream());
            bufferedReader = new BufferedReader(inputStreamReader);
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println(number_one+","+number_two);

            String res = bufferedReader.readLine();
            activity.showResult(res);

        } catch (IOException ioe) {
            Log.e(TAG, ioe.getMessage());
        } finally {
            try {
                if (socket != null) socket.close();
                if (inputStreamReader != null) inputStreamReader.close();
                if (bufferedReader != null) bufferedReader.close();
                if (printWriter != null) printWriter.close();
            } catch (IOException ioe) {
                Log.e(TAG, ioe.getMessage());
            }
        }
    }
}
