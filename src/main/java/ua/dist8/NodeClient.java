package ua.dist8;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class NodeClient {

    private Hashing hashing;
    private String nodeName;
    private Integer nextID;
    private Integer previousID;


    /**
     * Constructor for the NodeClient class
     */
    NodeClient(){
        hashing = new Hashing();
        try {
            nodeName = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        previousID = -1;
        nextID = -1;
    }

    /**
     * This function wil run when a node receives a multicast message from a new node that wants to join the network
     * @param receivedNodeName is the name of the node that wants to join
     * @param nodeIP is the IP-address of the node that wants to join
     * @return 1 if successfully completed
     */
    public int receivedMulticast(String receivedNodeName, InetAddress nodeIP)
    {

        Integer hash = hashing.createHash(receivedNodeName);

        try {
            nodeName = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        Integer currentID = hashing.createHash(nodeName);

        if(currentID<hash && hash<nextID){
            nextID = hash;
            //reply to sender of the multicast with currentID and nextID
        }
        if(previousID< hash && hash<currentID){
            previousID = hash;
            //reply to sender of the multicast with currentID and previousID
        }

        return 1;
    }
  
    public int receiveUnicastMessage() throws Exception{
        Integer receivedNumberOfMessages = 0;
        Boolean leaveWhile = Boolean.FALSE;

        //Initialize socket
        System.out.println("Waiting for respons of a multicast boodstrap.");
        ServerSocket serverSocket = new ServerSocket(5000);

        Socket clientSocket = serverSocket.accept();

        do{
            InputStream is = socket.getInputStream(); // Try to read an incomming unicast message.
            if( reveivedMessage){
                //readmessage
                receivedNumberOfMessages++;
                if(vanNameServer && aantalNodes <= 0){
                    leaveWhile = Boolean.TRUE; // er is maar 1 bericht dat ontvangen moest worden en dit is ontvangen
                }
                if(vanNode){
                    // handel dit verder af.
                }
            }
        }while(!leaveWhile && receivedNumberOfMessages<3);
        return 0;
    }

    //Initialize socket
        System.out.println("Client started working.");
        System.out.println(InetAddress.getByName("host2"));
    Socket socket = new Socket(InetAddress.getByName("host2"), 5000);
    byte[] contents = new byte[10000];

    //Initialize the FileOutputStream to the output file's full path.
    InputStream is = socket.getInputStream(); // I want to receive a file from the server.

    //Number of bytes read in one read() call
    int bytesRead = 0;

        is.read(contents);
        socket.close();

        System.out.println("File saved successfully!");

    public int sendUnicastMessage(){
        return 0;
    }
}
