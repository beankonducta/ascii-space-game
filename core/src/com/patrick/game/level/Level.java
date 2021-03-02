package com.patrick.game.level;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.patrick.game.entity.Player;

import java.util.List;

public interface Level {

    Player getPlayer();
    List<Wave> getWaves();
    void update(float delta);
    void render(BitmapFont font, Batch batch);

}
