package com.patrick.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.patrick.game.entity.Entity;
import com.patrick.game.screen.GameScreen;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AsciiSpaceGame extends Game {
	SpriteBatch batch;
	ShapeRenderer shape;
	BitmapFont font;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		shape = new ShapeRenderer();
		font = new BitmapFont(Gdx.files.internal("fonts/font.fnt"));
		setScreen(new GameScreen(font, batch));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.graphics.setVSync(true);
        Gdx.graphics.setWindowedMode(1000, 1400);
//		Gdx.graphics.setFullscreenMode();
		Gdx.graphics.setTitle(String.format("ASCII SPACE GAME", Gdx.graphics.getFramesPerSecond()));
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
