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

    public static final Music[] MUSIC = new Music[] {
            Gdx.audio.newMusic(Gdx.files.internal("./music/01.wav")),
            Gdx.audio.newMusic(Gdx.files.internal("./music/02.wav")),
            Gdx.audio.newMusic(Gdx.files.internal("./music/03.wav")),
            Gdx.audio.newMusic(Gdx.files.internal("./music/04.wav")),
            Gdx.audio.newMusic(Gdx.files.internal("./music/05.wav")),
            Gdx.audio.newMusic(Gdx.files.internal("./music/06.wav")),
            Gdx.audio.newMusic(Gdx.files.internal("./music/07.wav")),
            Gdx.audio.newMusic(Gdx.files.internal("./music/08.wav")),
            Gdx.audio.newMusic(Gdx.files.internal("./music/09.wav")),
            Gdx.audio.newMusic(Gdx.files.internal("./music/10.wav")),
            Gdx.audio.newMusic(Gdx.files.internal("./music/11.wav")),
            Gdx.audio.newMusic(Gdx.files.internal("./music/12.wav")),
            Gdx.audio.newMusic(Gdx.files.internal("./music/13.wav")),
            Gdx.audio.newMusic(Gdx.files.internal("./music/14.wav")),
            Gdx.audio.newMusic(Gdx.files.internal("./music/15.wav")),
    };

    public static final int [][][] RAW_MUSIC = {
            AudioUtils.rawWavData(Gdx.files.getLocalStoragePath()+"./music/01.wav"),
            AudioUtils.rawWavData(Gdx.files.getLocalStoragePath()+"./music/02.wav"),
            AudioUtils.rawWavData(Gdx.files.getLocalStoragePath()+"./music/03.wav"),
            AudioUtils.rawWavData(Gdx.files.getLocalStoragePath()+"./music/04.wav"),
            AudioUtils.rawWavData(Gdx.files.getLocalStoragePath()+"./music/05.wav"),
            AudioUtils.rawWavData(Gdx.files.getLocalStoragePath()+"./music/06.wav"),
            AudioUtils.rawWavData(Gdx.files.getLocalStoragePath()+"./music/07.wav"),
            AudioUtils.rawWavData(Gdx.files.getLocalStoragePath()+"./music/08.wav"),
            AudioUtils.rawWavData(Gdx.files.getLocalStoragePath()+"./music/09.wav"),
            AudioUtils.rawWavData(Gdx.files.getLocalStoragePath()+"./music/10.wav"),
            AudioUtils.rawWavData(Gdx.files.getLocalStoragePath()+"./music/11.wav"),
            AudioUtils.rawWavData(Gdx.files.getLocalStoragePath()+"./music/12.wav"),
            AudioUtils.rawWavData(Gdx.files.getLocalStoragePath()+"./music/13.wav"),
            AudioUtils.rawWavData(Gdx.files.getLocalStoragePath()+"./music/14.wav"),
            AudioUtils.rawWavData(Gdx.files.getLocalStoragePath()+"./music/15.wav")
    };

    public static final Sound SOUND(String str) {
        return null;
    }
}