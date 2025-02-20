package com.github.bruce_mig.zimra_fdms_mock_server_java.util;

import java.util.concurrent.ThreadLocalRandom;

public class OperationIDGenerator {

    /** OperationID Generator Function */
    public static String generateOperationID() {
        final String prefix ="0HMPH";

        // Generate a random 8-character base-36 string.
        long maxRandomValue = (long) Math.pow(36, 8);
        long randomValue = ThreadLocalRandom.current().nextLong(maxRandomValue);
        String randomPart = Long.toString(randomValue, 36).toUpperCase();

        // Pad with zeros to ensure exactly 8 characters.
        randomPart = String.format("%8s", randomPart).replace(' ', '0');

        // Convert the current timestamp to a base-36 string.
        String timestamp = Long.toString(System.currentTimeMillis(), 36).toUpperCase();

        // Concatenate the prefix, random part, and timestamp.
        String id = prefix + randomPart + timestamp;

        // Truncate to 60 characters if necessary.
        if (id.length() > 60) {
            id = id.substring(0, 60);
        }

        return id;
    }
}
