package com.czazo.peleplayer;

public class SongFactory {

    public Song getSong(String filePath) {
        return new Song(filePath);
    }
}
