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
     * Handles a http file request of format /nodeRequest?nodeHash="hash".
     * Uses the hash to extract the node location from the hashmap.
     * @param nodeHash The hash of the requested node.
     * @return The hash of the requested file and the IP address of the node with the requested file.
     */
    @GetMapping("/nodeRequest")
    public NodeLocation nodeLocation(@RequestParam(value = "nodeHash") Integer nodeHash){
        System.out.println("Received REST node request, executing query..");
        NetworkHashMap hashMap = NetworkHashMap.getInstance();
        InetAddress address = hashMap.getInetAddress(nodeHash);// will be null if there are no entries in the hashmap

        System.out.println("Replication destination found!, returning the location of the node");
        return new NodeLocation(address);
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

    /**
     * Handles a http nodeExists of format /nodeExists?nodeHash="hash".
     * @param nodeHash hash of the given node.
     * @return True if the node exists, false otherwise.
     */
    @GetMapping("/nodeExists")
    public NodeExists nodeExistsRequest(@RequestParam(value ="nodeHash")Integer nodeHash) {
        System.out.println("Received REST nodeExists request, executing query..");
        NetworkHashMap hashMap = NetworkHashMap.getInstance();
        boolean nodeExists = hashMap.getNodeExists(nodeHash);
        return new NodeExists(nodeExists);
    }

    /**
     * Handles a http numberOfNodes request of format /numberOfNodes.
     * Gets next and previous node with respect to a given node.
     * @return The number of nodes in the network
     */
    @GetMapping("/numberOfNodes")
    public NumberOfNodes numberOfNodes() {
        System.out.println("Received REST numberOfNodes request, executing query..");
        NetworkHashMap nhm = NetworkHashMap.getInstance();
        int numberOfNodesTemp = nhm.getNumberOfNodes();
        return new NumberOfNodes(numberOfNodesTemp);
    }
}
