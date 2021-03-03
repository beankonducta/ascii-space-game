package com.patrick.game.entity;

import com.badlogic.gdx.math.Vector2;

public class Particle extends Entity {

    protected float timeToLive;

    public float getTimeToLive() {
        return this.timeToLive;
    }
    public Particle(Vector2 position, float speed, float decel, char character, float timeToLive) {
        super(position, speed, decel, character);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        this.timeToLive -= delta;
    }
}
