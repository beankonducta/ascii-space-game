package com.patrick.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.patrick.game.entity.Enemy;
import com.patrick.game.entity.Player;
import com.patrick.game.util.Settings;

public class MovementController {

    public static void processPlayerMovement(Player player) {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
        }
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
            player.setXVelocity(-player.getSpeed());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.setXVelocity(player.getSpeed());
        }
    }

    /**
     * A very simple dumb enemy movement method. If enemy smarts are 1 or 2, move them in a constant direction left or right.
     * Flip the enemy if it hits the side.
     *
     * If the enemy smarts are 3, follow the player.
     *
     * @param enemy - the enemy to move
     * @param player - the player to position the enemy to
     * @param width - the viewport width
     */
    public static void processEnemyMovement(Enemy enemy, Player player, float width) {
        final float difference = enemy.getPosition().x - player.getPosition().x;
        if (enemy.getSmarts() == 3 && enemy.getPosition().y > player.getPosition().y) enemy.setDirection(difference > 0 ? -1 : 1);
        if (enemy.getPosition().x < 0 || enemy.getPosition().x > width) enemy.flipDirection();
        if (Math.abs(difference) >= Settings.PLAYER_ENEMY_X_OFFSET || enemy.getSmarts() < 3)
            enemy.setXVelocity(enemy.getSpeed());
        else
            enemy.setXVelocity(0);
        enemy.setYVelocity(-enemy.getSpeed());
    }
}