package ua.dist8;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.lang.Runnable;

@SpringBootApplication
public class NamingServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(NamingServerApplication.class, args);
        try{
            ServerSocket serverSocket = new ServerSocket(5000);
            int counter=0;
            System.out.println("Server Started ....");
            while(true){
                counter++;
                Socket clientSocket=serverSocket.accept();  //server accept the client connection request
                System.out.println(" >> " + "Client No:" + counter + " started!");
                MulitcastHandlerThread sct = new TcpServerClientThread(clientSocket,counter); //send  the request to a separate thread
                sct.start();
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
