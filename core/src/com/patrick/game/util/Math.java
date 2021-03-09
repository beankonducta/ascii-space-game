package com.patrick.game.util;

import java.util.concurrent.ThreadLocalRandom;

public class Math {

    public static int randomBetween(int min, int max) {
        if (min < max)
            return ThreadLocalRandom.current().nextInt(min, max + 1);
        return ThreadLocalRandom.current().nextInt(max, min + 1);
    }

    public static float floatRandomBetween(float min, float max) {
        if (min < max)
            return ThreadLocalRandom.current().nextInt((int) min, (int) max + 1);
        return ThreadLocalRandom.current().nextInt((int) max, (int) min + 1);
    }

    public static int EITHER_OR(int either, int or) {
        int random = 0;
        if (or > either)
            random = ThreadLocalRandom.current().nextInt(either, or + 1);
        else
            random = ThreadLocalRandom.current().nextInt(or, either + 1);

        if (random - either > or - random) return or;
        return either;
    }

    public static int ROUND(float value) {
        return java.lang.Math.round(value);
    }

    public static int RANDOM_POS_NEG(int value) {
        int random = ThreadLocalRandom.current().nextInt(0, 2);
        if (random == 0) return -1 * value;
        return value;
    }
}