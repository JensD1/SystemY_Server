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
        System.out.println("REST Server started succesfully!" );
        UDPListener udpThread = new UDPListener();
        udpThread.start();

    }
}
