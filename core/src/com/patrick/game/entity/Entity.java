package com.patrick.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.patrick.game.util.Settings;
import org.graalvm.compiler.word.Word;
import org.w3c.dom.css.Rect;

import java.awt.*;

public class Entity {

    protected Vector2 position;
    protected Vector2 velocity;
    protected float speed;
    protected float decel;
    protected char character;
    protected Rectangle collider;

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public void setXVelocity(float velocity) {
        this.velocity.x = velocity;
    }

    public void setYVelocity(float velocity) {
        this.velocity.y = velocity;
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

    public Vector2 getPosition() {
        return this.position;
    }

    public Vector2 getVelocity() {
        return this.velocity;
    }

    public float getSpeed() {
        return this.speed;
    }

    public float getDecel() {
        return this.decel;
    }

    public float x() {
        return this.position.x;
    }

    public float y() {
        return this.position.y;
    }

    public char getCharacter() {
        return this.character;
    }

    public Rectangle getCollider() {
        return this.collider;
    }

    public Entity(Vector2 position, float speed, float decel, char character) {
        this.position = position;
        this.speed = speed;
        this.decel = decel;
        this.character = character;
        this.velocity = new Vector2(0, 0);
    }

    public void move(float width) {
        // add velocity to entity
        this.position.add(this.velocity);
        if (this.collider != null)
            this.collider.setPosition(this.position);
    }

    public void update(float delta) {
        // handle x velocity
        if (Math.abs(this.velocity.x) <= this.decel) this.velocity.x = 0;
        else this.velocity.x -= this.decel * (this.velocity.x / Math.abs(this.velocity.x));

        // handle y velocity
        if (Math.abs(this.velocity.y) <= this.decel) this.velocity.y = 0;
        else this.velocity.y -= this.decel * (this.velocity.y / Math.abs(this.velocity.y));
    }

    public void render(BitmapFont font, Batch batch) {
        // draw the character
        font.draw(batch, "" + this.character, this.position.x, this.position.y);

        // debug collisions
        if(Settings.DEBUG_COLLISION && this.collider != null) {
            Color color = font.getColor();
            font.setColor(Color.RED);
            font.draw(batch, "^", this.collider.x, this.collider.y);
            font.setColor(color);
        }
    }
}
