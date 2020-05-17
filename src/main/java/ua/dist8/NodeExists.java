package ua.dist8;

public class NodeExists {
    private boolean nodeExists;

    /**
     * Constructor for NodeExists.
     * @param nodeExists is a boolean if the node exists.
     */
    public NodeExists(boolean nodeExists){
        this.nodeExists = nodeExists;

    }

    /**
     * return the boolean nodeExists.
     * @return number of nodes in the network.
     */
    public boolean getNumberOfNodes(){
        return this.nodeExists;
    }
}
