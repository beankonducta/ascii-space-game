package com.patrick.game.util;

public class Ascii {

    public static char[][] STRING_TO_2D_CHAR_ARRAY(String str, int width) {
        // convert a string to a char array, based on provided width
        char[][] chars = new char[str.length() / width][width];
        int count = 0;
        for(int i = 0; i < str.length() / width; i++) {
            for(int j = 0; j < width; j++) {
                chars[i][j] = str.charAt(count);
                System.out.println(str.charAt(count));
                count++;
            }
        }
        return chars;
    }
}
