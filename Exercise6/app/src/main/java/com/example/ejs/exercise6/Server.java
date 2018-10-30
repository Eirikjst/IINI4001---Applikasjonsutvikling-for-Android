package com.example.ejs.exercise6;

import android.util.Log;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {

    private final static String TAG = "Server";
    private final static int PORT_NUMbER = 8082;

    @Override
    public void run() {
        Socket socket = null;
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(PORT_NUMbER);
            Log.e(TAG, "Server started");

            while(true) {
                socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket);
                clientHandler.start();
            }

        } catch (IOException ioe) {
            Log.e(TAG, ioe.getMessage());
        } finally {
            try {
                if (serverSocket != null) serverSocket.close();
                if (socket != null) socket.close();
            } catch (IOException ioe2) {
                Log.e(TAG, ioe2.getMessage());
            }
        }
    }
}
