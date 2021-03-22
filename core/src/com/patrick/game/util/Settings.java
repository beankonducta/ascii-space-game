package com.patrick.game.util;

public class Settings {

    public static final int PLAYER_MAX_HEIGHT = 100;
    public static final int PLAYER_MIN_HEIGHT = 36;
    public static final int PLAYER_MAX_X = 24;
    public static final int PLAYER_MIN_X = 10;
    public static final int PLAYER_WIDTH = 16;
    public static final int PLAYER_HEIGHT = 16;

    public static final float PLAYER_SPEED = 250f;
    public static final float PLAYER_DECEL = 400f;

    public static final int PLAYER_FLIPPABLE_ENTITY_X_OFFSET = 2;

    public static final int BULLET_COOLDOWN = 64;

    public static final int BULLET_SIZE = 8;
    public static final float BULLET_SPEED = 800f;
    public static final int SPREAD_FIRE_COUNT = 6;

    public static final float EXPLOSION_SPEED = 120;
    public static final int EXPLOSION_SIZE = 10;
    public static final int BOSS_EXPLOSION_SIZE = 120;
    public static final int EXPLOSION_TTL = 1;

    public static final float RESOURCE_SPEED = 120;

    public static final int ENEMY_WIDTH = 16;
    public static final int ENEMY_HEIGHT = 16;

    public static final int MIN_ENEMY_SMARTS = 1;
    public static final int MAX_ENEMY_SMARTS = 5;

    public static final float MIN_ENEMY_SPEED = 140f;
    public static final float MAX_ENEMY_SPEED = 300f;
    public static final float ENEMY_DECEL = 220f;

    public static final int WAVE_COUNT = 5;

    public static final boolean DEBUG = false;

    public static final float MUSIC_VOLUME = .3f;
    public static final float SOUND_VOLUME = .2f;

    public static final int INITIAL_DIFFICULTY = 300;

    public static final int MAX_ENEMIES = 100;

    public static final int MAX_PARTICLES = 1000;
}
