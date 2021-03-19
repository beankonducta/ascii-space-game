package com.patrick.game.util;

import static java.util.Arrays.fill;

public class Ascii {

    /**
     * Converts a string to a char array of (n) width. Used so we can properly draw more complex
     * ascii objects (bosses, title text, etc)
     *
     * @param str
     * @param width
     * @return
     */
    public static char[][] stringTo2dCharArray(String str, int width) {
        char[][] chars = new char[str.length() / width][width];
        int count = 0;
        for (int i = 0; i < str.length() / width; i++) {
            for (int j = 0; j < width; j++) {
                chars[i][j] = str.charAt(count);
                count++;
            }
        }
        return chars;
    }

    /**
     * Creates an ascii cool 's' of (n) size.
     *
     * Borrowed from https://codegolf.stackexchange.com/questions/169045/draw-the-cool-s
     *
     * @param n
     * @return
     */
    public static char[][] coolS(int n) {
        int i, r, d = 3 + 6 * n, w = 3 + n * 4, h = 6 + n * 10, m = n + n, v = w / 2, k = h - 1, j = w - 1;
        char t[], S = '/', B = '\\', P = '|', s[][] = new char[h][w];
        for (char x[] : s) fill(x, ' ');
        s[0][v] = '^';
        s[k][v] = 'v';
        for (i = 0; i < 1 + m; i++) {
            r = i + 1;
            t = s[r];
            t[v - r] = S;
            t[v + r] = B;
            t = s[k - r];
            t[v - r] = B;
            t[v + r] = S;
        }
        for (i = 0; i < m; i++) {
            r = 2 + m + i;
            t = s[r];
            t[0] = t[v] = t[j] = P;
            t = s[k - r];
            t[0] = t[v] = t[j] = P;
        }
        for (i = 0; i < 1 + n; i++) {
            r = 2 + m + m + i;
            t = s[r];
            t[i] = t[i + 1 + m] = B;
            t[j - i] = S;
            t = s[d - i];
            t[i] = S;
            t[v - i] = t[j - i] = B;
        }
        return s;
    }
}
