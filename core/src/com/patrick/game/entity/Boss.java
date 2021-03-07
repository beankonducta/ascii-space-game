package com.patrick.game.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.patrick.game.util.Settings;

public class Boss extends Enemy {

    private char[][] shape;

    private int yDirection;
    private float health;
    private boolean entered;

    public void flipYDirection() {
        this.yDirection = -this.yDirection;
    }

    public boolean hasEntered() {
        return this.entered;
    }

    public float getHealth() {
        return this.health;
    }

    public void removeHealth(float amount) {
        this.health -= amount;
    }

    @Override
    public void setYVelocity(float velocity) {
        this.velocity.y = velocity * this.yDirection;
    }

    public Boss(Vector2 position, float speed, float decel, int smarts, char[][] shape, float health) {
        super(position, speed, decel, smarts, ' ');
        this.shape = shape;
        this.actionTime = 3f;
        this.yDirection = -1;
        this.entered = false;
        this.health = health;
        this.collider = new Rectangle(position.x, position.y, 6 * shape[0].length, 6 * shape.length);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (this.y() < 500 && !this.entered)
            this.entered = true;
    }

    @Override
    public void render(BitmapFont font, Batch batch) {
        if (this.shape == null) return;
        for (int i = 0; i < this.shape.length; i++) {
            for (int j = 0; j < this.shape[i].length; j++) {
                font.draw(batch, "" + shape[i][j], this.x() + (j * 8), this.y() + (i * 8));
            }
        }
        if(Settings.DEBUG_COLLISION && this.collider != null) {
            Color color = font.getColor();
            font.setColor(Color.RED);
            font.draw(batch, "c", this.collider.x, this.collider.y);
            font.draw(batch, "c", this.collider.x + this.collider.width, this.collider.y + this.collider.height);
            font.setColor(color);
        }
    }
}