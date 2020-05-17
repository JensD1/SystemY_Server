package ua.dist8;

public class NodeExists {
    private Boolean nodeExists;

    /**
     * Constructor for NodeExists.
     * @param nodeExists is a boolean if the node exists.
     */
    public NodeExists(Boolean nodeExists){
        this.nodeExists = nodeExists;
    }

    /**
     * return the boolean nodeExists.
     * @return number of nodes in the network.
     */
    public Boolean getNumberOfNodes(){
        return this.nodeExists;
    }
}
