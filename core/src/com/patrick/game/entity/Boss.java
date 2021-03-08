package com.patrick.game.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.patrick.game.util.Settings;
import org.w3c.dom.css.Rect;

public class Boss extends Enemy {

    private char[][] shape;
    private Rectangle[][] colliders;

    private int yDirection;
    private float health;
    private boolean entered;

    public Rectangle[][] getColliders() {
        return this.colliders;
    }

    public void flipYDirection() {
        this.yDirection = -this.yDirection;
    }

    public boolean hasEntered() {
        return this.entered;
    }

    public float getHealth() {
        return this.health;
    }

    public void removeHealth() {
        this.health -= 1;
    }

    public void removeCharAt(int x, int y) {
        this.shape[x][y] = ' ';
        this.colliders[x][y] = null;
    }

    @Override
    public void setYVelocity(float velocity) {
        this.yVelocity = velocity * this.yDirection;
    }

    public Boss(float x, float y, float speed, float decel, int smarts, char[][] shape) {
        super(x, y, speed, decel, smarts, ' ');
        this.shape = shape;
        this.colliders = new Rectangle[shape.length][shape[0].length];
        this.actionTime = 3f;
        this.yDirection = -1;
        this.entered = false;
        this.collider = new Rectangle(x, y, 6 * shape[0].length, 6 * shape.length);
        for (int i = 0; i < this.colliders.length; i++) {
            for (int j = 0; j < this.colliders[0].length; j++) {
                if (shape[i][j] != ' ') {
                    this.health ++;
                    this.colliders[i][j] = new Rectangle(x + (i * 6), y + (j * 6), 6, 6);
                }
            }
        }
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (this.y() < 500 && !this.entered)
            this.entered = true;
        for (int i = 0; i < this.colliders.length; i++) {
            for (int j = 0; j < this.colliders[i].length; j++) {
                if (this.colliders[i][j] != null)
                    this.colliders[i][j].setPosition(new Vector2(this.x() + (j * 8), this.y() + (i * 8)));
            }
        }
    }

    @Override
    public void render(BitmapFont font, Batch batch) {
        if (this.shape == null) return;
        for (int i = 0; i < this.shape.length; i++) {
            for (int j = 0; j < this.shape[i].length; j++) {
                font.draw(batch, "" + shape[i][j], this.x() + (j * 8), this.y() + (i * 8));
            }
        }
        if (Settings.DEBUG_COLLISION && this.collider != null) {
            Color color = font.getColor();
            font.setColor(Color.RED);
            for (int i = 0; i < this.colliders.length; i++) {
                for (int j = 0; j < this.colliders[i].length; j++) {
                    if (this.colliders[i][j] != null)
                        font.draw(batch, "c", this.x() + (j * 8), this.y() + (i * 8));
                }
            }
            font.setColor(color);
        }
    }
}