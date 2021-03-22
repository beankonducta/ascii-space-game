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

        // process movement keys
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                player.setYVelocity(player.getSpeed());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                player.setYVelocity(-player.getSpeed());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                player.setXVelocity(-player.getSpeed());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                player.setXVelocity(player.getSpeed());
        }

        // process action key, return true so we can perform an action elsewhere
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            return true;
        }

        // action key held, return true so we can perform an action elsewhere
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && player.getGunLevel() >= 2) {
            return true;
        }
        return false;
    }

    public static boolean processEnemyMovement(Enemy enemy, Player player) {
        // horizontal distance between enemy and player
        final float difference = enemy.x() - player.x();

        // player in enemy sights
        final boolean enemySeesPlayer = enemy.getSmarts() >= 3 && enemy.y() > player.y() && Math.abs(enemy.x() - player.x()) < 50 && enemy.y() - player.y() < 200;

        // flips enemy if it's on the horizontal edge of the screen
        if (enemy.x() < 30 || enemy.x() > CameraController.camera.viewportWidth - 30) enemy.flipDirection();

            // moves enemy towards player, only if it's not flipping on the edge of the screen already
        else if (enemySeesPlayer && enemy.x() > 31 && enemy.x() < CameraController.camera.viewportWidth - 31)
            enemy.setDirection(difference > 0 ? -1 : 1);

        // sets enemy x velo to move towards player if they're smart enough
        if (java.lang.Math.abs(difference) >= Settings.PLAYER_FLIPPABLE_ENTITY_X_OFFSET || enemy.getSmarts() < 3)
            enemy.setXVelocity(enemy.getSpeed());

            // makes enemy go straight down
            // this is kinda glitchy because it makes the enemy drop 'straight down' to the player
        else
            enemy.setXVelocity(0);

        // sets enemy y velocity. increases speed if they see the player
        enemy.setYVelocity(-enemy.getSpeed() - (enemySeesPlayer ? 200 : 0));

        // loops enemy around the screen vertically if they go off screen
        if (enemy.y() < 0) enemy.setPosition(enemy.x(), CameraController.camera.viewportHeight);

        // returns true (we use this to trigger a bullet)
        if (enemy.getTimer() == 0 && enemy.getSmarts() > 3 && Math.abs(player.x() - enemy.x()) < 50) {
            return true;
        }
        return false;
    }

    public static boolean processResourceMovement(Player player, Resource resource) {
        // horizontal distance between resource and player
        final float difference = resource.x() - player.x();

        // is the resource close to the player?
        final boolean resourceInRange = resource.y() > player.y() && resource.y() - player.y() < 250;

        // stops resource from going off screen horizontally
        if (resource.x() < 30 || resource.x() > CameraController.camera.viewportWidth - 45) {
            resource.setXVelocity(0);
            return false;
        }

        // resource is off screen vertically, return true so we know to remove
        if (resource.y() < -20) return true;

        // moves the resource towards the player
        if (resourceInRange && java.lang.Math.abs(difference) >= Settings.PLAYER_FLIPPABLE_ENTITY_X_OFFSET) {
            // changes resource direction based on position of player
            resource.setDirection(difference > 0 ? -1 : 1);

            // resource isn't above player, adjust position
            resource.setXVelocity(Settings.RESOURCE_SPEED / 4);
        } else
            resource.setXVelocity(0);
        return false;
    }

    public static boolean processBossMovement(Boss boss, Player player) {
        // keeps the boss on screen horizontally
        if (boss.x() + boss.width() < 140 || boss.x() > CameraController.camera.viewportWidth - 24)
            boss.flipDirection();

        // keeps the boss on screen vertically
        if ((boss.y() < 80 || boss.y() - (boss.height() * 3) > CameraController.camera.viewportHeight - 24))
            boss.flipYDirection();

        // set the boss velocity
        boss.setXVelocity(boss.getSpeed());
        boss.setYVelocity(boss.getSpeed());

        // triggers the boss to fire a bullet elsewhere
        if (boss.getTimer() == 0) return true;
        return false;
    }
}
