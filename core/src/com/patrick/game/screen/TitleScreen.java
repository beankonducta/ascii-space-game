package com.patrick.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.patrick.game.controller.CameraController;
import com.patrick.game.util.ColorShifter;

public class TitleScreen implements Screen {

    private BitmapFont font;
    private BitmapFont redFont;
    private Batch batch;
    private ShapeRenderer shape;
    private Game game;

    private String titleString;

    public TitleScreen(Game game, BitmapFont font, BitmapFont redFont, Batch batch, ShapeRenderer shape, String titleString) {
        this.game = game;
        this.font = font;
        this.redFont = redFont;
        this.batch = batch;
        this.shape = shape;
        this.titleString = titleString;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        delta = java.lang.Math.min(1 / 30f, Gdx.graphics.getDeltaTime());
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        ColorShifter.shiftColor(this.font, delta);
        this.font.setColor(ColorShifter.shiftColor(this.font, delta));
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            this.game.setScreen(new GameScreen(this.game, this.font, this.redFont, this.batch, this.shape));
        }

        this.batch.begin();
        this.batch.setProjectionMatrix(CameraController.camera.combined);
        this.font.draw(this.batch, this.titleString, CameraController.camera.viewportWidth / 2 - (this.titleString.length() * 3), CameraController.camera.viewportHeight / 2);
        this.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
