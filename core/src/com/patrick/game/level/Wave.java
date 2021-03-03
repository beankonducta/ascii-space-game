package com.patrick.game.level;

import com.patrick.game.controller.WaveBuildController;
import com.patrick.game.entity.Enemy;
import com.patrick.game.entity.Entity;

import java.util.List;

public class Wave {

    private int difficulty;
    private List<Enemy> enemies;

    public List<Enemy> getEnemies() {
        return this.enemies;
    }

    public int getDifficulty() {
        return this.difficulty;
    }

    public Wave(int difficulty) {
        this.difficulty = difficulty;
        this.enemies = WaveBuildController.BUILD_ENEMY_LIST(difficulty);
    }

    public void removeEnemy(Entity enemy) {
        this.enemies.remove(enemy);
    }
}
