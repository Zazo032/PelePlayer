package com.czazo.peleplayer;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.util.Log;

import java.io.IOException;
import java.util.List;

public class MusicPlayer extends MediaPlayer {
    private MusicPlayer instance;
    private int posCurrentSong;
    private List<Song> songs;

    MusicPlayer() {
        super();
        this.setAudioAttributes( new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build()
        );
    }

    public MusicPlayer getInstance() {
        if (instance == null) {
            instance = new MusicPlayer();
        }
        return instance;
    }

    public void play(int pos){
        this.reset();
        Song songToPlay = songs.get(pos);
        if(songToPlay == null) return;
        try {
            this.setDataSource(songToPlay.getFilePath());
            this.prepare();
            this.posCurrentSong = pos;
            this.start();
        } catch (IOException e) {
            Log.e("MusicPlayer", "No se ha encontrado el archivo en la ruta de datos");
            play(pos++);
        }
    }

    public void playNext() {
        play(posCurrentSong++);
    }

    public void playPrevious() {
        play(posCurrentSong--);
    }

    public int getPosCurrentSong() {
        return posCurrentSong;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
