package com.patrick.game.level;

import com.patrick.game.controller.CameraController;
import com.patrick.game.controller.WaveBuildController;
import com.patrick.game.entity.Boss;
import com.patrick.game.entity.Enemy;
import com.patrick.game.entity.Entity;
import com.patrick.game.util.Ascii;
import com.patrick.game.util.Math;
import com.patrick.game.util.Settings;

import java.util.ArrayList;
import java.util.List;

public class Wave {

    private int difficulty;
    private List<Enemy> enemies;

    public List<Enemy> getEnemies() {
        return this.enemies;
    }

    public Wave(int difficulty) {
        this.difficulty = difficulty;
        this.enemies = WaveBuildController.buildEnemyList(difficulty);
    }

    public Wave(boolean bossOnly, int difficulty) {
        if (bossOnly) {
            int size = difficulty / Settings.INITIAL_DIFFICULTY;
            this.enemies = new ArrayList<>();
            if(size > 5) size = 5;
            Boss boss = new Boss((CameraController.camera.viewportWidth / 2) - Math.randomBetween(-25, 25), (CameraController.camera.viewportHeight - 48) - Math.randomBetween(-25, 25), 50f, 25f, difficulty, Ascii.scimitar());
            boss.setDirection(Math.eitherOr(-1, 1));
            boss.setYDirection(Math.eitherOr(-1, 1));
            this.enemies.add(boss);

        } else return;
    }

    public void removeEnemy(Entity enemy) {
        this.enemies.remove(enemy);
    }
}
