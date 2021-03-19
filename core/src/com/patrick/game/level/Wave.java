package com.patrick.game.level;

import com.patrick.game.controller.CameraController;
import com.patrick.game.controller.WaveBuildController;
import com.patrick.game.entity.Boss;
import com.patrick.game.entity.Enemy;
import com.patrick.game.entity.Entity;
import com.patrick.game.util.Ascii;
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
            this.enemies = new ArrayList<>();
            this.enemies.add(new Boss(CameraController.camera.viewportWidth / 2, CameraController.camera.viewportHeight - 48, 50f, 25f, difficulty, Ascii.coolS((difficulty / Settings.INITIAL_DIFFICULTY) - 1)));

        } else return;
    }

    public void removeEnemy(Entity enemy) {
        this.enemies.remove(enemy);
    }
}
