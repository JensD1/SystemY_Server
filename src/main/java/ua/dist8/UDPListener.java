package ua.dist8;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class UDPListener extends Thread {

    /**
     * Constantly listens to UDP requests.
     * When there is an incoming request, it generates a new thread to handle it.
     */
    public void run() {
        try {
            // UDP parameters
            System.out.println("Initializing UDP listener..." );
            MulticastSocket ms = new MulticastSocket(6012);
            InetAddress MCgroup = InetAddress.getByName("224.0.0.200");
            ms.joinGroup(MCgroup);
            NetworkHashMap networkHashMap = NetworkHashMap.getInstance();


            while (true) {
                byte[] buf = new byte[1000];
                DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length);
                System.out.println("Listening on Multicast address 224.0.0.200");
                ms.receive(datagramPacket);
                System.out.println("Packet received! Creating new thread to process the request.");
                UDPHandlerThread thread = new UDPHandlerThread(datagramPacket); //send  the request to a separate thread
                thread.start();
                // networkHashMap.storeHashMap(); todo
                // System.out.println("NSData.ser updated with current Network structure.");

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}