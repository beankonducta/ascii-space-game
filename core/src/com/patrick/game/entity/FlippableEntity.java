package com.patrick.game.entity;

public class FlippableEntity extends Entity {

    protected int direction;
    protected float flipTimer;
    protected float flipTimerMax;

    public void setDirection(int direction) {
        if (this.flipTimer == 0)
            this.direction = direction;
    }

    public int getDirection() {
        return this.direction;
    }

    public void flipDirection() {
        if (this.flipTimer == 0)
            this.direction = -this.direction;
    }

    @Override
    public void setXVelocity(float velocity) {
        this.xVelocity = velocity * this.direction;
    }

    public FlippableEntity(float x, float y, float speed, float decel, char character) {
        super(x, y, speed, decel, character);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        this.flipTimer += delta;
        if (this.flipTimer >= this.flipTimerMax)
            this.flipTimer = 0;
    }
}
