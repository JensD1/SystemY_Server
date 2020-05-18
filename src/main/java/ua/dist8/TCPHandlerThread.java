package ua.dist8;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Iterator;
import java.util.Map;

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

                if (json.getString("typeOfMsg").equals("shutdown")) {
                    NetworkHashMap hMap = NetworkHashMap.getInstance();
                    System.out.println("test print" + clientSocket.getInetAddress()+" hash value= " +json.getInt("ID") );
                    hMap.removeNode(clientSocket.getInetAddress(), json.getInt("ID"));
                }

                // Todo: Special cases of replication eg. file already locally store / no smaller hash
                if (json.getString("typeOfMsg").equals("replicationStart")) {

                    Integer fileHash = json.getInt("fileHash");
                    NetworkHashMap hMap = NetworkHashMap.getInstance();
                    Map.Entry<Integer, InetAddress> addressEntry = hMap.getFloorEntry(fileHash);
                    InetAddress ipAdress = addressEntry.getValue();
                    JSONObject jsonReplication = new JSONObject();
                    jsonReplication.put("typeOfMsg","replicationStart");
                    jsonReplication.put("typeOfNode","NS");
                    jsonReplication.put("replicationAddress", ipAdress.getHostName());
                    hMap.sendUnicastMessage(clientSocket.getInetAddress(), jsonReplication);
                }
            }
            clientInput.close();
            clientSocket.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
