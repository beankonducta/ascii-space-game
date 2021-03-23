package com.patrick.game.entity;

import com.badlogic.gdx.math.Rectangle;
import com.patrick.game.util.Settings;

public class Enemy extends FlippableEntity {

    protected int smarts;
    protected int points;

    public int getPoints() {
        return this.points;
    }

    public int getSmarts() {
        return this.smarts;
    }

    public Enemy(float x, float y, float speed, float decel, int smarts, char character) {
        super(x, y, speed, decel, character);
        this.smarts = smarts;
        this.direction = smarts == 1 ? 1 : -1;
        this.collider = new Rectangle(x, y, Settings.ENEMY_WIDTH, Settings.ENEMY_HEIGHT);
        this.actionTime = 1f;
        this.points = (this.smarts + 1 * 100);
        this.flipTimerMax = .1f;
    }
}
