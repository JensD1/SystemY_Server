package ua.dist8;

import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONObject;
import java.io.IOException;
import java.net.*;

public class NodeClient {

    public void multicast() throws IOException {
        InetAddress MCgroup = InetAddress.getByName("192.1.1.69");
        InetAddress nodeIP = InetAddress.getLocalHost();
        JSONObject obj = new JSONObject();
        Hashing h = new Hashing();
        obj.put("name", "node1");
        obj.put("ip",nodeIP );
        MulticastSocket ms = new MulticastSocket(6012);
        ms.joinGroup(MCgroup);
        byte[] contents = obj.toString().getBytes();
        DatagramPacket packet = new DatagramPacket(contents,contents.length, MCgroup, 6012);
        ms.send(packet);
        //ms.leaveGroup(ms.getLocalSocketAddress(), NetworkInterface.getByInetAddress(group));

    }

    public void receiveMC () throws IOException {
        MulticastSocket ms = new MulticastSocket(6012);
        InetAddress MCgroup = InetAddress.getByName("192.1.1.69");
        ms.joinGroup(MCgroup);
        while(true){
            byte[] buf = new byte[1000];
            DatagramPacket recv = new DatagramPacket(buf, buf.length);
            ms.receive(recv);
            if (recv.getLength()>0){

                String s = new String(String.valueOf(recv));
                JSONObject jsonObject = new JSONObject(s);
                receivedmulticast(jsonObject);

            }
        }
    }
}
