package ua.dist8;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.net.*;


@SpringBootApplication
public class NamingServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(NamingServerApplication.class, args);
        System.out.println("Server Started ....");
       try{
            // UDP parameters
            byte[] receivedContent = null;
            int size = 10000;
            DatagramPacket receivedPacket = new DatagramPacket(receivedContent, size);
            DatagramSocket serverSocket = new DatagramSocket(1234);


            // Server listens on port 1234
            while(true){
                System.out.println("Listening on port 1234..");
                serverSocket.receive(receivedPacket); //server accept the client connection request

                MulticastHandlerThread thread = new MulticastHandlerThread(receivedPacket); //send  the request to a separate thread
                thread.start();
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
