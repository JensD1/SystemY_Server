package ua.dist8;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;

/**
 *  takes a http request of format /fileRequest?filename="name"
 *  calculates the hash value of filename
 *  uses the hash to extract the file location from the hashmap
 *  returns the ip address of the node that contains the file
 */
@RestController
public class NamingServerController {
    @GetMapping("/fileRequest")
    public FileLocation fileLocation(@RequestParam(value = "filename") String filename){
        System.out.println("Reveived REST file request, executing query..");
        Hashing hash = new Hashing();
        NetworkHashMap hashMap = new NetworkHashMap();

        Integer fileHash = hash.createHash(filename);
        InetAddress address = hashMap.getInetAddress(fileHash);// will be null if there are no entries in the hashmap

        System.out.println("File found!, returning the location of the file");
        return new FileLocation(filename,address);
    }

    @GetMapping("/neighbourRequest")
    public NeighbourInetAddress neighbourInetAddress(@RequestParam(value ="nodeHash")Integer nodeHash) {
        System.out.println("Reveived REST neighbour request, executing query..");
        NetworkHashMap hashMap = new NetworkHashMap();
        InetAddress nextNode = hashMap.getNextNode(nodeHash);
        InetAddress previousNode = hashMap.getPreviousNode(nodeHash);
        if (nextNode.equals(null))
            System.out.println("There are no other devices in the network, nextNode and previousNode are empty!");
        return new NeighbourInetAddress(previousNode,nextNode,nodeHash);
    }
}

