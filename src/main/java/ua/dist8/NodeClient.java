package ua.dist8;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NodeClient {

    private Hashing hashing;
    private String nodeName;
    private Integer nextID;
    private Integer previousID;


    /**
     * Constructor for the NodeClient class
     */
    NodeClient(){
        hashing = new Hashing();
        try {
            nodeName = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        previousID = -1;
        nextID = -1;
    }

    /**
     * This function wil run when a node receives a multicast message from a new node that wants to join the network
     * @param receivedNodeName is the name of the node that wants to join
     * @param nodeIP is the IP-address of the node that wants to join
     * @return 1 if successfully completed
     */
    public int receivedMulticast(String receivedNodeName, InetAddress nodeIP)
    {
        Integer hash = hashing.createHash(receivedNodeName);

        try {
            nodeName = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        Integer currentID = hashing.createHash(nodeName);

        if(currentID<hash && hash<nextID){
            nextID = hash;
            //reply to sender of the multicast with currentID and nextID
        }
        if(previousID< hash && hash<currentID){
            previousID = hash;
            //reply to sender of the multicast with currentID and previousID
        }

        return 1;
    }

    public int sendUnicastMessage()
    {
        return 0;
    }


}
