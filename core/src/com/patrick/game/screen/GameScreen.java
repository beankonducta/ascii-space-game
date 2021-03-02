package com.patrick.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.patrick.game.controller.CameraController;
import com.patrick.game.controller.MovementController;
import com.patrick.game.entity.Enemy;
import com.patrick.game.entity.Entity;
import com.patrick.game.entity.Player;
import com.patrick.game.util.Math;
import com.patrick.game.util.Settings;
import org.graalvm.compiler.lir.aarch64.AArch64Move;

public class GameScreen implements Screen {

    private BitmapFont font;
    private Batch batch;

    private Player player;
    private Enemy enemy;

    public GameScreen(BitmapFont font, Batch batch) {
        this.font = font;
        this.batch = batch;
        this.player = new Player(new Vector2(200, 200), 6f, .25f, 'P');
        this.resetEnemy();
    }

    private void resetEnemy() {
        this.enemy = new Enemy(new Vector2(200, 500), Math.FLOAT_RANDOM_BETWEEN(3f, 6f), .2f, Math.RANDOM_BETWEEN(1, 3), 'E');
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
        enemy.update(delta);
        enemy.render(font, batch);
        enemy.move(CameraController.camera.viewportWidth);
        player.update(delta);
        player.render(font, batch);
        player.move(CameraController.camera.viewportWidth);
        MovementController.processPlayerMovement(player);
        MovementController.processEnemyMovement(enemy, player, CameraController.camera.viewportWidth);
        if(enemy.getPosition().y < 0)
            this.resetEnemy();
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
