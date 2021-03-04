package com.patrick.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.patrick.game.controller.CameraController;
import com.patrick.game.level.Level;

public class GameScreen implements Screen {

    private BitmapFont font;
    private Batch batch;

    private Level level;

    private int colorMod;

    private int difficulty;

    public GameScreen(BitmapFont font, Batch batch) {
        this.difficulty = 1000;
        this.font = font;
        this.batch = batch;
        this.level = new Level(this.difficulty);
        this.font.setColor(new Color(0f, 0f, 1f, 1f));
        this.colorMod = 1;
    }

    @Override
    public void show() {
        CameraController.resetCamera();
    }

    @Override
    public void render(float delta) {
        delta = java.lang.Math.min(1 / 30f, Gdx.graphics.getDeltaTime());
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.shiftColor(delta);
        this.nextLevel();
        batch.begin();
        batch.setProjectionMatrix(CameraController.camera.combined);
        level.process(delta, font, batch);
        batch.end();
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

    private void shiftColor(float delta) {
        float r = this.font.getColor().r + (delta / 10 * this.colorMod);
        if(r > 1 || r < 0)
            this.colorMod = this.colorMod * -1;
        this.font.setColor(r, this.font.getColor().g, this.font.getColor().b, 1f);
    }

    private void nextLevel() {
        if (this.level.isFinished())
            this.level = new Level(Math.round(this.difficulty * 2));
    }
}
