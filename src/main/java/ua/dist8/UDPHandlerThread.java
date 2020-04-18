package ua.dist8;

import netscape.javascript.JSObject;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.DatagramPacket;
import java.net.InetAddress;


public class UDPHandlerThread extends Thread{
    private Hashing hash;
    private DatagramPacket datagramPacket;

    /**
     * Constructor for UDPHandlerThread.
     * @param datagramPacket UDP packet.
     */
    public UDPHandlerThread(DatagramPacket datagramPacket){
        this.datagramPacket = datagramPacket;
        this.hash = new Hashing();
    }

    /**
     * Checks if packet concerns discovery.
     * If so, it calls the method to add a new node to the hashmap.
     */
    public void run() {
        String dataString = new String(datagramPacket.getData());
        try {
            JSONObject json = new JSONObject(dataString);


            if(json.getString("typeOfMsg").equals("Discovery")){
                newNode();
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    //todo: return number of nodes

    /**
     * Adds a given node (corresponding to the datagram packet) to the hashmap.
     * Sends the number of nodes already in the network to the newly added node.
     * If the node was already in the map, it sends -1.
     */
    private void newNode(){
        try {
            InetAddress clientAddress = datagramPacket.getAddress();
            String clientHostName = clientAddress.getHostName();
            Integer hashingValue = hash.createHash(clientHostName);
            NetworkHashMap hashMap = new NetworkHashMap();
            int addSuccess = hashMap.addNode(clientAddress);
            if(addSuccess == 0){
                int numberOfNodes = hashMap.getNumberOfNodes();
                // stuur numberOfNodes door
            }
            else{
                // Stuur -1
            }
            System.out.println("UDP request completed.");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}


