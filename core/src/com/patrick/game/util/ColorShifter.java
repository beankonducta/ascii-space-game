package com.patrick.game.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class ColorShifter {

    private static int COLOR_MOD = 1;

    public static Color shiftColor(BitmapFont font, float delta) {
        float r = font.getColor().r + (delta / 10 * COLOR_MOD);
        if (r > 1 || r < 0)
            COLOR_MOD = COLOR_MOD * -1;
        return new Color(r, font.getColor().g, font.getColor().b, 1f);
    }
}