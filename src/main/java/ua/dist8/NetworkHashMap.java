// see https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/ConcurrentSkipListMap.html for more documentation on ConcurrentSkipListMap


package ua.dist8;

import java.io.*;
import java.net.InetAddress;
import java.util.Map;
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

    /**
     * loadHasMap loads the hashtable from a local file "NSData.ser".
     * The file will be loaded in by using the serialization property of ConcurrentSkipListMap.
     * The loaded file will be stored in the static variable nodesHashMap
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
     * storeHashMap stores the content of nodesHashMap into local file "NSData.ser".
     * Just like the loadHashMap, we take advantage of the serialization property of the ConcurrentSkipListMap class.
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
     * getInetAdress is the method used by the REST service to get the inetAdress of the node that contains the request file (the fileHash is the hash value of the requested file).
     * We will look for the largest key in nodesHashMap smaller than the given fileHash. We then extract the InetValue corresponding with the found key.
     * If no such key exists, we will take the largest keyValue from the nodesHashMap.
     * If the nodesHashMap is empty, we will return null.
     * @param fileHash
     * @return address
     */
    public InetAddress getInetAddress(Integer fileHash){

        Map.Entry<Integer,InetAddress> addressEntry = nodesHashMap.floorEntry(fileHash);
        if(addressEntry == null) {
            addressEntry = nodesHashMap.lastEntry();//return null if map is empty
            if(addressEntry == null)
                return null;
        }
        System.out.println("test nullpointer succes");
        return addressEntry.getValue();
    }

    /**
     * This method adds the given address to the nodesHashMap.
     * If the hostname from the adsress already exists in the nodesHashMap, we return -1, otherwise return 0.
     *
     * @param address
     * @return
     */
    public int addNode(InetAddress address){
        Hashing hash = new Hashing();
        Integer hashValue = hash.createHash(address.getHostName());
        if(!nodesHashMap.containsKey(hashValue)) {
            nodesHashMap.put(hashValue, address);
            return 0;
        }
        return -1;
    }

    // deze methode kan nog veranderen
    public void removeNode(InetAddress address){
        Hashing hash = new Hashing();
        Integer hashValue = hash.createHash(address.getHostName());
        nodesHashMap.remove(hashValue, address);
    }

    /**
     * returns the amount of other nodes in the network.
     * This method gets called when a new node enters the network.
     *
     * @return size of nodesHashMap
     */
    public int getNumberOfNodes(){
        return (nodesHashMap.size() -1);
    }

    public InetAddress getPreviousNode(Integer hash){
        Map.Entry<Integer,InetAddress> addressEntry = nodesHashMap.lowerEntry(hash);
        if(addressEntry == null){
            addressEntry = nodesHashMap.lastEntry();//return null if map is empty
            if (addressEntry == null)
                return  null;
        }
        return addressEntry.getValue();
    }

    public InetAddress getNextNode(Integer hash){
        Map.Entry<Integer,InetAddress> addressEntry = nodesHashMap.higherEntry(hash);
        if (addressEntry==null){
            addressEntry = nodesHashMap.firstEntry();
            if (addressEntry==null)
                return null;
        }
        return addressEntry.getValue();
    }
}
