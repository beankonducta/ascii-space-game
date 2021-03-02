package com.patrick.game.entity;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.patrick.game.util.Settings;

public class Enemy extends Entity {

    protected int smarts;
    protected int direction;

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return this.direction;
    }

    public void flipDirection() {
        this.direction = -this.direction;
    }

    @Override
    public void setXVelocity(float velocity) {
        this.velocity.x = velocity * this.direction;
    }

    public void setSmarts(int smarts) {
        this.smarts = smarts;
    }

    public int getSmarts() {
        return this.smarts;
    }

    public Enemy(Vector2 position, float speed, float decel, int smarts, char character) {
        super(position, speed, decel, character);
        this.smarts = smarts;
        this.direction = smarts == 1 ? 1 : -1;
        this.collider = new Rectangle(position.x, position.y, Settings.ENEMY_WIDTH, Settings.ENEMY_HEIGHT);
    }
}
