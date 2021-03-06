package com.patrick.game.entity;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.patrick.game.util.Settings;

public class Player extends Entity {

    private int gunLevel;
    private int lives;
    private int bulletCooldown;
    private boolean shield;

    public int getBulletCooldown() {
        return this.bulletCooldown;
    }

    public void addBulletCooldown() {
        if(this.bulletCooldown < Settings.BULLET_COOLDOWN) this.bulletCooldown ++;
    }

    public boolean getShield() {
        return this.shield;
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

    public void processResource(Resource resource) {
        if(resource.getType() == Resource.ResourceType.GUN) this.gunLevel += resource.getLevel();
        else if(resource.getType() == Resource.ResourceType.LIFE) this.lives += resource.getLevel();
    }

    public Player(Vector2 position, float speed, float decel, char character) {
        super(position, speed, decel, character);
        this.collider = new Rectangle(position.x, position.y, Settings.PLAYER_WIDTH, Settings.PLAYER_HEIGHT);
        this.gunLevel = 0;
        this.lives = 3;
        this.actionTime = .15f;
    }

    @Override
    public void update(float delta) {
        if(this.timer == 0 && this.bulletCooldown > 0) {
            this.bulletCooldown --;
        }
        super.update(delta);
    }
}
