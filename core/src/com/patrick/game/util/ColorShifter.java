package com.patrick.game.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class ColorShifter {

    private static int colorMod = 1;

    public static Color shiftColor(BitmapFont font, float delta) {
        float r = font.getColor().r + (delta / 10 * colorMod);
        if (r > 1 || r < 0)
            colorMod = colorMod * -1;
        return new Color(r, font.getColor().g, font.getColor().b, 1f);
    }
}