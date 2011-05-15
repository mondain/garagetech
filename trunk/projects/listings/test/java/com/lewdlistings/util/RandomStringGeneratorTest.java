package com.lewdlistings.util;

import org.junit.Assert;
import org.junit.Test;

public class RandomStringGeneratorTest {

    @Test
    public void testNextRandomString() {
        String random = RandomStringGenerator.getNextRandomString();
        System.out.println("Random: " + random.toLowerCase());
        Assert.assertEquals(12, random.length());
    }
}
