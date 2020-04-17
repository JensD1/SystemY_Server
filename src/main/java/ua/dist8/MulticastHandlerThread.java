package ua.dist8;

import java.net.DatagramPacket;
import java.net.InetAddress;

// verwerk multicast udp pakket
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
            // kijk of deze hash in de treemap zit => treemap inladen vanuit file (gecontroleerd via semaphores)
            //int numberOfNodes = addNode()
            //if( numberOfNodes >= 0){
            // send number of nodes in network
            //}
            //else{
            // send -1 so client knows there was a problem

        } catch (Exception ex) {
            System.out.println(ex);
        }


    }
}


