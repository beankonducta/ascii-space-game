package com.patrick.game.controller;

import com.badlogic.gdx.audio.Music;
import com.patrick.game.util.Resources;
import com.patrick.game.util.Settings;

public class MusicController {


    private static Music music;
    private static int musicId;

    /**
     * Sets current music, adds oncomplete listener to play the next song afterwards.
     *
     * @param id
     */
    public static void setMusic(int id) {
        if(id > Resources.MUSIC.length - 1) id = 0;
        musicId = id;
        stop();
        music = Resources.MUSIC[id];
        music.setVolume(Settings.MUSIC_VOLUME);
        play();
        music.setOnCompletionListener(new Music.OnCompletionListener() {
            @Override
            public void onCompletion(Music music) {
                setMusic(musicId ++);
            }
        });
    }

    /**
     * Stops the music.
     */
    public static void stop() {
        if (music == null) return;
        music.stop();
    }

    /**
     * Plays the music.
     */
    public static void play() {
        if (music == null) return;
        music.play();
    }

    /**
     * Pauses the music.
     */
    public static void pause() {
        if (music == null) return;
        music.pause();
    }

    /**
     * Disposes the music.
     */
    public static void dispose() {
        if (music == null) return;
        music.dispose();
    }
}
