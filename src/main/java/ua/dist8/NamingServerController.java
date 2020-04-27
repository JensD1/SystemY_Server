package ua.dist8;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;


@RestController
public class NamingServerController {

    /**
     * Handles a http file request of format /fileRequest?filename="name".
     * Calculates the hash value of the filename.
     * Uses the hash to extract the file location from the hashmap.
     * @param filename The name of the requested file.
     * @return The name of the requested file and the IP address of the node with the requested file.
     */
    @GetMapping("/fileRequest")
    public FileLocation fileLocation(@RequestParam(value = "filename") String filename){
        System.out.println("Received REST file request, executing query..");
        NetworkHashMap hashMap = NetworkHashMap.getInstance();
        Integer fileHash = Hashing.createHash(filename);
        InetAddress address = hashMap.getInetAddress(fileHash);// will be null if there are no entries in the hashmap

        System.out.println("File found!, returning the location of the file");
        return new FileLocation(filename,address);
    }

    /**
     * Handles a http neighbour request of format /neighbourRequest?nodeHash="hash".
     * Gets next and previous node with respect to a given node.
     * @param nodeHash hash of the given node.
     * @return previous and next node with respect to the current node.
     */
    @GetMapping("/neighbourRequest")
    public NeighbourInetAddress neighbourInetAddress(@RequestParam(value ="nodeHash")Integer nodeHash) {
        System.out.println("Received REST neighbour request, executing query..");
        NetworkHashMap hashMap = NetworkHashMap.getInstance();
        InetAddress nextNode = hashMap.getNextNode(nodeHash);
        String nextNodeString = nextNode.getHostName();
        InetAddress previousNode = hashMap.getPreviousNode(nodeHash);
        String previousNodeString = previousNode.getHostName();
        if (nextNode == null)
            System.out.println("There are no other devices in the network, nextNode and previousNode are empty!");
        return new NeighbourInetAddress(previousNodeString,nextNodeString,nodeHash);
    }
}
