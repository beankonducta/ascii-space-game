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
import com.patrick.game.util.Ascii;
import com.patrick.game.util.ColorShifter;
import com.patrick.game.util.Resources;

public class TitleScreen implements Screen {

    private BitmapFont font;
    private BitmapFont secondaryFont;
    private BitmapFont thirdFont;
    private Batch batch;
    private ShapeRenderer shape;
    private Game game;

    private String titleString;
    private char[][] titleChars;

    public TitleScreen(Game game, BitmapFont font, BitmapFont secondaryFont, BitmapFont thirdFont, Batch batch, ShapeRenderer shape, String titleString) {
        this.game = game;
        this.font = font;
        this.secondaryFont = secondaryFont;
        this.thirdFont = thirdFont;
        this.batch = batch;
        this.shape = shape;
        this.titleString = titleString;
        this.thirdFont.setColor(new Color(0, 0, 1, 1));
        this.titleChars = Ascii.stringTo2dCharArray(" ______     ______     ______     __     __                            " +
                "/\\  __ \\   /\\  ___\\   /\\  ___\\   /\\ \\   /\\ \\                           " +
                "\\ \\  __ \\  \\ \\___  \\  \\ \\ \\____  \\ \\ \\  \\ \\ \\                          " +
                " \\ \\_\\ \\_\\  \\/\\_____\\  \\ \\_____\\  \\ \\_\\  \\ \\_\\                         " +
                "  \\/_/\\/_/   \\/_____/   \\/_____/   \\/_/   \\/_/                         " +
                "                                                                       " +
                "                   ______     ______   ______     ______     ______    " +
                "                  /\\  ___\\   /\\  == \\ /\\  __ \\   /\\  ___\\   /\\  ___\\   " +
                "                  \\ \\___  \\  \\ \\  _-/ \\ \\  __ \\  \\ \\ \\____  \\ \\  __\\   " +
                "                   \\/\\_____\\  \\ \\_\\    \\ \\_\\ \\_\\  \\ \\_____\\  \\ \\_____\\ " +
                "                    \\/_____/   \\/_/     \\/_/\\/_/   \\/_____/   \\/_____/ " +
                "                                                                       ", 71);
        Resources.TEST_MUSIC.play();
        Resources.TEST_MUSIC.setLooping(true);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        delta = java.lang.Math.min(1 / 30f, Gdx.graphics.getDeltaTime());
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.thirdFont.setColor(ColorShifter.colorFromMusic(Resources.RAW_TEST_MUSIC[0][(int) (Resources.TEST_MUSIC.getPosition() * 44100)]));
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            this.game.setScreen(new GameScreen(this.game, this.font, this.secondaryFont, this.thirdFont, this.batch, this.shape));
        }
        this.batch.begin();
        this.batch.setProjectionMatrix(CameraController.camera.combined);
        this.thirdFont.draw(this.batch, this.titleString, CameraController.camera.viewportWidth / 2 - (this.titleString.length() * 3), CameraController.camera.viewportHeight / 2);
        for (int i = 0; i < this.titleChars.length; i++)
            for (int j = 0; j < this.titleChars[i].length; j++)
                this.thirdFont.draw(this.batch, "" + this.titleChars[i][j], 100 + (j * 6), CameraController.camera.viewportHeight - 50 - (i * 12));
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
