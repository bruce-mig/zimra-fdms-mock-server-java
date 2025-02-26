package com.github.bruce_mig.zimra_fdms_mock_server_java.util.op_id;

import java.util.concurrent.ThreadLocalRandom;

public class OperationIDGenerator {

    /** OperationID Generator Function */
    public static String generateOperationID() {
        // Prefix: always "0H"
        final String prefix = "0H";

        // Generate a random 11-character base-36 (alphanumeric) string.
        // Math.pow(36, 11) gives the range for an 11-character base-36 number.
        long maxRandomValue = (long) Math.pow(36, 11);
        long randomValue = ThreadLocalRandom.current().nextLong(maxRandomValue);
        String randomPart = Long.toString(randomValue, 36).toUpperCase();
        // Pad with zeros to ensure exactly 11 characters.
        randomPart = String.format("%11s", randomPart).replace(' ', '0');

        // Generate the part after the colon: 7 zeros followed by a random digit.
        int randomDigit = ThreadLocalRandom.current().nextInt(10);
        String afterColon = "0000000" + randomDigit;

        // Construct the final ID in the format: 0H + 11-character randomPart + ":" + afterColon
        return prefix + randomPart + ":" + afterColon;
    }
}
