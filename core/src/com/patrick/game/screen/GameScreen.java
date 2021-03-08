package com.patrick.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.patrick.game.controller.CameraController;
import com.patrick.game.entity.Boss;
import com.patrick.game.entity.Bullet;
import com.patrick.game.entity.Enemy;
import com.patrick.game.entity.Player;
import com.patrick.game.level.Level;
import com.patrick.game.util.Settings;

public class GameScreen implements Screen {

    private BitmapFont font;
    private BitmapFont redFont;
    private Batch batch;
    private ShapeRenderer shape;

    private Level level;
    private Player player;

    private int colorMod;

    private int difficulty;

    public GameScreen(BitmapFont font, BitmapFont redFont, Batch batch, ShapeRenderer shape) {
        this.difficulty = 300;
        this.font = font;
        this.redFont = redFont;
        this.batch = batch;
        this.shape = shape;
        this.player = new Player(CameraController.camera.viewportWidth / 2, 40, Settings.PLAYER_SPEED, Settings.PLAYER_DECEL, 'P');
        this.level = new Level(this.difficulty, this.player);
        this.font.setColor(new Color(0f, 0f, 1f, 1f));
        this.colorMod = 1;
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        delta = java.lang.Math.min(1 / 30f, Gdx.graphics.getDeltaTime());
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.shiftColor(delta);
        this.nextLevel();
        batch.begin();
        batch.setProjectionMatrix(CameraController.camera.combined);
        this.drawHud();
        level.process(delta, font, redFont, batch);
        batch.end();

//        collision debugging:

//        if (Settings.DEBUG_COLLISION) {
//            shape.begin();
//            shape.setColor(Color.WHITE);
//            shape.setProjectionMatrix(CameraController.camera.combined);
//            for (Enemy enemy : level.waves.get(0).getEnemies()) {
//                if (enemy instanceof Boss) {
//                    Boss boss = (Boss) enemy;
//                    for (int i = 0; i < boss.getColliders().length; i++) {
//                        for (int j = 0; j < boss.getColliders()[i].length; j++) {
//                            if (boss.getColliders()[i][j] != null)
//                                shape.rect(boss.getColliders()[i][j].x, boss.getColliders()[i][j].y, boss.getColliders()[i][j].width, boss.getColliders()[i][j].height);
//                        }
//                    }
//                } else {
//                    shape.rect(enemy.getCollider().x, enemy.getCollider().y, enemy.getCollider().width, enemy.getCollider().height);
//                }
//            }
//            for (Bullet bullet : level.bullets) {
//                if (bullet.getCollider() != null)
//                    shape.rect(bullet.getCollider().x, bullet.getCollider().y, bullet.getCollider().width, bullet.getCollider().height);
//            }
//            shape.end();
//        }
    }

    private void drawHud() {
        redFont.draw(batch, "" + this.player.getPoints(), 24, CameraController.camera.viewportHeight - 48);
        for (int i = 1; i < this.player.getLives(); i++) {
            redFont.draw(batch, "L", 24 * i, CameraController.camera.viewportHeight - 24);
        }

        int y = 0;
        for (int i = 0; i < Settings.BULLET_COOLDOWN - this.player.getBulletCooldown(); i++) {
            if (i == 32) y = 1;
            redFont.draw(batch, "-", CameraController.camera.viewportWidth - 24 - ((y == 0 ? i : i - 32) * 8), CameraController.camera.viewportHeight - 24 + (y * 16));
        }
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
        if (r > 1 || r < 0)
            this.colorMod = this.colorMod * -1;
        this.font.setColor(r, this.font.getColor().g, this.font.getColor().b, 1f);
    }

    private void nextLevel() {
        if (this.level.isFinished()) {
            this.difficulty = this.difficulty * 2;
            this.level = new Level(this.difficulty, this.player);
        }
    }
}
