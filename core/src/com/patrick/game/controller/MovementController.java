package com.patrick.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.patrick.game.entity.Enemy;
import com.patrick.game.entity.Player;
import com.patrick.game.util.Settings;

public class MovementController {

    public static boolean processPlayerMovement(Player player) {
        // keeps player in bounds
        if (player.getPosition().x < 10) player.setXVelocity(0);
        if (player.getPosition().x > CameraController.camera.viewportWidth - 24) player.setXVelocity(0);

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            if (player.getPosition().y > Settings.PLAYER_MAX_HEIGHT) player.setYVelocity(0);
            else
                player.setYVelocity(player.getSpeed());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            if (player.getPosition().y < Settings.PLAYER_MIN_HEIGHT) player.setYVelocity(0);
            else
                player.setYVelocity(-player.getSpeed());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if (player.getPosition().x < 10) player.setXVelocity(0);
            else
                player.setXVelocity(-player.getSpeed());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if (player.getPosition().x > CameraController.camera.viewportWidth - 24) player.setXVelocity(0);
            else
                player.setXVelocity(player.getSpeed());
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            return true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && player.getGunLevel() == 2) {
            return true;
        }
        return false;
    }

    /**
     * A very simple dumb enemy movement method. If enemy smarts are 1 or 2, move them in a constant direction left or right.
     * Flip the enemy if it hits the side.
     * <p>
     * If the enemy smarts are >= 3, follow the player.
     *
     * If the enemy smarts are > 3, return true (which fires a bullet elsewhere!)
     *
     * @param enemy  - the enemy to move
     * @param player - the player to position the enemy to
     * @param width  - the viewport width
     */
    public static boolean processEnemyMovement(Enemy enemy, Player player, float width) {
        final float difference = enemy.x() - player.x();
        final boolean enemySeesPlayer = enemy.getSmarts() >= 3 && enemy.y() > player.y() && enemy.x() - player.x() < 50 && enemy.y() - player.y() < 200;
        if (enemySeesPlayer)
            enemy.setDirection(difference > 0 ? -1 : 1);
        if (enemy.x() < 0 || enemy.x() > width) enemy.flipDirection();
        if (Math.abs(difference) >= Settings.PLAYER_ENEMY_X_OFFSET || enemy.getSmarts() < 3)
            enemy.setXVelocity(enemy.getSpeed());
        else
            enemy.setXVelocity(0);
        enemy.setYVelocity(-enemy.getSpeed() - (enemySeesPlayer ? 200 : 0));
        if (enemy.y() < 0) enemy.setPosition(new Vector2(enemy.x(), 700));
        if(enemy.getTimer() >= 3 && enemy.getSmarts() > 3) {
            return true;
        }
        return false;
    }
}
