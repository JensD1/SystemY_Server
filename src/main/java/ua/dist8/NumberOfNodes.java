package ua.dist8;

public class NumberOfNodes {
    private int numberOfNodes;

    /**
     * Constructor for NeighbourIpAddress with previous node, next node and node hash of the given node.
     * @param previousNode The previous node with respect to the given node.
     * @param nextNode The previous node with respect to the given node.
     * @param nodeHash The hash of the given node.
     */
    public NumberOfNodes(int numberOfNodes){
        this.numberOfNodes = numberOfNodes;

    }

    /**
     * Gets the number of nodes in the network.
     * @return number of nodes in the network.
     */
    public int getNumberOfNodes(){
        return this.numberOfNodes;
    }
}
