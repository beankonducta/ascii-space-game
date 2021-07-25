package com.patrick.game.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class Resources {

    // I know I could loop over these and fill the array but I want to keep them final

//    public static final Music[] MUSIC = new Music[] {
//            Gdx.audio.newMusic(Gdx.files.internal("music/01.wav")),
//            Gdx.audio.newMusic(Gdx.files.internal("music/02.wav")),
//            Gdx.audio.newMusic(Gdx.files.internal("music/03.wav")),
//            Gdx.audio.newMusic(Gdx.files.internal("music/04.wav")),
//            Gdx.audio.newMusic(Gdx.files.internal("music/05.wav")),
//            Gdx.audio.newMusic(Gdx.files.internal("music/06.wav")),
//            Gdx.audio.newMusic(Gdx.files.internal("music/07.wav")),
//            Gdx.audio.newMusic(Gdx.files.internal("music/08.wav")),
//            Gdx.audio.newMusic(Gdx.files.internal("music/09.wav")),
//            Gdx.audio.newMusic(Gdx.files.internal("music/10.wav")),
//            Gdx.audio.newMusic(Gdx.files.internal("music/11.wav")),
//            Gdx.audio.newMusic(Gdx.files.internal("music/12.wav")),
//            Gdx.audio.newMusic(Gdx.files.internal("music/13.wav")),
//            Gdx.audio.newMusic(Gdx.files.internal("music/14.wav")),
//            Gdx.audio.newMusic(Gdx.files.internal("music/15.wav")),
//    };
//
//    public static final int [][][] RAW_MUSIC = {
//            AudioUtils.rawWavData(Gdx.files.getLocalStoragePath()+"/core/assets/music/01.wav"),
//            AudioUtils.rawWavData(Gdx.files.getLocalStoragePath()+"/core/assets/music/02.wav"),
//            AudioUtils.rawWavData(Gdx.files.getLocalStoragePath()+"/core/assets/music/03.wav"),
//            AudioUtils.rawWavData(Gdx.files.getLocalStoragePath()+"/core/assets/music/04.wav"),
//            AudioUtils.rawWavData(Gdx.files.getLocalStoragePath()+"/core/assets/music/05.wav"),
//            AudioUtils.rawWavData(Gdx.files.getLocalStoragePath()+"/core/assets/music/06.wav"),
//            AudioUtils.rawWavData(Gdx.files.getLocalStoragePath()+"/core/assets/music/07.wav"),
//            AudioUtils.rawWavData(Gdx.files.getLocalStoragePath()+"/core/assets/music/08.wav"),
//            AudioUtils.rawWavData(Gdx.files.getLocalStoragePath()+"/core/assets/music/09.wav"),
//            AudioUtils.rawWavData(Gdx.files.getLocalStoragePath()+"/core/assets/music/10.wav"),
//            AudioUtils.rawWavData(Gdx.files.getLocalStoragePath()+"/core/assets/music/11.wav"),
//            AudioUtils.rawWavData(Gdx.files.getLocalStoragePath()+"/core/assets/music/12.wav"),
//            AudioUtils.rawWavData(Gdx.files.getLocalStoragePath()+"/core/assets/music/13.wav"),
//            AudioUtils.rawWavData(Gdx.files.getLocalStoragePath()+"/core/assets/music/14.wav"),
//            AudioUtils.rawWavData(Gdx.files.getLocalStoragePath()+"/core/assets/music/15.wav")
//    };

    public static final Music[] MUSIC = new Music[]{
            Gdx.audio.newMusic(Gdx.files.internal("./ascii_space_assets/music/01.wav")),
            Gdx.audio.newMusic(Gdx.files.internal("./ascii_space_assets/music/02.wav")),
            Gdx.audio.newMusic(Gdx.files.internal("./ascii_space_assets/music/03.wav")),
            Gdx.audio.newMusic(Gdx.files.internal("./ascii_space_assets/music/04.wav")),
            Gdx.audio.newMusic(Gdx.files.internal("./ascii_space_assets/music/05.wav")),
            Gdx.audio.newMusic(Gdx.files.internal("./ascii_space_assets/music/06.wav")),
            Gdx.audio.newMusic(Gdx.files.internal("./ascii_space_assets/music/07.wav")),
            Gdx.audio.newMusic(Gdx.files.internal("./ascii_space_assets/music/08.wav")),
            Gdx.audio.newMusic(Gdx.files.internal("./ascii_space_assets/music/09.wav")),
            Gdx.audio.newMusic(Gdx.files.internal("./ascii_space_assets/music/10.wav")),
            Gdx.audio.newMusic(Gdx.files.internal("./ascii_space_assets/music/11.wav")),
            Gdx.audio.newMusic(Gdx.files.internal("./ascii_space_assets/music/12.wav")),
            Gdx.audio.newMusic(Gdx.files.internal("./ascii_space_assets/music/13.wav")),
            Gdx.audio.newMusic(Gdx.files.internal("./ascii_space_assets/music/14.wav")),
            Gdx.audio.newMusic(Gdx.files.internal("./ascii_space_assets/music/15.wav")),
    };

    public static final int[][][] RAW_MUSIC = {
            AudioUtils.rawWavData(Gdx.files.getLocalStoragePath() + "./ascii_space_assets/music/01.wav"),
            AudioUtils.rawWavData(Gdx.files.getLocalStoragePath() + "./ascii_space_assets/music/02.wav"),
            AudioUtils.rawWavData(Gdx.files.getLocalStoragePath() + "./ascii_space_assets/music/03.wav"),
            AudioUtils.rawWavData(Gdx.files.getLocalStoragePath() + "./ascii_space_assets/music/04.wav"),
            AudioUtils.rawWavData(Gdx.files.getLocalStoragePath() + "./ascii_space_assets/music/05.wav"),
            AudioUtils.rawWavData(Gdx.files.getLocalStoragePath() + "./ascii_space_assets/music/06.wav"),
            AudioUtils.rawWavData(Gdx.files.getLocalStoragePath() + "./ascii_space_assets/music/07.wav"),
            AudioUtils.rawWavData(Gdx.files.getLocalStoragePath() + "./ascii_space_assets/music/08.wav"),
            AudioUtils.rawWavData(Gdx.files.getLocalStoragePath() + "./ascii_space_assets/music/09.wav"),
            AudioUtils.rawWavData(Gdx.files.getLocalStoragePath() + "./ascii_space_assets/music/10.wav"),
            AudioUtils.rawWavData(Gdx.files.getLocalStoragePath() + "./ascii_space_assets/music/11.wav"),
            AudioUtils.rawWavData(Gdx.files.getLocalStoragePath() + "./ascii_space_assets/music/12.wav"),
            AudioUtils.rawWavData(Gdx.files.getLocalStoragePath() + "./ascii_space_assets/music/13.wav"),
            AudioUtils.rawWavData(Gdx.files.getLocalStoragePath() + "./ascii_space_assets/music/14.wav"),
            AudioUtils.rawWavData(Gdx.files.getLocalStoragePath() + "./ascii_space_assets/music/15.wav")
    };

    private static final Sound[] SOUNDS = {
            Gdx.audio.newSound(Gdx.files.internal("sound/SHOT.wav")),
            Gdx.audio.newSound(Gdx.files.internal("sound/SHOTGUN.wav")),
            Gdx.audio.newSound(Gdx.files.internal("sound/MACHINE_GUN.wav")),
            Gdx.audio.newSound(Gdx.files.internal("sound/BOSS_HURT.wav")),
            Gdx.audio.newSound(Gdx.files.internal("sound/BOSS_DEATH.wav")),
            Gdx.audio.newSound(Gdx.files.internal("sound/ENEMY_DEATH.wav")),
            Gdx.audio.newSound(Gdx.files.internal("sound/PLAYER_DEATH.wav")),
            Gdx.audio.newSound(Gdx.files.internal("sound/PLAYER_FINAL_DEATH.wav")),
            Gdx.audio.newSound(Gdx.files.internal("sound/POWERUP.wav")),
            Gdx.audio.newSound(Gdx.files.internal("sound/COIN.wav"))
    };

    public static final Sound SOUND(String str) {
        switch(str) {
            case "shot":
                return SOUNDS[0];
            case "shotgun":
                return SOUNDS[1];
            case "machinegun":
                return SOUNDS[2];
            case "bosshurt":
                return SOUNDS[3];
            case "bossdeath":
                return SOUNDS[4];
            case "enemydeath":
                return SOUNDS[5];
            case "playerdeath":
                return SOUNDS[6];
            case "playerfinaldeath":
                return SOUNDS[7];
            case "powerup":
                return SOUNDS[8];
            case "coin":
                return SOUNDS[9];
        }
        return null;
    }
}