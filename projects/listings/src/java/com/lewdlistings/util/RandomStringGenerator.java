package com.lewdlistings.util;

import static org.apache.commons.codec.digest.DigestUtils.md5Hex;

public final class RandomStringGenerator {

    public static final int MIN_LENGTH = 8;
    protected static java.util.Random random = new java.util.Random();

    /*
    * Set of characters that is valid. Must be printable, memorable, and "won't
    * break HTML" (i.e., not ' <', '>', '&', '=', ...). or break shell commands
    * (i.e., not ' <', '>', '$', '!', ...). I, L and O are good to leave out,
    * as are numeric zero and one.
    */
    protected static char[] chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g',
            'h', 'j', 'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
            'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K',
            'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            '2', '3', '4', '5', '6', '7', '8', '9'};

    /**
     * Generates a random string.
     *
     * @return a random string.
     */
    public static String getNextRandomString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < MIN_LENGTH; i++) {
            sb.append(chars[random.nextInt(chars.length)]);
        }
        return sb.toString();
    }

    /**
     * Generates a hashed string based upon the specified input and a random 8 char string.
     *
     * @param input the base of the hash
     * @return the hashed string
     */
    public static String getNextHashedRandomString(String input) {
        StringBuilder sb = new StringBuilder(input);
        sb.append(getNextRandomString());
        // Return a hash of the generated value.
        return md5Hex(sb.toString());
    }

    private RandomStringGenerator() {}
}