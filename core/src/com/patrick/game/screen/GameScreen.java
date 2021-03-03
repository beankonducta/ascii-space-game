package com.patrick.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.patrick.game.controller.CameraController;
import com.patrick.game.controller.MovementController;
import com.patrick.game.entity.Bullet;
import com.patrick.game.entity.Enemy;
import com.patrick.game.entity.Player;
import com.patrick.game.level.Level;
import com.patrick.game.level.Wave;
import com.patrick.game.util.Math;

public class GameScreen implements Screen {

    private BitmapFont font;
    private Batch batch;

    private Level level;

    public GameScreen(BitmapFont font, Batch batch) {
        this.font = font;
        this.batch = batch;
        this.level = new Level(10);
    }

    @Override
    public void show() {
        CameraController.resetCamera();
    }

    @Override
    public void render(float delta) {
        delta = java.lang.Math.min(1 / 30f, Gdx.graphics.getDeltaTime());
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
}
