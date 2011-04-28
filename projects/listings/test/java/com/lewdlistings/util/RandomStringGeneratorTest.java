package com.lewdlistings.util;

import org.junit.Assert;
import org.junit.Test;

public class RandomStringGeneratorTest {

    @Test
    public void testNextRandomString() {
        String random = RandomStringGenerator.getNextRandomString();
        System.out.println("Random: " + random);
        Assert.assertEquals(random.length(), 8);
    }
}
