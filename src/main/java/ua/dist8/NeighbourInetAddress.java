package ua.dist8;

import java.net.InetAddress;

public class NeighbourInetAddress {
    private InetAddress previousNode;
    private InetAddress nextNode;
    private Integer nodeHash;

    /**
     * Constructor for NeighbourIpAddress with previous node, next node and node hash of the given node.
     * @param previousNode The previous node with respect to the given node.
     * @param nextNode The previous node with respect to the given node.
     * @param nodeHash The hash of the given node.
     */
    public NeighbourInetAddress(InetAddress previousNode, InetAddress nextNode, Integer nodeHash){
        this.previousNode = previousNode;
        this.nextNode = nextNode;
        this.nodeHash = nodeHash;

    }

    /**
     * Gets previous node.
     * @return previous node.
     */
    public InetAddress getPreviousNode(){
        return previousNode;
    }

    /**
     * Gets next node.
     * @return next node.
     */
    public InetAddress getNextNode(){
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
