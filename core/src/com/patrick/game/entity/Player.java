package com.patrick.game.entity;

import com.badlogic.gdx.math.Vector2;

public class Player extends Entity {

    public Player(Vector2 position, float speed, float decel, char character) {
        super(position, speed, decel, character);
    }

    @Override
    public void move(float width) {
        super.move(width);

        // loops entity around screen
        if (this.position.x > width) this.position.x = 0;
        else if (this.position.x < 0) this.position.x = width;
    }
}
