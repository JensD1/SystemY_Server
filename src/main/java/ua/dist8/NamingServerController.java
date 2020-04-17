package ua.dist8;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;

@RestController
public class NamingServerController {
    @GetMapping("/fileRequest")
    public FileLocation fileLocation(@RequestParam(value = "filename") String filename){
        InetAddress ip = null;
        Hashing hash = new Hashing();
        Integer fileHash = hash.createHash(filename);
        // do hashtable lookup
        // temporary value
        try {
            ip = InetAddress.getLocalHost(); // = findIpadress(fileHash)

        }catch(Exception e){System.out.println(e);}
        return new FileLocation(filename,ip);
    }

}
