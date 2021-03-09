package com.patrick.game.controller;

import com.badlogic.gdx.math.Vector2;
import com.patrick.game.entity.Enemy;
import com.patrick.game.util.Math;
import com.patrick.game.util.Settings;

import java.util.ArrayList;
import java.util.List;

public class WaveBuildController {

    private static char CHAR_FOR[] = new char[]{'i', 'e', 'E', '@', '?'};

    public static List<Enemy> BUILD_ENEMY_LIST(int difficulty) {
        List<Enemy> enemyList = new ArrayList<Enemy>();
        int count = 0;
        float max = CameraController.camera.viewportWidth - 50f;
        while (count < difficulty) {
            float y = Math.RANDOM_BETWEEN((int) CameraController.camera.viewportHeight, (int) (CameraController.camera.viewportHeight * 1.5f));
            float x = Math.RANDOM_BETWEEN(50, (int)(CameraController.camera.viewportWidth - 50));
            int smarts = Math.RANDOM_BETWEEN(Settings.MIN_ENEMY_SMARTS, Settings.MAX_ENEMY_SMARTS);
            float speed = Math.FLOAT_RANDOM_BETWEEN(Settings.MIN_ENEMY_SPEED, Settings.MAX_ENEMY_SPEED);
            count += smarts + Math.ROUND(speed / 2);
            enemyList.add(new Enemy(x, y, speed, Settings.ENEMY_DECEL, smarts, CHAR_FOR[smarts - 1]));
        }
        return enemyList;
    }
}
