package ua.dist8;

import java.net.InetAddress;

public class FileLocation {
    private String filename;
    private InetAddress inetAddress;


    /**
     * Creates a FileLocation with file name and IP address.
     * @param filename The name of the requested file.
     * @param ip The IP address of the node with the requested file.
     */
    public FileLocation(String filename,InetAddress ip){
        this.filename = filename;
        this.inetAddress = ip;
    }

    /**
     * Gets the IP address.
     * @return The IP address of the node with the requested file.
     */
    public InetAddress getInetAddress(){
        return inetAddress;
    }

    /**
     * Gets the file name.
     * @return The name of the requested file.
     */
    public String getFilename(){
        return  filename;
    }

}
