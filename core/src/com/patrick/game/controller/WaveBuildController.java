package com.patrick.game.controller;

import com.patrick.game.entity.Enemy;
import com.patrick.game.util.Math;
import com.patrick.game.util.Settings;

import java.util.ArrayList;
import java.util.List;

public class WaveBuildController {

    private static final char CHAR_FOR[] = new char[]{'i', 'e', 'E', '@', '?'};

    public static List<Enemy> buildEnemyList(int difficulty) {
        List<Enemy> enemyList = new ArrayList<Enemy>();
        int count = 0;
        while (count < difficulty && count / Settings.INITIAL_DIFFICULTY <= Settings.MAX_ENEMIES) {
            final float y = Math.randomBetween((int) CameraController.camera.viewportHeight, (int) (CameraController.camera.viewportHeight * 1.5f));
            final float x = Math.randomBetween(50, (int)(CameraController.camera.viewportWidth - 50));
            final int smarts = Math.randomBetween(Settings.MIN_ENEMY_SMARTS, Settings.MAX_ENEMY_SMARTS);
            final float speed = Math.floatRandomBetween(Settings.MIN_ENEMY_SPEED, Settings.MAX_ENEMY_SPEED);
            count += smarts + Math.round(speed / 2);
            enemyList.add(new Enemy(x, y, speed, Settings.ENEMY_DECEL, smarts, CHAR_FOR[smarts - 1]));
        }
        return enemyList;
    }
}
