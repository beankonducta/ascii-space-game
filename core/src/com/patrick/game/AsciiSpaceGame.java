package com.patrick.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.patrick.game.controller.CameraController;
import com.patrick.game.entity.Entity;
import com.patrick.game.screen.GameScreen;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AsciiSpaceGame extends Game {
	/**
	 *
	 * TODO: Glitch where enemies can go offscreen
	 *
	 * TODO: Make more weapons and powerups (speed, shield)
	 *
	 * TODO: Add transitions
	 *
	 * TODO: Add music and sounds
	 *
	 * TODO: Add bounding box around player
	 *
	 * TODO: Add current weapons and shields etc to hud. Weapons should be cyclable.
	 *
	 * TODO: COOL S BOSS
	 *
	 * TODO: Fix box bounding.
	 *
	 * TODO: Make boss fire from random bounding boxes rather than the 'center'
	 *
	 * TODO: Make more bosses
	 *
	 */
	SpriteBatch batch;
	ShapeRenderer shape;
	BitmapFont font;
	BitmapFont redFont;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		shape = new ShapeRenderer();
		shape.setAutoShapeType(true);
		font = new BitmapFont(Gdx.files.internal("fonts/font.fnt"));
		redFont = new BitmapFont(Gdx.files.internal("fonts/font.fnt"));
		redFont.setColor(Color.RED);
		CameraController.resetCamera();
		setScreen(new GameScreen(font, redFont, batch, shape));
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
