package com.patrick.game.entity;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.patrick.game.util.Settings;

import java.awt.*;

public class Player extends Entity {

    protected int gunLevel;
    protected int lives;
    protected int bulletCooldown;
    protected int points;
    protected float shieldTimer;
    protected boolean shield;

    public int getBulletCooldown() {
        return this.bulletCooldown;
    }

    public void addBulletCooldown() {
        if(this.bulletCooldown < Settings.BULLET_COOLDOWN) this.bulletCooldown ++;
    }

    public boolean getShield() {
        return this.shield;
    }

    public void addShield() {
        this.shield = true;
        this.shieldTimer = 0f;
    }

    public int getGunLevel() {
        return this.gunLevel;
    }

    public void setGunLevel(int gunLevel) {
        this.gunLevel = gunLevel;
    }

    public int getLives() {
        return this.lives;
    }

    public void removeLife() {
        this.lives --;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public void subtractPoints(int points) {
        this.points -= points;
    }

    public int getPoints() {
        return this.points;
    }

    public void processResource(Resource resource) {
        if(resource.getType() == Resource.ResourceType.GUN) this.gunLevel += resource.getLevel();
        else if(resource.getType() == Resource.ResourceType.LIFE) this.lives += resource.getLevel();
    }

    public Player(float x, float y, float speed, float decel, char character) {
        super(x, y, speed, decel, character);
        this.collider = new Rectangle(x, y, Settings.PLAYER_WIDTH, Settings.PLAYER_HEIGHT);
        this.gunLevel = 0;
        this.lives = 3;
        this.actionTime = .15f;
        this.points = 0;
    }

    // reset the player after death
    public void killPlayer() {
        this.gunLevel = 0;
    }

    @Override
    public void render(BitmapFont font, Batch batch) {
        super.render(font, batch);
        if(this.shield) {
            for(int i = 0; i < 32; i += 4) {
                font.draw(batch, "-", this.x() - 8 + i, this.y() + 14);
            }
        }
    }

    @Override
    public void update(float delta) {

        // process bullet cooldown
        if(this.timer == 0 && this.bulletCooldown > 0)
            this.bulletCooldown --;

        // process shield
        if(this.shield)
            this.shieldTimer += delta;
        if(this.shieldTimer > 5f)
            this.shield = false;

        super.update(delta);
    }
}
