package ua.dist8;
import java.net.InetAddress;
import java.util.HashMap;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

public class NamingServer extends HttpServlet{

    private HashMap<Integer, InetAddress> nodesHashMap = new HashMap<Integer, InetAddress>();


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
