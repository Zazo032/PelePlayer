package com.czazo.peleplayer;

import android.media.MediaPlayer;

public class MusicPlayer extends MediaPlayer {
    private MusicPlayer instance;
    private Song currentSong;

    private MusicPlayer() {
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
