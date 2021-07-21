package com.patrick.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.patrick.game.controller.CameraController;
import com.patrick.game.screen.TitleScreen;

public class AsciiSpaceGame extends Game {
    /**
     * TODO: Add and sounds
     * <p>
     * TODO: NEW GLITCH where enemies can go offscreen, now they ping back and forth in the lower 10% of left and right of screen sometimes
     */
    private SpriteBatch batch;
    private ShapeRenderer shape;
    private BitmapFont font;
    private BitmapFont secondaryFont;
    private BitmapFont thirdFont;

    @Override
    public void create() {
        this.batch = new SpriteBatch();
        this.shape = new ShapeRenderer();
        this.shape.setAutoShapeType(true);
        this.font = new BitmapFont(Gdx.files.internal("fonts/font.fnt"));
        this.secondaryFont = new BitmapFont(Gdx.files.internal("fonts/second_font.fnt"));
        this.thirdFont = new BitmapFont(Gdx.files.internal("fonts/second_font.fnt"));
        this.secondaryFont.setColor(Color.WHITE);
        this.font.setColor(new Color(0f, 0f, 1f, 1f));
        CameraController.resetCamera();
        this.setScreen(new TitleScreen(this, this.font, this.secondaryFont, this.thirdFont, this.batch, this.shape, "press enter to begin", -1));
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
