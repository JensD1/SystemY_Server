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
        System.out.println("Reveived REST request, executing query..");
        InetAddress ip = null;
        Hashing hash = new Hashing();
        Integer fileHash = hash.createHash(filename);
        NetworkHashMap hashMap = new NetworkHashMap();
        InetAddress address = hashMap.getInetAddress(fileHash); // will be null if there are no entries in the hashmap

        try {
            ip = InetAddress.getLocalHost(); // = findIpadress(fileHash)

        }catch(Exception e){System.out.println(e);}
        System.out.println("File found!, returning the location of the file");
        return new FileLocation(filename,ip);
    }

}
