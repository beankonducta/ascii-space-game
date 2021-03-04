package com.patrick.game.entity;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.patrick.game.util.Math;
import com.patrick.game.util.Settings;

public class Bullet extends Entity {

    public Bullet(Vector2 position, float speed, float decel, char character, boolean spread) {
        super(position, speed, decel, character);
        this.collider = new Rectangle(position.x, position.y, Settings.BULLET_SIZE, Settings.BULLET_SIZE);
        this.setYVelocity(speed);
        if(spread)
            this.setXVelocity(Math.EITHER_OR((int)Math.FLOAT_RANDOM_BETWEEN(-speed / 3, 0), (int)Math.FLOAT_RANDOM_BETWEEN(1, speed / 3)));
    }

    @Override
    public void update(float delta) {
        // don't call super update because we don't want to lower the velocity
    }
}