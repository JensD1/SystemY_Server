package ua.dist8;

import java.net.InetAddress;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.Semaphore;

public class NetworkHashMap {

    static private ConcurrentSkipListMap<Integer, InetAddress> nodesHashMap;

    public NetworkHashMap() {
        nodesHashMap = new ConcurrentSkipListMap<>();
    }

    public void loadTreemap(){

    }
    // method to load in treemap from a file stored locally
    //          write out treemap to a local file
    // method to find the hash with the highest value below the given Integer(filename hash)
    // and return the corresponding inetAddress
    // method
    // method
}
