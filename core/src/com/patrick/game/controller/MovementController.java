package com.patrick.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.patrick.game.entity.Boss;
import com.patrick.game.entity.Enemy;
import com.patrick.game.entity.Player;
import com.patrick.game.entity.Resource;
import com.patrick.game.util.Settings;

public class MovementController {

    public static boolean processPlayerMovement(Player player) {
        // keeps player in bounds
        if (player.x() < Settings.PLAYER_MIN_X) player.setXVelocity(0);
        if (player.x() > CameraController.camera.viewportWidth - Settings.PLAYER_MAX_X) player.setXVelocity(0);
        if (player.y() < Settings.PLAYER_MIN_HEIGHT) player.setYVelocity(0);
        if (player.y() > Settings.PLAYER_MAX_HEIGHT) player.setYVelocity(0);

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            if (player.y() > Settings.PLAYER_MAX_HEIGHT) player.setYVelocity(0);
            else
                player.setYVelocity(player.getSpeed());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            if (player.y() < Settings.PLAYER_MIN_HEIGHT) player.setYVelocity(0);
            else
                player.setYVelocity(-player.getSpeed());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if (player.x() < Settings.PLAYER_MIN_X) player.setXVelocity(0);
            else
                player.setXVelocity(-player.getSpeed());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if (player.x() > CameraController.camera.viewportWidth - Settings.PLAYER_MAX_X) player.setXVelocity(0);
            else
                player.setXVelocity(player.getSpeed());
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            return true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && player.getGunLevel() >= 2) {
            return true;
        }
        return false;
    }

    public static boolean processEnemyMovement(Enemy enemy, Player player) {
        final float difference = enemy.x() - player.x();
        final boolean enemySeesPlayer = enemy.getSmarts() >= 3 && enemy.y() > player.y() && enemy.x() - player.x() < 50 && enemy.y() - player.y() < 200;
        if (enemySeesPlayer && enemy.x() > 31 && enemy.x() < CameraController.camera.viewportWidth - 31)
            enemy.setDirection(difference > 0 ? -1 : 1);
        if (enemy.x() < 30 || enemy.x() > CameraController.camera.viewportWidth - 30) enemy.flipDirection();
        if (java.lang.Math.abs(difference) >= Settings.PLAYER_ENEMY_X_OFFSET || enemy.getSmarts() < 3)
            enemy.setXVelocity(enemy.getSpeed());
        else
            enemy.setXVelocity(0);
        enemy.setYVelocity(-enemy.getSpeed() - (enemySeesPlayer ? 200 : 0));
        if (enemy.y() < 0) enemy.setPosition(enemy.x(), CameraController.camera.viewportHeight);
        if (enemy.getTimer() == 0 && enemy.getSmarts() > 3 && Math.abs(player.x() - enemy.x()) < 50) {
            return true;
        }
        return false;
    }

    public static boolean processResourceMovement(Player player, Resource resource) {
        final boolean resourceInRange = resource.y() > player.y() && resource.y() - player.y() < 150;
        if (resource.x() < 10 || resource.x() > CameraController.camera.viewportWidth - 24) {
            resource.setXVelocity(0);
            return false;
        }
        if (resource.y() < -20) return true;
        if (resourceInRange) {
            resource.setXVelocity((resource.x() > player.x() ? -Settings.RESOURCE_SPEED / 2 : Settings.RESOURCE_SPEED / 2));
        }
        return false;
    }

    public static boolean processBossMovement(Boss boss, Player player) {
        if (boss.x() < 30 || boss.x() > CameraController.camera.viewportWidth - 30) boss.flipDirection();
        if ((boss.y() < 30 || boss.y() > CameraController.camera.viewportHeight - 30) && boss.hasEntered()) boss.flipYDirection();
        boss.setXVelocity(boss.getSpeed());
        boss.setYVelocity(boss.getSpeed());
        if(boss.getTimer() == 0) return true;
        return false;
    }
}
