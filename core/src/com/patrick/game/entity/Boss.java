package com.patrick.game.entity;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Boss extends Enemy {

    private char[][] shape;
    private Rectangle[][] colliders;

    private int yDirection;
    private float health;
    private float startingHealth;
    private boolean entered;

    public Rectangle[][] getColliders() {
        return this.colliders;
    }

    public void flipYDirection() {
        this.yDirection = -this.yDirection;
    }

    public void setYDirection(int yDirection) {
        this.yDirection = yDirection;
    }

    public boolean hasEntered() {
        return this.entered;
    }

    public void removeHealth() {
        this.health -= 1;
    }

    public float width() {
        return this.colliders.length * 6;
    }

    public float height() {
        return this.colliders[0].length * 6;
    }

    public void removeCharAt(int x, int y) {
        this.shape[x][y] = ' ';
        this.colliders[x][y] = null;
    }

    public boolean dead() {
        return this.health <= startingHealth * .2f;
    }

    @Override
    public void setYVelocity(float velocity) {
        this.yVelocity = velocity * this.yDirection;
    }

    public Boss(float x, float y, float speed, float decel, int smarts, char[][] shape) {
        super(x, y, speed, decel, smarts, ' ');
        this.shape = shape;
        this.colliders = new Rectangle[shape.length][shape[0].length];
        this.actionTime = .25f;
        this.yDirection = -1;
        this.entered = false;
        this.collider = new Rectangle(x, y, 6 * shape[0].length, 6 * shape.length);
        for (int i = 0; i < this.colliders.length; i++) {
            for (int j = 0; j < this.colliders[0].length; j++) {
                if (shape[i][j] != ' ') {
                    this.health++;
                    this.colliders[i][j] = new Rectangle(x + (i * 8), y + (j * 8), 8, 8);
                }
            }
        }
        this.startingHealth = health;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (this.y() < 500 && !this.entered)
            this.entered = true;
        for (int i = 0; i < this.colliders.length; i++) {
            for (int j = 0; j < this.colliders[i].length; j++) {
                if (this.colliders[i][j] != null)
                    this.colliders[i][j].setPosition(new Vector2(this.x() + (j * 8), this.y() - (i * 8)));
            }
        }
    }

    @Override
    public void render(BitmapFont font, Batch batch) {
        if (this.shape == null) return;
        for (int i = 0; i < this.shape.length; i++)
            for (int j = 0; j < this.shape[i].length; j++)
                font.draw(batch, "" + shape[i][j], this.x() + (j * 8), this.y() - (i * 8));
    }
}