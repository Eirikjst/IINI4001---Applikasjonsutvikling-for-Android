package com.example.ejs.exercise6;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {

    private final static String TAG = "clienthandler";
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run(){
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);

            String line = bufferedReader.readLine();
            while (line != null) {
                if (line.contains(",")) {
                    int number_one = Integer.parseInt(line.split(",")[0]);
                    int number_two = Integer.parseInt(line.split(",")[1]);
                    int sum = number_one + number_two;
                    printWriter.println(sum);
                }
                line = bufferedReader.readLine();
            }

            bufferedReader.close();
            printWriter.close();

        } catch (IOException ioe) {
            Log.e(TAG, ioe.getMessage());
        }
    }
}
