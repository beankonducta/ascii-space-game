package com.patrick.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.patrick.game.controller.CameraController;
import com.patrick.game.controller.MusicController;
import com.patrick.game.util.Ascii;
import com.patrick.game.util.ColorShifter;
import com.patrick.game.util.Math;
import com.patrick.game.util.Resources;

import java.util.Arrays;
import java.util.Comparator;

public class TitleScreen implements Screen {

    private BitmapFont font;
    private BitmapFont secondaryFont;
    private BitmapFont thirdFont;
    private Batch batch;
    private ShapeRenderer shape;
    private Game game;

    private String titleString;
    private int score;
    private String[] scores;
    private char[] name;
    private int pointer;
    private char[][] titleChars;

    public TitleScreen(Game game, BitmapFont font, BitmapFont secondaryFont, BitmapFont thirdFont, Batch batch, ShapeRenderer shape, String titleString, int score) {
        this.game = game;
        this.font = font;
        this.score = score;
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
        MusicController.setMusic(Math.randomBetween(0, Resources.MUSIC.length - 1));
        this.readScores();
        if (this.score > Integer.parseInt(this.scores[this.scores.length -1].substring(10)) || (this.scores.length < 15 && this.score > -1)) {
            this.name = new char[3];
            this.name[0] = 'a';
            this.name[1] = 'a';
            this.name[2] = 'a';
            this.pointer = 0;
            this.writeScore();
            this.readScores();
        }
    }

    private void movePointer(int dir) {
        if (this.pointer + dir > 0 && this.pointer + dir < 2) this.pointer += dir;
    }

    private void changeLetter(int dir) {
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        int slot = this.name[this.pointer] + dir;
        if (slot < 0) slot = alphabet.length - 1;
        if (slot > alphabet.length - 1) slot = 0;
        this.name[this.pointer] = alphabet[slot];
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        delta = java.lang.Math.min(1 / 30f, Gdx.graphics.getDeltaTime());
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.thirdFont.setColor(ColorShifter.colorFromMusic(Resources.RAW_MUSIC[MusicController.ID][0][(int) (Resources.MUSIC[MusicController.ID].getPosition() * 44100)]));
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            this.game.setScreen(new GameScreen(this.game, this.font, this.secondaryFont, this.thirdFont, this.batch, this.shape));
        }
        this.batch.begin();
        this.batch.setProjectionMatrix(CameraController.camera.combined);
        this.thirdFont.draw(this.batch, this.titleString, CameraController.camera.viewportWidth / 2 - (this.titleString.length() * 3), CameraController.camera.viewportHeight / 2);
        for (int i = 0; i < this.titleChars.length; i++)
            for (int j = 0; j < this.titleChars[i].length; j++)
                this.thirdFont.draw(this.batch, "" + this.titleChars[i][j], 100 + (j * 6), CameraController.camera.viewportHeight - 50 - (i * 12));

        // high scores!
        this.thirdFont.draw(this.batch, "Hi - Scorez:", 15, CameraController.camera.viewportHeight - 200 + 20);
        for (int i = 0; i < this.scores.length - 1; i++)
            this.thirdFont.draw(this.batch, this.scores[i], 15, CameraController.camera.viewportHeight - 200 - (i * 12));

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

    private void writeScore() {
        FileHandle file = Gdx.files.local("myfile.txt");
        file.writeString(" fff" + this.score, true);
    }

    private void readScores() {
        FileHandle file = Gdx.files.local("myfile.txt");
        String[] scoresArray = file.readString().split(" ");
        Arrays.sort(scoresArray, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (Math.isNumeric(o1.substring(3)) && Math.isNumeric(o2.substring(3)))
                    return Integer.parseInt(o2.substring(3)) - Integer.parseInt((o1.substring(3)));
                return o2.substring(3).compareTo(o1.substring(3));
            }
        });
        int max = scoresArray.length;
        if (max > 15) max = 15;
        this.scores = new String[max];
        for (int i = 0; i < max; i++)
            this.scores[i] = scoresArray[i].substring(0, 3) + "  ---  " + scoresArray[i].substring(3);
    }
}
