package ua.dist8;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class HashingTest {
    @Test
    public void testIfHashingWorks() {
        Hashing hash = new Hashing();
        Integer test1 = hash.createHash("Node1");
        Integer test2 = hash.createHash("Node2");
        Integer test3 = hash.createHash("Node2");
        Integer test4 = hash.createHash("Node15");
        Integer test5 = hash.createHash("node2");
        System.out.println(test1 + "\n" + test2 + "\n" + test3 + "\n" + test4 + "\n" + test5 + "\n");
        Assert.assertEquals(test2, test3);
        Assert.assertNotEquals(test2, test5);
        Assert.assertNotEquals(test1, test4);
        Assert.assertNotEquals(test1, test2);
        Assert.assertNotEquals(test3, test4);
        Assert.assertTrue(test1 >= 0 && test1 < 32768);
        Assert.assertTrue(test2 >= 0 && test2 < 32768);
        Assert.assertTrue(test3 >= 0 && test3 < 32768);
        Assert.assertTrue(test4 >= 0 && test4 < 32768);
        Assert.assertTrue(test5 >= 0 && test5 < 32768);
        System.out.println("Successfully completed");
    }
}