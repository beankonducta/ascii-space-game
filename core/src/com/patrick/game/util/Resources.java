package com.patrick.game.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class Resources {

    public static final Music[] MUSIC = new Music[] {};

    public static final Music TEST_MUSIC = Gdx.audio.newMusic(Gdx.files.internal("music/funk.wav"));
    public static final int [][] RAW_TEST_MUSIC = AudioUtils.rawWavData(Gdx.files.getLocalStoragePath()+"/core/assets/music/funk.wav");

    public static final Sound SOUND(String str) {
        return null;
    }
}