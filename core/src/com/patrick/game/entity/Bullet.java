package com.patrick.game.entity;

import com.badlogic.gdx.math.Rectangle;
import com.patrick.game.util.Math;
import com.patrick.game.util.Settings;

public class Bullet extends Entity {

    public enum BulletOwner {
        PLAYER, ENEMY;
    }

    private BulletOwner owner;

    public BulletOwner getOwner() {
        return this.owner;
    }

    public Bullet(float x, float y, float speed, float decel, char character, boolean spread, BulletOwner owner) {
        super(x, y, speed, decel, character);
        this.collider = new Rectangle(x, y, Settings.BULLET_SIZE, Settings.BULLET_SIZE);
        this.owner = owner;
        this.setYVelocity(speed);
        if(spread)
            this.setXVelocity(Math.eitherOr((int)Math.floatRandomBetween(-speed / 3, 0), (int)Math.floatRandomBetween(1, speed / 3)));
    }

    public Bullet(float x, float y, char character, BulletOwner owner) {
        super(x, y, 0, 0, character);
        this.x = x;
        this.y = y;
        this.collider = new Rectangle(x, y, Settings.BULLET_SIZE, Settings.BULLET_SIZE);
        this.owner = owner;
    }

    @Override
    public void update(float delta) {
        // don't call super update because we don't want to lower the velocity
    }
}