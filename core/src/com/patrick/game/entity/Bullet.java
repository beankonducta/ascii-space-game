package com.patrick.game.entity;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.patrick.game.util.Settings;

public class Bullet extends Entity {

    public Bullet(Vector2 position, float speed, float decel, char character) {
        super(position, speed, decel, character);
        this.collider = new Rectangle(position.x, position.y, Settings.BULLET_SIZE, Settings.BULLET_SIZE);
        this.setYVelocity(speed);
    }

    @Override
    public void update(float delta) {
        // don't call super update because we don't want to lower the velocity
    }
}