package com.patrick.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.patrick.game.controller.CameraController;
import com.patrick.game.controller.MusicController;
import com.patrick.game.screen.TitleScreen;
import com.patrick.game.util.AudioUtils;
import com.patrick.game.util.Resources;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class AsciiSpaceGame extends Game {
    /**
     * TODO: Make more weapons and powerups (speed, shield)
     * <p>
     * TODO: Add transitions
     * <p>
     * TODO: Add music and sounds
     * <p>
     * TODO: Add bounding box around player
     * <p>
     * TODO: Add current weapons and shields etc to hud. Weapons should be cyclable.
     * <p>
     * TODO: COOL S BOSS
     * <p>
     * TODO: Make boss fire from random bounding boxes rather than the 'center'
     * <p>
     * TODO: Make more bosses
     * <p>
     * TODO: NEW GLITCH where enemies can go offscreen, now they ping back and forth in the lower 10% of left and right of screen sometimes
     */
    SpriteBatch batch;
    ShapeRenderer shape;
    BitmapFont font;
    BitmapFont redFont;

    @Override
    public void create() {
        batch = new SpriteBatch();
        shape = new ShapeRenderer();
        shape.setAutoShapeType(true);
        font = new BitmapFont(Gdx.files.internal("fonts/font.fnt"));
        redFont = new BitmapFont(Gdx.files.internal("fonts/font.fnt"));
        redFont.setColor(Color.RED);
        font.setColor(new Color(0f, 0f, 1f, 1f));
        CameraController.resetCamera();
        setScreen(new TitleScreen(this, font, redFont, batch, shape, "press enter to begin"));
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.graphics.setVSync(true);
//        Gdx.graphics.setWindowedMode(1000, 1400);
        Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
        Gdx.graphics.setTitle(String.format("ASCII SPACE GAME", Gdx.graphics.getFramesPerSecond()));
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
