package ua.dist8;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.net.*;
import java.lang.Runnable;

@SpringBootApplication
public class NamingServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(NamingServerApplication.class, args);
        try{
            byte[] receivedContent = null;
            int size = 10000;
            DatagramPacket receivedPacket = new DatagramPacket(receivedContent, size);
            DatagramSocket serverSocket = new DatagramSocket(1234);
            System.out.println("Server Started ....");

            while(true){
                serverSocket.receive(receivedPacket); //server accept the client connection request

                MulticastHandlerThread sct = new MulticastHandlerThread(receivedPacket); //send  the request to a separate thread
                sct.start();
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
