package ua.dist8;

public class Hashing {

    /**
     * Calculates hash value of a given string.
     * @param string The string from which we want the hash value.
     * @return hash value of given string.
     */
    public Integer createHash(String string){
        return java.lang.Math.abs(string.hashCode() % 32768);
    }
}
