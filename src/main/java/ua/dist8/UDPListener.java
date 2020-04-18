package ua.dist8;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPListener extends Thread {
    public void run() {
        try {
            // UDP parameters
            System.out.println("Initializing UDP listener...");
            byte[] receivedContent = new byte[10000];
            int size = 10000;
            DatagramPacket receivedPacket = new DatagramPacket(receivedContent, size);
            DatagramSocket serverSocket = new DatagramSocket(1234);


            while (true) {
                System.out.println("Listening for UDP messages on port 1234..");
                serverSocket.receive(receivedPacket); //server accept the client connection request
                System.out.println("Packet received! Creating new thread to process the request.");
                UDPHandlerThread thread = new UDPHandlerThread(receivedPacket); //send  the request to a separate thread
                thread.start();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}