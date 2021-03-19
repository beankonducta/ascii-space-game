package com.patrick.game.util;

import java.util.concurrent.ThreadLocalRandom;

public class Math {

    /**
     * Returns a random int between provided ints.
     *
     * @param min
     * @param max
     * @return
     */
    public static int randomBetween(int min, int max) {
        if (min < max)
            return ThreadLocalRandom.current().nextInt(min, max + 1);
        return ThreadLocalRandom.current().nextInt(max, min + 1);
    }

    /**
     * Returns a random float between provided floats.
     *
     * @param min
     * @param max
     * @return
     */
    public static float floatRandomBetween(float min, float max) {
        if (min < max)
            return ThreadLocalRandom.current().nextInt((int) min, (int) max + 1);
        return ThreadLocalRandom.current().nextInt((int) max, (int) min + 1);
    }

    /**
     * Returns either, or, somewhat randomly.
     *
     * @param either
     * @param or
     * @return
     */
    public static int eitherOr(int either, int or) {
        int random = 0;
        if (or > either)
            random = ThreadLocalRandom.current().nextInt(either, or + 1);
        else
            random = ThreadLocalRandom.current().nextInt(or, either + 1);

        if (random - either > or - random) return or;
        return either;
    }

    /**
     * Wrapper for Math.round(n) (so we don't have to import both our Math class & java.lang's Math class)
     *
     * @param value
     * @return
     */
    public static int round(float value) {
        return java.lang.Math.round(value);
    }

    /**
     * Returns the provided value, either positive or negative randomly.
     *
     * @param value
     * @return
     */
    public static int randomPosNeg(int value) {
        int random = ThreadLocalRandom.current().nextInt(0, 2);
        if (random == 0) return -1 * value;
        return value;
    }
}