package com.czazo.peleplayer;

public class SongFactory {
    public static Song getSong(String filePath) {
        if(filePath.equals("")) return new Song();
        return new Song(filePath);
    }
}
