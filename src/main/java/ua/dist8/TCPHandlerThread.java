package ua.dist8;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.Socket;

public class TCPHandlerThread extends Thread{

    private Socket clientSocket;
    TCPHandlerThread(Socket clientSocket){
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            InputStream clientInput = clientSocket.getInputStream();
            byte[] contents = new byte[10000]; // todo pas mogelijks aan
            if( clientInput.read(contents) != -1) { // the message is not empty.
                String message = new String(contents);
                JSONObject json = new JSONObject(message);

                if ("shutdown".equals(json.getString("typeOfMsg"))) {
                    //todo roep/maak de juiste functie aan!
                }
            }
            clientInput.close();
            clientSocket.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}