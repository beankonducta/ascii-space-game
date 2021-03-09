package com.patrick.game.entity;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.patrick.game.util.Settings;

public class Enemy extends Entity {

    protected int smarts;
    protected int direction;
    protected int points;

    protected float flipTimer;
    protected float flipTimerMax;

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return this.direction;
    }

    public void flipDirection() {
        if (this.flipTimer == 0)
            this.direction = -this.direction;
    }

    public int getPoints() {
        return this.points;
    }

    @Override
    public void setXVelocity(float velocity) {
        this.xVelocity = velocity * this.direction;
    }

    public void setSmarts(int smarts) {
        this.smarts = smarts;
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
        this.flipTimerMax = .5f;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        this.flipTimer += delta;
        if (this.flipTimer >= this.flipTimerMax)
            this.flipTimer = 0;
    }
}
