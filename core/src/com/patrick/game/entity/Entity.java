package com.patrick.game.entity;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.patrick.game.util.Settings;

import java.awt.*;

public class Entity {

    private Vector2 position;
    private Vector2 velocity;

    private float speed;
    private char character;

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public Vector2 getPosition() {
        return this.position;
    }

    public Vector2 getVelocity() {
        return this.velocity;
    }

    public float getSpeed() {
        return this.speed;
    }

    public char getCharacter() {
        return this.character;
    }

    public void move(float velocity) {
        // add velocity to entity
        this.position.add(this.velocity);
    }

    public void update(float delta) {
        // handle x velocity
        if(Math.abs(this.velocity.x) <= Settings.MIN_VELO) this.velocity.x = 0;
        else this.velocity.x += this.speed * (this.velocity.x / Math.abs(this.velocity.x));

        // handle y velocity
        if(Math.abs(this.velocity.y) <= Settings.MIN_VELO) this.velocity.y = 0;
        else this.velocity.y += this.speed * (this.velocity.y / Math.abs(this.velocity.y));
    }

    public void render(BitmapFont font, Batch batch) {
        // draw the character
        font.draw(batch, ""+this.character, this.position.x, this.position.y);
    }
}
