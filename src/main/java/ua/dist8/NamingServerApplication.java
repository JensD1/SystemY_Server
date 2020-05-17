package ua.dist8;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@SpringBootApplication
public class NamingServerApplication {
    /**
     * Starts the REST server, listeners and threads.
     *
     */
    public static void main(String[] args) {
        SpringApplication.run(NamingServerApplication.class, args);
        System.out.println("SystemY server version 1.0.3");
        System.out.println("REST Server started succesfully!" );
        NetworkHashMap networkHashMap = NetworkHashMap.getInstance();
        //todo inlezen file.
//        try {
//            networkHashMap.loadHashMap();
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("NSData.ser not available! NameServer will start with empty node Database!");
//        }
//        System.out.println("NSData.ser loaded!...");
        UDPListener udpListener = new UDPListener();
        udpListener.start();
        TCPListener tcpListener = new TCPListener();
        tcpListener.start();
    }
}
