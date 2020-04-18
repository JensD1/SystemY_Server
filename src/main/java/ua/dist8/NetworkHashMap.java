package ua.dist8;

import java.io.*;
import java.net.InetAddress;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.Semaphore;

public class NetworkHashMap {

    // This type of sorted concurrent hashmap does not need any type of external synchronization
    // since it is already internally synchronized.
    static private ConcurrentSkipListMap<Integer, InetAddress> nodesHashMap;

    // Constructor.
    public NetworkHashMap() {
        nodesHashMap = new ConcurrentSkipListMap<>();
    }

    // Method to load in treemap from a file stored locally.
    public void loadHashMap() throws IOException {
        ObjectInputStream objectinputstream = null;
        try {
            FileInputStream streamIn = new FileInputStream("G:\\address.ser");
            objectinputstream = new ObjectInputStream(streamIn);
            nodesHashMap = (ConcurrentSkipListMap<Integer, InetAddress>) objectinputstream.readObject();
            // System.out.println(nodesHashMap.get(i));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(objectinputstream != null){
                objectinputstream .close();
            }
        }
    }

    // Method to write out treemap to a local file.
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


    // Method to return the corresponding inetAddress.
    public InetAddress getInetAddress(Integer hash){
        InetAddress address = nodesHashMap.floorEntry(hash).getValue();
        return address;
    }

    // Method to add node to map.
    public int addNode(InetAddress address){
        Hashing hash = new Hashing();
        Integer hashValue = hash.createHash(address.getHostName());
        if(!nodesHashMap.containsKey(hashValue)) {
            nodesHashMap.put(hashValue, address);
            return 0;
        }
        return -1;
    }

    // Method to remove node from map.
    public void removeNode(InetAddress address){
        Hashing hash = new Hashing();
        Integer hashValue = hash.createHash(address.getHostName());
        nodesHashMap.remove(hashValue, address);
    }

    public int getNumberOfNodes(){
        return (nodesHashMap.size() -1);
    }
}
