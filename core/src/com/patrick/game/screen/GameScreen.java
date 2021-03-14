package com.patrick.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.patrick.game.controller.CameraController;
import com.patrick.game.entity.Player;
import com.patrick.game.level.Level;
import com.patrick.game.util.ColorShifter;
import com.patrick.game.util.Resources;
import com.patrick.game.util.Settings;

public class GameScreen implements Screen {

    private BitmapFont font;
    private BitmapFont redFont;
    private Batch batch;
    private ShapeRenderer shape;
    private Game game;

    private Level level;
    private Player player;

    private int difficulty;

    public GameScreen(Game game, BitmapFont font, BitmapFont redFont, Batch batch, ShapeRenderer shape) {
        this.difficulty = 300;
        this.font = font;
        this.redFont = redFont;
        this.batch = batch;
        this.shape = shape;
        this.game = game;
        this.player = new Player(CameraController.camera.viewportWidth / 2, 40, Settings.PLAYER_SPEED, Settings.PLAYER_DECEL, 'P');
        this.level = new Level(this.difficulty, this.player);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        delta = java.lang.Math.min(1 / 30f, Gdx.graphics.getDeltaTime());
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        this.shape.begin(ShapeRenderer.ShapeType.Line);
////        this.shape.setColor(ColorShifter.shiftColor(this.font, delta));
//        this.shape.setColor(ColorShifter.shiftColor(this.font, delta));
//        this.shape.rect(0, 0, CameraController.camera.viewportWidth / 8, Math.abs(Resources.RAW_TEST_MUSIC[0][(int)(Resources.TEST_MUSIC.getPosition() * 44100)] / 10));
//        this.shape.rect(CameraController.camera.viewportWidth * .875f, 0, CameraController.camera.viewportWidth / 8, Math.abs(Resources.RAW_TEST_MUSIC[0][(int)(Resources.TEST_MUSIC.getPosition() * 44100)] / 10));
//        this.shape.setColor(Color.BLACK);
//        this.shape.rect(CameraController.camera.viewportWidth / 8, 0, CameraController.camera.viewportWidth * .75f, CameraController.camera.viewportHeight);
//        this.shape.end();
        Gdx.gl.glViewport(Gdx.graphics.getWidth() / 8, 0, (int)(Gdx.graphics.getWidth() * .75f), Gdx.graphics.getHeight());
        this.font.setColor(ColorShifter.colorFromMusic(Resources.RAW_TEST_MUSIC[0][(int)(Resources.TEST_MUSIC.getPosition() * 44100)]));
        this.nextLevel();
        this.playerDeath();
        this.batch.begin();
        this.batch.setProjectionMatrix(CameraController.camera.combined);
        this.drawHud();
        this.level.process(delta, this.font, this.redFont, this.batch);
        this.batch.end();
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
        this.redFont.draw(this.batch, "" + this.player.getPoints(), 24, CameraController.camera.viewportHeight - 48);
        for (int i = 1; i < this.player.getLives(); i++) {
            this.redFont.draw(this.batch, "L", 24 * i, CameraController.camera.viewportHeight - 24);
        }

        int y = 0;
        for (int i = 0; i < Settings.BULLET_COOLDOWN - this.player.getBulletCooldown(); i++) {
            if (i == 32) y = 1;
            this.redFont.draw(this.batch, "-", CameraController.camera.viewportWidth - 24 - ((y == 0 ? i : i - 32) * 8), CameraController.camera.viewportHeight - 24 + (y * 16));
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

    private void playerDeath() {
        if(this.player.getLives() <= 0)
            this.game.setScreen(new TitleScreen(this.game, this.font, this.redFont, this.batch, this.shape, "you died, press enter to try again. your score was "+this.player.getPoints()));
    }

    private void nextLevel() {
        if (this.level.isFinished()) {
            this.difficulty = this.difficulty * 2;
            this.level = new Level(this.difficulty, this.player);
        }
    }
}
