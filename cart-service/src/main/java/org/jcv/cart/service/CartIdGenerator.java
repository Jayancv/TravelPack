package org.jcv.cart.service;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Generate random unique cart ID
 */
public class CartIdGenerator {

    public static long generateId() {
        long timestamp = System.currentTimeMillis();
        int randomPart = ThreadLocalRandom.current().nextInt(1000, 9999);
        return Long.parseLong(timestamp + "" + randomPart);
    }
}
