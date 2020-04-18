package ua.dist8;

import java.net.InetAddress;

public class NeighbourInetAddress {
    private InetAddress previousNode;
    private InetAddress nextNode;
    private Integer nodeHash;

    public NeighbourInetAddress(InetAddress previousNode, InetAddress nextNode, Integer nodeHash){
        this.previousNode = previousNode;
        this.nextNode = nextNode;
        this.nodeHash = nodeHash;

    }
    public InetAddress getPreviousNode(){
        return previousNode;
    }

    public InetAddress getNextNode(){
        return  nextNode;
    }

    public Integer getNodeHash() {
        return nodeHash;
    }
}
