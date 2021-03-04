package com.patrick.game.entity;

import com.badlogic.gdx.math.Vector2;
import com.patrick.game.util.Math;

public class Particle extends Entity {

    protected float timeToLive;

    public float getTimeToLive() {
        return this.timeToLive;
    }

    public Particle(Vector2 position, float speed, float decel, char character, float timeToLive) {
        super(position, speed, decel, character);
        this.timeToLive = timeToLive;
    }

    public void randomDir() {
        this.setYVelocity(Math.EITHER_OR(-(int)this.speed, (int)this.speed));
        this.setXVelocity(Math.EITHER_OR(-(int)this.speed, (int)this.speed));
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        this.timeToLive -= delta;
    }
}
