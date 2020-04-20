package ua.dist8;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.Semaphore;


public class UDPHandlerThread extends Thread{
    private Hashing hash;
    private DatagramPacket datagramPacket;
    static private Semaphore sem = new Semaphore(1);

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

    /**
     * Adds a given node (corresponding to the datagram packet) to the hashmap.
     * Sends the number of nodes already in the network to the newly added node.
     * If the node was already in the map, it sends -1.
     */
    private void newNode(){
        try {
            InetAddress clientAddress = datagramPacket.getAddress();
            System.out.println("Hashing: " + clientAddress);
            NetworkHashMap hashMap = NetworkHashMap.getInstance();
            int addFailure = hashMap.addNode(clientAddress);
            JSONObject json = new JSONObject();
            json.put("typeOfMsg", "multicastReply");
            json.put("typeOfNode", "NS");
            if(addFailure == 0) {
                int numberOfNodes = hashMap.getNumberOfNodes();
                System.out.println("numberOfNodes = " + numberOfNodes);
                json.put("amountOfNodes", numberOfNodes);
            }
            else{
                System.out.println("Failed to add node to hashmap.");
                json.put("amountOfNodes", -1); // en handel af bij de client, hou er rekening mee dat de andere nodes zich al hebben aangepast!!
            }
            System.out.println("Sending a reply message to the sender of the discovery multicast message.");
            //Thread.sleep(1000); // todo verwijder dit
            sendUnicastMessage(clientAddress, json);
            System.out.println("UDP request completed.");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void sendUnicastMessage(InetAddress toSend,JSONObject json) throws IOException, InterruptedException {
        sem.acquire();
        Socket socket = new Socket(toSend, 5000);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(json.toString().getBytes());
        outputStream.flush();
        outputStream.close();
        socket.close();
        sem.release();
    }
}


