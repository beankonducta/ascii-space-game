package com.patrick.game.entity;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Entity {

    protected float x;
    protected float y;
    protected float xVelocity;
    protected float yVelocity;
    protected float speed;
    protected float decel;
    protected char character;
    protected Rectangle collider;
    protected float timer;
    protected float actionTime;

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setXVelocity(float velocity) {
        this.xVelocity = velocity;
    }

    public void setYVelocity(float velocity) {
        this.yVelocity = velocity;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setDecel(float decel) {
        this.decel = decel;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public void setCollider(Rectangle collider) {
        this.collider = collider;
    }

    public void setTimer(float time) {
        this.timer = time;
    }

    public void setActionTime(float time) {
        this.actionTime = time;
    }

    public float getSpeed() {
        return this.speed;
    }

    public float getDecel() {
        return this.decel;
    }

    public float x() {
        return this.x;
    }

    public float y() {
        return this.y;
    }

    public char getCharacter() {
        return this.character;
    }

    public Rectangle getCollider() {
        return this.collider;
    }

    public float getTimer() {
        return this.timer;
    }

    public float getActionTime() {
        return this.actionTime;
    }

    public Entity(float x, float y, float speed, float decel, char character) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.decel = decel;
        this.character = character;
        this.xVelocity = 0;
        this.yVelocity = 0;
    }

    public void update(float delta) {
        // handle x velocity
        if (Math.abs(this.xVelocity) <= 1) this.xVelocity = 0;
        else this.xVelocity -= this.decel * (this.xVelocity / Math.abs(this.xVelocity) * delta);

        // handle y velocity
        if (Math.abs(this.yVelocity) <= 1) this.yVelocity = 0;
        else this.yVelocity -= this.decel * (this.yVelocity / Math.abs(this.yVelocity) * delta);

        // update timer
        this.timer += delta;
        if(this.timer >= this.actionTime)
            this.timer = 0;
    }

    public void move(float delta) {
        // add velocity
        this.x += this.xVelocity * delta;
        this.y += this.yVelocity * delta;

        // move collider
        if (this.collider != null)
            this.collider.setPosition(new Vector2(this.x, this.y));
    }

    public void render(BitmapFont font, Batch batch) {
        // draw the character
        font.draw(batch, "" + this.character, this.x, this.y);
    }
}
