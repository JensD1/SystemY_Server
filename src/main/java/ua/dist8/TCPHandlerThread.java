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
    /***
     * Checks if packet concerns shutdown.
     * If so, it calls the method to remove this node from the hashmap.
     */
    public void run() {
        try {
            InputStream clientInput = clientSocket.getInputStream();
            byte[] contents = new byte[10000]; // todo pas mogelijks aan
            if( clientInput.read(contents) != -1) { // the message is not empty.
                String message = new String(contents);
                JSONObject json = new JSONObject(message);

                if (json.getString("typeOfNode").equals("shutdown")) {
                    NetworkHashMap hMap = NetworkHashMap.getInstance();
                    hMap.removeNode(clientSocket.getInetAddress());
                }
            }
            clientInput.close();
            clientSocket.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
