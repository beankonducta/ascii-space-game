package com.patrick.game.entity;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.patrick.game.util.Settings;

public class Player extends Entity {

    private int gunLevel;

    public int getGunLevel() {
        return this.gunLevel;
    }

    public void setGunLevel(int gunLevel) {
        this.gunLevel = gunLevel;
    }

    public Player(Vector2 position, float speed, float decel, char character) {
        super(position, speed, decel, character);
        this.collider = new Rectangle(position.x, position.y, Settings.PLAYER_WIDTH, Settings.PLAYER_HEIGHT);
        this.gunLevel = 1 ;
    }
}
