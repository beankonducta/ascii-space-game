package com.patrick.game.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.patrick.game.util.Settings;

public class Resource extends Entity {

    public enum ResourceType {
        LIFE, GUN;
    }

    private ResourceType type;
    private int level;

    public ResourceType getType() {
        return this.type;
    }

    public int getLevel() {
        return this.level;
    }

    public Resource(float x, float y, float speed, float decel, ResourceType type, int level) {
        super(x, y, speed, decel, ' ');
        this.type = type;
        this.level = level;
        this.character = fetchChar(type, level);
        this.setYVelocity(-speed);
        this.collider = new Rectangle(x, y, Settings.ENEMY_WIDTH, Settings.ENEMY_HEIGHT);
    }

    private char fetchChar(ResourceType type, int level) {
        if(type == ResourceType.GUN) return 'V';
        else if(type == ResourceType.LIFE) return 'L';
        return ' ';
    }

    @Override
    public void update(float delta) {
    }

    @Override
    public void render(BitmapFont font, Batch batch) {
        // draw the character
//        Color color = font.getColor();
//        font.setColor(Color.RED);
        font.draw(batch, "" + this.character, this.x, this.y);
//        font.setColor(color);
    }
}