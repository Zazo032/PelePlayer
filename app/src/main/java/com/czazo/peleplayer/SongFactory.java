package com.czazo.peleplayer;

public class SongFactory {

    public static Song getSong(String filePath) {
        return new Song(filePath);
    }
}
