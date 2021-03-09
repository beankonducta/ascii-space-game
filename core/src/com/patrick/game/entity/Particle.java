package com.patrick.game.entity;

import com.patrick.game.util.Math;

public class Particle extends Entity {

    protected float timeToLive;

    public float getTimeToLive() {
        return this.timeToLive;
    }

    public Particle(float x, float y, float speed, float decel, char character, float timeToLive) {
        super(x, y, speed, decel, character);
        this.timeToLive = timeToLive;
    }

    public void randomDir() {
        this.setYVelocity(Math.eitherOr(-(int)this.speed, (int)this.speed));
        this.setXVelocity(Math.eitherOr(-(int)this.speed, (int)this.speed));
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        this.timeToLive -= delta;
    }
}
