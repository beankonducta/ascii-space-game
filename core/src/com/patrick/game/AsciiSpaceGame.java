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
     * TODO: Add and sounds
     * <p>
     * TODO: Add bounding box around player
     * <p>
     * TODO: NEW GLITCH where enemies can go offscreen, now they ping back and forth in the lower 10% of left and right of screen sometimes
     * <p>
     * TODO: Make max boss size, at which point we cycle back to more quantity of small bosses.
     * <p>
     * TODO: Slow down wave progression (waves get unweildy pretty quick)
     * <p>
     * TODO: Glitch if boss dies at same time as player (Game keeps progressing)
     */
    SpriteBatch batch;
    ShapeRenderer shape;
    BitmapFont font;
    BitmapFont secondaryFont;
    BitmapFont thirdFont;

    @Override
    public void create() {
        batch = new SpriteBatch();
        shape = new ShapeRenderer();
        shape.setAutoShapeType(true);
        font = new BitmapFont(Gdx.files.internal("fonts/font.fnt"));
        secondaryFont = new BitmapFont(Gdx.files.internal("fonts/second_font.fnt"));
        thirdFont = new BitmapFont(Gdx.files.internal("fonts/second_font.fnt"));
        secondaryFont.setColor(Color.WHITE);
        font.setColor(new Color(0f, 0f, 1f, 1f));
        CameraController.resetCamera();
        setScreen(new TitleScreen(this, font, secondaryFont, thirdFont, batch, shape, "press enter to begin"));
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        Gdx.graphics.setVSync(true);
        Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
        Gdx.graphics.setTitle(String.format("ASCII SPACE GAME", Gdx.graphics.getFramesPerSecond()));
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
