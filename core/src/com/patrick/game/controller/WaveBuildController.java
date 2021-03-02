package com.patrick.game.controller;

import com.badlogic.gdx.math.Vector2;
import com.patrick.game.entity.Enemy;
import com.patrick.game.util.Math;
import com.patrick.game.util.Settings;

import java.util.ArrayList;
import java.util.List;

public class WaveBuildController {

    private static char CHAR_FOR[] = new char[] {'#', '@', '%' };

    public static List<Enemy> BUILD_ENEMY_LIST(int difficulty) {
        List<Enemy> enemyList = new ArrayList<Enemy>();
        int count = 0;
        float y = 800;
        float x = 100;
        while(count < difficulty) {
            int smarts = Math.RANDOM_BETWEEN(Settings.MIN_ENEMY_SMARTS, Settings.MAX_ENEMY_SMARTS);
            float speed = Math.FLOAT_RANDOM_BETWEEN(Settings.MIN_ENEMY_SPEED, Settings.MAX_ENEMY_SPEED);
            count += smarts + Math.ROUND(speed / 2);
            enemyList.add(new Enemy(new Vector2(x, y), speed, Settings.ENEMY_DECEL, smarts, CHAR_FOR[smarts - 1]));
            x += 10;
        }
        return enemyList;
    }
}
