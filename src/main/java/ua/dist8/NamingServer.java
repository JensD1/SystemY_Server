package ua.dist8;
import java.net.InetAddress;
import java.util.TreeMap;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

public class NamingServer extends HttpServlet{

    /**
     * The private fields (objects) of a Naming server.
     */
    static private TreeMap<Integer, InetAddress> nodesHashMap;
    private Hashing hashing;

    /**
     * The constructor of a NamingServer where the hashing and nodesHashMap objects
     * will be created.
     */
    NamingServer(){
        hashing = new Hashing();
        nodesHashMap = new TreeMap<Integer, InetAddress>();
    }

    /**
     * This function will run when the NamingServer receives a multicast message from
     * a new node that wants to join the network.
     * @param nodeName is the name of the node that wants to join the network.
     * @param nodeIP is the IP-address of the node that wants to join the network.
     * @return The amount of nodes currently in the network (before assignment of the new one).
     */
    public int receivedMulticast(String nodeName, InetAddress nodeIP){
        Integer nodeHash = hashing.createHash(nodeName);
        nodesHashMap.put(nodeHash, nodeIP);
        // The -1 serves its purpose by making sure that the last added node is not included.
        return (nodesHashMap.size() - 1);
    }

    public static void main(String[] args) {
        //todo
        //check status existing nodes,if no response --> removeNode
    }

    public int storeHashMap(){
            // stores the hashmap locally in an xml file
            return 0;
    }
    public int loadHashMap(){
            // loads data from local xml file to hashmap
        return 0;
    }
    public void addNode() {
        //todo
    }

    public void removeNode() {
        //todo
    }
    //file requested REST format

    /**
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String requestUrl = request.getRequestURI();
        //Extract accountName from the URL
        String fileName = requestUrl.substring("/SystemY/".length());
        //hash filename
        //get nodeId from hashmap
        //return ip.adress


    }

    @Override
    //POST method,
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // extract nodeName,Ip-addr from request
        // addNode to hashmap


        //get all filenames from the node --> replication
        // response the amount of existing nodes in the network


    }
}
