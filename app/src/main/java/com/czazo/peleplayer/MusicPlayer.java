package com.czazo.peleplayer;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.File;

public class MusicPlayer extends MediaPlayer {
    private MusicPlayer instance;
    private Song currentSong;

    protected MusicPlayer() {
        super();
    }

    public MusicPlayer getInstance() {
        if (instance == null) {
            instance = new MusicPlayer();
        }
        return instance;
    }

    public Song getCurrentSong() {
        return currentSong;
    }

    public void setCurrentSong(Song currentSong) {
        this.currentSong = currentSong;
    }


}
