package com.patrick.game.entity;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.patrick.game.controller.CameraController;
import com.patrick.game.util.Settings;

public class Player extends Entity {

    protected int gunLevel;
    protected int lives;
    protected int bulletCooldown;
    protected int points;
    protected float shieldTimer;
    protected float heightTimer;
    protected boolean shield;
    protected boolean dead;
    protected boolean heightIncrease;

    public boolean isDead() {
        return this.dead;
    }

    public int getBulletCooldown() {
        return this.bulletCooldown;
    }

    public void addBulletCooldown() {
        if (this.bulletCooldown < Settings.BULLET_COOLDOWN) this.bulletCooldown++;
    }

    public boolean getShield() {
        return this.shield;
    }

    public void addShield() {
        this.shield = true;
        this.shieldTimer = 0f;
    }

    public void addHeightIncrease() {
        this.heightIncrease = true;
        this.heightTimer = 0f;
    }

    public int getGunLevel() {
        return this.gunLevel;
    }

    public int getLives() {
        return this.lives;
    }

    public void removeLife() {
        this.lives--;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public int getPoints() {
        return this.points;
    }

    public void processResource(Resource resource) {
        if (resource.getType() == Resource.ResourceType.GUN) {
            this.gunLevel += resource.getLevel();
            this.bulletCooldown = 0;
        }
        else if (resource.getType() == Resource.ResourceType.LIFE) this.lives += resource.getLevel();
        else if (resource.getType() == Resource.ResourceType.SHIELD) this.addShield();
        else if (resource.getType() == Resource.ResourceType.HEIGHT) this.addHeightIncrease();
    }

    public Player(float x, float y, float speed, float decel, char character) {
        super(x, y, speed, decel, character);
        this.collider = new Rectangle(x, y, Settings.PLAYER_WIDTH, Settings.PLAYER_HEIGHT);
        this.gunLevel = 0;
        this.lives = 3;
        this.actionTime = .15f;
        this.points = 0;
    }

    // kill the player
    public void killPlayer() {
        if (this.x == -5000) return;
        this.gunLevel = 0;
        this.dead = true;
        this.setPosition(-5000, -5000);
        this.removeLife();
    }

    public void permaKillPlayer() {
        this.lives = 0;
        this.dead = true;
        this.gunLevel = 0;
        this.setPosition(-5000, -5000);
    }

    // reset the player after respawn
    public void resetPlayer() {
        this.setPosition(CameraController.camera.viewportWidth / 2, 40);
        this.addShield();
        this.dead = false;
    }


    @Override
    public void render(BitmapFont font, Batch batch) {
        super.render(font, batch);
        if (this.shield) {
            for (int i = 0; i < 32; i += 4) {
                font.draw(batch, "-", this.x() - 8 + i, this.y() + 14);
            }
        }
    }

    @Override
    public void move(float delta) {
        if (this.x == -5000) return;
        if (this.x <= Settings.PLAYER_MIN_X && this.xVelocity <= 0) {
            this.setXVelocity(0);
            this.x = Settings.PLAYER_MIN_X;
        }
        if (this.x >= CameraController.camera.viewportWidth - Settings.PLAYER_MAX_X && this.xVelocity > 0) {
            this.setXVelocity(0);
            this.x = CameraController.camera.viewportWidth - Settings.PLAYER_MAX_X;
        }
        if (this.y <= Settings.PLAYER_MIN_HEIGHT && this.yVelocity <= 0) {
            this.setYVelocity(0);
            this.y = Settings.PLAYER_MIN_HEIGHT;
        }
        if (this.y() >= Settings.PLAYER_MAX_HEIGHT && this.yVelocity > 0) {
            this.setYVelocity(0);
            this.y = Settings.PLAYER_MAX_HEIGHT;
        }
        super.move(delta);
    }

    @Override
    public void update(float delta) {

        // process bullet cooldown
        if (this.timer == 0 && this.bulletCooldown > 0)
            this.bulletCooldown--;

        // process shield
        if (this.shield)
            this.shieldTimer += delta;
        if (this.shieldTimer > 5f)
            this.shield = false;

        // process height increase
        if (this.heightIncrease && this.heightTimer <= 15f) {
            if (Settings.PLAYER_MAX_HEIGHT <= Settings.PLAYER_BASE_MAX_HEIGHT + Settings.PLAYER_HEIGHT_INCREASE)
                Settings.PLAYER_MAX_HEIGHT += 3;
            this.heightTimer += delta;
        }
        if (this.heightTimer > 15f && this.heightIncrease) {
            if (Settings.PLAYER_MAX_HEIGHT > Settings.PLAYER_BASE_MAX_HEIGHT)
                Settings.PLAYER_MAX_HEIGHT -= 1;
            else
                this.heightIncrease = false;
        }

        // keep player in bounds
        if (this.y > Settings.PLAYER_MAX_HEIGHT)
            this.y -= 1;

        super.update(delta);
    }
}
