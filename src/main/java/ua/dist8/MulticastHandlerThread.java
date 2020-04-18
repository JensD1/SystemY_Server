package ua.dist8;

import java.net.DatagramPacket;
import java.net.InetAddress;


public class MulticastHandlerThread extends Thread{
    Hashing hash;
    DatagramPacket datagramPacket;

    public MulticastHandlerThread(DatagramPacket datagramPacket){
        this.datagramPacket = datagramPacket;
        this.hash = new Hashing();
    }

    public void run() {
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

        } catch (Exception ex) {
            System.out.println(ex);
        }


    }
}


