package com.czazo.peleplayer;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.util.Log;

import java.io.IOException;
import java.util.List;

public class MusicPlayer extends MediaPlayer {
    private static MusicPlayer instance;
    private int posCurrentSong;
    private List<Song> songs;
    private int pausedTime;
    private Command command;

    private MusicPlayer() {
        super();
        this.setAudioAttributes(new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build());
        instance = this;
    }

    public static MusicPlayer getInstance() {
        if (instance == null) {
            instance = new MusicPlayer();
        }
        return instance;
    }

    public static void setInstance(MusicPlayer ins) {
        instance = ins;
    }

    public static boolean isInstanciated() {
        return instance != null;
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
            play(++pos);
        }
    }

    public void playNext() {
        if (posCurrentSong+1 == songs.size()) {
            posCurrentSong = 0;
            play(posCurrentSong);
        } else {
            play(++posCurrentSong);
        }
        pausedTime = 0;
    }

    public void playPrevious() {
        if (getCurrentPosition() > 2500) {
            play(posCurrentSong);
        } else {
            if (posCurrentSong == 0) {
                play(songs.size()-1);
            } else {
                play(--posCurrentSong);
            }
        }
        pausedTime = 0;
    }

    public Song getCurrentSong(){
        return songs.get(posCurrentSong);
    }

    public int getPosCurrentSong() {
        return posCurrentSong;
    }

    public int getPausedTime() {
        int aux = pausedTime;
        this.pausedTime = 0;
        return aux;
    }

    public void setPausedTime(int p){
        this.pausedTime = p;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public void setCommand(Command c){
        this.command = c;
    }

    public Command getCommand() {
        return command;
    }

    public void buttonPressed(){
        command.execute();
    }
}
