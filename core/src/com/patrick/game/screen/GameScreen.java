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
import com.patrick.game.level.Wave;
import com.patrick.game.util.Math;

public class GameScreen implements Screen {

    private BitmapFont font;
    private Batch batch;

    private Player player;

    private Wave wave;
    private Bullet bullet;

    public GameScreen(BitmapFont font, Batch batch) {
        this.font = font;
        this.batch = batch;
        this.player = new Player(new Vector2(200, 200), 6f, .25f, 'P');
        this.bullet = new Bullet(new Vector2(200, 200), 2f, .25f, '/');
        bullet.setYVelocity(bullet.getSpeed());
        this.wave = new Wave(20);
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
        for(Enemy enemy : this.wave.getEnemies()) {
            enemy.update(delta);
            enemy.render(font, batch);
            enemy.move(CameraController.camera.viewportWidth);
            MovementController.processEnemyMovement(enemy, player, CameraController.camera.viewportWidth);
        }
        bullet.update(delta);
        bullet.render(font, batch);
        bullet.move(CameraController.camera.viewportWidth);
        player.update(delta);
        player.render(font, batch);
        player.move(CameraController.camera.viewportWidth);
        MovementController.processPlayerMovement(player);
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
