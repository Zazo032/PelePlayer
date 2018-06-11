package com.czazo.peleplayer;

public class SongFactory {

    public static Song getSong(String filePath) {
        if(filePath == "") return new Song();
        return new Song(filePath);
    }
}
