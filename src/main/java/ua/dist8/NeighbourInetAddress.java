package ua.dist8;

import java.net.InetAddress;

public class NeighbourInetAddress {
    private String previousNode;
    private String nextNode;
    private Integer nodeHash;

    /**
     * Constructor for NeighbourIpAddress with previous node, next node and node hash of the given node.
     * @param previousNode The previous node with respect to the given node.
     * @param nextNode The previous node with respect to the given node.
     * @param nodeHash The hash of the given node.
     */
    public NeighbourInetAddress(String previousNode, String nextNode, Integer nodeHash){
        this.previousNode = previousNode;
        this.nextNode = nextNode;
        this.nodeHash = nodeHash;

    }

    /**
     * Gets previous node.
     * @return previous node.
     */
    public String getPreviousNode(){
        return previousNode;
    }

    /**
     * Gets next node.
     * @return next node.
     */
    public String getNextNode(){
        return  nextNode;
    }

    /**
     * Gets the hash of the given node.
     * @return hash of the given node.
     */
    public Integer getNodeHash() {
        return nodeHash;
    }
}
