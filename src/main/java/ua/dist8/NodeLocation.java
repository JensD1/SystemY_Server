package ua.dist8;

import java.net.InetAddress;

public class NodeLocation {
    private InetAddress inetAddress;


    /**
     * Creates a NodeLocation with file name and IP address.
     * @param ip The IP address of the node with the requested file.
     */
    public NodeLocation(InetAddress ip){
        this.inetAddress = ip;
    }

    /**
     * Gets the IP address.
     * @return The IP address of the node with the requested file.
     */
    public InetAddress getInetAddress(){
        return inetAddress;
    }

}
