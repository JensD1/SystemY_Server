// see https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/ConcurrentSkipListMap.html for more documentation on ConcurrentSkipListMap


package ua.dist8;

import org.json.JSONObject;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.Semaphore;

public class NetworkHashMap {

    private static NetworkHashMap networkHashMap = new NetworkHashMap();
    static private Semaphore sem = new Semaphore(1);

    // This type of sorted concurrent hashmap does not need any type of external synchronization
    // since it is already internally synchronized.
    private ConcurrentSkipListMap<Integer, InetAddress> nodesHashMap;

    public static NetworkHashMap getInstance(){
        return networkHashMap;
    }

    /**
     * Constructor for NetworkHashMap.
     */
    private NetworkHashMap() {
        nodesHashMap = new ConcurrentSkipListMap<>();
    }

    /**
     * Loads the hashtable from a local file "NSData.ser".
     * The file will be loaded in by using the serialization property of ConcurrentSkipListMap.
     * The loaded file will be stored in the static variable nodesHashMap.
     * This method will only be used once on startup of the NamingServer.
     *
     * @throws IOException
     */
    public void loadHashMap() throws IOException {
        ObjectInputStream objectinputstream = null;
        try {
            FileInputStream streamIn = new FileInputStream("NSData.ser");
            objectinputstream = new ObjectInputStream(streamIn);
            nodesHashMap = (ConcurrentSkipListMap<Integer, InetAddress>) objectinputstream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(objectinputstream != null){
                objectinputstream .close();
            }
        }
    }

    /**
     * Stores the content of nodesHashMap into local file "NSData.ser".
     * We take advantage of the serialization property of the ConcurrentSkipListMap class.
     * Writing to the file will never happen in multiple threads which means semaphores are not needed.
     * @throws IOException
     */
    public void storeHashMap() throws IOException {
        ObjectOutputStream oos = null;
        FileOutputStream fout = null;
        fout = new FileOutputStream("NSData.ser");
        oos = new ObjectOutputStream(fout);
        oos.writeObject(nodesHashMap);

        if(oos != null){
            oos.close();
        }
    }


    /**
     * Gets the inetAdress of the node that contains the requested file.
     * Looks for the largest key in nodesHashMap smaller than the given fileHash.
     * Then extracts the InetValue corresponding with the found key.
     * If no such key exists, we take the largest keyValue from the nodesHashMap.
     * If the nodesHashMap is empty, we will return null.
     * @param fileHash Hash value of the requested file
     * @return address
     */
    public InetAddress getInetAddress(Integer fileHash){
        Map.Entry<Integer,InetAddress> addressEntry = nodesHashMap.floorEntry(fileHash);
        if(addressEntry == null) {
            addressEntry = nodesHashMap.lastEntry();//return null if map is empty
            if(addressEntry == null)
                return null;
        }
        System.out.println("The requested InetAddress is: " + addressEntry.getValue().getHostName());
        return addressEntry.getValue();
    }

    /**
     * Adds the given address and its hash value to the nodesHashMap.
     * If the hostname from the address already exists in the nodesHashMap, we return -1, otherwise return 0.
     * @param address IP address of the node we want to add.
     * @return
     */
    public int addNode(InetAddress address, String name){
        Integer hashValue = Hashing.createHash(name);
        if(!nodesHashMap.containsKey(hashValue)) {
            nodesHashMap.put(hashValue, address);
            System.out.println("Added node " + hashValue + " Successfully.\nThe map contains: ");
            for (Integer key : nodesHashMap.keySet())
            {
                System.out.println(key + ": " + nodesHashMap.get(key));
            }
            return 0;
        }
        return -1;
    }

    /**
     * Removes the given address and its hash value from the nodesHashMap.
     * @param address IP address of the node we want to remove.
     */
    public void removeNode(InetAddress address, Integer ID){
        nodesHashMap.remove(ID, address);
        System.out.println("Removed Node " + ID + " Successfully.");
    }

    /**
     * Returns the amount of other nodes in the network.
     * This method gets called when a new node enters the network.
     * @return Size of nodesHashMap
     */
    public int getNumberOfNodes(){
        return (nodesHashMap.size() - 1);
    }

    /**
     * Gets previous node with respect to a given node in the hashmap.
     * If there is no previous node, it takes the last (highest) node (circular).
     * @param hash Hash value of the given node.
     * @return Previous node if possible. Last node if there is no previous one. Null if there are no other nodes.
     */
    public InetAddress getPreviousNode(Integer hash){
        Map.Entry<Integer,InetAddress> addressEntry = nodesHashMap.lowerEntry(hash);
        if(addressEntry == null){
            addressEntry = nodesHashMap.lastEntry();//return null if map is empty
            if (addressEntry == null)
                return  null;
        }
        return addressEntry.getValue();
    }

    /**
     * Gets next node with respect to a given node in the hashmap.
     * If there is no next node, it takes the first (lowest) node (circular).
     * @param hash Hash value of the given node.
     * @return Next node if possible. First node if there is no next one. Null if there are no other nodes.
     */
    public InetAddress getNextNode(Integer hash){
        Map.Entry<Integer,InetAddress> addressEntry = nodesHashMap.higherEntry(hash);
        if (addressEntry==null){
            addressEntry = nodesHashMap.firstEntry();
            if (addressEntry==null)
                return null;
        }
        return addressEntry.getValue();
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
  
    public boolean getNodeExists(Integer hash) {
        InetAddress address = nodesHashMap.get(hash);
        boolean returnValue = true;
        if (address == null)
            returnValue = false;
        return returnValue;
    }
}
