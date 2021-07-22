package com.patrick.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.patrick.game.controller.CameraController;
import com.patrick.game.controller.MusicController;
import com.patrick.game.entity.Player;
import com.patrick.game.level.Level;
import com.patrick.game.util.ColorShifter;
import com.patrick.game.util.OneShotTimer;
import com.patrick.game.util.Resources;
import com.patrick.game.util.Settings;

public class GameScreen implements Screen {

    private BitmapFont font;
    private BitmapFont secondaryFont;
    private BitmapFont thirdFont;
    private Batch batch;
    private ShapeRenderer shape;
    private Game game;

    private Level level;
    private Player player;

    private OneShotTimer deathTimer;

    private int difficulty;

    public GameScreen(Game game, BitmapFont font, BitmapFont secondaryFont, BitmapFont thirdFont, Batch batch, ShapeRenderer shape) {
        this.difficulty = Settings.INITIAL_DIFFICULTY;
        this.font = font;
        this.secondaryFont = secondaryFont;
        this.thirdFont = thirdFont;
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
        delta = this.player.getLives() <= 0 ? .001f : java.lang.Math.min(1 / 30f, Gdx.graphics.getDeltaTime());
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glViewport(Gdx.graphics.getWidth() / 8, 0, (int) (Gdx.graphics.getWidth() * .75f), Gdx.graphics.getHeight());
        this.font.setColor(ColorShifter.colorFromMusic(Resources.RAW_MUSIC[MusicController.ID][0][(int) (Resources.MUSIC[MusicController.ID].getPosition() * 44100)]));
        this.thirdFont.setColor(ColorShifter.colorFromMusic(Resources.RAW_MUSIC[MusicController.ID][0][(int) (Resources.MUSIC[MusicController.ID].getPosition() * 44100)]));
        this.playerDeath(delta);
        this.nextLevel();
        this.batch.begin();
        this.batch.setProjectionMatrix(CameraController.camera.combined);
        this.drawHud();
        this.level.process(delta, this.font, this.secondaryFont, this.thirdFont, this.batch);
        this.batch.end();
        this.shape.begin(ShapeRenderer.ShapeType.Line);
        this.shape.setColor(ColorShifter.shiftColor(this.font, delta));
        if (Settings.PLAYER_MAX_HEIGHT > Settings.PLAYER_BASE_MAX_HEIGHT || this.player.x() <= Settings.PLAYER_MIN_X ||
                this.player.x() >= CameraController.camera.viewportWidth - Settings.PLAYER_MAX_X ||
                this.player.y() <= Settings.PLAYER_MIN_HEIGHT ||
                this.player.y() >= Settings.PLAYER_MAX_HEIGHT)
            this.shape.rect(Settings.PLAYER_MIN_X, Settings.PLAYER_MIN_HEIGHT- 20, CameraController.camera.viewportWidth - Settings.PLAYER_MAX_X + 6, Settings.PLAYER_MAX_HEIGHT - 20);
        this.shape.end();
    }

    private void drawHud() {
        this.secondaryFont.draw(this.batch, "" + this.player.getPoints(), 24, CameraController.camera.viewportHeight - 48);
        for (int i = 1; i < this.player.getLives(); i++) {
            this.secondaryFont.draw(this.batch, "[L]", 24 * i, CameraController.camera.viewportHeight - 24);
        }

        int y = 0;
        for (int i = 0; i < Settings.BULLET_COOLDOWN - this.player.getBulletCooldown(); i++) {
            if (i == 32) y = 1;
            this.secondaryFont.draw(this.batch, "-", CameraController.camera.viewportWidth - 24 - ((y == 0 ? i : i - 32) * 8), CameraController.camera.viewportHeight - 24 + (y * 16));
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

    private void playerDeath(float delta) {
        if (this.player.getLives() <= 0) {
            if (this.deathTimer == null)
                this.deathTimer = new OneShotTimer(.5f); // .5f standard
            this.deathTimer.update(delta);
            if (this.deathTimer.isFinished())
                this.game.setScreen(new TitleScreen(this.game, this.font, this.secondaryFont, this.thirdFont, this.batch, this.shape, "you died, press enter to try again. your score was " + this.player.getPoints(), this.player.getPoints()));
        }
    }

    private void nextLevel() {
        if (this.level.isFinished()) {
            this.difficulty = (int) (this.difficulty * 1.55f);
            this.level = new Level(this.difficulty, this.player);
        }
    }
}
